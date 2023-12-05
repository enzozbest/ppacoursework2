package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to remove an item from the player's inventory and add it to the current room's inventory.
 * In addition, it prints out the updated inventory once an item has been removed.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class DropCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public DropCommand(Command command, Player player) {
        this.command = command;
        this.player = player;
    }

    /**
     * Player can drop items.
     * The item is removed from the player's inventory and added to the current room's set of available items.
     **/
    @Override
    public void execute() {
        if (!command.hasSecondWord()) {
            System.out.println("Please specify what you would like to drop");
            return;
        }
        if (!player.getInventory().containsKey(command.getSecondWord().toLowerCase())) {
            System.out.println("This item is already not in your inventory!");
            return;
        }
        Item item = player.getInventory().get(command.getSecondWord().toLowerCase());
        player.getCurrentRoom().getItems().put(item.getName().toLowerCase(), item);
        player.getInventory().remove(item.getName().toLowerCase());
        player.setCurrentWeight(player.getCurrentWeight() - item.getWeight());
        System.out.println("You have dropped " + item.getName());
        player.printInventory();
    }
}
