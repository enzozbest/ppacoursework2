package uk.ac.kcl.enzo.bestetti.ppacw2.util;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * The "Character" class is used to represent NPCs that can be added to rooms in the game.
 * The Player can talk to NPCs to obtain information and/or gain items.
 * NPCs are free to move around AFTER their first encounter with the player.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class Character {

    //ATTRIBUTES
    private final String name;
    private final ArrayList<String> dialogue;
    private final Item giveable;
    private final String moveSet;
    private boolean firstEncounter;
    //ATTRIBUTES

    /**
     * Create a character object.
     *
     * @param name           to indicate what the character is called
     * @param giveable       specifies an Item which is given to the player by the character. This may be null.
     * @param dialogueString details the sentences in the character's dialogue
     * @param moveSet        keeps track of the character movements in relation to the NPC's movements.
     **/
    public Character(String name, Item giveable, String dialogueString, String moveSet) {
        this.name = name;
        this.dialogue = new ArrayList<>();
        this.giveable = giveable;
        this.moveSet = moveSet;
        this.firstEncounter = true;
        createDialgoue(dialogueString);

    }

    /**
     * @return the NPC's name.
     **/
    public String getName() {
        return name;
    }


    /**
     * This method extracts the sentences from the main dialogue String passed a construction and adds them to an ArrayList.
     * The sentences are later used to display a character's dialogue.
     **/
    public void createDialgoue(String dialogueString) {
        String[] sentences = dialogueString.split(" : "); // Dialogue is passed as one string containing multiple sentences. The expression " : " separates sentences
        dialogue.addAll(Arrays.asList(sentences)); //Obtain individual sentences in an ArrayList.
    }


    /**
     * Display the NPC's dialogue on screen with a 1.5s delay between sentences. The delay uses the Thread.sleep() method.
     * If the NPC has a giveable item it gets added to the player's inventory. If not, we return from the method.
     **/
    public void speak() {
        firstEncounter = false;
        for (String sentence : dialogue) {
            System.out.println(sentence);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @return true if the player has never talked to this NPC; false otherwise.
     **/
    public boolean isFirstEncounter() {
        return this.firstEncounter;
    }

    /**
     * @return the character's move set.
     **/
    public String getMoveSet() {
        return moveSet;
    }

    /**
     * @return the character's giveable item.
     **/
    public Item getGiveable() {
        return giveable;
    }

}
