
package States;

import java.awt.Dimension;
import java.awt.Graphics;

import Display.Media;
import Game.Game;

public class LevelsState extends State{

	Game game;
	Boolean simple ,hard,medium;
	
	public LevelsState(Game game) {
		super(game);
		this.game = game;
		simple = false;
		hard = false;
		medium = false;
	}
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Media.bgMenu, 0, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(Media.logo, 0,30 ,700,400, null);
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

}
