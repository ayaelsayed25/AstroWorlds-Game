package Input;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import Display.Media;
import Game.Game;
import Momento.CareTaker;
import Objects.Shapes;
import Objects.Stopwatch;
import States.GameOverState;
import States.GameState;
import States.HelpState;
import States.LevelsState;
import States.MenuState;
import States.State;
import States.TopTenState;
import States.levelfactory;

public class MouseInput implements MouseListener,Serializable,MouseMotionListener {

	private Game game;
	private String StackFile;
	private String recFile;
	private String PlayerFile,HeartsFile,TimeFile;
	private boolean cont;
	private String playerName,undo;

	public MouseInput(Game game) {
		this.game =game;

		StackFile= "stack.bin";
		recFile= "recFile.bin";
		PlayerFile="player.bin";
		HeartsFile="hearts.bin";
		TimeFile="time.bin";
		playerName = "name.bin";
		undo="undo.bin";
		cont=false;
	}


	public void mouseClicked(MouseEvent e) {

	}


	public void mousePressed(MouseEvent e) {


		Point point = new Point(e.getX(),e.getY());
		int ScreenH = game.getHeight();
		int ScreenW = game.getWidth();
		
		if(State.getState().Type() == "GameState")
		{
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			Rectangle undo = new Rectangle((int)(ScreenW *0.9), (int)(ScreenH*0.85),(int)(ScreenW *0.9)+70, (int)(ScreenH*0.85)+70);
			
			if(menu.contains(point))
			{
				cont=true;
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("isOver.bin");
				    ObjectOutputStream oos = new ObjectOutputStream(fos);
				    oos.writeBoolean(false);
				    oos.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				((MenuState)game.getmenuState()).setDrawContinue(true);
				((MenuState)game.getmenuState()).setOver(false);
				State.setState(game.getmenuState());
			}
			
			if(undo.contains(point)) {
			if(((GameState) game.getgameState()).getstatus().getUndoNumber()>0) {
					
					((GameState) game.getgameState()).setUndo(true);
					((GameState) game.getgameState()).getstatus().decreaseUndoes();
			
			}	
				
				
			}
		}
		
		if(State.getState().Type() == "GameOverState")
		{
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			if(menu.contains(point))
			{
				State menuState = new MenuState(game);
				game.setMenuState(menuState);
				State.setState(menuState);
				
			}
		}
		if(State.getState().Type() == "TopTenState")
		{
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			if(menu.contains(point))
			{
				State.setState(game.getmenuState());
			}
		}
		if(State.getState().Type() == "HelpState")
		{
			Rectangle menu = new Rectangle((int)(game.getWidth()* TopTenState.XSCALE), (int)(game.getHeight()* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			if(menu.contains(point))
			{
				State.setState(game.getmenuState());
			}
		}
		
		if(State.getState().Type() == "MenuState")
		{
			Rectangle start = new Rectangle( (int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH *MenuState.YSCALE) + 130 , MenuState.WIDTH , MenuState.HEIGHT);
			Rectangle continu = new Rectangle((int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) , MenuState.WIDTH, MenuState.HEIGHT );
			Rectangle help = new Rectangle( (int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 260 , MenuState.WIDTH , MenuState.HEIGHT);
			Rectangle topten = new Rectangle((int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 390 , MenuState.WIDTH , MenuState.HEIGHT );
			Rectangle exit = new Rectangle((int)(ScreenW*MenuState.XSCALE), (int)(ScreenH * MenuState.YSCALE) + 520 , MenuState.WIDTH , MenuState.HEIGHT );
			
			if(topten.contains(point)) {
				TopTenState toptenstate = new TopTenState(game);
				State.setState(toptenstate);
			}

			if(start.contains(point)) {

				LevelsState levelstate=new LevelsState(game);
				State.setState(levelstate);
				
			}
			
			if(help.contains(point))
			{
				HelpState helpState = new HelpState(game);
				State.setState(helpState);
			}

			if(exit.contains(point)) {
				if(cont == true) {
			    FileOutputStream fos;
				try {
					fos = new FileOutputStream(StackFile);
				    ObjectOutputStream oos = new ObjectOutputStream(fos);
				    Stack<Shapes>[] stacks=new Stack[2];
				    stacks[0]=((GameState) game.getgameState()).getstack1();
				    stacks[1]=((GameState) game.getgameState()).getstack2();
				    oos.writeObject(stacks);
				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				FileOutputStream fos2 ;
				try {
					fos2 = new FileOutputStream(recFile);
				    ObjectOutputStream oos = new ObjectOutputStream(fos2);
				    Rectangle[] rec=new Rectangle[2];
				    rec[0]=((GameState) game.getgameState()).getplayer().getRec1();

				    rec[1]=((GameState) game.getgameState()).getplayer().getRec2();

				    oos.writeObject(rec);
				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}


				FileOutputStream fos3 ;
				try {
					fos3 = new FileOutputStream(PlayerFile);
					ObjectOutputStream oos = new ObjectOutputStream(fos3);
					int[] position=new int[5];
					position[0]=((GameState) game.getgameState()).getplayer().getX();
				    position[1]=((GameState) game.getgameState()).getplayer().getY();
				    position[2]=((GameState) game.getgameState()).getstatus().getScore();
				    position[3]=((GameState) game.getgameState()).getSpeed();
				    position[4]=((GameState) game.getgameState()).getDirection();
				   
				    oos.writeObject(position);
				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				FileOutputStream fos4 ;
				try {
					fos4 = new FileOutputStream(HeartsFile);
				    ObjectOutputStream oos = new ObjectOutputStream(fos4);
				    int[] position=new int[2];

				    position[0]=((GameState) game.getgameState()).getstatus().getheartsNumber();
				    position[1]=((GameState) game.getgameState()).getstatus().getUndoNumber();
					oos.writeObject(position);


					oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				FileOutputStream fos5 ;
				try {
					fos5 = new FileOutputStream(TimeFile);
					ObjectOutputStream oos = new ObjectOutputStream(fos5);
					double position;

					((GameState) game.getgameState()).gettime();
					position= Stopwatch.elapsedTime()+((GameState) game.getgameState()).getconstant();
				    oos.writeObject(position);


				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				FileOutputStream fos6;
				try {
					fos6 = new FileOutputStream(playerName);
					ObjectOutputStream oos = new ObjectOutputStream(fos6);
					String name;
					name = ((GameState)game.getgameState()).getPlayer().getName() ;
				    oos.writeObject(name);
				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
			}
				
				FileOutputStream fos7;
				try {
					fos7 = new FileOutputStream(undo);
					ObjectOutputStream oos = new ObjectOutputStream(fos7);
					CareTaker position;
					position = ((GameState)game.getgameState()).getUndo();
					System.out.println(position+"a333333333");
				    oos.writeObject(position);
				    oos.close();

				} catch (Exception e1) {
					e1.printStackTrace();
			}
				
				
				
				
				
				}
				
				System.exit(1);	
			}
			
			
			if(!((MenuState)State.getState()).isOver() && (new File("isOver.bin")).exists()) {
			if(continu.contains(point)) {
				if(cont) {
					State.setState(game.getgameState());
				}
				else {
					int num_of_hearts=0;
					int num_of_undoes=0;
					FileInputStream fis4;     
					try {
						fis4 = new FileInputStream(HeartsFile);
						ObjectInputStream ois = new ObjectInputStream(fis4);
						int[] result = (int[]) ois.readObject();
						num_of_hearts=result[0];
						num_of_undoes=result[1];

						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					GameState gameState=new GameState(game,num_of_hearts,num_of_undoes,"");

					FileInputStream fis;
					try {
						fis = new FileInputStream(StackFile);
						ObjectInputStream ois = new ObjectInputStream(fis);
						Stack<Shapes>[] result = (Stack<Shapes>[]) ois.readObject();
						gameState.setstack1(result[0]);
						gameState.setstack2(result[1]);
						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}


					FileInputStream fis2;     
					try {
						fis2 = new FileInputStream(recFile);
						ObjectInputStream ois = new ObjectInputStream(fis2);
						Rectangle[] result = (Rectangle[]) ois.readObject();

						gameState.getplayer().setRec1(result[0]);
						gameState.getplayer().setRec2(result[1]);
						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}




					FileInputStream fis3;     
					try {
						fis3 = new FileInputStream(PlayerFile);
						ObjectInputStream ois = new ObjectInputStream(fis3);
						int[] result = (int[]) ois.readObject();
						System.out.println(result.toString());
						gameState.getplayer().setX(result[0]);
						gameState.getplayer().setY(result[1]);
						gameState.getstatus().setScore(result[2]);
						gameState.setSpeed(result[3]);
						gameState.setDirection(result[4]);
						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}




					FileInputStream fis5;     
					try {
						fis5 = new FileInputStream(TimeFile);
						ObjectInputStream ois = new ObjectInputStream(fis5);
						double result = (double) ois.readObject();

						gameState.setconstant(result);
						ois.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FileInputStream fis6;
					try {
						fis6 = new FileInputStream(playerName);
						ObjectInputStream ois = new ObjectInputStream(fis6);
						String result = (String) ois.readObject();

						gameState.setPlayerName(result);
						System.out.println(result);
						ois.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					FileInputStream fis7;
					try {
						fis7 = new FileInputStream(undo);
						ObjectInputStream ois = new ObjectInputStream(fis7);
						CareTaker result = (CareTaker) ois.readObject();

						gameState.setUndo(result);
						System.out.println(result+"heloooooooooooooooo");
						ois.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					game.setgameState(gameState);
					State.setState(gameState);

				}

			}
			}
		}


		if(State.getState().Type() == "LevelsState") {
			Rectangle simple = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle medium = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) + 150 , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle hard = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) + 300 , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			if(menu.contains(point))
			{
				State.setState(game.getmenuState());
			}
			if(simple.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(1, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				game.setgameState(gamestate);
				State.setState(gamestate);
			}
			if(medium.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(2, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				game.setgameState(gamestate);
				State.setState(gamestate);
			}
			if(hard.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(3, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				game.setgameState(gamestate);
				State.setState(gamestate);
			}
		}



	}


	@Override
	public void mouseMoved(MouseEvent e) {
		Point point = new Point(e.getX(),e.getY());
		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ScreenH = (int) d.getHeight();
		int ScreenW = (int) d.getWidth();
		
		if(State.getState().Type() == "LevelsState") {
			Rectangle simple = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle medium = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) + 150 , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle hard = new Rectangle((int)(ScreenW*LevelsState.XSCALE) , (int)(ScreenH * LevelsState.YSCALE) + 300 , LevelsState.WIDTH, LevelsState.HEIGHT);
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);
			
			if(menu.contains(point)) {
				((LevelsState)State.getState()).setMenu(true);
			}else {
				((LevelsState)State.getState()).setMenu(false);
			}
			if(simple.contains(point)) {
				((LevelsState)State.getState()).setSimple(true);
			}else {
				((LevelsState)State.getState()).setSimple(false);
			}
			if(medium.contains(point)) {
				((LevelsState)State.getState()).setMedium(true);
			}else {
				((LevelsState)State.getState()).setMedium(false);
			}
			if(hard.contains(point)) {
				((LevelsState)State.getState()).setHard(true);
			}else {
				((LevelsState)State.getState()).setHard(false);
			}
		}
		
		if( State.getState().Type() == "MenuState") {
			Rectangle start = new Rectangle( (int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 130, MenuState.WIDTH, MenuState.HEIGHT);
			Rectangle continu = new Rectangle((int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE), MenuState.WIDTH, MenuState.HEIGHT);
			Rectangle help = new Rectangle( (int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 260, MenuState.WIDTH, MenuState.HEIGHT);
			Rectangle topten = new Rectangle((int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 390, MenuState.WIDTH, MenuState.HEIGHT );
			Rectangle exit = new Rectangle((int)(ScreenW*MenuState.XSCALE) , (int)(ScreenH * MenuState.YSCALE) + 520, MenuState.WIDTH, MenuState.HEIGHT );
				
				
			if(continu.contains(point)) {
						
				((MenuState) game.getmenuState()).setcont(true);
			}else {
				((MenuState) game.getmenuState()).setcont(false);
			}
			
			if(start.contains(point)) {
				((MenuState) game.getmenuState()).setstart(true);
			}else {
				((MenuState) game.getmenuState()).setstart(false);
			}
	
			if(help.contains(point)) {
				((MenuState) game.getmenuState()).sethelp(true);
			}else {
				((MenuState) game.getmenuState()).sethelp(false);
			}
	
			
			if(exit.contains(point)) {
				((MenuState) game.getmenuState()).setexit(true);
			}else {
				((MenuState) game.getmenuState()).setexit(false);
			}
			if(topten.contains(point)) {
				((MenuState) game.getmenuState()).setTopten(true);
			}else {
				((MenuState) game.getmenuState()).setTopten(false);
			}
		}
		//Menu in GameOver State
		if(State.getState().Type() == "GameOverState") {
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);

			if(menu.contains(point)) {
				((GameOverState)State.getState()).setMenu(true);
			}else {
				((GameOverState)State.getState()).setMenu(false);
			}
		}
		if(State.getState().Type() == "GameState")
		{
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);

			if(menu.contains(point)) {
				((GameState)State.getState()).setMenu(true);
			}else {
				((GameState)State.getState()).setMenu(false);
			}
		}
		if(State.getState().Type() == "TopTenState")
		{
			Rectangle menu = new Rectangle((int)(ScreenW* TopTenState.XSCALE), (int)(ScreenH* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);

			if(menu.contains(point)) {
				((TopTenState)State.getState()).setMenu(true);
			}else {
				((TopTenState)State.getState()).setMenu(false);
			}
		}
		if(State.getState().Type() == "HelpState")
		{
			Rectangle menu = new Rectangle((int)(game.getWidth()* TopTenState.XSCALE), (int)(game.getHeight()* TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT);

			if(menu.contains(point)) {
				((HelpState)State.getState()).setMenu(true);
			}else {
				((HelpState)State.getState()).setMenu(false);
			}
		}

}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public boolean checkName(String text)
	{
		if(text.equals(""))
		{
			LevelsState.isMessage(true);
			return false;
		}
		LevelsState.isMessage(false);
		return true;
	}
}