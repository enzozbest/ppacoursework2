package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

/**
 * This class is part of the "White Souls" application
 * <p>
 * The interface Executable is implemented here to tag this class as a command that can be performed by the CommandExecutor class.
 * Here we check that the player really wants to quit the game. If so, we quit. Otherwise, we carry on.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class QuitCommand implements Executable {

    //ATTRIBUTES
    private boolean wantToQuit;
    private Command command;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public QuitCommand(Command command) {
        this.wantToQuit = false;
        this.command = command;
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     **/
    @Override
    public void execute() {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            wantToQuit = false;
            return;
        }
        wantToQuit = true;  // signal that we want to quit
    }

    /***
     * @return true, if this command quits the game, false otherwise.
     **/
    public boolean quit() {
        return wantToQuit;
    }
}
