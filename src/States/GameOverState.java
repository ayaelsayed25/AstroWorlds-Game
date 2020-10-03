package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

import Display.Media;
import Game.Entry;
import Game.Game;
import Objects.Status;

public class GameOverState extends State{

	private Game game;
	private Status statusBarInfo;
	private int[] time;
	private int score;
	private int numOfStars;
	private BufferedImage gameOverImage;
	private String playerName;
	private String Topten;
	private static boolean menuMouse = false;
	
	public GameOverState(Game game, Status status) {
		super(game);
		this.game = game;
		statusBarInfo = status;
		time = status.getTime();
		score = status.getScore();
		gameOverImage = Media.bgOver;
		playerName = ((GameState) game.getgameState()).getPlayerName();
		Topten = "Topten.bin";
		saveScoreAndPlayer();
	}

	private void saveScoreAndPlayer() {
		FileOutputStream fos = null ;
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(Topten);
			ObjectInputStream ois = new ObjectInputStream(fis);
			PriorityQueue<Entry> top10;
			if(ois.readObject( ) == null) {
				fos = new FileOutputStream(Topten);
				top10 = new PriorityQueue<Entry>();
			}else {
				top10 = (PriorityQueue<Entry>) ois.readObject();
				ois.close();
			}
			top10.add(new Entry(Integer.toString( score),playerName));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(top10);
		    oos.close();
		    Entry e = top10.remove();
		    System.out.println(e.key + "  "+e.value);
				
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		Color color = new Color(5, 10, 32);
		g.setColor(color);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		g.drawImage(gameOverImage, 450, 0, 600, 600, null);
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		g.drawString("Time : " + time[0] + " : " + time[1] + " : " + time[2] , 550, 650);
		g.drawString("Score : " + score, 550, 700);
		if(menuMouse)
			g.drawImage(Media.menu2, 20, 750, 130, 50, null);
		else
			g.drawImage(Media.menu1, 20, 750, 130, 50, null);
		}
	
	public static void setMenuMouse(boolean menu)
	{
		menuMouse = menu;
	}
	public static boolean getMenuMouse()
	{
		return menuMouse;
	}

	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return null;
	}

}
