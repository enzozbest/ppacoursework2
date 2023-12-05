package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

import java.util.HashMap;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to feed an Imperial Soul to the Lordbucket to allow for game progression.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class FeedCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    private HashMap<Integer, Room> rooms;
    private RoomHandler roomHandler;
    private static int numSoulsFed = 0;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public FeedCommand(Command command, RoomHandler roomHandler) {
        this.roomHandler = roomHandler;
        this.command = command;
        this.player = roomHandler.getInitialiser().getPlayer();
        this.rooms = roomHandler.getRooms();
    }

    /**
     * Feed one Imperial Soul item to the Lordbucket.
     * The Imperial Soul item is removed from the player's inventory and the count of the total number of souls fed updated.
     **/
    @Override
    public void execute() {
        if (!command.hasSecondWord()) {
            System.out.println("Be more specific...");
            return;
        }
        if (!player.getInventory().containsKey(command.getSecondWord().toLowerCase())) {
            System.out.println("You do not have this item!");
            return;
        }
        if (!(player.getCurrentRoom() == rooms.get(10)) || !roomHandler.isLordbucket()) {
            System.out.println("You can only feed items to the Lordbucket. It does not seem the Lordbucket is in this room, though...");
            return;
        }
        if (!(command.getSecondWord().equalsIgnoreCase("Yellow Soul") || command.getSecondWord().equalsIgnoreCase("Orange Soul") || command.getSecondWord().equalsIgnoreCase("Crystalised Soul") || command.getSecondWord().equalsIgnoreCase("Black Soul"))) {
            System.out.println("You can only feed items to the Lordbucket. It does not seem to be accepting this item, though...");
            return;
        }
        Item item = player.getInventory().get(command.getSecondWord().toLowerCase());
        player.getInventory().remove(item.getName().toLowerCase());
        player.setCurrentWeight(player.getCurrentWeight() - item.getWeight());
        numSoulsFed++;
        System.out.println("You have successfully fed " + item.getName() + " to the Lordbucket");
        if (numSoulsFed >= 4) {
            roomHandler.openSource();
        }
    }
}
