/**
 * Un objet de type Item est un objet.
 * Il a un nom et un poid
 */
public class Item
{
    private int aWeight;
    private String aName;
    /**Constructeur*/
    public Item(final String pName, final int pWeight)
    {
        this.aName=pName;
        this.aWeight=pWeight;
    }
    /**
     *Modificateur du nom de l'item
     */
    public String getName(){return this.aName;}
    /**
     * Modificateur du poid de l'item
     */
    public int getWeight() {return this.aWeight;}
}
