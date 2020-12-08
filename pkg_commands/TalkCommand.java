package pkg_commands;

import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

/**
 * Write a description of class TalkCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TalkCommand extends Command
{
    public TalkCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        if( !this.hasSecondWord() ){
            vU.println("Talk to who?");
            return;
        }
        
        String vS = pP.talkTo(this.getSecondWord());
        vU.println(vS);
    }
}
