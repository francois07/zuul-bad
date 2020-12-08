package pkg_gameobjects;

/**
 * The TransporterRoom class
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRandom;
    
    public TransporterRoom(final String pDescription, final RoomRandomizer pRandom){
        super(pDescription);
        this.aRandom = pRandom;
    }
    
    public TransporterRoom(final String pDescription, final RoomRandomizer pRandom, final String pImage){
        super(pDescription, pImage);
        this.aRandom = pRandom;
    }
    
    @Override
    public Room getExit(String pDirection){
        return findRandomRoom();
    }
    
    /**
     * @return a random room
     */
    private Room findRandomRoom(){
        return aRandom.getRandomRoom();
    }
}
