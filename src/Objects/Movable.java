package Objects;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Movable implements GameObject,Serializable{

	public static final int WIDTH = 40, HEIGHT = 40, YDEFAULT = 50, SPEED = 8;
	private int x , y ;
	private int height = HEIGHT , width = WIDTH;
	public static final int XSTACK1 = 0, XSTACK2 = 125, YSTACK = 30;
	public static final int YPLUSINIT = 30;
	public static final int VALUEH = 50, VALUEHBEFORE = 200, VALUEYBEFORE = 0;
	private int speed = SPEED;
	private boolean isVisible = true;
	private int yPlus = 0;
	public  int xstack;
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
	   return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isVisible() {
		
		return isVisible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public void setYPlus(int yPlus)
	{
		this.yPlus = yPlus;
	}
	
	public int getYPlus()
	{
		return yPlus;
	}

}
