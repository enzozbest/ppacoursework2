package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to print out the map of the game to the player screen.
 * In addition, it prepares all the elements of the map for displaying, e.g. colouring room names.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class MapCommand implements Executable {

    //ATTRIBUTES
    private HashMap<Integer, Room> rooms;
    private Player player;
    private HashMap<Room, String> mapElements;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public MapCommand(RoomHandler handler) {
        mapElements = new HashMap<>();
        this.rooms = handler.getRooms();
        this.player = handler.getInitialiser().getPlayer();
    }

    /**
     * Print out a map of the game.
     * Accessible rooms are displayed in white.
     * Inaccessible (locked) rooms are displayed in red.
     * The room in which the player is currently is displayed in green.
     **/
    @Override
    public void execute() {
        if (!player.getInventory().containsKey("map")) {
            System.out.println("You do not have a map!");
            return;
        }
        colourNames(rooms);
        char upDownArrow = '\u2195';
        char rightLeftArrow = '\u2194';

        //FIRST LINE
        System.out.println(mapElements.get(rooms.get(10)) + " " + rightLeftArrow + " " + mapElements.get(rooms.get(11)));
        //SECOND LINE
        System.out.println("         " + upDownArrow);
        //THIRD LINE
        System.out.println(mapElements.get(rooms.get(0)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(1)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(6)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(7)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(8)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(9)));
        //LINE FOUR
        System.out.println("         " + upDownArrow + "             " + upDownArrow);
        //LINE FIVE
        System.out.println(mapElements.get(rooms.get(4)) + "  "
                + mapElements.get(rooms.get(2)));
        //LINE SIX
        System.out.println("        " + upDownArrow + "              " + upDownArrow);
        //LINE SEVEN
        System.out.println("   " + mapElements.get(rooms.get(5)) + "  "
                + mapElements.get(rooms.get(3)) + " " + rightLeftArrow + " "
                + mapElements.get(rooms.get(12)));
    }

    /**
     * Assign ANSI escape sequences to room names.
     * Display them in a special format with arrows to indicate the connections between rooms.
     *
     * @return a HashMap which maps coloured room names to their rooms objects.
     **/
    private HashMap<Room, String> colourNames(HashMap<Integer, Room> rooms) {
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        Set<Integer> keys = rooms.keySet();
        String roomName = "";

        for (Integer key : keys) {
            Room room = rooms.get(key);
            if (room.isLocked()) {
                roomName = RED + room.getName() + RESET;
            } else if (room == player.getCurrentRoom()) {
                roomName = GREEN + room.getName() + RESET;
            } else {
                roomName = RESET + room.getName() + RESET;
            }
            mapElements.put(room, roomName);
        }
        return mapElements;
    }

}
