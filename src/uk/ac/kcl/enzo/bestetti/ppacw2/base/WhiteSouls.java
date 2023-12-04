package uk.ac.kcl.enzo.bestetti.ppacw2.base;

import uk.ac.kcl.enzo.bestetti.ppacw2.commands.*;
import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.CharacterHandler;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is part of the "White Souls" application.
 * "White Souls" is a very simple text based adventure game.
 * Users can walk around some scenery, interact with items, interact with NPCs, and fight enemies.
 * <p>
 * This class implements the high-level game logic (e.g. processing command requests) and stores some information which can be used by other classes
 * through the RoomHandler, CharacterHandler, Initialiser, and BossHandler objects.
 * <p>
 * This is the Client class of the Command Pattern Design implemented in this project.
 *
 * @author Michael Kölling, David J. Barnes, Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/

public class WhiteSouls {

    //ATTRIBUTES
    private final Parser parser;
    private boolean finished;
    private RoomHandler roomHandler;
    private final CharacterHandler characterHandler;
    private HashMap<Integer, Room> rooms;
    private Player player;
    //ATTRIBUTES

    /**
     * Create the game.
     **/
    public WhiteSouls() {
        Initialiser initialiser = new Initialiser();
        initialiser.init(); //Initialise the Game Elements

        //Store some information that other classes may require to access game elements.
        this.roomHandler = initialiser.getRoomHandler();
        this.rooms = roomHandler.getRooms();
        this.player = roomHandler.getInitialiser().getPlayer();
        this.characterHandler = new CharacterHandler(roomHandler);
        this.parser = new Parser();
    }

    /**
     * Main play routine. Loops until end of play.
     **/
    public void play() {
        player.setCurrentRoom(rooms.get(0)); //Start game at Waterlink Well
        roomHandler.getAccessedRooms().push(player.getCurrentRoom());

        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing White Souls. We hope to see you again soon");
    }

    /**
     * Given a command, pass the command request to the CommandExecutor.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     **/
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandExecutor executor = new CommandExecutor();

        if (command.isUnknown()) {
            System.out.println("You cannot perform this action!");
            return false;
        }

        String commandWord = command.getCommandWord().toLowerCase();
        switch (commandWord) {
            case "help" -> printHelp();
            case "go" -> executor.perform(new GoCommand(command, roomHandler));
            case "pickup" -> executor.perform(new PickupCommand(command, player));
            case "inventory" -> executor.perform(new InventoryCommand(player));
            case "drop" -> executor.perform(new DropCommand(command, player));
            case "use" -> executor.perform(new UseCommand(command, player, rooms));
            case "back" -> executor.perform(new BackCommand(roomHandler));
            case "place" -> executor.perform(new PlaceCommand(command, roomHandler));
            case "replenish" -> executor.perform(new ReplenishCommand(command, player));
            case "map" -> executor.perform(new MapCommand(roomHandler));
            case "feed" -> executor.perform(new FeedCommand(command, roomHandler));
            case "attack" -> executor.perform(new AttackCommand(roomHandler));
            case "talk" -> executor.perform(new TalkCommand(command, player, characterHandler));
            case "quit" -> {
                QuitCommand shouldQuit = new QuitCommand(command);
                executor.perform(shouldQuit);
                wantToQuit = shouldQuit.quit();
            }
        }

        // else command not recognised.
        return wantToQuit;
    }

    /**
     * Print out some help information.
     * List all available command words.
     **/
    private void printHelp() {
        System.out.println("You are lost. Are you are alone? You wonder... If you only had a map, this would make your life so much easier.");
        System.out.println("You hear some noise nearby. Perhaps try investigating it?");
        System.out.println("Have you tried talking to people around you? They may be more helpful than you think.");
        System.out.println();
        System.out.println("In any case, your command words are:");

        parser.showCommands();
    }

    /**
     * Print out the opening message for the player.
     * 1s delays are introduced between sentences to make the game more interactive and easier to play. This allows more time for
     * the player to read the messages.
     **/
    private void printWelcome() {
        try {
            System.out.println();
            System.out.println("Welcome to White Souls, Chosen Dryman!");
            Thread.sleep(1000);
            System.out.println("Your journey begins here, at Waterlink Well. This is the last place water has been seen flowing, many years past.");
            Thread.sleep(1000);
            System.out.println("All humans have been cursed with the Dryman's curse as a punishment from the gods for abusing the Water. Humans who lose their purpose dry out; they become dusty and crazy.");
            Thread.sleep(1000);
            System.out.println("Your job here, like many before you, is to replenish the First Droplet. You must not fail! Not like the others. Your world is running out of time.");
            System.out.println();
            Thread.sleep(1000);
            System.out.println("A good start is to talk to people around here. They have seen far more than you and could be of help.");
            Thread.sleep(1000);
            System.out.println("This is where I shall leave you, Chosen Dryman. I bid you farewell in the hopes of seeing you again, soon enough.");
            Thread.sleep(1000);
            System.out.println("Good luck, Chosen Dryman!");
            Thread.sleep(1000);
            System.out.println("If you would like extra help, all you have to do is type 'help'.");
            System.out.println();
            Thread.sleep(1000);
            System.out.println(player.getCurrentRoom().getLongDescription());
        } catch (InterruptedException e) {
            System.out.println("Exception found while printing the welcome message: Thread interrupted." + Arrays.toString(e.getStackTrace()));
        }
    }
}
