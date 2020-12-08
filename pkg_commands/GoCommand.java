package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class GoCommand extends Command
{
    public GoCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if(!this.hasSecondWord()){
            vU.println("Go where?");
            return;
        }

        String vDirection = this.getSecondWord();
        String vS = pP.walk( vDirection );

        vU.println(vS);
        if ( pP.getCurrentRoom().getImageName() != null )
            vU.showImage( pP.getCurrentRoom().getImageName() );
    }
}
