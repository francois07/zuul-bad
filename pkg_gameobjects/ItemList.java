package pkg_gameobjects;

import java.util.HashMap;
/**
 * The ItemList class.
 */
public class ItemList
{
    private HashMap<String, Item> aList;
    
    public ItemList(){
        this.aList = new HashMap<String, Item>();
    }
    
    public Item getItem(final String pS){
        return this.aList.get(pS.toLowerCase());
    }
    
    public String getItemString(){
        String vS = "";
        for(Item vI : this.aList.values()) vS+= " "+vI.toString();
        if (vS.length() > 0) return vS;
        else return " No items";
    }
    
    public void setItem(final String pS, final Item pI){
        this.aList.put(pS.toLowerCase(), pI);
    }
    
    public void removeItem(final String pS){
        this.aList.remove(pS);
    }
    
}
