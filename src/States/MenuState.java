package States;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import Display.Media;
import Game.Game;


public class MenuState extends State implements Serializable{

	private Game game;
	private boolean cont,start,help,exit,menu,topten;
	public static final double XSCALE = 0.6, YSCALE = 0.18, XLOGO = 0.05 ,YLOGO = 0;
	public static final int WIDTH = 350, HEIGHT = 130, WLOGO = 670, HLOGO = 320;
	private boolean isOver = false;
	private boolean drawContinue = false;

	
	public MenuState(Game game) {
		super(game);
		this.game = game;
		cont=false;
		start=false;
		help=false;
		exit=false;
		menu=false;
		topten = false;
		if((new File("isOver.bin")).exists()) {
		FileInputStream fiss;
		try {
			fiss = new FileInputStream("isOver.bin");
			ObjectInputStream oiss = new ObjectInputStream(fiss);
			isOver = oiss.readBoolean();
			System.out.println(isOver);
			oiss.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		drawContinue = !isOver;
		}
	}

	@Override
	public void refresh() {
	}

	@Override
	public void draw(Graphics g) {
		int ScreenH = game.getHeight();
		int ScreenW = game.getWidth();
		g.drawImage(Media.bgMenu, 0, 0, ScreenW,ScreenH, null);
		g.drawImage(Media.logo,(int)(ScreenW* XLOGO), (int)(YLOGO * ScreenH), WLOGO, HLOGO, null);
		if(drawContinue)
		{
			if(cont) {
				g.drawImage(Media.Continue2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) , WIDTH, HEIGHT , null);
			}
			else
				g.drawImage(Media.Continue,  (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) , WIDTH, HEIGHT , null);
			
		}
		
		if(start)
			g.drawImage(Media.new_game2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 130 , WIDTH , HEIGHT, null);
			
		else
			g.drawImage(Media.new_game, (int)(ScreenW*XSCALE) ,  (int)(ScreenH * YSCALE) + 130 , WIDTH , HEIGHT, null);
		
		if(help)
			g.drawImage(Media.help2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 260 , WIDTH , HEIGHT, null);
		else
			g.drawImage(Media.help, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 260 , WIDTH , HEIGHT, null);
			
		if(topten)
			g.drawImage(Media.topten2,(int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 390 , WIDTH , HEIGHT, null);
		else
			g.drawImage(Media.topten1, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 390 , WIDTH , HEIGHT, null);
	     if(exit)
	 		g.drawImage(Media.exit2,(int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 520 , WIDTH , HEIGHT, null);
		else
			g.drawImage(Media.exit,(int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 520 , WIDTH , HEIGHT, null);
	     
	     
	     //draw Aliens :
	     g.drawImage(Media.alien1, 230, 330, 230, 330, null);
	     
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

	public boolean isOver()
	{
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	
	@Override
	public String Type() {
		return "MenuState";
	}

	public boolean isDrawContinue() {
		return drawContinue;
	}

	public void setDrawContinue(boolean drawContinue) {
		this.drawContinue = drawContinue;
	}

}