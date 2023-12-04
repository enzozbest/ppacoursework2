package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

import uk.ac.kcl.enzo.bestetti.ppacw2.specialCharacters.Player;
import uk.ac.kcl.enzo.bestetti.ppacw2.util.CharacterHandler;

import java.util.Locale;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * It represents an action that can be executed by the CommandExecutor class.
 * The main responsibility of this class is to display an NPC's dialogue when the player interacts with them.
 * Should the NPC have a giveable item, the item will also be given to the player
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class TalkCommand implements Executable {

    //ATTRIBUTES
    private Command command;
    private Player player;
    private CharacterHandler characterHandler;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class
     **/
    public TalkCommand(Command command, Player player, CharacterHandler characterHandler) {
        this.command = command;
        this.player = player;
        this.characterHandler = characterHandler;
    }

    /**
     * Talk with an NPC.
     * If there is an alive boss in the room, the command returns. If there are no NPCs in the room, we also return.
     * If an NPC is present and the command correctly addresses their name, it starts the dialogue for that NPC.
     **/
    @Override
    public void execute() {
        if (player.getCurrentRoom().hasBoss()) {
            if (player.getCurrentRoom().getRoomBoss().isBossAlive()) {
                return;
            }
        }
        if (!player.getCurrentRoom().hasNPC()) {
            System.out.println("You may want to stop talking to yourself. People could think you have gone crazy!");
            return;
        }
        if (!command.hasSecondWord()) {
            System.out.println("Who would you like to talk to?");
            return;
        }
        if (!player.getCurrentRoom().getPeople().containsKey(command.getSecondWord().toLowerCase(Locale.ROOT))) {
            System.out.println("This person is not in the room!");
            return;
        }
        characterHandler.initiateSpeech(player.getCurrentRoom().getPeople().get(command.getSecondWord().toLowerCase()));
    }
}

