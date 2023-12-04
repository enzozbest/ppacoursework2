package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;

import java.util.HashMap;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to perform the use of an in-game item on the player or the game map.
 * This class assigns what we mean by "use" to each item and when called performs said use.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class UseCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    HashMap<Integer, Room> rooms;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public UseCommand(Command command, Player player, HashMap<Integer, Room> rooms) {
        this.command = command;
        this.player = player;
        this.rooms = rooms;
    }

    /**
     * Use an item.
     * If the item's durability reaches 0, the item is removed from the player's inventory.
     **/
    @Override
    public void execute() {
        if (!command.hasSecondWord()) {
            System.out.println("Please specify which item you would like to use!");
            return;
        }
        if (!player.getInventory().containsKey(command.getSecondWord().toLowerCase())) {
            System.out.println("You do not have this item in your inventory!");
            return;
        }
        String itemName = command.getSecondWord().toLowerCase();
        Item item = player.getInventory().get(itemName);
        if (!item.isUseable()) {
            System.out.println("You cannot use this item!");
            return;
        }
        if (!item.isKey()) {
            useItem(item);
            if (item.getDurability() <= 0) {
                player.getInventory().remove(item.getName().toLowerCase());
                player.setCurrentWeight(player.getCurrentWeight() - item.getWeight());
                System.out.println(item.getName() + " has broken!");
            }
            return;
        }
        useKey(item);
        System.out.println("You have used " + item.getName() + " to open a door");

    }

    /**
     * Executes the behaviours of a given item when it is used.
     *
     * @param item
     **/
    private void useItem(Item item) {
        item.setDurability(item.getDurability() - 1);

        switch (item.getId()) {
            case 0 -> {
                player.updateMaxWeight();
                System.out.println("The power of the Whtie Soul has granted you extra strength!");
            }
            case 3 ->
                    System.out.println("You are trying to eat the Kraken Tail. Please note, this is a weapon, not food!");
            case 5 -> {
                player.wear(item);
                System.out.println("You have equipped the Parasol. Perhaps now the sun will not burn you...");
            }
            case 6 ->
                    System.out.println("You tried to eat sand. Now you are covered in it. You also have a dry mouth:P");
            case 7 ->
                    System.out.println("You are trying to eat the Death Scythe. Please note, this is a weapon, not food!");
            case 10 -> {
                player.wear(item);
                System.out.println("You have equipped the Lava Ring. Perhaps now the Lava won't instantly kill you");
            }
            case 13 -> System.out.println("You have drunk some water! You no longer have a dry mouth");
            case 300 -> {
                player.updateMaxWeight();
                System.out.println("The power of the Demon Soul has granted you extra strength!");
            }
            case 200 -> {
                player.updateMaxWeight();
                System.out.println("The power of the Guardian Soul has granted you extra power.");
            }
        }

    }

    /**
     * Unlocks a door by using an item with the "isKey" flag set to true.
     * This item does not break with use (i.e. it has infinite durability)
     *
     * @param item
     **/
    private void useKey(Item item) {
        switch (item.getId()) {
            case 2 -> rooms.get(3).unlockRoom(); //Opens Drenched Oasis
            case 8 -> rooms.get(8).unlockRoom();//Opens Mineral Mines
        }
    }

}
