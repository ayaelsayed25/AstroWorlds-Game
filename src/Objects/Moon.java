package Objects;

import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;

public class Moon extends Shapes implements Serializable{
	

	public Moon(int number) {
		super(number);
		isShape = new IsOtherShape();

	}
	
	@Override
	public void draw(Graphics g) {
		if(number == 4) {
			g.drawImage( Media.moon1, getX(),getY(), getWidth(), getHeight(), null);

		}
		else if (number == 5) {
			g.drawImage( Media.moon2, getX(),getY(), getWidth(), getHeight(), null);
		}
	}

}