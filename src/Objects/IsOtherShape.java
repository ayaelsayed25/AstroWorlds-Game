package Objects;

import java.io.Serializable;

public class IsOtherShape implements IsShape,Serializable {

	@Override
	public String whatShape() {
		return "shape";
	}

	
}