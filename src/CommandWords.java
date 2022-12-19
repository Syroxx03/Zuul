import java.util.HashMap;

/**
 * Cette classe indique a la classe Parser si une commande  existe et a quels mots elle
 * est associe. Elle possede une liste de commande valide donnes par la classe CommandWord
 */
public class CommandWords
{
    private HashMap<String,CommandWord> aValidCommands;
    /**
     * Constructeur, initialise les commandes valides
     */
    public CommandWords()
    {
        this.aValidCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                this.aValidCommands.put(command.toString(), command);
            }
        }
    }
    /**
     * Renvoie true si un commandword est associe a la String
     * pNameCommand passe en parametre
     */
    public boolean isCommand( final String pNameCommand )
    {
        return this.aValidCommands.containsKey(pNameCommand);
    }
    /**
     *Cette fonction return un String de la liste des commandes valides
     */
    public String getCommandList()
    {
        String vCommands="";
        for(String vNomCommand : this.aValidCommands.keySet())
        {
            if(!vNomCommand.equals("aller")&&!vNomCommand.equals("aide")&&!vNomCommand.equals("prendre")&&!vNomCommand.equals("placer")&&!vNomCommand.equals("inventaire"))
                vCommands+=(vNomCommand + "  ");
        }
        return vCommands;
    }
    /**
     * return l'objet de type CommandeWord associe a la String
     * pCommandWord en parametre
     */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord vCommand = aValidCommands.get(pCommandWord);
        if(vCommand==null)
            return CommandWord.UNKNOWN;
        return vCommand;
    }
}
