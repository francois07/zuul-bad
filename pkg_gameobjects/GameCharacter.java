package pkg_gameobjects;

/**
 * The GameCharacter class
 */
public class GameCharacter
{
    protected String aName;
    protected String aDialogue;
    
    public GameCharacter(final String pName, final String pDialogue){
        this.aName = pName;
        this.aDialogue = pDialogue;
    }
    
    public String getName(){
        return aName;
    }
    
    public String getDialogueString(){
        return aName + " says: " + aDialogue;
    }
}
