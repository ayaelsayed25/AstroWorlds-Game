package Objects;

import java.awt.image.BufferedImage;

public class Constant implements GameObject {

	public static final int WIDTH = 400, HEIGHT = 400, XDEFAULT1 = 170, YDEFAULT1 = 40,XDEFAULT2 = 750, YDEFAULT2 = 40;
	private int x, y;
	private int width = WIDTH, height = HEIGHT;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return null;
	}

}
