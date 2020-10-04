package Objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import Display.Media;

public class Player extends UserControlled {
	
	private static Player instance;
	public int score = 0;
	private String name;

	
	private Player() {}
	
	public static Player getInstance() {
		if (instance == null)
			instance = new Player();
		instance.setRectangle();
		return instance;
	}
	
	public void setRectangle()
	{
		boundsLeft = new Rectangle(0, 0, getWidth(), getHeight());
		boundsLeft.x = -30 ; boundsLeft.y = 0 ; boundsLeft.width = 80; boundsLeft.height = 200;
		boundsRight = new Rectangle(0, 0, getWidth(), getHeight());
		boundsRight.x = 100  ; boundsRight.y = 0 ; boundsRight.width = 80; boundsRight.height = 200;
	}
	
	public void draw(Graphics g) {
		g.drawImage( Media.astronaut, getX(), getY(), getWidth(), getHeight(), null );

	}
	
	public void refresh() {
		getInput();
	}
	
	private void getInput() {
		if(game.getKeyManager().right) 
		{
			if(!(getX() >= (game.getWidth() - 170)))
					setX(getX() + SPEED);
		}
		else if(game.getKeyManager().left)
		{
			if(getX() > 0)
				setX(getX() - SPEED);	
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
