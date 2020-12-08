package pkg_commands;

import java.io.File;
import java.util.Scanner;
import pkg_gameobjects.Player;
import pkg_interface.UserInterface;
import pkg_game.GameEngine;

public class TestCommand extends Command
{
    public TestCommand(){
    }
    
    public void execute(Player pP){
        GameEngine vG = pP.getGameEngine();
        UserInterface vU = vG.getGUI();
        
        vG.switchTestMode();
        String vF = this.getSecondWord();
        try {
            Scanner vSc = new Scanner( new File(".\\Tests\\" + vF + ".txt") );
            while (vSc.hasNextLine()) {
                vG.interpretCommand(vSc.nextLine());
            }
            vSc.close();
        } catch(final java.io.FileNotFoundException pE) {
            vU.println("File \"Tests\\" + vF + ".txt\" does not exist!");
        }
        vG.switchTestMode();
    }
}
