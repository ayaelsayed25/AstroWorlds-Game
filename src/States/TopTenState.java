package States;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.PriorityQueue;

import Display.Media;
import Game.Entry;
import Game.Game;

public class TopTenState extends State {

	private String Topten;
	private Entry[] arr;
	private Game game;
 	public TopTenState(Game game) {
		super(game);
		this.game = game;
		Topten ="Topten.bin";
		arr = new Entry[10];
		init();
	}

	@Override
	public void init() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(Topten);
			ObjectInputStream ois = new ObjectInputStream(fis);
			PriorityQueue result = (PriorityQueue) ois.readObject();
			for(int i=0; i<2; i++)
			{
				
				arr[i] = (Entry) result.remove();
				System.out.println(arr[i].value);
			}
			System.out.println(result.toString());
			ois.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.bgMenu, 0, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(Media.topten4, 100, 100, 400, 600, null);
		for(int i=0; i<2; i++)
		{
			g.setColor(Color.white);
			g.drawString(arr[i].value + "   " + arr[i].key , 100 , 200 * (i + 1));
		}
	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return null;
	}

}
