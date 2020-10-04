package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Display.Media;
import Game.Game;
import Objects.Status;

public class GameOverState extends State{

	private Game game;
	private int[] time;
	private int score;
	private BufferedImage gameOverImage;
	private String playerName;
	private static boolean menuMouse = false;
	
	public GameOverState(Game game, Status status) {
		super(game);
		this.game = game;
		time = status.getTime();
		score = status.getScore();
		gameOverImage = Media.bgOver;
		playerName = ((GameState) game.getgameState()).getPlayerName();
		try {
			saveScoreAndPlayer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveScoreAndPlayer() throws IOException {
		File f = new File("Topten.txt");
		f.createNewFile();
		FileWriter fw = new FileWriter(f,true);
		fw.write(Integer.toString(score)); 
		fw.write(String.format("%n"));
		fw.write(playerName); 
		fw.write(String.format("%n"));
		fw.close();
		
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
		return "GameOverState";
	}

}