package uk.ac.kcl.enzo.bestetti.ppacw2.util;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * Higher-level NPC logic is implemented in this class, e.g. moving between rooms and giving items.
 * The main purpose of this class is to serve as a mediator between other classes and the Character class.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public class CharacterHandler {

    //ATTRIBUTES
    private RoomHandler roomHandler;
    //ATTRIBUTES

    /**
     * Initialise variables needed in the class.
     **/
    public CharacterHandler(RoomHandler roomHandler) {
        this.roomHandler = roomHandler;
    }

    /**
     * This method "moves" an NPC to a new room based on its move set.
     * In reality, this method only adds the NPC to their new room. A different method is called to remove the NPC from the current room.
     *
     * @param nextRoomID which is the ID of the room the player has just entered.
     **/
    public void move(int nextRoomID, Character character) {
        if (character.isFirstEncounter()) {
            return;
        }
        String[] moves = character.getMoveSet().split(" : ");

        switch (nextRoomID) {
            case 0 -> roomHandler.getRooms().get(Integer.parseInt(moves[0])).addCharacter(character);
            case 1 -> roomHandler.getRooms().get(Integer.parseInt(moves[1])).addCharacter(character);
            case 2 -> roomHandler.getRooms().get(Integer.parseInt(moves[2])).addCharacter(character);
            case 3 -> roomHandler.getRooms().get(Integer.parseInt(moves[3])).addCharacter(character);
            case 4 -> roomHandler.getRooms().get(Integer.parseInt(moves[4])).addCharacter(character);
            case 5 -> roomHandler.getRooms().get(Integer.parseInt(moves[5])).addCharacter(character);
            case 6 -> roomHandler.getRooms().get(Integer.parseInt(moves[6])).addCharacter(character);
            case 7 -> roomHandler.getRooms().get(Integer.parseInt(moves[7])).addCharacter(character);
            case 8 -> roomHandler.getRooms().get(Integer.parseInt(moves[8])).addCharacter(character);
            case 9 -> roomHandler.getRooms().get(Integer.parseInt(moves[9])).addCharacter(character);
            case 10 -> roomHandler.getRooms().get(Integer.parseInt(moves[10])).addCharacter(character);
            case 11 -> roomHandler.getRooms().get(Integer.parseInt(moves[11])).addCharacter(character);
            case 12 -> roomHandler.getRooms().get(Integer.parseInt(moves[12])).addCharacter(character);
        }
    }

    /**
     * Call the speak method from the Character class.
     * If the NPC has a giveable item, give it to the player.
     **/
    public void initiateSpeech(Character character) {
        character.speak(); // display dialogue

        // give NPCs item. If it exceeds the player's max weight, drop it on the floor.
        if (character.getGiveable() != null) {
            if (!((roomHandler.getInitialiser().getPlayer().getCurrentWeight() + character.getGiveable().getWeight()) > roomHandler.getInitialiser().getPlayer().getMaxWeight())) {
                roomHandler.getInitialiser().getPlayer().getInventory().put(character.getGiveable().getName().toLowerCase(), character.getGiveable());
                roomHandler.getInitialiser().getPlayer().setCurrentWeight(roomHandler.getInitialiser().getPlayer().getCurrentWeight() + character.getGiveable().getWeight());
                return;
            }
            roomHandler.getInitialiser().getPlayer().getCurrentRoom().getItems().put(character.getGiveable().getName().toLowerCase(), character.getGiveable());
            System.out.println("You were not strong enough to hold " + character.getGiveable().getName() + " so it has fallen on the floor.");
            System.out.println("You can still pick it up from this room, just find a way to carry its weight.");
        }
    }
}
