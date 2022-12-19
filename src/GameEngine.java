import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;
/**La classe GameEngine est le moteur du jeu, il gere les autres differents types d'objets*/
public class GameEngine
{
    private HashMap<String,Room> aRooms;//Liste des objets Room du jeu
    private Parser aParser;
    private UserInterface aUI;
    private Player aPlayer;
    private MovingCharacter aMovingCharacter;
    private Sounds aSounds;
    private Timer aTimer;
    private int aEtatTimer;//Etat du timer (decompte, en attente ou arrete)
    private String[]aVerif={"map3","map2","room2","room3","exitdoor"};//liste de chose qui n'arrive qu'une fois, puis se suppriment du tableau
    /**Contstructeur par default*/
    public GameEngine()
    {
        this.aSounds=new Sounds();
        this.aEtatTimer=1;//0=s'arrete, 2=jeu en pause, 1=decompte
        this.aRooms =new HashMap<String,Room>();
        this.aParser=new Parser();
        this.createRooms();
        this.aPlayer=new Player(this.aRooms.get("entrance"));
        this.aMovingCharacter=new MovingCharacter(this.aRooms.get("entrance"),"charac1.png","We must hurry to get out, the air seems toxic");
    }
    //#rInitialisation
    //#rInitialisation
    //#rInitialisation
    /**Creer l'interface graphique, creer le timer et affiche le message de bienvenue*/
    public void setUI(final UserInterface pUserInterface)
    {
        this.aUI=pUserInterface;
        this.aUI.showImage("start.png",this.aUI.getBackImage());
        this.aUI.showImage("emptyMap.png",this.aUI.getMapImage());
        this.aUI.println("");
        this.aUI.pauseOn();
        this.aTimer=new Timer(this);
    }
    /**Dialogue de départ*/
    public void start(final int pNbr)
    {
        switch(pNbr)
        {
            case 1: this.aUI.println("Jhon: We finally found it, we are in front of the entrance to the temple!\n"); break;
            case 2: this.aUI.println("Jhon: Come on, I can't wait to see what we'll find out\n"); break;
            case 3: this.aUI.showImage("empty.png",this.aUI.getBackImage()); break;
            case 4: this.updateInfo();
                this.aUI.println("No sooner have you entered the temple than the door of the temple definitively closes behind you\n");
                this.aSounds.doSound("doorClose");  break;
            case 5: this.aUI.println("Jhon: What the hell, we're stuck! We have to find another way out\n"); break;
            case 6: this.printWelcome(); break;
        }
    }
    /**Affiche le message de bienvenue et initialise les elements de depart*/
    private void printWelcome()
    {
        this.aUI.addButton();
        this.aUI.pauseOff();
        this.cmdExpl();
        this.aPlayer.getItemList().getList().put("'map'",new Item("'map'",2));
        this.aUI.showImage("'map'.png",this.aUI.getMapImage());
        this.updateInfo();
        this.printLocationInfo();
    }
    /**Creer les salles du jeu, leurs sorties et items*/
    private void createRooms()
    {
        Room vEntr= new Room(" at the entrance of the temple, there is a chest with beautiful crystals and a 'map'","entrance.png","entrance");
        Room vRoom1 = new Room("in the room one","room1.png","room1");
        Room vRoom2 = new Room("in the room two, there is a skeleton with a  'paper' in his hand","room2.png","room2");
        Room vRoom3 = new Room("in the room three, there are strange drawings on the  wall","room3.png","room3");
        Room vRoom4 = new Room("in the room four","room4.png","room4");
        Room vRoom5 = new Room("in the room five","room5.png","room5");
        Room vExit = new Room("in front of a large sealed door,  it seems to open with a letter code","exit.png","exit");
        Room vOutside= new Room("outside","outside.png","outside");

        vRoom2.getItemList().getList().put("'paper'", new Item("'paper'",2));
        vEntr.getItemList().getList().put("redCrystal", new Item("redCrystal",50));
        vEntr.getItemList().getList().put("blueCrystal", new Item("blueCrystal",50));
        vEntr.getItemList().getList().put("greenCrystal", new Item("greenCrystal",50));
        vEntr.getItemList().getList().put("yellowCrystal", new Item("yellowCrystal",50));
        vEntr.getItemList().getList().put("purpleCrystal", new Item("purpleCrystal",50));

        vEntr.setExits("room1",vRoom1);
        vEntr.setExits("room4",vRoom4);

        vRoom1.setExits("entrance",vEntr);
        vRoom1.setExits("room2",vRoom2);
        vRoom1.setExits("room4",vRoom4);
        vRoom1.setExits("room3",vRoom3);

        vRoom4.setExits("entrance",vEntr);
        vRoom4.setExits("room5",vRoom5);
        vRoom4.setExits("room1",vRoom1);
        vRoom4.setExits("room3",vRoom3);

        vRoom5.setExits("exit",vExit);
        vRoom5.setExits("room4",vRoom4);
        vRoom5.setExits("room2",vRoom2);
        vRoom5.setExits("room3",vRoom3);

        vRoom2.setExits("exit",vExit);
        vRoom2.setExits("room1",vRoom1);
        vRoom2.setExits("room5",vRoom5);
        vRoom2.setExits("room3",vRoom3);

        vRoom3.setExits("room2",vRoom2);
        vRoom3.setExits("room1",vRoom1);
        vRoom3.setExits("room5",vRoom5);
        vRoom3.setExits("room4",vRoom4);

        vExit.setExits("room2",vRoom2);
        vExit.setExits("room5",vRoom5);

        this.aRooms.put("outside", vOutside);
        this.aRooms.put("entrance", vEntr);
        this.aRooms.put("exit", vExit);
        this.aRooms.put("room1", vRoom1);
        this.aRooms.put("room2", vRoom2);
        this.aRooms.put("room3", vRoom3);
        this.aRooms.put("room4", vRoom4);
        this.aRooms.put("room5", vRoom5);
    }
    //#rTimer 
    //#rTimer
    //#rTimer
    /**Accesseur de l'etat du timer (0=Arret, 1=Normal, 2=En pause)*/
    public int getEtatTimer(){return this.aEtatTimer;}
    /**Accesseur de UserInterface(pour que le timer puisse afficher le temps)*/
    public UserInterface getUI(){return this.aUI;}
    /**Modificateur de l'etat du timer*/
    public void setEtatTimer(final int pEtatTimer){this.aEtatTimer=pEtatTimer;}
    /**Met le timer en pause, change l'image et desactive le champs de texte (ou les remets si deja en pause)*/
    public void pause()
    {
        if(this.aEtatTimer==1)
        {
            this.aEtatTimer=2;
            this.aUI.pauseOn();
            this.aUI.showImage("pause.png",this.aUI.getPauseImage());
            this.aSounds.soundOff();
        }
        else if(this.aEtatTimer==2)
        {
            this.aEtatTimer=1;
            this.aUI.pauseOff();
            this.updateInfo();
            this.aSounds.soundOn();
        }
    }
    //#rCommandes
    //#rCommandes
    //#rCommandes
    /**Exectue la commande passee en parametre (apres l'avoir allonge si abregee)*/
    public void interpretCommand(final String pNomCommand)
    {
        this.aUI.println( "\n>" + pNomCommand );
        String vNomCommand=verifAbrev(pNomCommand);
        Command pCommand= this.aParser.getCommand( vNomCommand);
        switch ( pCommand.getCommandWord())
        {
            case BACK : back(); break;
            case TALK : talk(); break;
            case PUT : put(pCommand); break;
            case LOOK : look(pCommand); break;
            case GO : goRoom(pCommand); break;
            case TAKE : take(pCommand); break;
            case SOUND : sound(pCommand); break;
            case INVENTORY : inventory(); break;
            case UNLOCK : unlock(pCommand); break;
            case HELP : printHelp(pCommand); break;
            case NOTE : note(this.aParser.getCommand(pNomCommand)); break;
            case UNKNOWN : this.aSounds.doSound("error");
                this.aUI.println("I don't know what you mean...");
        }
    }
    /**Affiche le dialogue du Character present dans la meme piece que le joueur*/
    private void talk()
    {
        if(this.aPlayer.getCurrentRoom()==this.aMovingCharacter.getCurrentRoom())
        {
            this.aUI.println("Jhon : "+this.aMovingCharacter.getDialogue());
            this.aSounds.doSound("pnj");
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println("There's no one to talk to in this room");
        }
    }
    /**Affiche un message d'aide pour la commande choisit qui est le second mot du parametre pCommand*/
    private void printHelp(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            String vNmCmd=pCommand.getSecondWord();
            switch ( vNmCmd )
            {
                case "back" : this.aUI.println("Use back to return to the previous room"); break;
                case "put" : this.aUI.print("Use put + an item in your inventory to put it in the room,");
                    this.aUI.println(" you can see your items with the command inventory "); break;
                case "unlock" : this.aUI.println("Use unlock + the good password in the exit room to escape"); break;
                case "go" : this.aUI.print("Use go + a direction to go to one of the exits of the room,");
                    this.aUI.println(" you can see your items with the command inventory (*ne=northeast)"); break;
                case "look" : this.aUI.println("Use look to view your current room, its exits and items available inside");
                    this.aUI.println("Use look + an item in your inventory surrounded by '   ' to view it"); break;
                case "take" : this.aUI.println("Use take + an item * in the room to take it, use take all to take all the items in the room");
                    this.aUI.println("You can see items present in the room with the command look (*red=redCrystal)"); break;
                case "inventory" : this.aUI.println("Use inventory to see all your item, their total weight and the maximum bearable weight"); break;
                case "sound" : this.aUI.println("Use 'sound off'/'sound on' to turn off/on the soundtrack"); break;
                case "talk" : this.aUI.println("Use talk to talk to your teammate"); break;
                case "note" : this.aUI.println("Use note + something what you want to memorize to let it display. Use note clear to erase everything"); break;
                default : this.aUI.print("The command "+vNmCmd+" doesn' exist, type help + the name of the command to have details on it, ");
                    this.aUI.println("your command words are:  \n"+aParser.showCommands());
            }
        }
        else
            this.aUI.println("Type help + the name of the command to have details on it, your command words are:  \n"+aParser.showCommands()+"");
    }
    /**Affiche en haut de l'ecran le mot passe en second mot du parametre pCommand ou efface tout si est egal a clear*/
    private void note(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            if(pCommand.getSecondWord().equals("clear"))
            {
                this.aUI.println("All notes have been erased");
                this.aUI.clearNotes();
            }
            else
            {
                this.aUI.println("Note added");
                this.aUI.printlnNotes(pCommand.getSecondWord());
            }
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println("Note what?");
        }
    }
    /**Active/Desactive le son selon le second mot pCommand*/
    private void sound(final Command pCommand)
    {
        if(pCommand.hasSecondWord()&&pCommand.getSecondWord().equals("on"))
        {
            this.aSounds.soundOn();
            this.aUI.println("Soundtrack enabled");
        }
        else if(pCommand.hasSecondWord()&&pCommand.getSecondWord().equals("off"))
        {
            this.aSounds.soundOff();
            this.aUI.println("Soundtrack disabled");
        }
        else
        {
            this.aUI.println("Use 'sound on' or 'sound off'");
            this.aSounds.doSound("error");
        }
    }
    /**Parametre pCommand sans second mot: affiche les sorties, la salle du joueur et ses Item
     * Parametre pCommand avec second mot: affiche a l'ecran l'image si elle existe*/
    private void look(final Command pCommand)
    {
        if (pCommand.hasSecondWord())
        {
            String vScdWord=pCommand.getSecondWord();
            if(vScdWord.equals("'map'")||vScdWord.equals("'paper'")||vScdWord.equals("'map3'")||vScdWord.equals("'map2'"))
            {
                if(this.aPlayer.hasItem(vScdWord))
                {
                    this.aUI.showImage(vScdWord+".png",this.aUI.getMapImage());
                    this.aUI.println("You are studying  "+vScdWord);
                    this.aSounds.doSound("look");
                }
                else
                {
                    this.aSounds.doSound("error");
                    this.aUI.println("You don't have it on you");
                }
            }
            else
            {
                this.aSounds.doSound("error");
                this.aUI.println("I don't know what it is");
            }
        }
        else
            this.aUI.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }
    /**Affiche tout les Items du joueur, leurs poids total et le poid maximum qu'il peut porter*/
    private void inventory()
    {
        this.aUI.println(""+this.aPlayer.getItemString()+"    (Weight: "+this.aPlayer.getTotalWeight()+"/"+ this.aPlayer.getMaxWeight()+")");
    }
    /**Deplace le joueur vers la direction passe en parametre. Si il y a une sortie et met a jour l'image */
    private void goRoom(final Command pCommand)
    {
        Room vPlayerRoom=this.aPlayer.getCurrentRoom();
        if (!pCommand.hasSecondWord())
        {
            this.aSounds.doSound("error");
            this.aUI.println("Go where ?");
        }
        else if(this.aPlayer.goRoom(pCommand.getSecondWord()))
        {
            if(vPlayerRoom!=this.aMovingCharacter.getCurrentRoom())//evite que le character suive le joueur ce qui rend bizzare visuellement 
                this.aMovingCharacter.changeRoom();
            this.printLocationInfo();
            this.updateInfo();
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println("There is no door !");
        }
    }
    /**Renvoie le joueur dans la salle precedente*/
    private void back()
    {
        Room vPlayerRoom=this.aPlayer.getCurrentRoom();
        if(this.aPlayer.back())
        {
            if(vPlayerRoom!=this.aMovingCharacter.getCurrentRoom())
                this.aMovingCharacter.changeRoom();
            this.printLocationInfo();
            this.updateImage();
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println("There is no previous room");
        }
    }
    /**Retire a la Room courante l'Item en parametre et le donne au Player (si l'Item est dans la Room)*/
    private void take(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            String vNameItem=pCommand.getSecondWord();
            if(vNameItem.equals("all"))
            {
                if((this.aPlayer.getTotalWeight()+this.aPlayer.getCurrentRoom().getItemWeight())<=this.aPlayer.getMaxWeight())
                {
                    this.aPlayer.takeall();
                    this.aUI.println("You take all the items in the room in your inventory");;
                    updateInfo();
                    this.aSounds.doSound("take");
                }
                else
                {
                    this.aSounds.doSound("error");
                    this.aUI.println("You can't carry so many items   (Weight: "+this.aPlayer.getTotalWeight()+"/"+ this.aPlayer.getMaxWeight()+")");
                }
            }
            else if(this.aPlayer.getCurrentRoom().hasItem(vNameItem))
            {
                if((this.aPlayer.getTotalWeight()+this.aPlayer.getCurrentRoom().getItemList().getWeight(vNameItem))<=this.aPlayer.getMaxWeight())
                {
                    this.aPlayer.take(vNameItem);
                    this.aUI.println("You take the "+vNameItem+ " in your bag");
                    updateInfo();
                    this.aSounds.doSound("take");
                }
                else
                {
                    this.aSounds.doSound("error");
                    this.aUI.println("You can't carry so many items   (Weight: "+this.aPlayer.getTotalWeight()+"/"+ this.aPlayer.getMaxWeight()+")");
                }
            }
            else
            {
                this.aSounds.doSound("error");
                this.aUI.println( "There is not this object in this room\n" +this.aPlayer.getCurrentRoom().getItemString());
            }
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println( "Take what?");
        }
    }
    /**Retire au Player l'Item en parametre et le place dans la Room courante (si le Player possede l'Item)*/
    private void put(final Command pCommand)
    {
        if(pCommand.hasSecondWord())
        {
            String vNmItm=pCommand.getSecondWord();
            if(this.aPlayer.hasItem(vNmItm))
            {
                //Si la salle est vRoom(1-5),que l'objet place est un crystal, redefinit l'image d'item a afficher
                String vNm= this.aPlayer.getCurrentRoom().getName();
                if((this.isCrystal(vNmItm))&&(vNm=="room1"||vNm=="room2"||vNm=="room3"||vNm=="room4"||vNm=="room5"))
                {
                    //Si un crystal est deja present, le recupere
                    Iterator<String> iterator = this.aPlayer.getCurrentRoom().getItemList().getList().keySet().iterator();
                    while(iterator.hasNext())
                    {
                        String vNameItem = iterator.next();
                        if(this.isCrystal(vNameItem))
                        {
                            this.aPlayer.take(vNameItem);
                            break;
                        }
                    }
                    this.aUI.println("You put the "+vNmItm+ " in the center of the room");
                }
                else
                    this.aUI.println("You put the "+vNmItm+ " in the room");
                this.aSounds.doSound("put");
                this.aPlayer.put(vNmItm);
                this.verifMap();
                this.updateImage();
                this.aPlayer.updateWeight();
            }
            else
            {
                this.aSounds.doSound("error");
                this.aUI.print( "You don't have this object: ");
                inventory();
            }
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println( "Put what?" );
        }
    }
    /**Rajoute une sortie a la Room "vExit" si le second mot dela commande en parametre est egale au mot de passe vPass*/
    private void unlock(final Command pCommand)
    {
        if(this.aPlayer.getCurrentRoom().equals(this.aRooms.get("exit")))
        {
            if (pCommand.hasSecondWord())
            {
                String vPass="terminus";
                if(pCommand.getSecondWord().equals((vPass)))
                {
                    if(this.aVerif[4]!=null)
                    {
                        this.aVerif[4]=null;
                        this.aRooms.get("exit").setExits("outside",aRooms.get("outside"));
                        this.aRooms.get("exit").setImageName("exit2.png");
                        this.aUI.println("The door is open"+this.aPlayer.getCurrentRoom().getExitString());
                        this.aSounds.soundOff();
                        this.aSounds.doSound("stonedoor");
                        updateImage();
                    }
                    else
                        this.aUI.println("The door is already open"+this.aPlayer.getCurrentRoom().getExitString());
                }
                else
                {
                    this.aSounds.doSound("error");
                    this.aUI.println("It's a wrong code");
                }
            }
            else
            {
                this.aSounds.doSound("error");
                this.aUI.println("You need the code");
            }
        }
        else
        {
            this.aSounds.doSound("error");
            this.aUI.println("There is nothing to unlock in this room");
        }
    }
    //#rVerifications:
    //#rVerifications:
    //#rVerifications:
    /**Affiche la description d'une Room*/
    private void printLocationInfo()
    {
        this.aUI.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }
    /**Cette procedure met a jour les informations des Room et du Player*/
    private void updateInfo()
    {
        this.updateImage();
        this.aPlayer.updateWeight();
        this.verifDescription();
        this.verifMap();
        if(this.aPlayer.getCurrentRoom().getName().equals("outside"))
            this.printWin();
    }
    /**Verifie les images a afficher et les affiches*/
    private void updateImage()
    {
        this.clearImage();
        this.aUI.showImage( this.aPlayer.getCurrentRoom().getImageName() , this.aUI.getBackImage());
        if(this.aPlayer.getCurrentRoom().getName().equals("entrance"))
        {
            if(this.aPlayer.getCurrentRoom().hasItem("redCrystal"))
                this.aUI.showImage("redCrystal2.png",this.aUI.getAddRed());
            if(this.aPlayer.getCurrentRoom().hasItem("greenCrystal"))
                this.aUI.showImage("greenCrystal2.png",this.aUI.getAddGreen());
            if(this.aPlayer.getCurrentRoom().hasItem("purpleCrystal"))
                this.aUI.showImage("purpleCrystal2.png",this.aUI.getAddPurple());
            if(this.aPlayer.getCurrentRoom().hasItem("blueCrystal"))
                this.aUI.showImage("blueCrystal2.png",this.aUI.getAddBlue());
            if(this.aPlayer.getCurrentRoom().hasItem("yellowCrystal"))
                this.aUI.showImage("yellowCrystal2.png",this.aUI.getAddYellow());
        }
        else if(!this.aPlayer.getCurrentRoom().getName().equals("exit"))
        {
            if(this.aPlayer.getCurrentRoom().hasItem("redCrystal"))
                this.aUI.showImage("redCrystal.png",this.aUI.getAddRed());
            if(this.aPlayer.getCurrentRoom().hasItem("greenCrystal"))
                this.aUI.showImage("greenCrystal.png",this.aUI.getAddGreen());
            if(this.aPlayer.getCurrentRoom().hasItem("purpleCrystal"))
                this.aUI.showImage("purpleCrystal.png",this.aUI.getAddPurple());
            if(this.aPlayer.getCurrentRoom().hasItem("blueCrystal"))
                this.aUI.showImage("blueCrystal.png",this.aUI.getAddBlue());
            if(this.aPlayer.getCurrentRoom().hasItem("yellowCrystal"))
                this.aUI.showImage("yellowCrystal.png",this.aUI.getAddYellow());
        }
        if(this.aPlayer.getCurrentRoom()==this.aMovingCharacter.getCurrentRoom()&&(!this.aRooms.get("exit").isExit(aRooms.get("outside"))))
            this.aUI.showImage(this.aMovingCharacter.getImageName(),this.aUI.getCharacterImage());
    }
    /**Desaffiche les images affichees*/
    private void clearImage()
    {
        this.aUI.showImage("empty.png",this.aUI.getPauseImage());
        this.aUI.showImage("empty.png",this.aUI.getBackImage());
        this.aUI.showImage("empty.png",this.aUI.getCharacterImage());
        this.aUI.showImage("empty.png",this.aUI.getAddCrystal());
        this.aUI.showImage("empty.png",this.aUI.getAddRed());
        this.aUI.showImage("empty.png",this.aUI.getAddBlue());
        this.aUI.showImage("empty.png",this.aUI.getAddYellow());
        this.aUI.showImage("empty.png",this.aUI.getAddPurple());
        this.aUI.showImage("empty.png",this.aUI.getAddGreen());
        this.aUI.showImage("empty.png",this.aUI.getAddGreen());
    }
    /**Return true si le nom d'item en parametre est un Crystal*/
    private boolean isCrystal(String pNm)
    {
        return pNm.equals("redCrystal")||pNm.equals("blueCrystal")||pNm.equals("greenCrystal")||pNm.equals("purpleCrystal")||pNm.equals("yellowCrystal");
    }
    /**Met a jour le dialogue du MovingCharacter en fonction de l'avancement du joueur a 1h de jeu*/
    public void updateDialogue()
    {
        if(!this.aPlayer.hasItem("'map3'")&&!this.aPlayer.hasItem("'map2'"))
            this.aMovingCharacter.setDialogue("Have you seen those drawings on the wall? Reminds me of a game. It was called ... megamind I think.");
        else if(!this.aPlayer.hasItem("'map3'")||!this.aPlayer.hasItem("'map2'"))
            this.aMovingCharacter.setDialogue("Well done for the enigma. I hope there were no other possible solutions.");
        else
            this.aMovingCharacter.setDialogue("These maps can probably be associated. But how?");
    }
    /**Donne au joueur 'map3' / 'map2' la premiere fois qu'il place correctement les crystaux dans les salles*/
    private void verifMap()
    {
        if(aRooms.get("room1").hasItem("yellowCrystal")&&aRooms.get("room2").hasItem("greenCrystal")&&aRooms.get("room3").hasItem("blueCrystal"))
        {
            if(aRooms.get("room4").hasItem("redCrystal")&&aRooms.get("room5").hasItem("purpleCrystal")&&this.aVerif[1]!=null)
            {
                this.aVerif[1]=null;
                this.aPlayer.getItemList().getList().put("'map3'",new Item("'map3'",2));
                this.aUI.println( "You unlocked a new map  'map3'" );
                this.aPlayer.updateWeight();
            }
        }
        if(aRooms.get("room1").hasItem("purpleCrystal")&&aRooms.get("room2").hasItem("blueCrystal")&aRooms.get("room3").hasItem("greenCrystal"))
        {
            if(aRooms.get("room4").hasItem("redCrystal")&&aRooms.get("room5").hasItem("yellowCrystal")&&this.aVerif[2]!=null)
            {
                this.aVerif[2]=null;
                this.aPlayer.getItemList().getList().put("'map2'",new Item("'map2'",2));
                this.aUI.println( "You unlocked a new map  'map2'" );
                this.aPlayer.updateWeight();
            }
        }
    }
    /**Met a jour la description des pieces si necessaire*/
    private void verifDescription()
    {
        if(this.aVerif[3]!=null&&!this.aRooms.get("room2").hasItem("'paper'"))
        {
            this.aVerif[3]=null;
            this.aRooms.get("room2").setDescription(" in the room two");
        }
    }
    /**Return une String apres avoir rallonger/traduit les mots abreges de la String en parametre*/
    public String verifAbrev(final String pString)
    {
        String vName=pString+" ";
        vName=vName.toLowerCase();

        vName = vName.replace(" 1 "," room1 ");
        vName = vName.replace(" 2 "," room2 ");
        vName = vName.replace(" 3 "," room3 ");
        vName = vName.replace(" 4 "," room4 ");
        vName = vName.replace(" 5 "," room5 ");

        vName = vName.replace(" red "," redCrystal");
        vName = vName.replace(" blue "," blueCrystal");
        vName = vName.replace(" green "," greenCrystal");
        vName=vName.replace(" purple "," purpleCrystal");
        vName=vName.replace(" yellow "," yellowCrystal");

        vName=vName.replace(" map "," 'map'");
        vName=vName.replace(" map3 "," 'map3'");
        vName=vName.replace(" map2 "," 'map2'");
        vName=vName.replace(" paper "," 'paper'");
        return vName;
    }
    /**Affiche une liste de commandes possibles*/
    private void cmdExpl()
    {
        this.aUI.printlnCmdExpl("           Examples of commands:\n");
        this.aUI.printlnCmdExpl("           -Talk");
        this.aUI.printlnCmdExpl("           -Take red");
        this.aUI.printlnCmdExpl("           -Inventory");
        this.aUI.printlnCmdExpl("           -Put red");
        this.aUI.printlnCmdExpl("           -Look");
        this.aUI.printlnCmdExpl("           -Go 1");
        this.aUI.printlnCmdExpl("           -Back");
        this.aUI.printlnCmdExpl("           -Take all");
        this.aUI.printlnCmdExpl("           -Look map");
        this.aUI.printlnCmdExpl("           -Note idea");
        this.aUI.printlnCmdExpl("           -Unlock (+code)");
        this.aUI.printlnCmdExpl("           -Sound off");
        this.aUI.printlnCmdExpl("           -Help");
    }
    /** Affiche Game Over, desactive le champs de texte, arrete le timer*/
    public void printLoose()
    {
        this.aUI.clearCmdExpl();
        this.aUI.clearNotes();
        this.aUI.entreeDisable();
        this.clearImage();
        this.aSounds.soundOff();
        this.aUI.showImage("loose.png",this.aUI.getBackImage());
        this.aUI.print("\n                                                              __________       _________        _____       ______ ");
        this.aUI.print("         _________                     __________        _____                                    ____  _________    ");
        this.aUI.print("        ___________\n                                                            |\\               ____\\|\\         ");
        this.aUI.print("   __      \\|\\           _    \\        _       \\| \\            ___     \\             |\\              __       ");
        this.aUI.print("\\|  \\            \\                          /         /|\\            ___     \\    |\\               __        \\");
        this.aUI.print("\n                                                              \\     \\        \\___|  \\     \\       \\|\\      ");
        this.aUI.print("\\    \\      \\\\   \\__\\   \\      \\      \\          ___/ |            \\    \\         \\|\\       \\       \\ ");
        this.aUI.print("          \\              /          /    |      \\             ___/ | \\   \\            \\|\\         \\\n         ");
        this.aUI.print("                                                         \\     \\        \\      ___\\     \\          __     \\    ");
        this.aUI.print(" \\     \\\\|__|      \\      \\      \\       \\_|/     ___        \\     \\         \\\\\\       \\       \\       ");
        this.aUI.print("     \\/           /      /    \\        \\        \\_|/   __\\    \\         __        __\\\n                       ");
        this.aUI.print("                                               \\     \\        \\|\\         \\      \\     \\     \\     \\      \\");
        this.aUI.print("     \\          \\      \\      \\      \\        \\__| \\     \\          \\     \\         \\\\\\      \\         ");
        this.aUI.print("\\                 /    /                \\         \\         \\_|\\      \\    \\     \\     \\      \\\n          ");
        this.aUI.print("                                                                 \\     \\_________\\      \\__\\     \\__\\      \\_");
        this.aUI.print("_\\          \\      \\__\\      \\__________\\          \\     \\_________\\        \\__/     /                     ");
        this.aUI.print("       \\        \\_________\\     \\__\\    \\__\\                                                                  ");
        this.aUI.print("                \\|_________|   \\|__|   \\|__|   \\|__|               \\|__|    \\|__________|               \\|____");
        this.aUI.print("_____|    \\|__|/                                        \\|__________|  \\|__|  \\|__|\n                            ");
        this.aUI.print("                                                     \\|_________|   \\|__|   \\|__|   \\|__|               \\|__|    \\|");
        this.aUI.print("__________|               \\|_________|    \\|__|/                                        \\|__________|  \\|__|  \\|__|\n");
    }
    /**Affiche You win, desactive le champs de texte, arrete le timer*/
    private void printWin()
    {
        this.aUI.clearCmdExpl();
        this.aUI.clearNotes();
        this.aUI.entreeDisable();
        this.aEtatTimer=0;
        this.aUI.print("                                                                                                           ____     ");
        this.aUI.print("               ___   ________       ___        ___                                      ___                        _");
        this.aUI.print("__         ___        __________\n                                                                                  ");
        this.aUI.print("                      |\\          \\          /        /|\\         __       \\|\\        \\|\\        \\          ");
        this.aUI.print("                      |\\      \\                 |\\        \\|\\        \\|\\            ___        \\\n          ");
        this.aUI.print("                                                                                               \\    \\          \\/");
        this.aUI.print("         /   |    \\       \\|\\        \\    \\        \\\\\\        \\                             \\    \\     \\");
        this.aUI.print("              \\    \\        \\  \\        \\   \\        \\\\    \\        \\\n                                   ");
        this.aUI.print("                                                                            \\    \\             /      /  \\       ");
        this.aUI.print("\\       \\\\\\       \\     \\       \\\\\\         \\                            \\    \\       \\        __\\    ");
        this.aUI.print("\\        \\  \\       \\   \\          \\\\    \\        \\\n                                                      ");
        this.aUI.print("                                                             \\/        /      /             \\       \\        \\\\");
        this.aUI.print("\\      \\    \\        \\\\\\        \\                            \\    \\        \\|\\___\\_\\        \\  \\     ");
        this.aUI.print("   \\    \\        \\\\     \\        \\\n                                                                          ");
        this.aUI.print("                               __/          /     /                         \\     \\_________\\  \\_________\\     ");
        this.aUI.print("                       \\    \\_______________\\  \\___\\   \\___\\\\     \\___\\\n                                 ");
        this.aUI.print("                                                                       |\\___/     /                                ");
        this.aUI.print("    \\|_________|\\|_________|                               \\|_______________|\\|___|\\|____|    \\|___|\n        ");
        this.aUI.print("                                                                                                \\|___|/\n");
    }
}  