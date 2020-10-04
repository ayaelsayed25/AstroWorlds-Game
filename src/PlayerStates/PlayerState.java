package PlayerStates;

public class PlayerState {
	
	private static PlayerState state;
	
	
	public static PlayerState getState()
	{
		return state;
	}
	public static void setState(PlayerState s)
	{
		state = s;
	}

}
