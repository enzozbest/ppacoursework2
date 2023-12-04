package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "White Souls" application.
 * "White Souls" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits. For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Michael Kölling and David J. Barnes, Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/

public class Room {

    //ATTRIBUTES
    private String description;
    private String name;
    private int id;
    private boolean isLocked;
    private Boss roomBoss;
    private boolean hasBoss;
    private boolean hasNPC;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Item> items;
    private HashMap<String, Character> people;
    //ATTRIBUTES

    /**
     * Create a room by setting its name, id, description, whether it is locked, and the items it contains.
     * Initially, it has no exits.
     **/
    public Room(String name, int id, String description, boolean isLocked, String itemString) {
        hasBoss = false;
        this.name = name;
        this.id = id;
        this.description = description;
        this.isLocked = isLocked;
        items = new HashMap<>();
        people = new HashMap<>();
        exits = new HashMap<>();
        addItems(itemString);
    }

    /**
     * Adds a given set of items to a room.
     * Items are passed as a string. The regex "," separates the different attributes of a single item.
     * The regex " : " separates the different items.
     * This method allows multiple items to be passes at construction as a single parameter. There is no upper or lower bound
     * on the number of items a room can have.
     *
     * @param stringItems in the specified format.
     **/
    public void addItems(String stringItems) {
        if (stringItems.isEmpty()) {
            return;
        }
        String[] fullItems = stringItems.split(" : ");
        for (String fullItem : fullItems) {
            String[] singleItem = fullItem.split(",");
            Item finalItem = new Item(singleItem[0], Integer.parseInt(singleItem[1]), Double.parseDouble(singleItem[2]), Integer.parseInt(singleItem[3]), Boolean.parseBoolean(singleItem[4]), Boolean.parseBoolean(singleItem[5]));
            items.put(finalItem.getName().toLowerCase(), finalItem);
        }
    }

    //GETTERS

    /**
     * @return room name
     **/
    public String getName() {
        return this.name;
    }

    /**
     * @return room id
     **/
    public int getId() {
        return this.id;
    }

    /**
     * @return isLocked
     **/
    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     **/
    public String getLongDescription() {
        if (items.isEmpty()) {
            if (people.isEmpty()) {
                return description + ".\n" + getExitString() + ".\n" + "There are no items in this room" + ".\n" + "There's no one in the room";
            }
        }
        if (people.isEmpty()) {
            return description + ".\n" + getExitString() + ".\n" + getItemString() + ".\n" + "There's no one in the room";
        }
        return description + ".\n" + getExitString() + ".\n" + getItemString() + ".\n" + getCharacterString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     **/
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits.
     **/
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return a string describing the room's items, for example
     * "Items in the Room: sword key".
     *
     * @return Details of the room's items.
     **/
    private String getItemString() {
        String returnString = "Items in the Room: | ";
        Set<String> keys = items.keySet();
        for (String item : keys) {
            returnString += items.get(item).getName() + " | ";
        }
        return returnString;
    }

    /**
     * @return a String stating the name of all the characters in a room.
     **/
    private String getCharacterString() {
        String returnString = "People in the Room: | ";
        Set<String> characters = people.keySet();
        for (String character : characters) {
            returnString += people.get(character).getName() + " | ";
        }
        return returnString;
    }

    /**
     * @return items
     **/
    public HashMap<String, Item> getItems() {
        return items;
    }

    /**
     * @return room boss if the room has a boss
     * @return null otherwise
     **/
    public Boss getRoomBoss() {
        if (hasBoss) {
            return this.roomBoss;
        }
        return null;
    }

    /**
     * @return whether the room has an NPC.
     **/
    public boolean hasNPC() {
        return this.hasNPC;
    }

    /**
     * @return a list of all NPCs present in a room
     **/
    public HashMap<String, Character> getPeople() {
        return this.people;
    }

    /**
     * @return hasBoss
     **/
    public boolean hasBoss() {
        return this.hasBoss;
    }
    //GETTERS

    //SETTERS

    /**
     * Used to set the isLocked flag of a room to false
     **/
    public void unlockRoom() {
        this.isLocked = false;
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     **/
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Adds a specified Boss to a room
     *
     * @param boss
     **/
    public void setRoomBoss(Boss boss) {
        this.roomBoss = boss;
        this.hasBoss = true;
    }

    /**
     * Add a new NPC to a room
     *
     * @param character
     **/
    public void addCharacter(Character character) {
        people.put(character.getName().toLowerCase(), character);
        this.hasNPC = true;
    }

    /**
     * Remove all characters from the room
     **/
    public void removeCharacters() {
        Set<String> characterNames = people.keySet();
        ArrayList<String> charactersToRemove = new ArrayList<>();

        for (String name : characterNames) {
            Character c = people.get(name);
            if (c.isFirstEncounter()) {
                continue;
            }
            charactersToRemove.add(name);
        }
        for (String name : charactersToRemove) {
            people.remove(name);
        }
    }
    //SETTERS
}

