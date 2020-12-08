package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class DropCommand extends Command
{
    public DropCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if ( !this.hasSecondWord() ){
            vU.println( "Drop what?" );
            return;
        }

        String vS = this.getSecondWord();
        String vD = pP.dropItem(vS);

        vU.println(vD);
    }
}
