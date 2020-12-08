package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_interface.Parser;
import pkg_game.GameEngine;

public class HelpCommand extends Command
{
    public HelpCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        Parser vP = vG.getParser();
        
        vU.println("So, you're lost?\n"
            + "Don't worry, i'll help you a little bit.\n"
            + "Your command words are: " + vP.getCommandString());
    }
}
