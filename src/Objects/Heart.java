package Objects;

import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;

public class Heart extends Shapes implements Serializable {

	public Heart(int number) {
		super(number);
		isShape = new IsHeart();
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.heart, getX(), getY(), getWidth() + 10 , getHeight() + 10,null);
	}
}
