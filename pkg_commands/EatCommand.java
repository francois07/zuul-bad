package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class EatCommand extends Command
{
    public EatCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println( "Eat what?" );
            return;
        }

        String vI = this.getSecondWord();
        String vS = pP.eatItem(vI);

        vU.println(vS);
    }
}
