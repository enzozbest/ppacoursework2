package uk.ac.kcl.enzo.bestetti.ppacw2.util;

import uk.ac.kcl.enzo.bestetti.ppacw2.base.Initialiser;
import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * This class is part of the "White Souls" application.
 * This class is responsible for handling the rooms of the game.
 * <p>
 * Namely, this class implements high-level room functionality and serves as a mediator between the Commands and the other game classes.
 * An instance of the global list of rooms can be obtained from this handler, as well as an instance of the player (this room uses the Initialiser,
 * which creates the player object).
 * <p>
 * It also keeps track of the rooms accessed by the player, which is implemented as an ArrayList.
 * Other classes use this to implement some game features, e.g. the "back" command
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class RoomHandler {

    //ATTRIBUTES
    private Player player;
    private boolean lordBucket;
    private BossHandler bossHandler;
    private Initialiser initialiser;
    private ArrayLifoStack<Room> accessedRooms = new ArrayLifoStack();
    //ATTRIBUTES

    /**
     * Create the RoomHandler object.
     *
     * @param initialiser The initialiser is passed as a parameter here because this class must have direct access to the player object.
     **/
    public RoomHandler(Initialiser initialiser) {
        lordBucket = false;
        this.player = initialiser.getPlayer();
        this.initialiser = initialiser;
    }

    /**
     * Check whether the player entered a special room that requires a certain item or wearable for passage, or if they should be teletransported.
     * If the player has entered a special room but does not meet the entrance criteria, the game ends in loss.
     * If the player did enter the Chamber of the Princess, they will be teleported.
     *
     * @return true if the player has been teleported. False otherwise.
     **/
    public boolean roomCheck() {
        //Player tries to enter Scalding Hydrolith. If they do not have the Lava Ring equipped, they lose in 2 seconds.
        if (checkRoom(9) && !checkWearable(10)) {
            Thread countdown = new Thread(new CountdownTimer(2));
            countdown.start();
            System.out.println("You do not have anything to protect you from the lava... You are instantly incinerated!!!");
            return false;
        }
        //Player tries to enter Depths of Well. If they do not have the CrookedLantern in their inventory, they lose in 10 seconds.
        if (checkRoom(6) && !checkItem("Crooked Lantern")) {
            Thread coutndown = new Thread(new CountdownTimer(5));
            coutndown.start();
            System.out.println("It is so dark in here... You cant see anything. Uh-oh, what was that noise?");
            return false;
        }
        //Player tries to enter the Desert. If they do not have a parasol equipped. they lose in 5 seconds.
        if (checkRoom(5) && !checkWearable(5)) {
            Thread countdown = new Thread(new CountdownTimer(5));
            countdown.start();
            System.out.println("You do not have anything to protect you from the sun... Tsc Tsc, burned to a crisp!!!");
            return false;
        }
        //Player tries to enter Source of the First Droplet. If they do not have Lord Alun's Ring in their inventory, they lose in 10 seconds.
        if (checkRoom(11) && !checkItem("Lord Alun's Ring")) {
            System.out.println("Lord Alun> HOW DARE YOU INVADE MY PRECIOUS STREAM LIKE THIS! YOU WILL PAY THE PRICE!");
            System.out.println("You notice the water level in the Source of The First Droplet rising very quickly");
            System.out.println("You are drowning!!!! HELP!");
            Thread coutndown = new Thread(new CountdownTimer(5));
            coutndown.start();
            return false;
        }
        if (checkRoom(12)) {
            teletransport();
            return true;
        }
        //Activate room boss, if the room has one.
        if (player.getCurrentRoom().hasBoss()) {
            if (player.getCurrentRoom().getRoomBoss().isBossAlive()) {
                try {
                    System.out.println("You have entered " + player.getCurrentRoom().getRoomBoss().getBossName() + "'s domain!");
                    Thread.sleep(2000);
                    bossHandler = new BossHandler(this);
                    bossHandler.bossAttack();
                } catch (InterruptedException ignored) {
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Check if the player has a particular item in their inventory.
     *
     * @param itemName
     * @return true if the item is in the player's inventory, and false otherwise.
     **/
    private boolean checkItem(String itemName) {
        if (!(player.getInventory().get(itemName.toLowerCase()) == null)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the player is in a particular room.
     *
     * @param id
     * @return true if the player is in that room, and false otherwise.
     **/
    private boolean checkRoom(int id) {
        if (player.getCurrentRoom().getId() == id) {
            return true;
        }
        return false;
    }


    /**
     * Check if the player has an item equipped.
     *
     * @param itemID
     * @return true if the item is equipped, and false otherwise.
     **/
    private boolean checkWearable(int itemID) {
        if (player.getWearable() == null || !(player.getWearable().getId() == itemID)) {
            return false;
        }
        return true;
    }

    /**
     * Send all the characters in the player's current room to a different room.
     * This is achieved by adding new characters to the new rooms and removing the characters from the player's current room.
     *
     * @param nextRoomID denoting the ID of the player has moved into.
     **/
    public void moveCharacters(int nextRoomID) {

        player.getCurrentRoom().getPeople().forEach((c, i) -> (new CharacterHandler(this)).move(nextRoomID, i));
        player.getCurrentRoom().removeCharacters();
    }

    /**
     * Sets the current room of the player to a random room.
     **/
    public void teletransport() {
        boolean done = false;
        Random random = new Random();

        Room nextRoom = null;
        int n = 11;
        while (!done) {
            int index = Math.abs(random.nextInt() % n);
            nextRoom = initialiser.getRooms().get(index);
            if (nextRoom.isLocked()) {
                continue;
            }
            done = true;
        }
        initialiser.getPlayer().setCurrentRoom(nextRoom);
        System.out.println(initialiser.getPlayer().getCurrentRoom().getLongDescription());
    }

    /**
     * Changes the Lordbucket flag to true to indicate the player has placed the Lordbucket.
     * Calls the unlockGoldgates() method to initiate the late-game.
     **/
    public void placeVessel() {
        lordBucket = true;
        unlockGoldgates();
    }

    /**
     * Change the isLocked flag of all rooms (apart from Source of the First Droplet) to true.
     * Allow the player to visit all rooms
     **/
    private void unlockGoldgates() {
        Set<Integer> keys = initialiser.getRooms().keySet();
        for (Integer key : keys) {
            if (!((initialiser.getRooms().get(key).getId() == 11) || (initialiser.getRooms().get(key).getId() == 8))) {
                initialiser.getRooms().get(key).unlockRoom();
            }
        }
    }

    /**
     * Unlock the "Source of The First Droplet" room.
     **/
    public void openSource() {
        initialiser.getRooms().get(11).unlockRoom();
        System.out.println("You have successfully filled the Lordbucket with souls.");
        System.out.println("You notice the gates ahead of you opening up - It is the Source of the First Droplet!");
        System.out.println("Kill Lord Alun and replenish the First Droplet to cure the Dryman's curse!");
    }

    //GETTERS

    /**
     * Check wether the Lordbucket has been placed
     *
     * @return lordbuclet
     **/
    public boolean isLordbucket() {
        return lordBucket;
    }

    /**
     * @return the global list of rooms
     **/
    public HashMap<Integer, Room> getRooms() {
        return initialiser.getRooms();
    }

    /**
     * @return the game initialiser.
     **/
    public Initialiser getInitialiser() {
        return initialiser;
    }

    /**
     * @return the BossHandler for a boss fight.
     **/
    public BossHandler getBossHandler() {
        return bossHandler;
    }

    /**
     * @return the global list of accesed rooms as a last-in-first-out stack.
     **/
    public ArrayLifoStack<Room> getAccessedRooms() {
        return accessedRooms;
    }
    //GETTERS

}
