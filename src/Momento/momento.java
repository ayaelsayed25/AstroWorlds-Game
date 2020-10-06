package Momento;

import java.awt.Shape;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

import Objects.Heart;
import Objects.Shapes;

public class momento implements Serializable {
	
	private final boolean IsRight;
	private final int score;
	private  LinkedList<Shapes> shape=  new LinkedList<Shapes>();



	public momento(LinkedList<Shapes> shape, boolean isRight, int score) {
	
		this.shape = shape;
		IsRight = isRight;
		this.score = score;
		
	}


	public  LinkedList<Shapes> getShape() {
		return shape;
	}



	


	public boolean isIsRight() {
		return IsRight;
	}



	



	public int getScore() {
		return score;
	}








}


