package States;

import Game.Game;

public class levelfactory {
	
	int level;
	Game game;
	
	public levelfactory(int level, Game game) {
		this.level = level;
		this.game = game;
		
	}
	
	public State create() {
		
		GameState gamestate ;
		if(level ==1)
		{
			gamestate = new GameState(game, 3);
			gamestate.setSpeed(50);
			gamestate.setDirection(1);
		}
		else if(level == 2) 
		{
			gamestate = new GameState(game, 3);
			gamestate.setSpeed(50);
			gamestate.setDirection(3);
		}else {
			gamestate = new GameState(game, 3);
			gamestate.setSpeed(30);
			gamestate.setDirection(3);
		}
		return gamestate;
		
	}

}
