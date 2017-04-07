package main.test;

public class SpeedTest {
	
	/*
	 * random = 1;
	 * exp ~= 2
	 * sin ~= 1				//14.32%
	 * sin (manual) ~= 0.87  
	 * cos ~= 0.88
	 * currentTimeMillis ~= 0.5
	 * hypot ~= 25.35
	 * hypot (manual) ~= 0.056
	 * sqrt ~= 0.00135 = 0.135%
	 * + (double) ~= 0.0596
	 * * (double) ~= 0.0597
	 */
	
	private static double randomDelay;
	private static int count = ((1 << 30) - 1) * 2 + 1;
	private static DelayFunction[] functions = {
			/*() -> Math.exp(Math.random()),
			() -> Math.sin(Math.random()),
			() -> Math.cos(Math.random()),
			() -> System.currentTimeMillis()*/
			/*() -> Math.hypot(Math.random(), Math.random()),
			() -> {
				double t1 = Math.random();
				double t2 = Math.random();
				Math.sqrt(t1*t1 + t2*t2);
			}*/
			() -> {double a = Math.random();},
		
	};
	public static void main(String[] args){
		long time = System.currentTimeMillis();
		generateRandomDelay();
		for(DelayFunction function : functions){
			double t = System.currentTimeMillis();
			for(int i = 0; i < count; i++)
				function.apply();
			t = System.currentTimeMillis() - t;
			System.out.printf("%s : %s,\t%s%n", function, t/count - randomDelay, (t/count - randomDelay)/randomDelay);
		}
		System.out.println(System.currentTimeMillis() - time);
	}
	
	private static void generateRandomDelay(){
		long t = System.currentTimeMillis();
		for(int i = 0; i < count; i++)
			Math.random();
		randomDelay = (double)(System.currentTimeMillis() - t)/count;
		System.out.printf("Random delay : %s%n", randomDelay);
	}
	
	
	
	private interface DelayFunction {
		public void apply();
		public default long delay(){
			long t = System.currentTimeMillis();
			apply();
			return System.currentTimeMillis() - t;
		}
	}
}
