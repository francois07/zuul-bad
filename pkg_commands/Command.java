package pkg_commands;

import pkg_gameobjects.Player;
 
/**
 * The Command class, used as a template for every other commands.
 * @author Francois SOULIE
 */
public abstract class Command
{
    private CommandWord aCommandWord;
    private String aSecondWord;
    
    /**
     * The class' natural constructor.
     */
    public Command(){
    }
    
    /**
     * @return the first word of the command.
     */
    public CommandWord getCommandWord(){
        return this.aCommandWord;
    }
    
    /**
     * @return The second word of the command.
     */
    public String getSecondWord(){
        return this.aSecondWord;
    }
    
    public void setSecondWord(final String pS){
        this.aSecondWord = pS;
    }
    
    /**
     * Checks if the command has a second word.
     */
    public boolean hasSecondWord(){
        return this.getSecondWord() != null;
    }
    
    /**
     * Checks if the first word is null.
     */
    public boolean isUnknown(){
        return this.getCommandWord() == null;
    }
    

    /**
     * Executes the command
     *
     * @param pP The player
     */
    public abstract void execute(Player pP);
    
} // Command
