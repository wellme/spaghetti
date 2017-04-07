package math.prime;

import java.util.Iterator;

public class NumberTheory {

	public static final PrimeNumberGenerator PNG = new PrimeNumberGenerator(n -> true, new Iterator<Long>() {
		
		private long primoral = primoralPn(7);
		private long n;
		private long i;
		private long[] list;
		
		{
			list = new long[];
		}
		
		
		public boolean hasNext() {
			return true;
		}
		
		public Long next() {
			return 0l;
		}
	});
	/*private static final long primoral;
	private static final long[] coprimes;
	
	static {
		primoral = PrimeUtils.primoralPn(7);
		ArrayList<Long> temp = new ArrayList<Long>();
		for(long i = 0; i < primoral; i++)
			if(PrimeUtils.gcd(i, primoral) == 1)
				temp.add(i);
		coprimes = new long[temp.size()];
		for(int i = 0; i < temp.size(); i++)
			coprimes[i] = temp.get(i);
		System.out.println(temp);
	}*/
	
	public static long primoralPn(int n) {
		long result = 1;
		for(int i = 0; i < n; i++)
			result *= PNG.get(i);
		return result;
	}
	
	public static boolean isSquareFree(int n) {
		for(int i = 1, j = 4; j <= n; i++, j += 2*i + 1)
			if(n % j == 0)
				return false;
		return true;
	}
	
	public static long square(long n) {
		return n*n;
	}
	
	public static int mobius(long num) {
		int ans = 1;
		if((num & 3) == 0)
			return 0;
		else if((num & 1) == 0) {
			ans *= -1;
			num >>= 1;
		}
		for(int i = 1; true; i++) {
			long temp = PNG.get(i);
			if(temp > num)
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
	
	public static long longerestingResult(int n) {
		long count = 1;
		for(int i = 0; i < n; i ++)
			count *= PNG.get(i) - 1;
		return count;
	}
	
	public static long gcd(int n, int m) {
		while(n != 0) {
			if(n < m) {
				int temp = n;
				n = m;
				m = temp;
			} else if(m == 0)
				return n;
			else
				n -= m;
		}
		return m;
	}
	
	public static long gcd(long n, long m) {
		while(n != 0) {
			if(n < m) {
				long temp = n;
				n = m;
				m = temp;
			} else if(m == 0)
				return n;
			else
				n -= m;
		}
		return m;
	}
}
