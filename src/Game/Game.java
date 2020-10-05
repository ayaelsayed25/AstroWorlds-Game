package Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.util.List;

import javax.swing.JFrame;

import Display.Display;
import Display.Media;
import Input.KeyManager;
import Input.MouseInput;
import Objects.GameObject;
import States.GameState;
import States.MenuState;
import States.State;

public class Game implements Runnable, World,Serializable{

	private int width;
	private int height;
	private String title;
	int z = 0;
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	private Media media ;
	private KeyManager keyManager;
	private MouseInput MouseInput;
	
	private Thread thread;
	private boolean running = false;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	
	private State gameState;
	private State menuState;
	
	private int controlSpeed;
	private int speed;
	private String status;
	public Game (String title)
	{
		this.title = title;
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		keyManager = new KeyManager();
		MouseInput=new MouseInput(this);

	}
	
	
	public void init()
	{
		display = new Display(width, height, title);
		display.getCanvas().addMouseListener(MouseInput);
		display.getCanvas().addMouseMotionListener(MouseInput);

		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().setFocusable(false);
		
		media = new Media();
		//gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(menuState);
	}
	@Override
	public void run() {
		long start;
		long elapsed;
		long wait;
		init();
		
		while(running)
		{
			start = System.nanoTime();
			refresh(); //update
			draw();   //draw
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			if(z>1000)z=0;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
			}
		}	
		stop();
	}
	public void start()
	{
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop()
	{
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void draw()
	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height); //so that the previous position won't show
		//draw here
		z++;
		if(State.getState().Type() == "GameState"   )
		{
			int speed = ((GameState) State.getState()).getSpeed();
			if(z%speed  == 0) {
				try {
					if(z%1000 == 0) {
						((GameState) State.getState()).generateHeartUndo();
	
					}else {
						int shape1 = GameState.getRandom(0, 7);
						int shape2 = GameState.getRandom(0, 7);
						((GameState) State.getState()).generateShapes(shape1,shape2);
					}
					
				} catch (CloneNotSupportedException e) {}
			}
		}
		if(State.getState() != null)
		{
			State.getState().draw(g);
		}

		//end drawing
		bs.show();
		g.dispose();
	}
	
	@Override
	public boolean refresh() {
		keyManager.tick();
		if(State.getState() != null)
		{
			State.getState().refresh();
		}
		return false;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	public KeyManager getKeyManager()
	{
		return keyManager;
	}

	public State getgameState()
	{
		return gameState;
	}
	public void setgameState(State gameState)
	{
		this.gameState=gameState;
	}
	public State getmenuState()
	{
		return menuState;
	}
	public void setMenuState(State menuState)
	{
		this.menuState = menuState;
	}
	public Canvas getCanvas()
	{
		return display.getCanvas();
	}
	public JFrame getFrame()
	{
		return display.getFrame();
	}



}
