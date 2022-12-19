
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
/** Cette classe gere tout les sons du jeu*/
public class Sounds
{
    private Clip aClip;
    /** Constructeur*/
    public Sounds()
    {
        soundOn();
    }
    /**Charge le fichier audio de nom du parametre pFile et lance la lecture*/
    public void doSound(final String pFile)
    {
        try
        {
            AudioInputStream vSoundtrack = AudioSystem.getAudioInputStream(new File("Sounds/"+pFile+".wav"));//Flux de donnees audio
            Clip vClip=AudioSystem.getClip();//AudioSystem=acces au melangeur,
            vClip.open(vSoundtrack);//Clip=charge donnees audio avant lecture
            vClip.start();//Lecture du clip
        }
        catch( LineUnavailableException | UnsupportedAudioFileException  | IOException  e) {e.printStackTrace();}
    }
    /** Lance la musique de fond et la fait tourner en boucle*/
    public void soundOn()
    {
        try
        {
            AudioInputStream vSoundtrack = AudioSystem.getAudioInputStream(new File("Sounds/soundtrack.wav"));//Flux de donnees audio
            this.aClip=AudioSystem.getClip();//AudioSystem=acces au melangeur,
            this.aClip.open(vSoundtrack);//Clip=charge donnees audio avant lecture
            this.aClip.start();//Lecture du clip
            this.aClip.loop(this.aClip.LOOP_CONTINUOUSLY);//lecteur en boucle
        }
        catch( LineUnavailableException | UnsupportedAudioFileException  | IOException  e) {e.printStackTrace();}
    }
    /**Arrete la musique de fond */
    public void soundOff()
    {
        this.aClip.stop();
    }
}