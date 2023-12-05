package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.base.WhiteSouls;

import java.util.Scanner;

/**
 * This class is part of the "White Souls" application.
 * "White Souls" is a very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as a command.
 * Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two string command. It returns the command
 * as an object of class Command.
 * <p>
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Michael Kölling and David J. Barnes, Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class Parser {

    //ATTRIBUTES
    private Scanner reader; // source of command input
    private WhiteSouls game;
    //ATTRIBUTES

    /**
     * Create a parser to read from the terminal window.
     **/
    public Parser() {
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     **/
    public Command getCommand() {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
            }
            while (tokenizer.hasNext()) {
                word2 = word2 + " " + tokenizer.next(); //join all subsequent words into one big second word, which is used as a key in the game logic.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    /**
     * Print out a list of valid command words.
     **/
    public void showCommands() {
        CommandWords.showAll();
    }


    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String word1) {
        if (word1 != null) {
            for (CommandWords commandWord : CommandWords.values()) {
                if (word1.toLowerCase().equals(commandWord.getCommandString())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
