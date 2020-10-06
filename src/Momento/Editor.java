package Momento;

import java.awt.Shape;
import java.util.LinkedList;
import java.util.Stack;

import Objects.Heart;
import Objects.Shapes;

public class Editor {


	private  boolean IsRight;
	private  int score;
	private  LinkedList<Shapes> shape=  new LinkedList<Shapes>();
	
	
	public momento createState() {
		return new momento(shape,IsRight,score) ;
	}
	
	public void restore(momento state) {
		shape=state.getShape();
		IsRight=state.isIsRight();
		score=state.getScore();
		
	}
	

	public boolean isIsRight() {
		return IsRight;
	}
	public void setIsRight(boolean isRight) {
		IsRight = isRight;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public LinkedList<Shapes>  getShape() {
		return shape;
	}

	public void setShape(LinkedList<Shapes>  shape2) {
		this.shape = shape2;
	}
	
}
