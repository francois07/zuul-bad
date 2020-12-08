package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class QuitCommand extends Command
{
    public QuitCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( this.hasSecondWord() ){
            vU.println( "Quit what?" );
            return;
        }
        vU.enable(false);
    }
}
