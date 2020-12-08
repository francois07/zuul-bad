package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class TakeCommand extends Command
{
    public TakeCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println( "Take what?" );
            return;
        }

        String vS = this.getSecondWord();
        String vT = pP.takeItem(vS);

        vU.println(vT);
    }
}
