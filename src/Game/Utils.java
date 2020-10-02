package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Utils {

	static BufferedImage image;
	static Clip clip;
	public static AudioInputStream audioInput;
	public static BufferedImage loadImage(String path)
	{
		try {
			image = ImageIO.read(Utils.class.getResource(path));
			return image;
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}
		return null;
	}
	
	public static Clip loadAudio(String path)
	{
		try {
			 audioInput = AudioSystem.getAudioInputStream(Utils.class.getResource(path));
			 clip = AudioSystem.getClip();
			 return clip;
		}catch(Exception e)
		{
			System.out.println("music is not working");
			e.printStackTrace();
		}
		return null;
	}
	
}
