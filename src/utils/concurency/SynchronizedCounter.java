package utils.concurency;

public class SynchronizedCounter {
		
	private long n = 0;
	
	public synchronized long next() {
		return n++;
	}
	
	public synchronized long get() {
		return n;
	}
	
	public synchronized void set(long value) {
		n = value;
	}
}