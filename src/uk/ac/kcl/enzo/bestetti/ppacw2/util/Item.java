package uk.ac.kcl.enzo.bestetti.ppacw2.util;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents interactive things in the game - items.
 * Players can interact with items by picking them up, dropping them, or using them. Different items have different uses.
 * For example, a sword can be used to deal damage, but a key can be used to open a door. This is implemented by the different
 * flags defined in this class.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class Item {

    //ATTRIBUTES
    private String name;
    private int id;
    private double weight;
    private int durability;
    private boolean isUseable;
    private boolean isKey;
    //ATTRIBUTES

    /**
     * Builds an Item object and initialises fields.
     *
     * @param name
     * @param id
     * @param weight
     * @param durability
     * @param isKey
     * @param isUsable
     **/
    public Item(String name, int id, double weight, int durability, boolean isUsable, boolean isKey) {
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.durability = durability;
        this.isUseable = isUsable;
        this.isKey = isKey;
    }

    //GETTERS

    /**
     * @return item name.
     **/
    public String getName() {
        return name;
    }

    /**
     * @return id.
     **/
    public int getId() {
        return id;
    }

    /**
     * @return weight.
     **/
    public double getWeight() {
        return weight;
    }

    /**
     * @return durability.
     **/
    public int getDurability() {
        return durability;
    }

    /**
     * @return consumable.
     **/
    public boolean isUseable() {
        return isUseable;
    }

    /**
     * @return isKey.
     **/
    public boolean isKey() {
        return isKey;
    }
    //GETTERS

    //SETTERS

    /**
     * Used to update the durability of an item to a specified value.
     *
     * @param durability
     **/
    public void setDurability(int durability) {
        this.durability = durability;
    }
    //SETTERS
}