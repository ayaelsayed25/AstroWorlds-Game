package States;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import Display.Media;
import Display.MediaPlayer;
import Game.Game;
import Momento.CareTaker;
import Momento.Editor;
import Objects.Constant;
import Objects.Movable;
import Objects.ObjectGenerator;
import Objects.Player;
import Objects.ShapeFactory;
import Objects.Shapes;
import Objects.Status;
import Objects.StatusBar;
import Objects.Stopwatch;
import Objects.UserControlled;

public class GameState extends State{

	private static Game game;
	private static UserControlled player;
	private BufferedImage backgroundImage;
	private Constant ufo;
	private Constant uforight;
	private static LinkedList<Shapes> shapes;
	private Shapes shape;
	private Shapes shaperight;
	private static Stack<Shapes> stack1, stack2;
	private static Status status;
	private StatusBar statusbar;
	private boolean start;
	private boolean menu;
	Iterator listIter;
	Iterator stack1Iter;
	Iterator stack2Iter;
	MyIterator iterator;
	Stopwatch stopwatch;
	double time;
	private double constanttime=0;
	private static State gameOverState;
	private static HashMap<String, String> sounds;
	private static MediaPlayer media;
	public int speed;
	int direction;
	private String playerName;
	static CareTaker undo;
	static Editor edit;
	private boolean isUndo;


	public GameState(Game game,int num_of_hearts,int num_of_undoes,String playerName) {
		super(game);
		this.game = game;
		this.playerName = playerName;
		backgroundImage = Media.bg;
		player = Player.getInstance();
		ufo = new ObjectGenerator();
		uforight = new ObjectGenerator();
		shapes = new LinkedList<Shapes>();
		stack1 = new Stack<Shapes>();
		stack2 = new Stack<Shapes>();
		status = new Status(num_of_hearts,num_of_undoes);
		statusbar = new StatusBar(status);
		status.register(statusbar);
		iterator = new MyIterator();
		stopwatch = new Stopwatch();
		start=false;
		menu=false;
		isUndo=false;
		undo=new CareTaker();
		 edit=new Editor();
		sounds = new HashMap<String, String>();
		media = new MediaPlayer();
		((Player) player).setName(playerName);
		init();
	}

	@Override
	public void refresh() {

		((Player) player).refresh();

		for(int i =0 ;i< shapes.size();i++) {
			Shapes shape = shapes.get(i);
			shape.refresh();
			detectCollision(shape, player);
		}
		stack1Iter = stack1.iterator();
		stack2Iter = stack2.iterator();
		iterator.refresh(stack1Iter, player);
		iterator.refresh(stack2Iter, player);
		time = stopwatch.elapsedTime();
		if(isUndo) {
			un();
		}
		isUndo=false;
	}


