package utils;

public class Chrono {

	private boolean isRunning;
	private long counter;
	private long time;
	
	public Chrono(boolean startOnCreation){
		if(startOnCreation)
			start();
	}
	
	
	public void start(){
		isRunning = true;
		time = System.currentTimeMillis();
	}
	
	public long pause(){
		if(!isRunning)
			return elapsedTime();
		long temp = System.currentTimeMillis();
		counter += temp - time;
		time = temp;
		return counter;
	}
	
	public long elapsedTime(){
		long temp = System.currentTimeMillis();
		long delta = temp - time;
		counter += temp - time;
		time = temp;
		return delta;
	}
	
}
