package pkg_gameobjects;

public class Beamer extends Item
{
    private boolean aCharged;
    private Room aTargetRoom;
    
    public Beamer(final String pN, final String pD, final double pW){
        super(pN,pD,pW);
    }
    
    public boolean canFire(){
        return aCharged;
    }
  
    public Room getTargetRoom(){
        return this.aTargetRoom;
    }
    
    public String charge(final Room pR){
        if( !aCharged ){
            this.aCharged = true;
            this.aTargetRoom = pR;
            return "You charged the beamer! Fire it anywhere to come back to this room.";
        } else return "The beamer is already charged.";
    }

    public String fire(){
        if( aCharged ){
            this.aCharged = false;
            return "You fired the beamer! Teleporting..." + "\n" + aTargetRoom.getLongDescription();
        } else {
            return "You need to charge the beamer first!";
        }
    }
}
