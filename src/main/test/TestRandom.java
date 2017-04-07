package main.test;

public class TestRandom {

	public static void main(String[] args) {
		int i = 1;
		double z = 1;
		double exp = 0.001;
		while(true) {
			if((++i & 1) == 0)
				z -= 1/Math.pow(i, exp);
			else
				z += 1/Math.pow(i, exp);
			int test = i % 100000000;
			if(test >> 2 == 0)
				System.out.printf("%s : %s%n", i, z);
			
		}
	}
	
}
