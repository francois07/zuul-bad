package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class BackCommand extends Command
{
    public BackCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        String vS = pP.walkBack();
        vU.println( vS );
        if ( pP.getCurrentRoom().getImageName() != null )
            vU.showImage( pP.getCurrentRoom().getImageName() );
    }
}
