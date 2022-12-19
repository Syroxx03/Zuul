import java.util.Stack;
/**Un objet player correspond a un joueur,il possede une liste d'items,un  poid porte, un poid maximun portable,et une salle courante*/
public class Player
{
    private Stack<Room> aLastRoom = new Stack<>();
    private Room aCurrentRoom;
    private ItemList aItemList;
    private int aMaxWeight;
    private int aTotalWeight;
    /**Constructeur*/
    public Player(final Room pRoom)
    {
        this.aCurrentRoom=pRoom;
        this.aItemList=new ItemList();
        this.aMaxWeight=999;
        this.aTotalWeight=0;
    }
    /** Vide la lise des salles precedentes*/
    public void clearLast(){this.aLastRoom.clear();}
    /** Accesseur de la liste d'item du joueur*/
    public ItemList getItemList(){return this.aItemList;}
    /**Accesseur du poid total porte */
    public int getTotalWeight(){return this.aTotalWeight;}
    /**Modificateur du poid total porte*/
    public void setTotalWeight(final int pWeight){this.aTotalWeight=pWeight;}
    /**Modificateur du poid maximum*/
    public void setMaxWeight(final int pWeight){this.aMaxWeight=pWeight;}
    /**Accesseur du poid maximum*/
    public int getMaxWeight(){return this.aMaxWeight;}
    /**Accesseur de la salle courante*/
    public Room getCurrentRoom(){return this.aCurrentRoom;}
    /**Modificateur de la salle courante*/
    public void setCurrentRoom(final Room pRoom){this.aCurrentRoom=pRoom;}
    /**Deplace le joueur dans la salle de la direction passe en parametre pDirection
     * renvoie false si il n'y a pas de sortie dans la direction indiquee*/
    public boolean goRoom(final String pDirection)
    {
        if(this.aCurrentRoom.getExit(pDirection)==null)
            return false;
        this.aLastRoom.push(this.aCurrentRoom);
        this.aCurrentRoom=this.aCurrentRoom.getExit(pDirection);
        //vide le stack de back si pas de retour en arriere possible
        if(!this.aCurrentRoom.isExit(this.aLastRoom.peek()))
            this.aLastRoom.clear();
        return true;
    }
    /**Cette procedure renvoie le Player dans la salle precedante, renvoie false si il n'y a pas de salle precedente*/
    public boolean back()
    {
        if(this.aLastRoom.empty())
            return false;
        this.aCurrentRoom=this.aLastRoom.peek();
        this.aLastRoom.pop();
        return true;
    }
    /**Ajoute l'item associe a la string passe en parametre pNameItem dans la liste d'item du joueur et la retire de la liste d'items de la salle*/
    public void take(final String pNameItem)
    {
        this.aItemList.getList().put(pNameItem,this.aCurrentRoom.getItemList().getItem(pNameItem));
        this.aCurrentRoom.removeItem(pNameItem);
    }
    /**Ajoute tout les items de la liste de la Room courante a la liste du joueur et les retire de la Room*/
    public void takeall()
    {
        this.aCurrentRoom.takeAll(this.aItemList.getList());
    }
    /**Ajoute l'item de nom pNameItem passe en parametre dans la liste d'item de la salle courante et la retire de la liste d'items du joueur*/
    public void put(final String pNameItem)
    {
        this.aCurrentRoom.getItemList().getList().put(pNameItem,this.aItemList.getItem(pNameItem));
        this.aItemList.getList().remove(pNameItem);
    }
    /**Supprime l'item de nom pNameItem passe en parametre de la liste d'item du joueur*/
    public void removeItem(final String pNameItem)
    {
        this.aTotalWeight=this.aTotalWeight-this.aItemList.getItem(pNameItem).getWeight();
        this.aItemList.getList().remove(pNameItem);
    }
    /** Renvoie true si possede un item dans sa hashmap associe a la string passe en parametre*/
    public boolean hasItem(final String pNameItem)
    {
        return this.aItemList.getList().containsKey(pNameItem);
    }
    /**Renvoie une String contenant tout les nom d'items de la hashmap du joueur*/
    public String getItemString()
    {
        return this.aItemList.getItemString();
    }
    /**Cette procedure met a jour le poid total du joueur*/
    public void updateWeight()
    {
        setTotalWeight(this.aItemList.getItemWeight());
    }
}
