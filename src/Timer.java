import java.text.DecimalFormat;
/**Cette classe gere tout ce qui est lié au temps*/
public class Timer
{
    private GameEngine aGE;
    private int aTempsDeJeu;
    /**Constructeur*/
    public Timer(final GameEngine pGE)
    {
        this.aGE=pGE;
        this.aTempsDeJeu=5417;//Temps de jeu en secondes (=2heures)
        this.decompte();
    }
    /** Creer un decompte et appelle un afficheur. Le decompte verifie a chaque boucle si gameEngine lui demande de s'arreter*/
    private void decompte()
    {
        double vTimeRepere = System.currentTimeMillis();//temps au lancement du compteur
        while(this.aGE.getEtatTimer()!=0)
        {
            if((int)((System.currentTimeMillis()-vTimeRepere)/1000)>=1)
            {
                vTimeRepere=System.currentTimeMillis();
                if(this.aGE.getEtatTimer()==1)
                {
                    this.aTempsDeJeu--;
                    this.timeEvent();//verifie si un evenement doit se produire
                    if(this.aTempsDeJeu<=5401)
                        this.aGE.getUI().printTime(decompteAffichage(this.aTempsDeJeu));//Affiche le temps restant
                }
            }
        }
    }
    /**Genere un evenement quand l'attribut aTempsDeJeu atteind certains temps*/
    private void timeEvent()
    {
        switch(this.aTempsDeJeu)
        {
            case 5416: this.aGE.start(1); break;
            case 5411: this.aGE.start(2); break;
            case 5410: this.aGE.start(3); break;
            case 5408: this.aGE.start(4); break;
            case 5406: this.aGE.start(5); break;
            case 5402: this.aGE.start(6); break;
            case 1800: this.aGE.updateDialogue(); break;
        }
    }
    /**Retourne une String sous format heures:minutes:secondes du temps passe en parametre*/
    private String decompteAffichage(final int pSecondesLeft)
    {
        int vSecondes=pSecondesLeft;
        int vHeures=0;
        int vMinutes=0;
        while(vSecondes>3600){//Conversion des secondes en heures
            vSecondes=vSecondes-3600;
            vHeures++;}
        while(vSecondes>60){//Conversion des secondes en minutes
            vSecondes=vSecondes-60;
            vMinutes++;}
        if(vSecondes==0)
        {
            if(vMinutes==0){
                if(vHeures==0){//Temps ecoule
                    this.aGE.setEtatTimer(0);
                    this.aGE.printLoose();}
                else{
                    vHeures--;
                    vMinutes=59;
                    vSecondes=59;}}
            else{
                vMinutes--;
                vSecondes=59;}
        }
        else
            vSecondes--;
        DecimalFormat vAf2 = new DecimalFormat("00");//pour afficher 02 au lieux de 2
        return (""+vAf2.format(vHeures)+":"+vAf2.format(vMinutes)+":"+vAf2.format(vSecondes));
    }
}