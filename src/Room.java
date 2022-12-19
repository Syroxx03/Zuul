import java.util.HashMap;
import java.util.Set;
/**Un objet de type Room est un lieu.Il a un nom, une liste d'items et des sorties qui le relie aux autres Room. Une image de fond*/
public class Room
{
    protected String aDescription;
    protected ItemList aItemList;
    protected HashMap<String,Room> aExits;
    protected String aImageName;
    protected String aName;
    /**
     * Constructeur naturel, initialise une Room
     */
    public Room(final String pDescription, final String pImage,final String pName)
    {
        this.aDescription=pDescription;
        this.aExits = new HashMap<String,Room>();
        this.aImageName=pImage;
        this.aItemList= new ItemList();
        this.aName=pName;
    }
    /** Accesseur de la liste d'item de la Room*/
    public ItemList getItemList(){return this.aItemList;}
    /**Accesseur du nom de la Room*/
    public String getName(){return this.aName;}
    /**Modificateur du nom de la Room*/
    public void setImageName(final String pImage){this.aImageName=pImage;}
    /**Cette fonction rajoute dans la liste en parametre(inventaire du Player)tout les items presents dans la Room puis retourne la liste du joueur*/
    public HashMap takeAll(HashMap pPlayerList)
    {
        Set <String> keys =this.aItemList.getList().keySet();
        for(String vNameItem: keys)
        {
            pPlayerList.put(vNameItem,this.aItemList.getItem(vNameItem));
        }
        this.aItemList.getList().clear();
        return pPlayerList;
    }
    /**Return une phrase avec la description de la room, ses items si il y en a ainsi que ses sorties*/
    public String getLongDescription()
    {
        String vExits=getExitString();
        String vItems=this.aItemList.getItemString();
        if(vItems.equals(" Items:"))
            vItems="";
        else
            vItems="\n"+vItems;
        if(vExits.equals("\nExits:"))
            vExits="";
        return ("You are "+  this.aDescription+vItems+vExits);
    }
    /**Initialise la sortie d'une Room dans une direction donnee en parametre pDirection vers une autre Room pNeighbor*/
    public void setExits(final String pDirection,final Room pNeighbor)
    {
        this.aExits.put(pDirection,pNeighbor);
    }
    /**Remplace l'attribut description de la Room par la String ne parametre pDescription*/
    public void setDescription(final String pDescription){this.aDescription=pDescription;}
    /**Renvoie true si la room contient dans sa Hashmap la String passe en parametre pNameItem sinon renvoie false*/
    public boolean hasItem(final String pNameItem)
    {
        return this.aItemList.getList().containsKey(pNameItem);
    }
    /**Renvoie true si la Hashmap de la Room est vide sinon renvoie false*/
    public boolean isEmpty()
    {
        return this.aItemList.isEmpty();
    }
    /**Supprime de la Hashmap de la Room l'Item associe a la string pNameItem en parametre*/
    public void removeItem(final String pNameItem)
    {
        this.aItemList.getList().remove(pNameItem);
    }
    /**Return la Room en sortie de cette Room dans une direction donnee en parametre pDirection*/
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }
    /**Return une chaine de caracteres contenant les differentes sorties d'une Room*/
    public String getExitString ()
    {
        String vPhrase ="\nExits:";
        Set <String> vKeys =this.aExits.keySet();
        for(String vNameExit : vKeys)
        {
            vPhrase += "   "+ vNameExit;
        }
        return vPhrase;
    }
    /**Return un tableau contenant toutes les sorties le cette room*/
    public Room[] getAllExit()
    {
        Room[] vRoom=new Room[this.aExits.size()];
        int i=0;
        Set <String> vKeys =this.aExits.keySet();
        for(String vNameExit : vKeys)
        {
            vRoom[i]=this.aExits.get(vNameExit);
            i++;
        }
        return vRoom;
    }
    /**Return la listes des nom d'items presents dans la Hashmap de la Room*/
    public String getItemString()
    {
        return this.aItemList.getItemString();
    }
    /**Return le poid total des tout les items dans la Hashmap de la Room*/
    public int getItemWeight()
    {
        return this.aItemList.getItemWeight();
    }
    /**Return le nom de l'image de la Room*/
    public String getImageName()
    {
        return this.aImageName;
    }
    /**Renvoie true si la Room passee en parametre est une sortie de la salle courante*/
    public boolean isExit(final Room pRoom)
    {
        return(this.aExits.containsValue(pRoom));
    }
} 