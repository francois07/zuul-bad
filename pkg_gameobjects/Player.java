package pkg_gameobjects;

import java.util.Stack;
import java.util.HashMap;
import pkg_game.GameEngine;

/**
 * The Player class.
 */
public class Player
{
    private GameEngine aGameEngine;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private double aMaxWeight;
    private double aCurrentWeight;
    private ItemList aInventory;
    private int aMoveMax;
    private int aCurrentMove;

    /**
     * The Player class' constructor.
     * @param pFirstRoom The room the player will spawn in.
     */
    public Player(final GameEngine pGameEngine, final Room pFirstRoom, final int pMoveMax){
        this.aGameEngine    = pGameEngine;
        this.aPreviousRooms = new Stack<Room>();
        this.aMaxWeight     = 60;
        this.aCurrentWeight = 0;
        this.aInventory     = new ItemList();
        this.aCurrentRoom   = pFirstRoom;
        this.aMoveMax       = pMoveMax;
        this.aCurrentMove   = 0;
    }

    public GameEngine getGameEngine(){
        return aGameEngine;
    }

    /**
     * Returns the Player's current room.
     */
    public Room getCurrentRoom(){
        return aCurrentRoom;
    }

    /**
     * Returns the player's remaining space in kg.
     */
    private double getSpareWeight(){
        return this.aMaxWeight - aCurrentWeight;
    }

    /**
     * Makes the Player walk in the desired direction.
     * @param pD The desired direction.
     * @return The String resulting this command which will be displayed on the UI.
     */
    public String walk(final String pD){
        Room vNextRoom = aCurrentRoom.getExit( pD );
        if( vNextRoom != null ){
            Door vD = aCurrentRoom.getDoor( pD );
            aCurrentMove++;
            if( !canGo( pD ) )
                return "The door is locked! You need the " + vD.getKey().getName() + " to open it."
                + getTimeLeftString();    
            if (aCurrentRoom instanceof TransporterRoom) resetPreviousRooms();    
            aPreviousRooms.push(this.aCurrentRoom);
            aCurrentRoom = vNextRoom;
            return aCurrentRoom.getLongDescription() + getTimeLeftString();
        } else {
            this.aCurrentMove++;
            return "There is no door!" + getTimeLeftString();
        }
    }

    public void resetPreviousRooms(){
        this.aPreviousRooms = new Stack<Room>();
    }

    /**
     * Makes the Player go back a room.
     * @return The String resulting this command which will be displayed on the UI.
     */
    public String walkBack(){
        if (!this.aPreviousRooms.empty()){
            Room vNextRoom = aPreviousRooms.peek();
            String vDirection = aCurrentRoom.getExitDirection(vNextRoom);
            aCurrentMove++;
            if(aCurrentRoom.isExit(vNextRoom) && canGo( vDirection )){
                aCurrentRoom = aPreviousRooms.pop();
                return aCurrentRoom.getLongDescription() + getTimeLeftString();
            } else {
                return "The door is locked!" + getTimeLeftString();
            }
        }
        else return "You can't go back!";
    }

    /**
     * Checks if the Player is currently carrying a certain item.
     * @param pS The name of the item.
     */
    public boolean hasItem(final String pS){
        return this.aInventory.getItem( pS.toLowerCase() )!=null;
    }

    /**
     * Checks wether the Player can walk in a certain direction or not. 
     * @param pS The direction.
     */
    private boolean canGo(final String pS){
        Door vD = aCurrentRoom.getDoor(pS);
        if(vD!=null && vD.isLocked())
            return hasItem(vD.getKey().getName());
        else return true;
    }

    /**
     * Checks if an item is to heavy for the Player to carry right now.
     * @param pI The item.
     */
    private boolean canCarry(final Item pI){
        return this.aCurrentWeight + pI.getWeight() <= aMaxWeight;
    }

    public boolean isOutOfMoves(){
        return aCurrentMove >= aMoveMax;
    }

    /**
     * Takes an item in the current room and puts it into the Player's inventory
     * @param pS The item's name.
     * @return The String resulting this command which will be displayed on the UI.
     */
    public String takeItem(final String pS){
        Item vI = aCurrentRoom.getItem(pS);
        if( vI != null){
            if ( !canCarry(vI) )
                return "This item is too heavy for you right now.";
            aInventory.setItem(pS, vI);
            aCurrentWeight += vI.getWeight();
            aCurrentRoom.removeItem(pS);

            return "You took the " + pS + ". You can take now items up to " 
            + this.getSpareWeight() + "kg.";
        } else return "There is no such item in this room";
    }

    /**
     * Takes an item into the Player's inventory and puts it in the current room.
     * @param pS The item's name.
     * @return The String resulting this command which will be displayed on the UI.
     */
    public String dropItem(final String pS){
        Item vI = this.aInventory.getItem(pS);
        if (vI != null){
            this.aCurrentRoom.addItem(vI);
            this.aCurrentWeight -= vI.getWeight();
            this.aInventory.removeItem(pS);

            return "You dropped the " + pS + ". You can now take items up to "
            + this.getSpareWeight() + "kg.";
        } else return "There is no such item in your inventory";
    }

    /**
     * Makes the Player try to eat an item he is carrying.
     * @param pS The item's name.
     * @return The String resulting this command which will be displayed on the UI.
     */
    public String eatItem(final String pS){
        Item vI = this.aInventory.getItem(pS);
        if( vI != null){
            if(vI.getName().equals("Cookie")){
                this.aInventory.removeItem(pS);
                this.aMaxWeight+=20;

                return "You suddenly feel stronger... this was a magic cookie!";
            }
            else return "You try to eat the " + vI.getName()
                + " but only end up hurting yourself... this item is not eatable!";
        } else {
            return "There is no such item in your inventory";
        }
    }

    public String talkTo(final String pS){
        GameCharacter vC = this.aCurrentRoom.getGameCharacter(pS);
        if(vC!=null){
            return vC.getDialogueString();
        } else {
            return "There is no one named " + pS + " in this room.";
        }
    }

    /**
     * @return The content of the Player's inventory.
     */
    public String getInventoryString(){
        return "Inventory: " + aInventory.getItemString();
    }
    
    /**
     * @return The player's remaining time in minutes.
     */
    public String getTimeLeftString(){
        return "\n" + (this.aMoveMax - aCurrentMove)*2 + " minutes remaining";
    }
    
    /**
     * Charges a beamer.
     * @param pS The item's name.
     * @return The result of this operation.
     */
    public String chargeBeamer(String pS){
        Item vI = aInventory.getItem(pS);
        if(vI!=null){
            if(vI instanceof Beamer){
                String vS = ( (Beamer)vI ).charge( aCurrentRoom );
                return vS;
            } else {
                return "This is not a beamer!";
            }
        } else {
            return "There is no such item in your inventory";
        }
    }

    /**
     * Fires a beamer.
     * @param pS The item's name.
     * @return The result of this operation.
     */
    public String fireBeamer(String pS){
        Item vI = aInventory.getItem(pS);
        if(vI!=null){
            if(vI instanceof Beamer){
                if( ((Beamer)vI).canFire() ) this.aCurrentRoom = ((Beamer)vI).getTargetRoom(); 
                String vS = ((Beamer)vI).fire();
                return vS;
            } else {
                return "This is not a beamer!";
            }
        } else {
            return "There is no such item in your inventory";
        }
    }
    
    /**
     * @param pR a room.
     * @return Whether or not the player has reached the room.
     */
    public boolean hasReached(Room pR){
        return this.aCurrentRoom == pR;
    }
}
