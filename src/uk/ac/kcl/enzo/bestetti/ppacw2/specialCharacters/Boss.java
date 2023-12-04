package uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters;


import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;

/**
 * This class is part of the "White Souls" application.
 * All enemies are instances of this class (i.e. bosses).
 * <p>
 * Different bosses can have different names, IDs, and health.
 * <p>
 * Bosses "attack" the player by issuing time challenges. The player has a limited amount of time to attack back.
 * If there is a counter-attack by the player, the countdown is interrupted and the boss loses some health.
 * Otherwise, the player loses and the game is finished. The player has lost and all progress is also lost.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 */
public class Boss {

    //ATTRIBUTES
    private String bossName;
    private double bossHealth;
    private boolean isAlive;
    private Item drop;
    //ATTRIBUTES

    /**
     * Constructs a boss by initialising attributes.
     *
     * @param name   of the boss
     * @param health of the boss
     * @param drop   of the boss
     **/
    public Boss(String name, double health, Item drop) {
        this.bossName = name;
        this.bossHealth = health;
        this.drop = drop;
        this.isAlive = true;
    }

    //GETTERS

    /**
     * @return true if the boss is alive. False otherwise.
     **/
    public boolean isBossAlive() {
        return isAlive;
    }

    /**
     * @return the boss name.
     **/
    public String getBossName() {
        return this.bossName;
    }

    /**
     * @return the current boss health.
     **/
    public double getBossHealth() {
        return bossHealth;
    }

    /**
     * @return the boss item drop.
     **/
    public Item getDrop() {
        return drop;
    }
    //GETTERS

    /**
     * Set boss health to a specified value.
     *
     * @param bossHealth indicating the new value for the boss health.
     **/
    public void setBossHealth(double bossHealth) {
        this.bossHealth = bossHealth;
    }

    /**
     * Set the boss "alive" state to the specified value.
     *
     * @param alive indicating whether the boss is alive (true) or dead (false).
     **/
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
}
