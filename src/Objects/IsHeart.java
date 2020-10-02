package Objects;

import java.io.Serializable;

public class IsHeart implements IsShape,Serializable{

	@Override
	public String whatShape() {
		return "Heart";
	}

}