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
	public static final double XSCALE = 0.01, YSCALE = 0.8;
	public static final int WIDTH = 350, HEIGHT = 150;
	private boolean menu = false;
	
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
		if(menu)
			g.drawImage(Media.menu2, (int)(game.getWidth() * XSCALE), (int)(game.getHeight() * YSCALE), WIDTH, HEIGHT, null);
		else
			g.drawImage(Media.menu1, (int)(game.getWidth() * XSCALE), (int)(game.getHeight() * YSCALE), WIDTH, HEIGHT, null);
		
		g.drawImage(Media.table, 400, 30, 720, 970, null);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		g.setColor(new Color(1, 28, 109));
		g.drawString("Name              Score" ,575, 300);
		int counter = 0;
		for(int i = 0; i < size ; i++)
		{
			if(i > 9)
				break;
			g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
			g.setColor(Color.white);
			g.drawString(arr[i].value,575 , 350 + counter);
			g.drawString(arr[i].key,900 , 350 + counter);

			counter += 40;
		}

	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "TopTenState";
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

}
