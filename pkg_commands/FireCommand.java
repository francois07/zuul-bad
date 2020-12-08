package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class FireCommand extends Command
{
    public FireCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println( "Fire what?" );
            return;
        }

        String vS = pP.fireBeamer(this.getSecondWord());
        vU.println(vS); 
        
        if( pP.getCurrentRoom().getImageName() != null )
            vU.showImage( pP.getCurrentRoom().getImageName() );
    }
}
