package Objects;

import java.io.Serializable;

public class IsAlien implements IsShape, Serializable{

	@Override
	public String whatShape() {
		return "Alien";
	}

}