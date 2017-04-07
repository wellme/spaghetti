package math.number;

import math.prime.NumberTheory;

public class SquareFreeBinary {

	private long bin;
	
	public SquareFreeBinary(long bin) {
		this.bin = bin;
	}
	
	
	public long value() {
		return bin;
	}
	
	public long bin() {
		return bin;
	}
	
	public static class FromInteger extends SquareFreeBinary {

		public FromInteger(long value) throws NonSquareFreeException {
			super(parse(value));
		}
		
		
		private static long parse(long value) throws NonSquareFreeException {
			long ans = 0;
			if((value & 3) == 0)
				throw new NonSquareFreeException(2);
			else if((value & 1) == 0) {
				ans = 1;
				value >>= 1;
			}
			for(int i = 1; true; i++) {
				long temp = NumberTheory.PNG.get(i);
				if(temp > value)
					return ans;
				if(value % temp == 0) {
					value /= temp;
					ans += 1 << i;
					if(value % temp == 0)
						throw new NonSquareFreeException(temp);
				}
			}
		}
		
		public static class NonSquareFreeException extends Exception {

			private static final long serialVersionUID = 3325873322955259001L;
			
			public final long divisor;
			public NonSquareFreeException(long number) {
				divisor = number;
			}
			
			public NonSquareFreeException() {
				divisor = -1;
			}
			@Override
			public String getMessage() {
				return "Number is divisible by " + divisor + " squared";
				
			}
			
		}
	}
}
