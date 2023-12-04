package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to allow the player to win the game. If the command "replenish" is performed successfully
 * i.e. all the conditions to allow the command to be performed are met, the end game routine for a win is triggered.
 * This class will end the game if the command is performed fully and register a player win.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class ReplenishCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public ReplenishCommand(Command command, Player player) {
        this.command = command;
        this.player = player;
    }

    /**
     * After the final boss is killed, this triggers the end routine (win).
     * If the command is used outside the Source of The First Droplet, or while the last boss (Lord Alun) is still alive,
     * the command will not cause the game to end.
     **/
    @Override
    public void execute() {
        if (!command.hasSecondWord()) {
            System.out.println("What would you like to replenish?");
            return;
        }
        if (!command.getSecondWord().equalsIgnoreCase("First Droplet")) {
            System.out.println("You cannot replenish something other than the First Droplet");
            return;
        }
        if (!(player.getCurrentRoom().getId() == 11)) {
            System.out.println("You cannot replenish the First Droplet here!");
            return;
        }
        if (player.getCurrentRoom().getRoomBoss().isBossAlive()) {
            return;
        }
        System.out.println("Congratulations! You have defeated Lord Alun and replenished the First Droplet!");
        System.out.println("All hail our new Lord!");
        System.out.println("Thank you for playing White Souls! We hope to see you again soon");
        System.exit(0);
    }
}
