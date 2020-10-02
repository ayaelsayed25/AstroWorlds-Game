package Objects;

import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;

public class Alien extends Shapes implements Serializable {

	public Alien(int number) {
		super(number);
		isShape = new IsAlien();
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.alien, getX(), getY(), getWidth() + 10, getHeight() + 10,null);
	}

}
