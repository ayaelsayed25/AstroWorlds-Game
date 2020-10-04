package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

import Display.Media;
import Game.Entry;
import Game.Game;

public class TopTenState extends State {

	private String Topten;
	private Entry[] arr;
	private Game game;
	PriorityQueue<Entry> top10;
	private int size ;
	
 	public TopTenState(Game game) {
		super(game);
		this.game = game;
		Topten ="Topten.bin";
		arr = new Entry[10];
		init();
	}

	@Override
	public void init() {
		Scanner s1;
		try {
			s1 = new Scanner (new File("Topten.txt"));
			int n =0;
			while(s1.hasNextLine()) {
				n++;
				s1.nextLine();
			}
			s1.close();
			Scanner s2=new Scanner (new File("Topten.txt"));
			top10 = new PriorityQueue<Entry>( Collections.reverseOrder());
			for(int i = 0 ; i < (n/2) ; i++) {
				top10.add(new Entry(s2.nextLine().toString(),s2.nextLine().toString()));
			}
			size = top10.size();
			s2.close();
			for(int i = 0 ;i < size ;i++) {
				arr[i] = top10.remove();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		for(int i = 0; i < size ; i++)
		{
			if(i > 9)
				break;
			g.setFont(new Font("TimesRoman",Font.ITALIC,40));
			g.setColor(Color.white);
			g.drawString(arr[i].value + "   " + arr[i].key ,100 , 200 * (i + 1));

		}
	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return null;
	}

}