	@Override
	public void draw(Graphics g) {
		//Draw Constants
		g.drawImage(backgroundImage, 0, 0, game.getWidth(), game.getHeight(), null);

		if(menu)
			g.drawImage(Media.menu2, (int)(game.getWidth() * TopTenState.XSCALE), (int)(game.getHeight() * TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
		else
			g.drawImage(Media.menu1, (int)(game.getWidth() * TopTenState.XSCALE), (int)(game.getHeight() * TopTenState.YSCALE), TopTenState.WIDTH, TopTenState.HEIGHT, null);
	
		
		((ObjectGenerator)ufo).draw(g);
		((ObjectGenerator)uforight).draw(g);
		statusbar.draw(g);
		int[] t  = measureStopWatch(); 
		status.setTime(t);
		g.setFont(new Font("TimesRoman",Font.ITALIC,30));
		g.drawString( t[0] + " : " + t[1] + " : " + t[2] , 610 , 40);
		//Draw Movable Objects
		((Player) player).draw(g);
		listIter = shapes.iterator();
		stack1Iter = stack1.iterator();
		stack2Iter = stack2.iterator();
		iterator.draw(listIter, g);
		iterator.draw(stack1Iter, g);
		iterator.draw(stack2Iter, g);
	}
	
	private int[] measureStopWatch() {
		double time = stopwatch.elapsedTime()+constanttime ;
		int seconds = 0,minutes = 0,hours = 0;
		if((int) (time/(60*60)) > 0) {
			hours = (int) (time/(60*60));
			time = time%(60*60);
		}
		if((int)(time/60) > 0) {
			minutes = (int) (time/(60));
			time = time % 60;
		}
		seconds = (int) time;
		int[] t = new int[3];
		t[0] = hours;t[1] = minutes ; t[2] = seconds;
		return t ;
		
	}

	@Override
	public void init() {
		player.setGame(game);
		player.setX(UserControlled.XDEFAULT); 
		player.setY(UserControlled.yDefault);
		ufo.setX(Constant.XDEFAULT1);ufo.setY(Constant.YDEFAULT1);
		uforight.setX(Constant.XDEFAULT2);uforight.setY(Constant.YDEFAULT2);
		sounds.put("alienOrStack", "/Music/alienOrStack.wav");
		sounds.put("chooseUndo", "/Music/chooseUndo.wav");
		sounds.put("heart", "/Music/heart.wav");
		sounds.put("shapes", "/Music/shapes.wav");
		sounds.put("three", "/Music/three.wav");
		sounds.put("undo", "/Music/undo.wav");

	}

	public void generateShapes(int shape1,int shape2) throws CloneNotSupportedException
	{
		shape = (Shapes) ShapeFactory.getShape(shape1).clone();
		shape.setRandom(getRandom(0,direction));
		
		shaperight = (Shapes) ShapeFactory.getShape(shape2).clone();
		shaperight.setRandom(getRandom(0,direction));

		shape.setX(350);shaperight.setX(930);
		shape.setY(100);shaperight.setY(100);
		shapes.add(shape);shapes.add(shaperight);

	}

	public void generateHeartUndo() throws CloneNotSupportedException {
		generateShapes(7,8);
	}
	public static int getRandom(int max , int min) {
		int  num = (int) ((Math.random() * (max - min) ) + min);
		return num;

	}
	public static void addToStack(Shapes shape, Stack<Shapes> stack)
	{
		//check if stack is full
		if(stack.size() >= 16) {
			media.play(sounds.get("alienOrStack"), false);
			if(status.getHearts().size() != 0) 
			{
				status.decreaseHearts();
				if(stack == stack1) {
					player.resetRectLeft();
					undo.clearundo(true);
				}
				else {
					player.resetRectRight();
					undo.clearundo(false);
				}
				stack.clear();
			}
			else 
			{
				gameOverState = new GameOverState(game, status);
				State.setState(gameOverState);
			}
			return;
		}
		shapes.remove(shape);
		//check if shape's alien or heart
		if(shape.isShape.whatShape().equals("Heart")) {/*Heart*/
			media.play(sounds.get("heart"), false);
			if(status.getHearts().size() <3)
			{
				status.increaseHearts(shape);
			}
			return;
		}
		
		if(shape.isShape.whatShape().equals("Alien")) {   /*alien*/
			media.play(sounds.get("alienOrStack"), false);
			if(status.getHearts().size() > 0)
			{
				status.decreaseHearts();
			}
			else 
			{
				gameOverState = new GameOverState(game, status);
				State.setState(gameOverState);
			}
			return;
		}
		if(shape.number == 8) {
			status.increaseUndoes(shape);
			media.play(sounds.get("undo"), false);
			return;
		}
		//check if the player got three similar shapes
		if(checkScore(shape, stack))
		{
			media.play(sounds.get("three"), false);
			return;
		}		
		//if it's not anything of the above then add to the stack
		media.play(sounds.get("shapes"), false);
		if(!stack.isEmpty())
		{
			shape.setYPlus(((Movable) stack.peek()).getYPlus() - Movable.YPLUSINIT);
		}
		else
		{
			shape.setYPlus(0);
		}
		if(stack == stack1)
		{
			shape.xstack = Movable.XSTACK1;
			player.setRectLeftHeight(Movable.YPLUSINIT);
		}
		else
		{
			shape.xstack = Movable.XSTACK2;
			player.setRectRightHeight(Movable.YPLUSINIT);
		}
		shape.setX(player.getX() + shape.xstack);
		shape.setY(player.getY() + Movable.YSTACK + shape.getYPlus());
		stack.add(shape);
		
	edit.setScore(status.Score);
		
		edit.setShape(null);
		if(stack==stack1) {
			edit.setIsRight(true);
		}
		else
			edit.setIsRight(false);
			
		undo.add(edit.createState());
	}

	
	
	public static boolean checkScore(Shapes shape, Stack<Shapes> stack)
	{
		if(stack.size() >= 2)
		{
			Shapes shape1 = (Shapes) stack.peek(); 
			Shapes shape2 = (Shapes) stack.get(stack.size() - 2);
			if(shape.number == shape1.number && shape2.number == shape1.number)
			{
				LinkedList<Shapes> temshapes=new LinkedList<Shapes>();
				for(int i=0; i<2; i++)
				{
				temshapes.add(stack.pop());
					if(stack == stack1) {
						player.setRectLeftHeight(Movable.YPLUSINIT * -1);
					edit.setIsRight(true);
					}
					else {
						player.setRectRightHeight(Movable.YPLUSINIT * -1);
					edit.setIsRight(false);
					}
				}
				
				edit.setScore(status.Score);
				
				status.setScore( status.getScore() + 1);
				
				edit.setShape(temshapes);
				
				undo.add(edit.createState());
				return true;
			}
		}
		return false;
	} 
	

	public void detectCollision(Shapes shape, UserControlled player)
	{
		Point p = new Point(shape.getX(), shape.getY() );

		if(player.getRectangleLeft().contains(p))
		{
			addToStack(shape, stack1);		
		}
		else if(player.getRectangleRight().contains(p))
		{
			addToStack(shape, stack2);			
		}
		else if(shape.getY() > game.getHeight() * 0.85)
		{
			shapes.remove(shape);
		}
     }
	public Stack<Shapes> getstack1(){
		return stack1;
	}
	public void setstack1 (Stack<Shapes> stack){
		this .stack1=stack;
		System.out.println(stack1);
	}
	
	public Stack<Shapes> getstack2(){
		return stack2;
	}
	public void setstack2 (Stack<Shapes> stack){
		
		this .stack2=stack;
		System.out.println(stack2);
	}
	
	public UserControlled getplayer(){
		return player;
	}
	public void setplayer (UserControlled player){
		this .player=player;
	}
	public Player getPlayer()
	{
		return (Player) player;
	}

	public void setstart(boolean start) {
		this.start=start;
	}
	
	
	public Status getstatus(){
		return status;
	}
	public void setstatus (Status status){
		this.status=status;
	}
	
	public Stopwatch gettime(){
		return stopwatch;
	}
	public void settime (Stopwatch time){
		this.stopwatch=time;
	}
	public void setconstant (double time){
		this.constanttime=time;
	}
	public double getconstant (){
	return constanttime;
	}
	
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String Type() {

		return "GameState";
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	
	
	
	
    public static CareTaker getUndo() {
		return undo;
	}

	public static void setUndo(CareTaker undo) {
		GameState.undo = undo;
	}

	
	public boolean isUndo() {
		return isUndo;
	}

	public void setUndo(boolean isUndo) {
		this.isUndo = isUndo;
	}

	
	public void un() {
		if(undo.getHistory().size()!=0) {
		edit.restore(undo.pop());
		media.play(sounds.get("undo"), false);
	
		if(edit.isIsRight()) {
			//if(stack1.size()!=0) {
			if(edit.getShape()==null) {
			stack1.pop();
			player.setRectLeftHeight(Movable.YPLUSINIT * -1);}
			
			else {
				for(int i=1;i>=0;i--) {
				stack1.add(edit.getShape().get(i));
				player.setRectLeftHeight(Movable.YPLUSINIT);
				}
			
		}
		}
		
		
			else {
				//if(stack2.size()!=0) {
				if(edit.getShape()==null) {
			         stack2.pop();
			         player.setRectRightHeight(Movable.YPLUSINIT * -1);}
				else {
					for(int i=1;i>=0;i--) {
					stack2.add(edit.getShape().get(i));
					player.setRectRightHeight(Movable.YPLUSINIT);
					}
				
				}
		}
		
		
			getstatus().setScore(edit.getScore());
			
		
		
		}
	}
}