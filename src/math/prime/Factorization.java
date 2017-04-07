package math.prime;

import java.util.ArrayList;

import math.Couple;

public class Factorization {

	
	private Couple<Integer>[] values;
	
	public Factorization(int n) {
		ArrayList<Couple<Integer>> values = new ArrayList<Couple<Integer>>();
		
	}
	
	public String toString() {
		String ans = "";
		for(int i = 0; i < values.length; i++) {
			ans += values[i].t1 + "^" + values[i].t2 + " * ";
		}
		return ans.substring(0, ans.length() - 2);
	}
}
