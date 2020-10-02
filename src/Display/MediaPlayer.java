package Display;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MediaPlayer {

	private Clip clip;
	
	
	public void play(String path, boolean bg)
	{
		try {
			if(bg)
			 close();
			 clip = AudioSystem.getClip();
			 AudioInputStream audioInput = AudioSystem.getAudioInputStream(MediaPlayer.class.getResource(path));
			 clip.open(audioInput);
			 clip.start();
			 if(bg)
				 clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			System.out.println("music is not working");
			((Throwable) e).printStackTrace();
		}
	}
	public void close()
	{
		if(clip != null)
		{
			clip.stop();
			clip.close();
		}
	}
}
