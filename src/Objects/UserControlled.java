package Objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.Game;

public class UserControlled implements GameObject{
	
	public static final int WIDTH =  170, HEIGHT = 170, XDEFAULT = 600, SPEED = 6;
	public Game game;
	private int x , y;
	private int width = WIDTH, height = HEIGHT;
	public Rectangle boundsLeft;
	public Rectangle boundsRight;
	public static int yDefault;
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		yDefault = (int) (game.getHeight() * 0.7); 

	}

	
	@Override
	public int getX() {
		return (int) x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return (int) y ;
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
		return false;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rectangle getRectangleLeft()
	{
		return new Rectangle(x + boundsLeft.x, y + boundsLeft.y  , boundsLeft.width, boundsLeft.height);
	}

	public Rectangle getRectangleRight()
	{
		return new Rectangle(x + boundsRight.x, y + boundsRight.y  , boundsRight.width, boundsRight.height);
	}
	public void setRectLeftHeight(int valuey)
	{
		boundsLeft.y -= valuey;
		if(boundsLeft.y != Movable.VALUEYBEFORE)
			boundsLeft.height = Movable.VALUEH;
		else			
			boundsLeft.height = Movable.VALUEHBEFORE;
	}
	public void setRectRightHeight(int valuey)
	{
		boundsRight.y -= valuey;
		if(boundsRight.y != Movable.VALUEYBEFORE)
			boundsRight.height = Movable.VALUEH;
		else			
			boundsRight.height = Movable.VALUEHBEFORE;
	}
	public void resetRectRight()
	{
		boundsRight.y = Movable.VALUEYBEFORE;
		boundsRight.height = Movable.VALUEHBEFORE;
	}

	public void resetRectLeft()
	{
		boundsLeft.y = Movable.VALUEYBEFORE;
		boundsLeft.height = Movable.VALUEHBEFORE;
	}
	public Rectangle getRec1(){
		return boundsLeft;
	}
	public void setRec1 (Rectangle rec){
		this.boundsLeft=rec;
	}
	public Rectangle getRec2(){
		return boundsRight;
	}
	public void setRec2 (Rectangle rec){
		this.boundsRight=rec;
	}

}
