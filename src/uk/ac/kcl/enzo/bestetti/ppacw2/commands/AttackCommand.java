package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

/**
 * This class is part of the "White Souls" application.
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to issue a player attack on a boss and a boss attack
 * on a player if the boss is still alive after a player attack.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class AttackCommand implements Executable {

    //ATTRIBUTES
    private RoomHandler roomHandler;
    private Player player;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public AttackCommand(RoomHandler roomHandler) {
        this.roomHandler = roomHandler;
        this.player = roomHandler.getInitialiser().getPlayer();
    }

    /**
     * Attack an enemy.
     * If the player has an attack item in their inventory, then the time challenge issued by the boss is interrupted.
     * Otherwise, it will cancel the player attack attempt and the boss attack is allowed to carry on.
     **/
    @Override
    public void execute() {
        if (!player.getCurrentRoom().hasBoss()) {
            System.out.println("There is nothing to attack here!");
            return;
        }
        if (!player.getCurrentRoom().getRoomBoss().isBossAlive()) {
            System.out.println("There is nothing to attack here!");
            return;
        }
        if (player.getInventory().containsKey("sword") || player.getInventory().containsKey("kraken tail") || player.getInventory().containsKey("death scythe") || player.getInventory().containsKey("dragon sword")) {
            roomHandler.getBossHandler().playerAttack();
            roomHandler.getBossHandler().bossAttack();
            return;
        }
        System.out.println("You do not have a weapon to attack with! This cannot be good...");
    }
}
