package uk.ac.kcl.enzo.bestetti.ppacw2.base;

/**
 * This class allows the "White Souls" application to be executed and played.
 * The method main() is called here to allow for the game to be played by a user.
 * An instance of the game is created and the play() method called to initiate the gameplay.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class GameMain {

    /**
     * Calling this method will initiate the gameplay.
     **/
    public static void main(String[] args) {
        WhiteSouls whiteSouls = new WhiteSouls();
        whiteSouls.play();
    }

}