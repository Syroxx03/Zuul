/**Cette classe creer le moteur du jeu GameEngine et l'interface graphique UserInterface et les associes ensembles*/
public class Game
{
    private UserInterface aUI;
    private GameEngine aGE;
    /**Constructeur*/
    public Game()
    {
        this.aGE = new GameEngine();
        this.aUI= new UserInterface(this.aGE);
        this.aGE.setUI( this.aUI );
    }
}