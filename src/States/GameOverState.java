package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Stack;

import Display.Media;
import Game.Game;
import Objects.Shapes;
import Objects.Status;

public class GameOverState extends State{

	private Game game;
	private int[] time;
	private int score;
	private BufferedImage gameOverImage;
	private String playerName;
	private boolean menu= false;
	private String isOverFile = "isOver.bin";
	
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
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(isOverFile);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeBoolean(true);
		    oos.close();
		} catch (Exception e1) {
			e1.printStackTrace();
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
		if(menu)
			g.drawImage(Media.menu2, (int)(game.getWidth() * TopTenState.XSCALE), (int)(game.getHeight() * TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		else
			g.drawImage(Media.menu1, (int)(game.getWidth() * TopTenState.XSCALE), (int)(game.getHeight() * TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
	}
	
	public void setMenu(boolean menuB)
	{
		menu = menuB;
	}

	@Override
	public String Type() {
		return "GameOverState";
	}

}