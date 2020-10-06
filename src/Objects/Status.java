package Objects;

import java.util.LinkedList;

public class Status implements IStatus{

	public int Score = 0;
	private int heartsNumber, UndoNumber;
	private int[] time;
	IObserver Observer;
	LinkedList<Heart> hearts;
	LinkedList<Undo> undoes;
	
	public Status(int heartsNumber,int num_of_undoes) {
		this.heartsNumber=heartsNumber;
		this.UndoNumber=num_of_undoes;
		Observer = new StatusBar(this);
		hearts = new LinkedList<Heart>();
		for(int i = 0 ;i< heartsNumber ;i++) {
			Heart e = new Heart(7);
			hearts.add(e);
		}
		undoes = new LinkedList<Undo>();
		for(int i = 0 ;i< num_of_undoes ;i++) {
			Undo e = new Undo(8);
			undoes.add(e);
		}
	}
	

	public LinkedList<Undo> getUndoes() {
		return undoes;
	}



	public void setUndoes(LinkedList<Undo> undoes) {
		this.undoes = undoes;
	}



	public int getScore() {
		return Score;
	}



	public void setScore(int score) {
		Score = score;
		notifyObserver();
	}


	public LinkedList<Heart> getHearts() {
		return hearts;
	}



	public void setHearts(LinkedList<Heart> hearts) {
		this.hearts = hearts;
	}



	@Override
	public void register(IObserver observer) {
		this.Observer = observer;
		
	}

	@Override
	public void remove(IObserver observer) {
		
		
	}
	public void increaseHearts(Shapes heart)
	{   
		heartsNumber++;;
		hearts.add((Heart)heart);
		notifyObserver();
	}

	public void decreaseUndoes()
	{  
		UndoNumber--;
		undoes.remove();
		notifyObserver();
	}
	public void increaseUndoes(Shapes undo)
	{   
		UndoNumber++;;
		undoes.add((Undo)undo);
		notifyObserver();
	}

	public void decreaseHearts()
	{  
		heartsNumber--;
		hearts.remove();
		notifyObserver();
	}
	@Override
	public void notifyObserver() {
		
		Observer.update();
	}

	public int getheartsNumber() {
	
		return heartsNumber;
	}
	public void setTime(int[] time)
	{
		this.time = time;
	}
	public int[] getTime()
	{
		return time;
	}

	public int getUndoNumber() {
		return UndoNumber;
	}


	public void setUndoNumber(int undoNumber) {
		UndoNumber = undoNumber;
	}
}
