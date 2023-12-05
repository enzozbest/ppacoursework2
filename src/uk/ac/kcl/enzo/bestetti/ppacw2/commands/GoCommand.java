package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.ArrayLifoStack;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to move the player from one room to another.
 * In addition, it adds all accessed rooms to a last-in-first-out stack which is used for the "back" command.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class GoCommand implements Executable {

    //ATTRIBUTES
    private Player player;
    private Command command;
    private RoomHandler handler;

    private ArrayLifoStack<Room> accessedRooms;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public GoCommand(Command command, RoomHandler roomHandler) {
        this.handler = roomHandler;
        this.player = roomHandler.getInitialiser().getPlayer();
        this.command = command;
        this.accessedRooms = roomHandler.getAccessedRooms();
    }

    /**
     * Try to exit a room towards a direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * If the room has a boss, the boss' (extremely rudimentary) AI is activated.
     * If the player is 'overweight' they cannot exit the current room (fail-safe).
     **/
    @Override
    public void execute() {
        if (player.getCurrentRoom().hasBoss()) {
            if (player.getCurrentRoom().getRoomBoss().isBossAlive()) {
                return;
            }
        }
        if (player.getCurrentWeight() > player.getMaxWeight()) {
            System.out.println("You cannot move! You are too heavy!!!");
            return;
        }
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Specify a direction to go!");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("Bonks! This is a wall, you have just hit your head. You are now concussed. Please proceed through a viable exit.");
        } else {
            if (nextRoom.isLocked()) {
                System.out.println("This room is locked. You cannot access it.Yet.");
                return;
            }
            handler.moveCharacters(nextRoom.getId());
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            accessedRooms.push(player.getCurrentRoom());
            boolean clear = handler.roomCheck();
            if (clear) {
                accessedRooms.clear();
                accessedRooms.push(player.getCurrentRoom());
            }
        }
    }
}
