/**Un Character est un personnage non joueur, il a une salle courante, une image et une phrase de dialogue*/
public class Character
{
    protected Room aCurrentRoom;
    protected String aImageName;
    protected String aDialogue;
    /**Constructeur d'objets de classe Character */
    public Character(final Room pRoom, final String pImageName,final String pDialogue)
    {
        this.aImageName=pImageName;
        this.aCurrentRoom=pRoom;
        this.aDialogue=pDialogue;
    }
    /**Modificateur de la salle courante*/
    public void setCurrentRoom(final Room pRoom){this.aCurrentRoom=pRoom;}
    /**Accesseur de la salle courante*/
    public Room getCurrentRoom(){return this.aCurrentRoom;}
    /**Modificateur de l'image*/
    public void setImageName(final String pImageName){this.aImageName=pImageName;}
    /**Accesseur de l'image*/
    public String getImageName(){return this.aImageName;}
    /**Modificateur du dialogue*/
    public void setDialogue(final String pDialogue){this.aDialogue=pDialogue;}
    /**Accesseur du dialogue*/
    public String getDialogue(){return this.aDialogue;}
}
