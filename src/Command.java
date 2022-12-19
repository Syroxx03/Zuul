
/**
 * La classe Command gere les commandes entrees par le joueurs. Elle est composee de deux mots pouvants êtres null
 * aCommandWord est le premier mot
 * aSecondWord est le second mot
 */
public class Command
{
    private CommandWord aCommandWord;
    private String aSecondWord;
    /**
     * Initialise les deux attributs aCommandWord aSecondWord
     * pCommandWord  est le premier mot
     * pSecondWord est le second mot
     */
    //#Constructeur naturel
    public Command(final CommandWord pCommandWord, final String pSecondWord)
    {
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    }
    /**
     * return le premier mot d'une command
     */
    //#Accesseur
    public CommandWord getCommandWord() {return this.aCommandWord;}
    /**
     * return le second mot d'une command
     */
    public String getSecondWord() {return this.aSecondWord;}
    /**
     * return true si la command a un second mot
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord!=null;
    }
    /**
     * return true si la command n'as pas de premier mot
     */
    public boolean isUnknown()
    {
        return this.aCommandWord==null;
    }
} 