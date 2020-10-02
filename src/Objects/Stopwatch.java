package Objects;

public class Stopwatch { 

    private static long start;

    public Stopwatch() {
    	
        start = System.currentTimeMillis();
    } 


    public static double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

		
	
    
}