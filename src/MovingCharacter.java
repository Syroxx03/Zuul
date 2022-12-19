import java.util.Random;
/** Un MovingCharacter est une sorte de Character qui peut en plus se deplacer aleatoirement*/
public class MovingCharacter extends  Character
{
    /**Constructeur d'objets de classe MovingCharacter*/
    public MovingCharacter(final Room pRoom, final String pImageName,final String pDialogue)
    {
        super(pRoom,pImageName,pDialogue);
    }
    /**Deplace  aleatoirement le MovingCharacter dans une des sorties*/
    public void changeRoom()
    {
        Room[] vExit=this.aCurrentRoom.getAllExit();
        int vNumExit=new Random().nextInt(vExit.length);
        if(!vExit[vNumExit].getName().equals("hole"))//Salle interdite au pnj
            this.aCurrentRoom=vExit[vNumExit];
        this.aImageName="charac"+new Random().nextInt(2)+".png";//Image choisit aleatoirement
    }
}