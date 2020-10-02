package States;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JLabel;

import Display.Media;
import Game.Game;


public class MenuState extends State implements Serializable{

	private Game game;
	private boolean cont,start,help,exit,menu;
	

	
	public MenuState(Game game) {
		super(game);
		this.game = game;
		cont=false;
		start=false;
		help=false;
		exit=false;
		menu=false;
	}

	@Override
	public void refresh() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.bgMenu, 0, 0, game.getWidth(), game.getHeight(), null);
	
	     if(exit)
	 		g.drawImage(Media.exit2, 500,600, 200,100, null);

		else
		g.drawImage(Media.exit, 500,600, 200,100, null);
		if(cont) {
			g.drawImage(Media.Continue2, 500,300, 200,100, null);
		}
		else
			g.drawImage(Media.Continue, 500,300, 200,100, null);
		
		if(start)
			g.drawImage(Media.new_game2, 500,400, 200,100, null);
			
		else
			g.drawImage(Media.new_game, 500,400, 200,100, null);
		
		if(help)
			g.drawImage(Media.help2, 500,500, 200,100, null);
		else
			g.drawImage(Media.help, 500,500, 200,100, null);
			
			
		
		
		
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
