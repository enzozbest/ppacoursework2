package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.ArrayLifoStack;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

import java.util.NoSuchElementException;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to move the player back one room.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class BackCommand implements Executable {

    //ATTRIBUTES
    private Player player;
    private ArrayLifoStack<Room> accessedRooms;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public BackCommand(RoomHandler handler) {
        this.player = handler.getInitialiser().getPlayer();
        this.accessedRooms = handler.getAccessedRooms();
    }

    /**
     * Takes the player to the room they were in before being in the current room.
     * A last-in-first-out stack keeps track of the rooms that were visited (in order). The command moves the player
     * to the second room on the top of the stack (the one at the top is always the current room).
     * If the player ends up in the Magic Transporter room, the stack is cleared and the player cannot go back anywhere.
     *
     * @throws NoSuchElementException when the player has no rooms to go back to. This is handled here and the player is informed.
     **/
    @Override
    public void execute() throws NoSuchElementException {
        try {
            if (player.getCurrentRoom().hasBoss() && player.getCurrentRoom().getRoomBoss().isBossAlive()) {
                System.out.println("You cannot escape!");
                return;
            }
            //Remove the element at the top of the stack (the room the player is currently in)
            accessedRooms.pop();

            //Moves the player back one room
            player.setCurrentRoom(accessedRooms.peek());
            assert player.getCurrentRoom() != null; //following lines of code rely on getCurrentRoom() not returning a null value.
            System.out.println(player.getCurrentRoom().getLongDescription());
        } catch (NoSuchElementException e) {
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println("You have not accessed any rooms to go back to!");
            accessedRooms.push(player.getCurrentRoom());
        }
    }
}
