package pkg_gameobjects;

import java.util.HashMap;
import java.util.Set;

/**
 * The Room class.
 * @author Francois SOULIE
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItems;
    private HashMap<String, Door> aDoors;
    private HashMap<String, GameCharacter> aGameCharacters;
    
    /**
     * The class' constructor.
     * @param pDescription A short description of the room.
     */
    public Room(final String pDescription){
        this.aExits = new HashMap<String, Room>();
        this.aDescription = pDescription;
        this.aItems = new ItemList();
        this.aGameCharacters = new HashMap<String,GameCharacter>();
        this.aDoors = new HashMap<String,Door>();
    }
    
    /**
     * The class' natural constructor.
     * @param pD A short description of the room.
     * @param pI The name of the image file associated to this room.
     */
    public Room(final String pD, final String pI){
        this(pD);
        this.aImageName = pI;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription(){
        return this.aDescription;
    }
    
    /**
     * Sets the room's exit in the desired direction.
     * @param pDirection The direction of the exit (north/south/east/west/up/down).
     * @param pNewRoom The exit.
     */
    public void setExit(final String pDirection, final Room pNewRoom){
        this.aExits.put(pDirection, pNewRoom);
        this.aDoors.put(pDirection, new Door());
    }
    
    /**
     * @return The room's exit that's in the desired direction.
     * @param pDirection The desired direction.
     */
    public Room getExit(final String pDirection){
        return this.aExits.get(pDirection);
    }
    
    /**
     * @return The direction of an exit.
     * @param pR The exit.
     */
    public String getExitDirection(final Room pR){
        for(String vR : this.aExits.keySet()) if ( this.aExits.get(vR) == pR ) return vR;
        return null;
    }
    
    /**
     * Checks if a Room is one of this Room's exit.
     */
    public boolean isExit(final Room pR){
        return this.aExits.containsValue(pR);
    }
    
    /**
     * @return The door of this Room in the desired direction.
     * @param pDirection The direction.
     */
    public Door getDoor(final String pDirection){
        return this.aDoors.get( pDirection );
    }
    
    public void lockDoor(final String pDirection, final Item pItem){
        this.aDoors.get(pDirection).lock(pItem);
    }
    
    /**
     * @return The room's available directions.
     */
    public String getExitString(){
        String vE = "Exits: ";
        Set<String> vKeyset = this.aExits.keySet();
        for(String i : vKeyset) vE+= " "+i;
        if( vE.length() > "Exits: ".length() ) return vE;
        else return "No exits";
    }
    
    /**
     * @return A somewhat long description of the room.
     */
    public String getLongDescription(){
        return "You are... " + getDescription() + " | " + getExitString()
        + " | " + "Items:" + aItems.getItemString() + "\n" + getCharacterString();
    }
    
    /**
     * @return The name of the image file associated to this Room.
     */
    public String getImageName(){
        return this.aImageName;
    }
    
    /**
     * Adds an item to this Room.
     * @param pI The item.
     */
    public void addItem(final Item pI){
            this.aItems.setItem(pI.getName(), pI);
    }
    
    /**
     * @return One of this Room's item.
     * @param pS The name of the item.
     */
    public Item getItem(final String pS){
        return this.aItems.getItem(pS);
    }
    
    /**
     * Removes one of this Room's item.
     * @param pS The name of the item.
     */
    public void removeItem(final String pS){
        this.aItems.removeItem(pS);
    }
    
    public void addCharacter(final GameCharacter pC){
        this.aGameCharacters.put(pC.getName(), pC);
    }
    
    public GameCharacter getGameCharacter(final String pS){
        return this.aGameCharacters.get(pS);
    }
    
    public String getCharacterString(){
        String vE = "There is " + aGameCharacters.size() + " people in this room:";
        for( GameCharacter vC : aGameCharacters.values()) vE+= " "+vC.getName();
        if( aGameCharacters.size() > 0 ) return vE;
        else return "There is nobody in this room";
    }
} // Room
