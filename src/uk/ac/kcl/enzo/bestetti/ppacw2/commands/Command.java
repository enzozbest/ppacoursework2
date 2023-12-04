package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

/**
 * This class is part of the "White Souls" application.
 * "White Souls" is a very simple, text based adventure game.
 * <p>
 * This class holds information about a command that was issued by the user.
 * A command consists of two strings: a command word and a second
 * string (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * <p>
 * The way this is used is:
 * <p>
 * Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 * <p>
 * If the command had only one word, then the second word is <null>.
 * <p>
 *
 * @author Michael Kölling, David J. Barnes, Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/

public class Command {
    private String commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     *
     * @param firstWord  The first word of the command. Null if the command
     *                   was not recognised.
     * @param secondWord The second word of the command.
     **/
    public Command(String firstWord, String secondWord) {
        if (firstWord != null) {
            this.commandWord = firstWord.toLowerCase();
        } else {
            commandWord = commandWord;
        }
        if (secondWord != null) {
            this.secondWord = secondWord.toLowerCase();
        } else {
            secondWord = secondWord;
        }

    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     **/
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     **/
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     **/
    public boolean isUnknown() {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     **/
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}

