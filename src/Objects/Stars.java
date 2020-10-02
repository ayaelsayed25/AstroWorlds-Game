package Objects;

import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;

public class Stars extends Shapes implements Serializable{


	public Stars(int number) {
		super(number);
		isShape = new IsOtherShape();

	}
	
	@Override
	public void draw(Graphics g) {
		if(number == 2) {
			g.drawImage(Media.star1, getX(), getY(), getWidth(), getHeight(), null);
		}
		else if (number == 3) {
			g.drawImage(Media.star2, getX(), getY(), getWidth(), getHeight(), null);
		}
	}

}
