package math.prime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class PrimeNumberGenerator {

	private static final Iterator<Long> DEFAULT_ITERATOR = new Iterator<Long>() {
		
		private long num = 1;
		private boolean first = true;

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Long next() {
			if(first) {
				first = false;
				return 2l;
			}
			num += 2;
			return num;
		}
		
	};
	private static final Predicate<Long> DEFAULT_TEST = n -> true;
	private Predicate<Long> predicate;
	private Iterator<Long> iterator;
	private long last = 1;
	private ArrayList<Long> list;
	
	{
		list = new ArrayList<Long>();
		//list.add(2l);
	}
	
	public PrimeNumberGenerator(){
		this(DEFAULT_TEST, DEFAULT_ITERATOR);
	}
	
	public PrimeNumberGenerator(Predicate<Long> test){
		this(test, DEFAULT_ITERATOR);
	}
	public PrimeNumberGenerator(Iterator<Long> iterator){
		this(DEFAULT_TEST, iterator);
	}
	public PrimeNumberGenerator(Predicate<Long> predicate, Iterator<Long> iterator){
		this.predicate = predicate;
		this.iterator = iterator;
	}
	

	public synchronized long next(){
		do{
			last = iterator.next();
		} while(!isPrime(last) || !predicate.test(last));
		list.add(last);
		return last;
	}
	
	public synchronized long get(int i) {
		while(list.size() <= i)
			next();
		return list.get(i);
	}
	
	public static boolean isPrime(long n){
		if(n == 2)
			return true;
		if(n == 1 || (n & 1) == 0 || n <= 0)
			return false;
		for(long i = 3; i*i <= n; i += 2)
			if(n % i == 0)
				return false;
		return true;
	}
	
	/*
		for(int i = 0; true; i++) {
			for(long l : coprimes) {
				long temp = l + i * primoral;
				if(temp * temp > l)
					return ans;
				if(num % temp == 0) {
					if(temp == 1)
						continue;
					num /= temp;
					ans *= -1;
					if(num % temp == 0)
						return 0;
				}
			}
		}
	 */
	
	//Save into file
	/*public static void main(String args[]) {
		ArrayList<Long> list = new ArrayList<Long>();
		list.add(2l);
		int j = 0;
		outer:
		for(long i = 3; i < Long.MAX_VALUE; i += 2) {
			for(Long l : list)
				if(i % l == 0)
					continue outer;
			list.add(i);
			if(j++ == 5000) {
				j = 0;
				System.out.printf("i : %s (%s%%)%n", i, (double) i / Long.MAX_VALUE * 100);
			}
		}
	}*/
}
