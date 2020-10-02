package Objects;

import java.awt.Graphics;

import Display.Media;

public class Undo extends Shapes{

	public Undo(int number) {
		super(number);
		isShape = new IsOtherShape();
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.Undo, getX(), getY(), getWidth() + 10 , getHeight() + 10,null);
	}

}
