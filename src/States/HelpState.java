package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Display.Media;
import Game.Entry;
import Game.Game;

public class HelpState extends State{

	Game game ;
	private boolean menu = false;
	private LinkedList<String> str; 
	public HelpState(Game game) {
		super(game);
		this.game = game;
		init();
	}

	@Override
	public void init() {
		str = new LinkedList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("help.txt"));
			String buffer;
			 while ((buffer = br.readLine()) != null) {
				 str.add(buffer);
			 }
			 br.close();
		} catch (Exception e) {
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
		g.drawImage(Media.helpbg, 380, 80, 800, 800, null);
		if(menu)
			g.drawImage(Media.menu2, (int)(game.getWidth()* TopTenState.XSCALE), (int)(game.getHeight()* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		else
			g.drawImage(Media.menu1, (int)(game.getWidth()* TopTenState.XSCALE), (int)(game.getHeight()* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,23));
		g.setColor(Color.white);
		int i = 0;
		for(String string : str)
		{
			g.drawString(string, 470, 200 + i);
			i += 50;
		}
	}

	@Override
	public String Type() {
		return "HelpState";
	}
	public void setMenu(boolean menu)
	{
		this.menu = menu;
	}

}
