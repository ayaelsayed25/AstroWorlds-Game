package Objects;

import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;

public class planets extends Shapes implements Serializable{

	
	public planets(int number) {
		super(number);
		isShape = new IsOtherShape();

	}
	
	
	@Override
	public void draw(Graphics g) {
		if(number == 0) {
			g.drawImage(Media.planet1, getX(),getY(), getWidth(), getHeight(), null);
		}
	    else if (number == 1) {
			g.drawImage(Media.planet2, getX(),getY(), getWidth(), getHeight(), null);
	    }
	}

}