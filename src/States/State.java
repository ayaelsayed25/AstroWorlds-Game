package States;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import Display.Media;
import Display.MediaPlayer;
import Game.Game;

public abstract class State {

	private static State state;
	private static Game game;
	private static Clip bgMusic;
	private static MediaPlayer media = new MediaPlayer();
	
	public State(Game game)
	{
		this.game = game;
	}
	public static State getState()
	{
		return state;
	}
	public static void setState(State s)
	{
		state = s;
		if(state instanceof MenuState || state instanceof LevelsState)
		{
			media.play("/Music/menu.wav", true);
		}
		else if(state instanceof GameState)
		{
			media.play("/Music/game.wav", true);
		}
		else
		{
			media.play("/Music/gameOver.wav", true);
		}
	}
	
	
	public abstract void init();
	public abstract void refresh();
	public abstract void draw(Graphics g);
	public abstract String Type();
}
