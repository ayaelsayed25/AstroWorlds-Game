package Momento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Objects.Heart;

public class CareTaker implements Serializable {
	private List<momento> history=new ArrayList<>();
	//static Editor edit=new;
	


	public void add(momento state) {
	//System.out.println(state.getStack());
		//System.out.println(state);
		history.add(state);
	}
	
	public momento pop() {
		
		int lastindex=history.size()-1;
		var laststate=history.get(lastindex);
		history.remove(lastindex);
		return laststate;
		
		
	}
	
	public void clearundo(boolean isRight) {
		System.out.println(history.size());
		for(int i=0;i<history.size();i++) {
			System.out.println(history.get(i).isIsRight());
			System.out.println(history.get(i).getShape());
			}
		for(int i=0;i<history.size();i++) {
			if(history.get(i).isIsRight()==isRight) {
				history.remove(i);
				i--;
			}
		}
		System.out.println(history.size());

		for(int i=0;i<history.size();i++) {
		System.out.println(history.get(i).isIsRight());
		System.out.println(history.get(i).getShape());
		}
	}
	
	   
	public List<momento> getHistory() {
		return history;
	}

	public void setHistory(List<momento> history) {
		this.history = history;
	}

}
