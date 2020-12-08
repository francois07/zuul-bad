package pkg_commands;

import pkg_gameobjects.Player;
import pkg_gameobjects.Room;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class LookCommand extends Command
{
    public LookCommand(){
    }
    
    public void execute( Player pP ){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println( pP.getCurrentRoom().getLongDescription() );
            return;
        }

        Room vDirection = pP.getCurrentRoom().getExit( getSecondWord() );
        if ( vDirection != null) {
            String vTs = vDirection.getDescription();
            vU.println( vTs );
        } else vU.println( "There is no door you can look through!" );
    }
}
