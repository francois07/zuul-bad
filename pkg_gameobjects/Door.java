package pkg_gameobjects;

/**
 * The Door class.
 */
public class Door
{
    private boolean aOpen;
    private Item aKey;
    
    /**
     * The class' constructor.
     */
    public Door(){
        this.aOpen = true;
    }
    
    /**
     * Locks the door
     * @param pKey The item required to open this door.
     */
    public void lock(final Item pKey){
        this.aOpen = false;
        this.aKey = pKey;
    }
    
    /**
     * Checks if the door is locked.
     */
    public boolean isLocked(){
        return !this.aOpen;
    }
    
    /**
     * @return The item required to open this door.
     */
    public Item getKey(){
        return this.aKey;
    }
}
