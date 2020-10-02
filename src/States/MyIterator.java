package States;

import java.awt.Graphics;
import java.util.Iterator;

import Objects.Player;
import Objects.Shapes;
import Objects.UserControlled;

public class MyIterator {

	public void draw(Iterator iterator, Graphics g)
	{
		while(iterator.hasNext())
		{
			Shapes shape = (Shapes) iterator.next();
			shape.draw(g);
		}
	}
	public void refresh(Iterator iterator, UserControlled player)
	{
		while(iterator.hasNext())
		{
			Shapes shape = (Shapes) iterator.next();
			shape.refreshInStack((Player)player);
		}
	}
}