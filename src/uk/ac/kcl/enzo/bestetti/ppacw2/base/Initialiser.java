package uk.ac.kcl.enzo.bestetti.ppacw2.base;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Boss;
import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Character;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Item;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.Room;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.RoomHandler;

import java.util.HashMap;

/**
 * This class is part of the "White Souls" application.
 * This class is responsible for creating all the rooms, items, bosses, and NPCs in the game.
 * All items, bosses, and NPCs are added to their initial rooms.
 * The Initialiser class also creates the Player object, as well as the global list of rooms.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class Initialiser {

    private Room waterlinkWell, chamberOfThePrincess, waterlinkAltar, source, drymanBurg, aridChurch, drenchedOasis, newOasisRemains, depthsWell, princesFiles, mineralMines, scaldingHydrolith, theDesert;


    //ATTRIBUTES
    private HashMap<Integer, Room> rooms = new HashMap<>();
    private final Player player = new Player();
    private RoomHandler roomHandler;
    //ATTRIBUTES

    /**
     * Build the Initialiser object.
     **/
    public Initialiser() {
        roomHandler = new RoomHandler(this);
    }

    /**
     * Create all the Room objects.
     * All items in rooms are set at construction of the rooms.
     **/
    private void createRooms() {
        waterlinkWell = new Room("Waterlink Well", 0, "Waterlink Well - Start of the Chosen Dryman's journey", false, "White Soul,0,0.25,1,true,false : Sword,1,0.5,100,false,false");
        rooms.put(0, waterlinkWell);
        drymanBurg = new Room("Dryman Burg", 1, "Dryman Burg - Make your way through to the Drenched Oasis.", false, "Merchant Table,1,1000,0,false,false");
        rooms.put(1, drymanBurg);
        aridChurch = new Room("Arid Church", 2, "Arid Church - Defeat the Kraken to gain passage to Drenched Oasis!", false, "Kraken Tail,3,1.0,500,true,false");
        rooms.put(2, aridChurch);
        drenchedOasis = new Room("Drenched Oasis", 3, "Drenched Oasis - Defeat the Guardians of the Princess to retrieve the Lordbucket", true, "Lordbucket,4,5.0,1,false,false");
        rooms.put(3, drenchedOasis);
        newOasisRemains = new Room("New Oasis Remains", 4, "New Oasis Remains - Curious place, a semi-dried oasis. You wonder...", false, "Parasol,5,1.5,100,true,false");
        rooms.put(4, newOasisRemains);
        theDesert = new Room("The Desert", 5, "The Desert - Defeat this dry prickly beast to get an Imperial Soul", true, "Sand,6,0.1,1,true,false");
        rooms.put(5, theDesert);
        depthsWell = new Room("Depths of Well", 6, "Depths of Well - Approach the colourful sarcophagus to kill the Dead Clown and get his Imperial Soul", true, "Death Scythe,7,10.0,10000,false,false");
        rooms.put(6, depthsWell);
        princesFiles = new Room("Prince's Files", 7, "Prince's Files - Make way through these files to reach the Mineral Mines. Remember to talk to people!", true, "Mineral Key,8,0.25,1,true,true");
        rooms.put(7, princesFiles);
        mineralMines = new Room("Mineral Mines", 8, "Mineral Mines - Defeat Levi, the Dragon to get his Imperial Soul", true, "Dragon Sword,9,6,600,false,false : Lava Ring,10,0.25,100,true,false");
        rooms.put(8, mineralMines);
        scaldingHydrolith = new Room("Scalding Hydrolith", 9, "Scalding Hydrolith - Find your way through the lava to kill the Unintelligent Witch", true, "Demon Soul,300,0.25,1,true,false");
        rooms.put(9, scaldingHydrolith);
        waterlinkAltar = new Room("Waterlink Altar", 10, "Waterlink Altar - Obtain the Lordbucket and place it here.", false, "Crooked Lantern,12,3.0,1,false,false");
        rooms.put(10, waterlinkAltar);
        source = new Room("Source of the First Droplet", 11, "Source of the First Droplet - The final destination. Kill Lord Alun and replenish the First Droplet", true, "Water,13,0.25,1,true,false");
        rooms.put(11, source);
        chamberOfThePrincess = new Room("Chamber of the Princess", 12, "The princess casts a spell upon you. You begin to feel dizzy and suddenly everything turns black... You are moving, you have been teletransported.", false, "Transporter,20,100000,100000,false,false");
        rooms.put(12, chamberOfThePrincess);
    }

    /**
     * Link the exits of the game rooms together
     **/
    private void linkExits() {
        //Waterlink Well exits
        waterlinkWell.setExit("east", drymanBurg);
        waterlinkWell.setExit("south", newOasisRemains);

        //Dryman Burg exits
        drymanBurg.setExit("west", waterlinkWell);
        drymanBurg.setExit("east", depthsWell);
        drymanBurg.setExit("south", aridChurch);

        //Arid Church exits
        aridChurch.setExit("north", drymanBurg);
        aridChurch.setExit("south", drenchedOasis);

        //Drenched Oasis exits
        drenchedOasis.setExit("north", aridChurch);
        drenchedOasis.setExit("east", chamberOfThePrincess);

        //New Oasis Remains exits
        newOasisRemains.setExit("north", waterlinkWell);
        newOasisRemains.setExit("south", theDesert);

        //The Desert Exits
        theDesert.setExit("north", newOasisRemains);

        //Depths of Well exits
        depthsWell.setExit("west", drymanBurg);
        depthsWell.setExit("east", princesFiles);

        //Prince's Files exits
        princesFiles.setExit("west", depthsWell);
        princesFiles.setExit("east", mineralMines);

        //Mineral Mines exits
        mineralMines.setExit("west", princesFiles);
        mineralMines.setExit("east", scaldingHydrolith);

        //Scalding Hydrolith exits
        scaldingHydrolith.setExit("west", mineralMines);

        //Waterlink Altar exits
        waterlinkAltar.setExit("south", waterlinkWell);
        waterlinkWell.setExit("north", waterlinkAltar);
        waterlinkAltar.setExit("east", source);

        //Source of the First Droplet exits
        source.setExit("west", waterlinkAltar);

        //Chamber of the Princess exits
        chamberOfThePrincess.setExit("west", drenchedOasis);
    }

    /**
     * Add characters to the rooms in which they are located at the beginning of the game.
     **/
    private void addCharacters() {
        //Sad Soldier at Waterlink Well
        waterlinkWell.addCharacter(new Character("Sad Soldier", 10, new Item("Map", 400, 0, 1000, false, false), "Welcome to the realm of White Souls. : " +
                "I am the Sad Soldier, who could not fulfill their duty as a once Chosen Dryman. : " +
                "Your task here is nothing but simple. Many challenges lie ahead. Here, have this map to help you find your way. : " +
                "Locked rooms are shown in red. And you can always see where you are by finding the room coloured green. : " +
                "Worry not, Chosen Dryman, the map does not weigh anything. : " +
                "Just remember not to forget your sword... Like I did :(", "0 : 1 : 6 : 8 : 10 : 5 : 2 : 7 : 3 : 9 : 4 : 11"));

        //Long Lizard at Waterlink Altar
        waterlinkAltar.addCharacter(new Character("Long Lizard", 100, new Item("Lord Alun's Ring", 700, 0.75, 10, false, false), "Well hello there! You don't look like you have lost your mind yet. : " +
                "I am the Long Lizard. I have existed for as long as this world, and I have seen... things. : " +
                "I will now reveal to you, Chosen Dryman, your fate. : " +
                "You must succeed Lord Alun. Only then will your curse be cured. Only when you defeat him and replenish the first droplet will you truly be free. : " +
                "He lies just behind me, through these gates. You must find the Lordcuket and place it here. Then feed it the four ancient Imperial Souls. : " +
                "These are the Yellow Soul, the Black Soul, the Crystalised Soul, and the Orange Soul. : " +
                "Once the Lordcuket is satiated, the gates will open and you will be able to confront your fate. : " +
                "You will also need this. This ring belonged to Lord Alun himself. Take it. You must not lose it, for without it you will stand no chance against him. : " +
                "Now be on your way. Maketh pilgramage to the Drenched Oasis in order to obtain the Lordbucket. : " +
                "And DO NOT forget your sword. There are enemies at every turn. He he he he...", "6 : 8 : 10 : 2 : 3 : 1 : 0 : 7 : 4 : 5 : 7 : 9"));

        //Prince Irving at Prince's Files
        princesFiles.addCharacter(new Character("Prince Irving", 10, null, "Goodness me! I can't remember when I last saw someone in these files. : " +
                "Pleasure, good Sir. My name is Prince Irving, at your service. : " +
                "I take it you are the unlucky soul who got chosen this time, to try to cure our curse. : " +
                "Dear, oh dear, I have seen many before you. None succeeded. : " +
                "As it happens, I have not seen anyone escape this room next door. No one has been able to defeat the dragon in that room. : " +
                "Daddy has always had a little bit of a temper... : " +
                "I should not have said that. : " +
                "I should not have said that. : " +
                "I should not have said that : " +
                "Anyway, I will let you go now. Best of luck. : " +
                "Ha ha ha ha ha", "7 : 5 : 2 : 1 : 0 : 9 : 10 : 8 : 3 : 4 : 6 : 11"));
    }

    /**
     * Add bosses to their arenas.
     **/
    private void addBosses() {
        //Giant Dry Cactus at the Desert
        theDesert.setRoomBoss(new Boss("Giant Dry Cactus", 7, new Item("Yellow Soul", 50, 0.25, 1, false, false)));

        //Kraken at Arid Church
        aridChurch.setRoomBoss(new Boss("Kraken", 4, new Item("Drenched Oasis Key", 2, 0.25, 1, true, true)));

        //Guardian of the Princess at Drenched Oasis
        drenchedOasis.setRoomBoss(new Boss("Guardian of the Princess", 9, new Item("Guardian Soul", 200, 0.25, 1, true, false)));

        //Dead Clown at Depths of Well
        depthsWell.setRoomBoss(new Boss("Dead Clown", 14, new Item("Black Soul", 51, 0.25, 1, false, false)));

        //Levi, the Dragon at Mineral Mines
        mineralMines.setRoomBoss(new Boss("Levi, the Dragon", 11, new Item("Crystalised Soul", 52, 0.25, 1, false, false)));

        //Unintelligent Witch at Scalding Hydrolith
        scaldingHydrolith.setRoomBoss(new Boss("Unintelligent Witch", 9, new Item("Orange Soul", 53, 0.25, 1, false, false)));

        //Lord Alun at Source of the First Droplet
        source.setRoomBoss(new Boss("Lord Alun", 29, new Item("Lord Alun's Soul", 500, 0.5, 1, true, false)));
    }

    /**
     * Initialise the game by creating all rooms, creating all items,
     * linking room exits together, adding all characters, and adding all bosses.
     **/
    public void init() {
        createRooms();
        linkExits();
        addCharacters();
        addBosses();
    }

    //GETTERS

    /**
     * @return global list of rooms.
     **/
    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    /**
     * @return the player object.
     **/
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the room handler for the game.
     **/
    public RoomHandler getRoomHandler() {
        return roomHandler;
    }
    //GETTERS
}

