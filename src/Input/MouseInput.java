package Input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

import Game.Game;
import Objects.Heart;
import Objects.Player;
import Objects.Shapes;
import Objects.Stopwatch;
import States.GameOverState;
import States.GameState;
import States.MenuState;
import States.State;

public class MouseInput implements MouseListener,Serializable,MouseMotionListener {
	
	private Game game;
	private String StackFile;
	private String recFile;
	private String PlayerFile,HeartsFile,TimeFile;
	private boolean cont;

	public MouseInput(Game game) {
		this.game =game;
	
		StackFile= "stack.bin";
		recFile= "recFile.bin";
		PlayerFile="player.bin";
		HeartsFile="hearts.bin";
		TimeFile="time.bin";
		cont=false;
	}


	public void mouseClicked(MouseEvent e) {
		System.out.println("hii");
		
	}

	
	public void mousePressed(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		
		
		if(x>=500 && x<=700) {
			if(y>=400 && y<=500) {
				
				GameState gameState=new GameState(game,3);
				State.setState(gameState);
				game.setgameState(gameState);
				
			}
		}
		
		
		if(x>=100 && x<=300) {
			if(y>=700 && y<=800) {
			
				cont=true;
				State.setState((MenuState)game.getmenuState());
				
			}
		}
		
		if(x>=500 && x<=700) {
			if(y>=600 && y<=700) {
			   
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
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
					 FileOutputStream fos3 ;
					try {
						fos3 = new FileOutputStream(PlayerFile);
					    ObjectOutputStream oos = new ObjectOutputStream(fos3);
					    int[] position=new int[3];
					    position[0]=((GameState) game.getgameState()).getplayer().getX();
					
					    position[1]=((GameState) game.getgameState()).getplayer().getY();
					    position[2]=((GameState) game.getgameState()).getstatus().getScore();
					 
					  
					    
					    oos.writeObject(position);
					
					    
					    oos.close();
				
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 FileOutputStream fos4 ;
						try {
							fos4 = new FileOutputStream(HeartsFile);
						    ObjectOutputStream oos = new ObjectOutputStream(fos4);
						    int position;
					
						    position=((GameState) game.getgameState()).getstatus().getheartsNumber();
						    System.out.println(((GameState) game.getgameState()).getstatus().getheartsNumber());
						    oos.writeObject(position);
						
						    
						    oos.close();
					
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						 FileOutputStream fos5 ;
							try {
								fos5 = new FileOutputStream(TimeFile);
							    ObjectOutputStream oos = new ObjectOutputStream(fos5);
							    double position;
						
							   position= ((GameState) game.getgameState()).gettime().elapsedTime()+((GameState) game.getgameState()).getconstant();
							    oos.writeObject(position);
							
							    
							    oos.close();
						
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
			
			
			System.exit(1);	
			}
		}
		
		if(x>=500 && x<=700) {
			if(y>=300 && y<=400) {
				
				
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
					GameState gameState=new GameState(game,num_of_hearts);
					
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
						// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
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
						
						State.setState(gameState);
						game.setgameState(gameState);
						
						ois.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
				
				
				FileInputStream fis5;     
				try {
					fis5 = new FileInputStream(TimeFile);
					ObjectInputStream ois = new ObjectInputStream(fis5);
				double result = (double) ois.readObject();
	
					((GameState) game.getgameState()).setconstant(result);
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



	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
	int x=e.getX();
		int y=e.getY();
		
	if(x>=500 && x<=700) {
			if(y>=300 && y<=400) {
				
				((MenuState) game.getmenuState()).setcont(true);
			}
		}
		
	

	if(!(x>=500 && x<=700) || !(y>=300 && y<=400)) {
		((MenuState) game.getmenuState()).setcont(false);
			
	}
	
	
	if(x>=500 && x<=700) {
		if(y>=400 && y<=500) {
			((MenuState) game.getmenuState()).setstart(true);
		}
	}
	
	

	if(!(x>=500 && x<=700) || !(y>=400 && y<=500)) {
		((MenuState) game.getmenuState()).setstart(false);
			
	}
	
	if(x>=500 && x<=700) {
		if(y>=500 && y<=600) {
			((MenuState) game.getmenuState()).sethelp(true);
		}
	}
	
	

	if(!(x>=500 && x<=700) || !(y>=500 && y<=600)) {
		((MenuState) game.getmenuState()).sethelp(false);
			
	}
	
	
	if(x>=500 && x<=700) {
		if(y>=600 && y<=700)  {
			((MenuState) game.getmenuState()).setexit(true);
		}
	}
	
	

	if(!(x>=500 && x<=700) || !(y>=600 && y<=700)) {
		((MenuState) game.getmenuState()).setexit(false);
			
	}
	
	//Menu in game over state:
		if(x>=20 && x<= 150)
		{
			if(y>=750 && y<=800)
			{
				((GameOverState) State.getState()).setMenuMouse(true);
			}
		}	
		
	//Menu in game over state:
		if(!(x>=20 && x<= 150))
		{
			if(!(y>=750 && y<=800))
			{
				((GameOverState) State.getState()).setMenuMouse(false);
			}
		}

}
}
	