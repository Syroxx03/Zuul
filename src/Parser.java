import java.util.StringTokenizer;

/**
 * Cette classe lie ce que le joueur entre dans le champs de texte de UserInterface 
 * et creer une commande  a l'aide des mots associe dans CommandWord
 *
 */
public class Parser
{
    private CommandWords aValidCommands;
    /**
     * Constructeur par defaut 
     */
    public Parser()
    {
        this.aValidCommands = new CommandWords();
    }
    /**
     * Retourne une commande a partir de la String pEntree en parametre
     */
    public Command getCommand(final String pEntree)
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pEntree );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();
        else
            vWord1 = null;
        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();
        else
            vWord2 = null;
        return new Command( this.aValidCommands.getCommandWord(vWord1), vWord2 );
    }
    /**
     * return la liste des commandes valides 
     */
    public String showCommands()
    {
        return aValidCommands.getCommandList();
    }
} 