package uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters;

import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;

import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents a Player object and holds all the play-time data related to the user playing the game.
 * Only one instance of this class can exist per gameplay. This game does not support multiple players.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
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
     * @return current room
     **/
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * @return current weight
     **/
    public double getCurrentWeight() {
        return this.currentWeight;
    }

    /**
     * @return maximum weight
     **/
    public double getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * @return wearable
     **/
    public Item getWearable() {
        return this.wearable;
    }

    /**
     * @return inventory
     **/
    public HashMap<String, Item> getInventory() {
        return this.inventory;
    }

    /**
     * Set the player's current wearable item (unequips all other wearables)
     *
     * @param item
     **/
    public void wear(Item item) {
        this.wearable = item;
    }

    /**
     * Move the player to a specified room
     *
     * @param currentRoom
     **/
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Update the current weight to a specified value
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
