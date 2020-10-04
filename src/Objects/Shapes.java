package Objects;

import java.awt.Graphics;
import java.io.Serializable;

public class Shapes extends Movable implements Cloneable,Serializable{
	public IsShape isShape;
	public int number ;
	int random;
	
	public void setRandom(int random) {
		this.random = random;
	}

	public Shapes(int number) {
		this.number = number;
    }
	
	public void draw(Graphics g)
	{
		
	}
	
	public void refresh()
	{
		int[] arr = {0,-1,1};
		setX(arr[random]+ getX());
		setY(getY() + 3 );
		
	}
	
	public void refreshInStack(Player player)
	{
		setX(player.getX() + xstack);
 		setY(player.getY() + YSTACK + getYPlus());
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  
}