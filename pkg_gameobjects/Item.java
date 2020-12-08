package pkg_gameobjects;

/**
 * The Item class.
 */
public class Item
{
    private String aName;
    private String aDescription;
    private double aWeight;
    
    /**
     * The class' constructor
     * @param pN The name of the Item.
     * @param pD The description of the Item.
     * @param pW The weight of the Item.
     */
    public Item(final String pN, final String pD, final double pW){
        this.aName = pN;
        this.aDescription = pD;
        this.aWeight = pW;
    }
    
    /**
     * @return The Item's name.
     */
    public String getName(){
        return this.aName;
    }
    
    /**
     * @return The Item's description.
     */
    public String getDescription(){
        return this.aDescription;
    }
    
    /**
     * @return The Item's weight.
     */
    public double getWeight(){
        return this.aWeight;
    }
    
    /**
     * @return The Item's name next to its weight in parenthesis.
     */
    @Override
    public String toString(){
        return this.aName + "(" + this.aWeight + "kg)";
    }
}
