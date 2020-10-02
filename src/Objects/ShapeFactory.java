package Objects;

import java.util.Hashtable;

public class ShapeFactory {
	
	private static final Hashtable<Integer , Shapes> ShapesBynumber = new Hashtable<Integer, Shapes>();
   
	public static Shapes getShape(int number) {
		
		Shapes shape = (Shapes)ShapesBynumber.get(number);
		
		if(shape == null) {
			if (number >= 0 && number <= 1 ) {
				
				shape = new planets(number);
			}
			else if(number >= 2 && number <= 3) {
				
				shape = new Stars(number);
			}
			else if(number >= 4 && number <= 5){
				shape = new Moon(number);
			}else if(number == 6) {
				shape = new Alien(number);
			}else if(number == 7){
				shape = new Heart(number);
			}else {
				shape = new Undo(number);
			}
			ShapesBynumber.put(number, shape);
         
		}
		return shape;
	}
}