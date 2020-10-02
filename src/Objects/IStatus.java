package Objects;

public interface IStatus {
	
public void register(IObserver observer);

public void remove(IObserver observer);

public void notifyObserver();
	
}
