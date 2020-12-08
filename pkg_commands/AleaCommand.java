package pkg_commands;

import java.util.HashMap;
import pkg_gameobjects.Player;
import pkg_gameobjects.Room;
import pkg_gameobjects.RoomRandomizer;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class AleaCommand extends Command
{
    public AleaCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        RoomRandomizer vRa = vG.getRandomizer();
        HashMap<String,Room> vRooms = vG.getRooms();
        
        if(!vG.isInTest()){
            vU.println("You need to be in test mode to use this command.");
            return;
        }

        if( this.hasSecondWord() && !vRa.isFakeRandom()){
            Room vR = vRooms.get(this.getSecondWord());
            if(vR==null){
                vU.println("Room could not be found.");
                return;
            }
            vRa.setFakeRandom(vR);
        } else {
            vRa.setRandom( vRooms );
        }
    }
}
