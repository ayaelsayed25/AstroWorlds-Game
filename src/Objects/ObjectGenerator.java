package Objects;

import java.awt.Graphics;

import Display.Media;

public class ObjectGenerator extends Constant{

	public void draw(Graphics g)
	{
		g.drawImage(Media.ufo, getX(), getY(), getWidth(), getHeight(), null);
	}
}
