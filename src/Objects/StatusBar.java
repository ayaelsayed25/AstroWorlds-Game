package Objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Display.Media;

public class StatusBar extends Constant implements IObserver {

	Status status;
	BufferedImage battery = Media.battery3;
	int score = 0;
	
	public StatusBar(Status status) {
		this.status = status;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Media.rocket, 0, 0,60,70 ,null);
		g.drawImage(Media.score, 60, 0,80,60, null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman",Font.ITALIC,40));
		g.drawString("" + score, 80 , 70);
		g.drawImage(battery, 1200, 10,120,60, null);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		g.drawImage(Media.Undo , (int)(screenSize.getWidth()*0.9) , (int)(screenSize.getHeight() *0.85) , 70 , 70,null);
		g.drawString("" + status.getUndoes().size(), (int)(screenSize.getWidth()*0.9) - 50 , (int)(screenSize.getHeight() *0.9));

	}
	@Override
	public void update() {
		if(status.getHearts().size() == 3)
			battery = Media.battery3;
		else if(status.getHearts().size() == 2)
			battery = Media.battery2;
		else if(status.getHearts().size() == 1)
			battery = Media.battery1;
		else
			battery = Media.battery0;
		this.score = status.Score;
	}
}
