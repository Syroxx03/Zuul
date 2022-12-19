import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
/**Liste d'items dans une hashmap associe a un String*/
public class ItemList
{
    private HashMap<String,Item> aItem;
    /**Constructeur*/
    public ItemList()
    {
        this.aItem= new HashMap<String,Item>();
    }
    /**Accesseur de la Hashmap*/
    public HashMap getList() {return this.aItem;}
    /**Renvoie l'item associe a la String passe en parametre*/
    public Item getItem(final String pNameItem){return this.aItem.get(pNameItem);}
    /**Renvoie une String contenant tout les noms des items dans la hashmap */
    public String getItemString()
    {
        String vPhrase =" Items:";
        Set <String> keys =this.aItem.keySet();
        for(String name: keys)
        {
            vPhrase += "   "+ name;
        }
        return vPhrase;
    }
    /**Renvoie le un entier correspondant au poid de tout les items dans la hashmap*/
    public int getItemWeight()
    {
        int vWeight =0;
        Set <String> keys =this.aItem.keySet();
        for(String vNmItm: keys)
        {
            vWeight+=this.aItem.get(vNmItm).getWeight();
        }
        return vWeight;
    }
    /**Renvoi le poid de l'item associe au String passe en parametre*/
    public int getWeight(final String pNmItm)
    {
        return this.aItem.get(pNmItm).getWeight();
    }
    /**Renvoie true si la hashmap contient un item associe a la String passe en parametre*/
    public boolean hasItem(final String pNameItem)
    {
        return this.aItem.containsKey(pNameItem);
    }
    /**Renvoie true si la Hashmap est vide*/
    public boolean isEmpty()
    {
        return this.aItem.isEmpty();
    }
}
