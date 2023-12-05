package uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters;

import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;

import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents a Player object and holds all the play-time data related to the user playing the game.
 * Only one instance of this class should exist per gameplay. This game does not support multiple players.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public final class Player {

    //ATTRIBUTES
    private Room currentRoom;
    private double currentWeight = 0;
    private double maxWeight = 5;
    private Item wearable;
    private HashMap<String, Item> inventory = new HashMap<>();
    //ATTRIBUTES

    /**
     * @return current room the player is in.
     **/
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * @return current weight the player is carrying.
     **/
    public double getCurrentWeight() {
        return this.currentWeight;
    }

    /**
     * @return maximum weight the player can carry.
     **/
    public double getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * @return wearable currently equipped by the player.
     **/
    public Item getWearable() {
        return this.wearable;
    }

    /**
     * @return current player inventory.
     **/
    public HashMap<String, Item> getInventory() {
        return this.inventory;
    }

    /**
     * Set the player's current wearable item.
     *
     * @param item to equip
     **/
    public void wear(Item item) {
        this.wearable = item;
    }

    /**
     * Move the player to a specified room.
     *
     * @param currentRoom meaning the room the player is moving into.
     **/
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Update the current weight to a specified value.
     *
     * @param currentWeight
     **/
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * This method changes the player maximum weight, representing a level up.
     **/
    public void updateMaxWeight() {
        this.maxWeight += 0.5;
    }

    /**
     * Print out the items the player has in their inventory.
     **/
    public void printInventory() {
        System.out.println("You now have the following items on your inventory: ");
        System.out.println();
        Set<String> inventoryItems = inventory.keySet();
        String printout = "| ";
        for (String inventoryItem : inventoryItems) {
            printout += inventory.get(inventoryItem).getName() + " | ";
        }
        System.out.println(printout);
        System.out.println();
        System.out.println("Your current weight is: " + currentWeight + "/" + maxWeight);
    }
}
