package States;

import Game.Game;

public class levelfactory {
	
	int level;
	Game game;
	String playerName;
	
	public levelfactory(int level, Game game,String playerName) {
		this.level = level;
		this.game = game;
		this.playerName = playerName;
		
	}
	
	public State create() {
		
		GameState gamestate ;
		if(level ==1)
		{
			gamestate = new GameState(game, 3,0,playerName);
			gamestate.setSpeed(50);
			gamestate.setDirection(1);
		}
		else if(level == 2) 
		{
			gamestate = new GameState(game, 3,0,playerName);
			gamestate.setSpeed(50);
			gamestate.setDirection(3);
		}else {
			gamestate = new GameState(game, 3,0,playerName);
			gamestate.setSpeed(30);
			gamestate.setDirection(3);
		}
		return gamestate;
		
	}

}
