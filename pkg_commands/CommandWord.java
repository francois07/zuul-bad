package pkg_commands;

/**
 * All the game's command words as constants.
 */

public enum CommandWord
{
    TEST("test",            new TestCommand()), 
    GO("go",                new GoCommand()), 
    BACK("back",            new BackCommand()), 
    LOOK("look",            new LookCommand()), 
    INVENTORY("inventory",  new InventoryCommand()), 
    TAKE("take",            new TakeCommand()), 
    DROP("drop",            new DropCommand()), 
    HELP("help",            new HelpCommand()), 
    QUIT("quit",            new QuitCommand()),
    EAT("eat",              new EatCommand()),
    CHARGE("charge",        new ChargeCommand()),
    FIRE("fire",            new FireCommand()),
    ALEA("alea",            new AleaCommand()),
    TALK("talk",            new TalkCommand()),
    UNKNOWN("?",            new UnknownCommand());
    
    
    private String aCommandWord;
    private Command aCommand;
    
    CommandWord(final String pS, final Command pCommand){
        this.aCommandWord = pS;
        this.aCommand = pCommand;
    }
    
    public String toString(){
        return this.aCommandWord;
    }
    
    public Command toCommand(){
        return this.aCommand;
    }
}
