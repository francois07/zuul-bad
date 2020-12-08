package pkg_game;

import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import pkg_gameobjects.RoomRandomizer;
import pkg_gameobjects.Room;
import pkg_gameobjects.TransporterRoom;
import pkg_gameobjects.Player;
import pkg_gameobjects.GameCharacter;
import pkg_gameobjects.Item;
import pkg_gameobjects.Beamer;
import pkg_interface.UserInterface;
import pkg_interface.Parser;
import pkg_commands.CommandWord;
import pkg_commands.Command;

/** 
 * The main class of the game.
 * @author Francois SOULIE
 */
public class GameEngine
{
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private HashMap<String, Room> aRooms;
    private RoomRandomizer aRandom;
    private boolean aInTest;

    /**
     * The class' default constructor
     */
    public GameEngine(){
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.aRandom = new RoomRandomizer();
        this.createRooms();
        this.aRandom.setRandom(aRooms);
    }

    /**
     * Initializes the UI and opens it.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Creates all the game's rooms.
     */
    private void createRooms(){
        Room vBedroom =     new Room("In your bedroom.", "Bedroom.jpg");
        Room vHouse =       new Room("In your house.", "House.jpg");
        Room vStreetM =     new Room("At the center of the street.", "StreetM.jpg");
        Room vStreetW =     new TransporterRoom("At the west part of the street. This is a dead end.", this.aRandom, "StreetW.jpg");
        Room vStreetE =     new Room("The east part of the street.", "StreetE.jpg");
        Room vBusStation =  new Room("At the bus station.", "BusStation.jpg");
        Room vBusFrontA =   new Room("At the front of the bus. Please take a seat at the back of the bus", "BusFrontA.jpg");
        Room vBusFrontB =   new Room("At the front of the bus.", "BusFrontA.jpg");
        Room vBusBack =     new Room("At the back of the bus. You've arrived to your destination! Please get off at the front of the bus", "BusBack.jpg");
        Room vStreetThi =   new Room("In the street. You can see a big metal spike far in the distance, you're almost there!", "StreetThi.jpg");
        Room vStreetSec =   new Room("In the street. You're getting closer, only a few meters left before you're at school, keep it up!\nYou cross path with some Gilet Jaunes...", "StreetSec.jpg");
        Room vSchool =      new Room("At ESIEE.", "ESIEE.jpg");
        
        aRooms.put( "Bedroom",      vBedroom );
        aRooms.put( "House",        vHouse );
        aRooms.put( "MiddleStreet", vStreetM );
        aRooms.put( "WestStreet",   vStreetW );
        aRooms.put( "EastStreet",   vStreetE );
        aRooms.put( "BusStation",   vBusStation );
        aRooms.put( "BusFront(A)",  vBusFrontA );
        aRooms.put( "BusFront(B)",  vBusFrontB );
        aRooms.put( "BusBack",      vBusBack );
        aRooms.put( "SecondStreet", vStreetSec );
        aRooms.put( "ThirdStreet",  vStreetThi );
        aRooms.put( "ESIEE",        vSchool);

        vBedroom    .setExit( "down",    vHouse );
        vHouse      .setExit( "up",      vBedroom );
        vHouse      .setExit( "north",   vStreetM );
        vStreetM    .setExit( "south",   vHouse );
        vStreetM    .setExit( "west",    vStreetW );
        vStreetM    .setExit( "east",    vStreetE );
        vStreetW    .setExit( "east",    vStreetM );
        vStreetE    .setExit( "west",    vStreetM ); 
        vStreetE    .setExit( "north",   vBusStation );
        vBusStation .setExit( "south",   vStreetE );
        vBusStation .setExit( "up",      vBusFrontA );
        vBusFrontA  .setExit( "south",   vBusBack );
        vBusBack    .setExit( "north",   vBusFrontB );
        vBusFrontB  .setExit( "south",   vBusBack );
        vBusFrontB  .setExit( "east",    vStreetSec );
        vStreetSec  .setExit( "east",    vStreetThi );
        vStreetThi  .setExit( "east",    vSchool );

        vBedroom    .addItem( new Item( "Navigo",  "A pass required to take the bus", 2 ) );
        vBedroom    .addItem( new Item( "Bag",     "Your schoolbag, it contains your copybooks and schoolwork", 20 ) );
        vHouse      .addItem( new Item( "Keys",    "The keys to your house", 1 ) );
        vStreetW    .addItem( new Item( "Cookie",  "A delicious-looking cookie... it seems to be shining somehow?", 2 ) );
        vBedroom    .addItem( new Beamer( "Beamer",  "A weird-looking device... seems like you can charge and fire it", 15 ) );
        
        vStreetSec    .addCharacter( new GameCharacter("Robert", "Les gilets jaunes sont la France de la mondialisation malheureuse!") );
        vStreetSec    .addCharacter( new GameCharacter("Nathalie", "Nous sommes convaincus que l'issue de la crise viendra du combat contre la bourgeoisie qui combat tous les pouvoirs!") );
        
        vStreetM    .lockDoor( "south",  vHouse.getItem( "Keys" ) );
        vBusStation .lockDoor( "up",     vBedroom.getItem( "Navigo" ) );

        this.aPlayer = new Player( this, vBedroom, 17 );
    }

    /**
     * Prints the introduction.
     */
    private void printWelcome(){
        aGui.println( "Get up, you're late for class!\n"
            +"It's the first day of the bus strike but you can't be late! Get to ESIEE before 8 am.\n"
            +"Type " + CommandWord.HELP + " if you need help." );

        aGui.println( aPlayer.getCurrentRoom().getLongDescription() );
        if( aPlayer.getCurrentRoom().getImageName() != null )
            aGui.showImage( aPlayer.getCurrentRoom().getImageName() );
    }

    /**
     * Checks if a command is valid and executes it.
     * @param pC The string associated to this command.
     */
    public void interpretCommand( final String pC ){
        aGui.println( "> " + pC );
        Command vC = aParser.getCommand( pC );

        vC.execute( aPlayer );
        if( aPlayer.isOutOfMoves() ){
            aGui.println( "You are out of moves, you're now way too late. You lost!" );
            aParser.getCommand( "quit" ).execute( aPlayer );
        }
        if( aPlayer.hasReached(aRooms.get("ESIEE")) && !aInTest){
            aGui.println( this.getEndString(aPlayer) );
            aParser.getCommand( "quit" ).execute( aPlayer );
        }
    }
    
    public boolean isInTest(){
        return aInTest;
    }

    public void switchTestMode(){
        if( aInTest ) this.aInTest = false;
        else this.aInTest = true;
    }

    public UserInterface getGUI(){
        return aGui;
    }

    public Parser getParser(){
        return aParser;
    }

    public RoomRandomizer getRandomizer(){
        return aRandom;
    }

    public <String,Room>HashMap getRooms(){
        return aRooms;
    }
    
    public String getEndString(Player pP){
         if( pP.hasItem("Bag") ) return "Congratulations, you got to ESIEE in time and didn't forget your school bag. You WIN!";
         else return "You forgot your school bag! You lost.";
    }

} // Game
