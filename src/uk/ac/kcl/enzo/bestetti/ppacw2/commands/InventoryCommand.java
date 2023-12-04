package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to print out the player's current inventory.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class InventoryCommand implements Executable {

    //ATTRIBUTES
    Player player;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public InventoryCommand(Player player) {
        this.player = player;
    }

    /**
     * Print out the player's current inventory
     **/
    @Override
    public void execute() {
        player.printInventory();
    }
}
