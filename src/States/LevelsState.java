
package States;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Display.Media;
import Game.Game;

public class LevelsState extends State {

	Game game;
	Boolean simple ,hard,medium;
	Rectangle rect = new Rectangle(200,200,250,30);
	String text = "";
	public LevelsState(Game game) {
		super(game);
		this.game = game;
		simple = false;
		hard = false;
		medium = false;
		init();
	}
	

	@Override
	public void init() {
		game.getFrame().addKeyListener(new KeyAdapter(){
			  public void keyPressed(KeyEvent e){
			    text+=e.getKeyChar();
			  }
		});
	}

	@Override
	public void refresh() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.bgMenu, 0, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(Media.logo, 0,30 ,700,400, null);
		g.drawImage(Media.name, 300, 150, 200, 200, null);
		//text field : 
		g.setColor(Color.blue);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.white);
		g.drawString(text, rect.x, rect.y+ rect.height);
		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ScreenH = (int) d.getHeight();
		int ScreenW = (int) d.getWidth();
		if(!simple) {
			g.drawImage(Media.Simple1, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 150 , null);
		}else {
			g.drawImage(Media.Simple2, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 150 , null);
		}
		if(!medium) {
			g.drawImage(Media.Medium1, (int)(ScreenW*0.35) ,  (int)(ScreenH * 0.28) + 150 , 400 , 150, null);
		}else {
			g.drawImage(Media.Medium2, (int)(ScreenW*0.35) ,  (int)(ScreenH * 0.28) + 150 , 400 , 150, null);
		}
		if(!hard) {
			g.drawImage(Media.Hard1, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 150, null);
		}else {
			g.drawImage(Media.Hard2, (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 150, null);
		}
		
	}

	public Boolean getSimple() {
		return simple;
	}


	public void setSimple(Boolean simple) {
		this.simple = simple;
	}


	public Boolean getHard() {
		return hard;
	}


	public void setHard(Boolean hard) {
		this.hard = hard;
	}


	public Boolean getMedium() {
		return medium;
	}


	public void setMedium(Boolean medium) {
		this.medium = medium;
	}


	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "LevelsState";
	}
	public String getText()
	{
		return text;
	}

}