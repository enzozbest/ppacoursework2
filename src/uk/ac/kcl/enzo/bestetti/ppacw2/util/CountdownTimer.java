package uk.ac.kcl.enzo.bestetti.ppacw2.util;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents a timer which is called when a boss issues an attack.
 * This is done by running this class on a separate dedicated Thread.
 * This class implements Runnable in order for it to be able to run on a different thread to the main game.
 * Once called, the thread will sleep for the specified amount of time.
 * If this thread wakes up, the player did not issue a counter-attack and hence they lost. The game will end in loss.
 * If the player issues a counter-attack, this thread will be interrupted and the game is allowed to carry on.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class CountdownTimer implements Runnable {

    //ATTRIBUTES
    private int countDown;
    //ATTRIBUTES

    /**
     * Initialise class fields.
     **/
    public CountdownTimer(int duration) {
        this.countDown = duration;
    }

    /**
     * Put thread to sleep for the specified amount of time.
     * If the thread wakes up, the player has lost and the game exits.
     * Otherwise, the thread is interrupted.
     **/
    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(countDown * 1000L);
            endGameLoss();
        } catch (InterruptedException e) {
            //Thread has been interrupted. Nothing else to do here.
        }
    }

    /**
     * Print the end game (loss) text.
     * Exits the game - the player has lost.
     **/
    public void endGameLoss() {
        System.out.println("You Died! All your progress has been lost");
        System.out.println("Better luck next time.");
        System.out.println("Thank you for playing White Souls! We hope to see you again soon");
        System.exit(0);
    }
}
