/**
 * Associe a chaque commande valide une liste de String
 */
public enum CommandWord
{
    GO("go"), HELP("help"), INVENTORY("inventory"),TAKE("take"), PUT("put"), BACK("back"), LOOK("look"),
    UNLOCK("unlock"), UNKNOWN("unknown"),  SOUND("sound"), TALK("talk"), NOTE("note");
    private String aCommandString;
    /**
     * Constructeur naturel
     */
    CommandWord(String pCommandString)
    {
        this.aCommandString = pCommandString;
    }
    /**
     * Retourn la command sous forme de String
     */
    public String toString()
    {
        return this.aCommandString;
    }
}