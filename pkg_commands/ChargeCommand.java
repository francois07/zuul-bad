package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class ChargeCommand extends Command
{
    public ChargeCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println( "Charge what?" );
            return;
        }

        String vS = pP.chargeBeamer(this.getSecondWord());

        vU.println(vS);
    }
}
