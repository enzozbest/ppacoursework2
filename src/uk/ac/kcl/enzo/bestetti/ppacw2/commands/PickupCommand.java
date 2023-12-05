package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to add an item from a room to the player's inventory.
 * In addition, it also prints out the updated inventory once an item has been added.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class PickupCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public PickupCommand(Command command, Player player) {
        this.command = command;
        this.player = player;
    }

    /**
     * Items from a room can be picked up by the player.
     * The item is removed from the current room's set of available items and added to the player's inventory.
     **/
    @Override
    public void execute() {
        if (player.getCurrentRoom().hasBoss()) {
            if (player.getCurrentRoom().getRoomBoss().isBossAlive()) {
                return;
            }
        }
        if (!command.hasSecondWord()) {
            System.out.println("Please specify what you would like to pickup!");
            return;
        }
        if (!player.getCurrentRoom().getItems().containsKey(command.getSecondWord().toLowerCase())) {
            System.out.println("This room does not contain the item you wish to pickup!");
            return;
        }
        Item item = player.getCurrentRoom().getItems().get(command.getSecondWord().toLowerCase());
        if ((player.getCurrentWeight() + item.getWeight()) > player.getMaxWeight()) {
            System.out.println("You cannot pick up this item because you are not strong enough");
            return;
        }
        player.getInventory().put(item.getName().toLowerCase(), item);
        player.setCurrentWeight(player.getCurrentWeight() + item.getWeight());
        player.getCurrentRoom().getItems().remove(command.getSecondWord().toLowerCase());
        System.out.println("You have picked up " + item.getName());
        player.printInventory();
    }
}
