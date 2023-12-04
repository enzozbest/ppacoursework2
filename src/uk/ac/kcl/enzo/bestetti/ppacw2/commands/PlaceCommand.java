package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to allow the player to place the Lordbucket in Waterlink Altar
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class PlaceCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    private RoomHandler roomHandler;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public PlaceCommand(Command command, RoomHandler handler) {
        this.roomHandler = handler;
        this.command = command;
        this.player = handler.getInitialiser().getPlayer();
    }


    /**
     * Player must place the Lordbucket in Waterlink Altar.
     * This has to be done in order for the game to proceed beyond the initial phases.
     * The command triggers the "place Lordbucket" routine that unlocks the late-game rooms.
     * The command can only be performed if the player has the Lordbucket in their inventory and the player is in Waterlink Altar.
     **/
    @Override
    public void execute() {
        if (!command.hasSecondWord()) {
            System.out.println("Please specify which item you would like to place!");
            return;
        }
        if (!command.getSecondWord().equalsIgnoreCase("lordbucket")) {
            System.out.println("You cannot place this item");
            return;
        }
        if (!(player.getCurrentRoom().getId() == 10)) {
            System.out.println("You cannot place the Lordbucket in this room. Please proceed to Waterlink Altar in order to place the Lordbucket");
            return;
        }
        if (!player.getInventory().containsKey("lordbucket")) {
            if (!roomHandler.isLordbucket()) {
                System.out.println("You have not yet acquired the Lordbucket!");
                return;
            }
            System.out.println("You have already placed the Lordbucket");
            return;
        }
        roomHandler.placeVessel();
        player.setCurrentWeight(player.getCurrentWeight() - player.getInventory().get("lordbucket").getWeight());
        player.getInventory().remove("lordbucket");
        System.out.println("You have placed the Lordbucket at Waterlink Altar");
        System.out.println("The power of the Lordbucket has granted you access to some previously locked rooms.");
        System.out.println("You must now find the Imperial Souls to feed the Lordbucket. Good luck!");
        System.out.println("Succeed the great Lord Aluns, replenish the First Droplet, and break the Dryman curse!");
    }
}
