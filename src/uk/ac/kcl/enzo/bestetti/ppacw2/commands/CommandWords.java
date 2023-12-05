package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

/**
 * This enum is part of the "White Souls" application.
 * "White Souls" is a very simple, text based adventure game.
 * <p>
 * This enum holds information about all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 */

public enum CommandWords {
    GO("go"), QUIT("quit"), HELP("help"), ATTACK("attack"), PLACE("place"),
    TALK("talk"), PICKUP("pickup"), DROP("drop"), USE("use"), BACK("back"),
    INVENTORY("inventory"), REPLENISH("replenish"), MAP("map"), FEED("feed");
    private String commandString;

    CommandWords(String commandWord) {
        this.commandString = commandWord;
    }

    public String getCommandString() {
        return commandString;
    }

    /**
     * Print all valid commands to System.out.
     */
    public static void showAll() {
        for (CommandWords word : CommandWords.values()) {
            System.out.print(word.commandString + "  ");
        }
        System.out.println();
    }
}
