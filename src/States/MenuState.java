package States;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import Display.Media;
import Game.Game;


public class MenuState extends State implements Serializable{

	private Game game;
	private boolean cont,start,help,exit,menu,topten;
	

	
	public MenuState(Game game) {
		super(game);
		this.game = game;
		cont=false;
		start=false;
		help=false;
		exit=false;
		menu=false;
		topten = false;
	}

	@Override
	public void refresh() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ScreenH = (int) d.getHeight();
		int ScreenW = (int) d.getWidth();
		g.drawImage(Media.bgMenu, 0, 0, game.getWidth(), game.getHeight(), null);
	

		if(cont) {
			g.drawImage(Media.Continue2, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 100 , null);
		}
		else
			g.drawImage(Media.Continue,  (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 100 , null);
		
		if(start)
			g.drawImage(Media.new_game2, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 100 , 400 , 100, null);
			
		else
			g.drawImage(Media.new_game, (int)(ScreenW*0.35) ,  (int)(ScreenH * 0.28) + 100 , 400 , 100, null);
		
		if(help)
			g.drawImage(Media.help2, (int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 200 , 200 , 100, null);
		else
			g.drawImage(Media.help, (int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 200 , 200 , 100, null);
			
		if(topten)
			g.drawImage(Media.topten1,(int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 100, null);
		else
			g.drawImage(Media.topten, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 100, null);
	     if(exit)
	 		g.drawImage(Media.exit2,(int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 400 , 200 , 100, null);

		else
			g.drawImage(Media.exit,(int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 400 , 200 , 100, null);
	}

	public boolean isTopten() {
		return topten;
	}

	public void setTopten(boolean topten) {
		this.topten = topten;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void setcont(boolean cont) {
		this.cont=cont;
	}
	public void setstart(boolean start) {
		this.start=start;
	}
	public void sethelp(boolean help) {
		this.help=help;
	}
	public void setexit(boolean exit) {
		this.exit=exit;
	}


	@Override
	public String Type() {
		return "MenuState";
	}
	
}