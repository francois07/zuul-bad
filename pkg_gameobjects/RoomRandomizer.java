package pkg_gameobjects;

import java.util.HashMap;
import java.util.Random;
import java.util.Collection;

/**
 * The RoomRandomizer class
 */

public class RoomRandomizer
{ 
    private HashMap<String, Room> aRooms;
    private Object[] aRoomsArray;
    private boolean aFakeRandomizer;

    public RoomRandomizer(){
        this.aFakeRandomizer = false;
    }

    /**
     * @return A random room.
     */
    public Room getRandomRoom(){
        Random vR = new Random();
        int vRandomInt = vR.nextInt(this.aRoomsArray.length);
        return (Room)aRoomsArray[vRandomInt];
    }

    /**
     * Sets a room as the only random room.
     *
     * @param pR A room
     */
    public void setFakeRandom(final Room pR){
        this.aRoomsArray = new Object[] {pR};
    }
    
    /**
     * Sets the HashMap of rooms that will be used.
     *
     * @param pRooms An HashMap of rooms
     */
    public void setRandom( final HashMap<String, Room> pRooms ){
        this.aRooms = pRooms;
        this.aRoomsArray = this.aRooms.values().toArray();
    }

    /**
     * @return Wheter or not the RoomRandomizer is not truly random.
     */
    public boolean isFakeRandom(){
        return this.aFakeRandomizer;
    }
}
