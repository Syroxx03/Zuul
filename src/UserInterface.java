import javax.swing.*;
import java.awt.GraphicsDevice;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**Interface graphique du jeu, elle possede un champs d'entree de texte, des champs de sortie de texte un bouton, plusieurs images, la taille de l'ecran*/
public class UserInterface implements ActionListener
{
    private GameEngine aGE;
    private JPanel aPanel;
    private JFrame aFenetre;
    private JTextField aEntree;
    private JTextArea aSortie;
    private JTextArea aTimeLeft;//Affiche le temps restant
    private JTextArea aNotes;//Affiche les notes prises
    private JTextArea aCmdExpl;//Exemples de commandes autorisees
    private JButton aPauseButton;//Bouton pause
    //Images:
    private JLabel aBackGroundImage;//image de fond
    private JLabel aMapImage;//image des cartes/photos
    //Ce sont les images d'items qui son superposes a aImage
    private JLabel aCharacterImage;
    private JLabel aPauseImage;
    private JLabel aAddCrystal;//Dans les rooms 1-5
    private JLabel aAddRed;//Dans la salle  entrance
    private JLabel aAddGreen;//Dans la salle entrance
    private JLabel aAddPurple;//Dans la salle entrance
    private JLabel aAddBlue;//Dans la salle entrance
    private JLabel aAddYellow;//Dans la salle entrance
    //Pour adapter le jeu a la taille de l'ecran
    private int aScreenWidth;
    private int aScreenHeight;
    GraphicsDevice aDevice;
    /**Constructeur qui initialise l'attribut de type game engine*/
    public UserInterface(final GameEngine pGE  )
    {
        this.aGE=pGE;
        this.createGUI();
    }
    /**Accesseur de aCharacterImage */
    public JLabel getCharacterImage(){return this.aCharacterImage;}
    /**Accesseur de aBackgroundImage*/
    public JLabel getBackImage(){return this.aBackGroundImage;}
    /**Accesseur de aBackgroundImage*/
    public JLabel getPauseImage(){return this.aPauseImage;}
    /**Accesseur de aImage*/
    public JLabel getMapImage(){return this.aMapImage;}
    /**Accesseur de aAddImage*/
    public JLabel getAddCrystal(){return this.aAddCrystal;}
    /** Accesseur de aAddRed*/
    public JLabel getAddRed(){return this.aAddRed;}
    /** Accesseur de aAddGreen*/
    public JLabel getAddGreen(){return this.aAddGreen;}
    /**Accesseur de aAddPurple*/
    public JLabel getAddPurple(){return this.aAddPurple;}
    /**Accesseur de aAddBlue*/
    public JLabel getAddBlue(){return this.aAddBlue;}
    /**Accesseur de aAddYellow*/
    public JLabel getAddYellow(){return this.aAddYellow;}
    /**Cette procedure creer les differents composants de l'interface graphique*/
    private void createGUI()
    {
        Dimension vScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.aScreenWidth = (int)vScreenSize.getWidth();
        this.aScreenHeight = (int)vScreenSize.getHeight();

        this.aPanel = new JPanel();
        this.aFenetre = new JFrame( "Colors" );
        this.aEntree = new JTextField( 34 );
        this.aSortie = new JTextArea();
        this.aTimeLeft = new JTextArea();
        this.aNotes = new JTextArea();
        this.aCmdExpl = new JTextArea();
        this.aPauseButton= new JButton("Pause");

        this.aMapImage= new JLabel();
        this.aCharacterImage = new JLabel();
        this.aAddCrystal = new JLabel();
        this.aAddRed = new JLabel();
        this.aAddGreen = new JLabel();
        this.aAddPurple = new JLabel();
        this.aAddBlue = new JLabel();
        this.aAddYellow = new JLabel();
        this.aPauseImage = new JLabel();
        this.aBackGroundImage = new JLabel("",JLabel.CENTER);

        this.aPauseButton.setBackground(Color.black);
        this.aPauseButton.setForeground(Color.orange);
        this.aPauseButton.setFont(new Font("Papyrus", Font.BOLD, 15));
        this.aPauseButton.addActionListener(this);

        this.aEntree.addActionListener(this);

        this.aSortie.setBackground(Color.black);
        this.aSortie.setForeground(Color.orange);
        this.aSortie.setFont(new Font("Papyrus", Font.BOLD, 15));
        this.aSortie.setEditable( false );

        this.aTimeLeft.setForeground(Color.orange);
        this.aTimeLeft.setFont(new Font("Papyrus", Font.BOLD, 30));
        this.aTimeLeft.setBounds((int)((this.aScreenWidth-900)/8),0,200,100);
        this.aTimeLeft.setOpaque(false);
        this.aTimeLeft.setEditable( false );

        this.aNotes.setForeground(Color.orange);
        this.aNotes.setFont(new Font("Papyrus", Font.BOLD,20));
        this.aNotes.setBounds((int)(11),50,300,550);
        this.aNotes.setOpaque(false);
        this.aNotes.setEditable( false );

        this.aCmdExpl.setForeground(Color.orange);
        this.aCmdExpl.setFont(new Font("Papyrus", Font.BOLD, 20));
        this.aCmdExpl.setBounds((int)((this.aScreenWidth)-((this.aScreenWidth)-900)/2),50,400,550);
        this.aCmdExpl.setOpaque(false);
        this.aCmdExpl.setEditable( false );

        JScrollPane vListScroller=new JScrollPane(this.aSortie);
        vListScroller.setPreferredSize(new Dimension(5, 200) );
        vListScroller.setMinimumSize(new Dimension(5,50) );

        this.aPanel.setLayout( new BorderLayout() );
        this.aPanel.setBackground(Color.black);
        this.aPanel.setOpaque(true);

        this.aCharacterImage.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aPauseImage.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddCrystal.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddRed.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddGreen.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddPurple.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddBlue.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aAddYellow.setBounds((int)((this.aScreenWidth-900)/2),0,900,600);
        this.aPanel.add(this.aPauseImage);
        this.aPanel.add(this.aCharacterImage);
        this.aPanel.add(this.aAddCrystal);
        this.aPanel.add(this.aAddRed);
        this.aPanel.add(this.aAddGreen);
        this.aPanel.add(this.aAddPurple);
        this.aPanel.add(this.aAddBlue);
        this.aPanel.add(this.aAddYellow);
        this.aPanel.add(this.aTimeLeft);
        this.aPanel.add(this.aNotes);
        this.aPanel.add(this.aCmdExpl);
        this.aPanel.add(this.aBackGroundImage,BorderLayout.NORTH);
        this.aPanel.add(this.aMapImage,BorderLayout.WEST);
        this.aPanel.add(vListScroller, BorderLayout.CENTER);
        this.aPanel.add(this.aEntree,BorderLayout.SOUTH);

        this.aFenetre.setBounds(-7,-7,this.aScreenWidth+15, this.aScreenHeight+15);
        this.aFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.aFenetre.setBackground(Color.black);
        this.aFenetre.getContentPane().add(this.aPanel, BorderLayout.CENTER);
        this.aFenetre.setVisible( true );
        this.aFenetre.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );
    }
    /**Definit l'action effectue selon le composant a l'origine de l'evenement(aPauseButton= pause, aEntree=execute le texte dans aEntree)*/
    public void actionPerformed( final ActionEvent pAction )
    {
        Object vSource =pAction.getSource();
        if (vSource==this.aPauseButton)
            this.aGE.pause();
        if (vSource==this.aEntree&&!this.aEntree.getText().equals(""))
            this.processCommand();
    }
    /**Affiche la String en parametre sur le composant aSortie*/
    public void printTime( final String pText )
    {
        this.aTimeLeft.setText(null);
        this.aTimeLeft.append(pText );
        this.aTimeLeft.setCaretPosition( this.aTimeLeft.getDocument().getLength());
    }
    /**Affiche l'image au nord correspondant au nom en parametre sur le composant en parametre*/
    public void showImage(final String pImageName, final JLabel pJLabel)
    {
        String vImagePath = "Images/"+pImageName;
        String vImageURL =  vImagePath ;
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else
        {
            ImageIcon vIcon = new ImageIcon( vImageURL);
            pJLabel.setIcon( vIcon );
        }
    }
    /**Execute la procedure interpretCommand de GE avec en parametre le text dans aEntree*/
    private void processCommand()
    {
        String vInput = this.aEntree.getText();
        this.aEntree.setText( "" );
        this.aGE.interpretCommand(vInput);
    }
    /**Affiche la String en parametre sur le composant aSortie a l'aide de la procedure print() et saute une ligne*/
    public void println(final String pText )
    {
        this.print( pText + "\n" );
    }
    /**Affiche la String en parametre sur le composant aSortie*/
    public void print( final String pText )
    {
        this.aSortie.append(pText );
        this.aSortie.setCaretPosition( this.aSortie.getDocument().getLength());
    }
    /**Affiche la String en parametre sur la sortie aNotes*/
    public void printlnNotes(final String pText)
    {
        this.aNotes.insert("-"+pText+"\n",0);
        this.aNotes.setCaretPosition( this.aNotes.getDocument().getLength());
    }
    /**Affiche la String en parametre sur la sortie aNotes*/
    public void printlnCmdExpl(final String pText)
    {
        this.aCmdExpl.append(pText+"\n");
        this.aCmdExpl.setCaretPosition( this.aNotes.getDocument().getLength());
    }
    /**Supprime le contenue de aNotes*/
    public void clearCmdExpl()
    {
        this.aCmdExpl.setText(null);
    }
    /**Supprime le contenue de aNotes*/
    public void clearNotes()
    {
        this.aNotes.setText(null);
    }
    /**Execute la String passee en parametre*/
    public void test(final String pLigne)
    {
        this.aEntree.setText( ""+pLigne );
        this.processCommand();
    }
    /**Desactive le champs de texte ,le bouton et l'image secondaire*/
    public void entreeDisable( )
    {
        this.aEntree.setEditable( false );
        this.aEntree.getCaret().setBlinkRate( 0 );
        this.aEntree.removeActionListener( this );
        this.aMapImage.setIcon( null );
        this.aPanel.remove(this.aPauseButton);
    }
    /**Desactive le champs de texte*/
    public void pauseOn()
    {
        this.aEntree.setEditable( false );
        this.aEntree.getCaret().setBlinkRate( 0 );
        this.aEntree.removeActionListener( this );
    }
    /**Reactive le champs de texte*/
    public void pauseOff()
    {
        this.aEntree.setEditable( true );
        this.aEntree.getCaret().setBlinkRate( 1 );
        this.aEntree.addActionListener(this);
    }
    public void addButton()
    {
        this.aPanel.add(this.aPauseButton,BorderLayout.EAST);
    }
}