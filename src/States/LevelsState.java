
package States;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Display.Media;
import Game.Game;

public class LevelsState extends State {

	Game game;
	Boolean simple ,hard,medium;
	Rectangle rect = new Rectangle(1000,165,285,40);
	String text = "";
	private boolean menu = false;
	public static final int WIDTH = 400, HEIGHT = 150;
	public static final double XSCALE = 0.35, YSCALE = 0.28;
	private static boolean isMessage = false;
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
				  int key = e.getKeyCode();
				  char ch = e.getKeyChar();
			  if(key == KeyEvent.VK_BACK_SPACE && !(text.equals("")))
			    {
			    	text = text.substring(0, text.length() - 1);
			    }
				if(text.length() < 19)
				{
				    if(key == KeyEvent.VK_SPACE)
				    {
				    	text = text + " ";
				    }
				    else if(Character.isAlphabetic(ch))
				    {
				    	text += ch;
				    }
				    else if(Character.isDigit(ch))
				    {
				    	text += ch;
				    }
				}
			  }
		});
	}

	@Override
	public void refresh() {
	}

	@Override
	public void draw(Graphics g) {
		int ScreenH = (int) game.getHeight();
		int ScreenW = (int) game.getWidth();
		g.drawImage(Media.bgMenu, 0, 0, ScreenW, ScreenH, null);
		g.drawImage(Media.logo,(int)(ScreenW* MenuState.XLOGO), (int)(MenuState.YLOGO * ScreenH), MenuState.WLOGO, MenuState.HLOGO, null);
		//text field : 
		g.drawImage(Media.name, 760, 135, 250, 100, null);
		g.setColor(Color.white);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		g.drawString(text, rect.x + 10, rect.y+ rect.height - 15);
		if(isMessage)
		{
			g.setColor(Color.red);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
			g.drawString("*Enter your name first!", rect.x + 50, rect.y+ rect.height + 30);
		}
		if(menu)
			g.drawImage(Media.menu2, (int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		else
			g.drawImage(Media.menu1, (int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		if(!simple) {
			g.drawImage(Media.Simple1, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) , WIDTH, HEIGHT , null);
		}else {
			g.drawImage(Media.Simple2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) , WIDTH, HEIGHT , null);
		}
		if(!medium) {
			g.drawImage(Media.Medium1, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 150 , WIDTH, HEIGHT , null);
		}else {
			g.drawImage(Media.Medium2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 150 , WIDTH, HEIGHT , null);
		}
		if(!hard) {
			g.drawImage(Media.Hard1, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 300 , WIDTH, HEIGHT , null);
		}else {
			g.drawImage(Media.Hard2, (int)(ScreenW*XSCALE) , (int)(ScreenH * YSCALE) + 300 , WIDTH, HEIGHT , null);
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


	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	
	public static void isMessage(boolean Message)
	{
		isMessage = Message;
	}

}