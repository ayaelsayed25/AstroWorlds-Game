package Input;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import Game.Game;
import Objects.Shapes;
import Objects.Stopwatch;
import States.GameOverState;
import States.GameState;
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
	private String playerName;

	public MouseInput(Game game) {
		this.game =game;

		StackFile= "stack.bin";
		recFile= "recFile.bin";
		PlayerFile="player.bin";
		HeartsFile="hearts.bin";
		TimeFile="time.bin";
		playerName = "name.bin";
		cont=false;
	}


	public void mouseClicked(MouseEvent e) {

	}


	public void mousePressed(MouseEvent e) {


		Point point = new Point(e.getX(),e.getY());
		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ScreenH = (int) d.getHeight();
		int ScreenW = (int) d.getWidth();
		
		if(State.getState().Type() == "GameState")
		{

			int x=e.getX();
			int y=e.getY();
			if(x>=100 && x<=300) {
				if(y>=700 && y<=800) {

					cont=true;
					State.setState(game.getmenuState());
				}
			}
		}
		if(State.getState().Type() == "MenuState")
		{
			Rectangle start = new Rectangle( (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 100 , 400 , 100);
			Rectangle continu = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 100 );
			Rectangle help = new Rectangle( (int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 200 , 200 , 100);
			Rectangle topten = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 100 );
			Rectangle exit = new Rectangle((int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 400 , 200 , 100 );
			
			if(topten.contains(point)) {
				TopTenState toptenstate = new TopTenState(game);
				State.setState(toptenstate);
				game.setgameState(toptenstate);
			}

			if(start.contains(point)) {

				LevelsState levelstate=new LevelsState(game);
				State.setState(levelstate);
				game.setgameState(levelstate);

			}


			if(exit.contains(point)) {

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
				    int position;

				    position=((GameState) game.getgameState()).getstatus().getheartsNumber();
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

				System.exit(1);	
			}
			

			if(continu.contains(point)) {
				if(cont) {
					State.setState(game.getgameState());
				}
				else {

					int num_of_hearts=0;
					FileInputStream fis4;     
					try {
						fis4 = new FileInputStream(HeartsFile);
						ObjectInputStream ois = new ObjectInputStream(fis4);
						int result = (int) ois.readObject();
						num_of_hearts=result;

						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					GameState gameState=new GameState(game,num_of_hearts,"");

					FileInputStream fis;
					try {
						fis = new FileInputStream(StackFile);
						ObjectInputStream ois = new ObjectInputStream(fis);
						Stack<Shapes>[] result = (Stack<Shapes>[]) ois.readObject();

						gameState.setstack1(result[0]);
						gameState.setstack2(result[1]);
						State.setState(gameState);
						game.setgameState(gameState);
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

						State.setState(gameState);
						game.setgameState(gameState);
						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}




					FileInputStream fis3;     
					try {
						fis3 = new FileInputStream(PlayerFile);
						ObjectInputStream ois = new ObjectInputStream(fis3);
						int[] result = (int[]) ois.readObject();


						gameState.getplayer().setX(result[0]);
						gameState.getplayer().setY(result[1]);
						gameState.getstatus().setScore(result[2]);
						gameState.setSpeed(result[3]);
						gameState.setDirection(result[4]);
						State.setState(gameState);
						game.setgameState(gameState);

						ois.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}




					FileInputStream fis5;     
					try {
						fis5 = new FileInputStream(TimeFile);
						ObjectInputStream ois = new ObjectInputStream(fis5);
						double result = (double) ois.readObject();

						((GameState) game.getgameState()).setconstant(result);;
						State.setState(gameState);
						game.setgameState(gameState);

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

						((GameState) game.getgameState()).setPlayerName(result);
						System.out.println(result);
						State.setState(gameState);
						game.setgameState(gameState);
						ois.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		}


		if(State.getState().Type() == "LevelsState") {
			Rectangle simple = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 140 );
			Rectangle medium = new Rectangle((int)(ScreenW*0.35) ,   (int)(ScreenH * 0.28) + 150 , 400 , 140);
			Rectangle hard = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 140);

			if(simple.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(1, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				State.setState(gamestate);
				game.setgameState(gamestate);
			}
			if(medium.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(2, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				State.setState(gamestate);
				game.setgameState(gamestate);
			}
			if(hard.contains(point) && checkName(((LevelsState)State.getState()).getText())) {
				levelfactory lf = new levelfactory(3, game,((LevelsState)State.getState()).getText());
				GameState gamestate = (GameState) lf.create();
				State.setState(gamestate);
				game.setgameState(gamestate);
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
			Rectangle simple = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 150 );
			Rectangle medium = new Rectangle((int)(ScreenW*0.35) ,  (int)(ScreenH * 0.28) + 150 , 400 , 150);
			Rectangle hard = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 150);
			
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
			Rectangle start = new Rectangle( (int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 100 , 400 , 100);
			Rectangle continu = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) , 400, 100 );
			Rectangle help = new Rectangle( (int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 200 , 200 , 100);
			Rectangle topten = new Rectangle((int)(ScreenW*0.35) , (int)(ScreenH * 0.28) + 300 , 400 , 100 );
			Rectangle exit = new Rectangle((int)(ScreenW*0.35)+100 , (int)(ScreenH * 0.28) + 400 , 200 , 100 );
				
				
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
			Rectangle Menu = new Rectangle(20, 750, 130, 50);
			
			if(Menu.contains(point))
			{
				((GameOverState) State.getState()).setMenuMouse(true);
			}else {
				((GameOverState) State.getState()).setMenuMouse(false);	
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
			return false;
		}
		System.out.println(text);
		return true;
	}
}