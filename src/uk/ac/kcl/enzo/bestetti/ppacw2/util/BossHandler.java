package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Boss;

import java.util.Random;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * The boss fight mechanics are implemented in this class. That is, the extremely rudimentary boss AI.
 * When a player enters a room with a boss who is alive, the fight begins. A time challenge is issued to the player (boss attack),
 * who needs to attack (player attack).
 * The player attack interrupts the time challenge and decreases the boss health by 1 point. This process repeats until the boss dies.
 * <p>
 * If the player fails to issue an attack within the specified time period, the game is lost and we end the game in player loss.
 * If the boss is defeated by the player, an item is added to the room where the boss was and the player can pick it up.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class BossHandler {

    //ATTRIBUTES
    private Boss boss;
    private RoomHandler roomHandler;
    private Random random;
    private Thread countdown;
    //ATTRIBUTES

    /**
     * Checks if the room the player has entered has a boss. If not, we stop the creation of the BossHandler object.
     * Otherwise we retrieve the boss information from the RoomHandler.
     *
     * @param roomHandler
     **/
    public BossHandler(RoomHandler roomHandler) {
        this.roomHandler = roomHandler;
        if (!roomHandler.getInitialiser().getPlayer().getCurrentRoom().hasBoss()) {
            return;
        }
        boss = roomHandler.getInitialiser().getPlayer().getCurrentRoom().getRoomBoss();
        this.random = new Random();
    }

    /**
     * Check if the current boss "alive" state is set to true or false.
     * If the boss is dead, print out the victory message and drop item.
     *
     * @return true if the boss is still alive. False otherwise.
     **/
    public boolean checkAlive() {
        if (!boss.isBossAlive()) {
            System.out.println("You have defeated " + boss.getBossName());
            System.out.println("The boss has dropped " + boss.getDrop().getName());
            System.out.println("You can now pickup this item from this room");
            roomHandler.getInitialiser().getPlayer().getCurrentRoom().getItems().put(boss.getDrop().getName().toLowerCase(), boss.getDrop());
            return false;
        }
        return true;
    }

    /**
     * Issue boss attacks while the boss is alive.
     **/
    public void bossAttack() {
        if (checkAlive()) {
            dealDamage();
        }
    }

    /**
     * Start a time challenge.
     * A random number between 0 and 3 (inclusive) is generated and this determines the amount the time the player will have
     * to counter-attack the boss.
     * If the player does so, the time-challenge is over and the boss-fight continues.
     * Otherwise, the player loses and the game ends.
     **/
    public void dealDamage() {
        int index = random.nextInt(4);

        switch (index) {
            case 0:
                countdown = new Thread(new CountdownTimer(10)); //Creates a thread that will sleep for the specified time
                countdown.start(); //Starts the thread
                System.out.println("Quick! You only have 10 seconds to attack!");
                break;
            case 1:
                countdown = new Thread(new CountdownTimer(7));
                countdown.start();
                System.out.println("Quick! You only have 7 seconds to attack!");
                break;
            case 2:
                countdown = new Thread(new CountdownTimer(5));
                countdown.start();
                System.out.println("Quick! You only have 5 seconds to attack!");
                break;
            case 3:
                countdown = new Thread(new CountdownTimer(3));
                countdown.start();
                System.out.println("Quick! You only have 3 seconds to attack!");
                break;
        }
    }

    /**
     * Register a player counter-attack to a boss attack.
     * Interrupt the count-down timer Thread.
     *
     * @throws InterruptedException which is handled by the interrupted thread.
     **/
    public void playerAttack() {
        try {
            countdown.interrupt();//Interrupts the countdown thread, i.e. player has attacked -> cancel boss attack.
        } catch (NullPointerException e) {
            System.out.println("I am not sure what you are trying to attack... Have you finally gone crazy?");
        }
        boss.setBossHealth(boss.getBossHealth() - 1);
        if (boss.getBossHealth() >= 0) {
            boss.setAlive(true);
            return;
        }
        boss.setAlive(false);
    }
}
