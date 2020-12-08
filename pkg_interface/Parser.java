package pkg_interface;

 

import java.util.StringTokenizer;
import pkg_commands.CommandWords;
import pkg_commands.Command;

/**
 * The Parser class, used to recognize and execute commands.
 */
public class Parser 
{

    private CommandWords aCommandWords;  // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;
        
        Command pC = (Command)aCommandWords.getCommandFunction(vWord1);
        pC.setSecondWord(vWord2);
        
        return pC;
    } // getCommand(.)

    /**
     * Returns a String with valid command words.
     */
    public String getCommandString() // was showCommands()
    {
        return this.aCommandWords.getCommandList();
    } // getCommandString()

} // Parser