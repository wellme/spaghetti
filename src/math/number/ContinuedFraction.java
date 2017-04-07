package math.number;

import java.util.ArrayList;
import java.util.Arrays;

public class ContinuedFraction {


	private long[] coefs;
	
	public ContinuedFraction(double x){
		ArrayList<Long> coefs = new ArrayList<Long>();
		long e = (long) Math.floor(x);
		double f = x % 1;
		coefs.add(e);
		System.out.printf("%s, %s, %s%n", x, f, e);
		while(f >= 0.05){
			x = 1/f;
			f = x%1;
			e = (long) Math.floor(x);
			coefs.add(e);
			System.out.printf("%s, %s, %s%n", x, f, e);
		}
		Long[] temp = coefs.toArray(new Long[0]);
		this.coefs = new long[temp.length];
		for(int i = 0; i < temp.length; i++)
			this.coefs[i] = temp[i];
	}
	
	public static void main(String[] args){
		System.out.println(new ContinuedFraction(Math.PI).toFraction().getValue());
	}
	
	public Fraction toFraction(){
		Fraction fraction = new Fraction(1, coefs[coefs.length - 1]);
		
		for(int i = coefs.length - 2; i >= 0; i--)
			fraction = new Fraction(coefs[i], 1).add(fraction).inverse();
		
		return fraction.inverse();
	}
	
	public String toString(){
		return Arrays.toString(coefs);
	}
	
	public ContinuedFraction(long[] coefs){
		this.coefs = coefs;
	}
	
	
	public long[] getCoefs(){
		return coefs;
	}
}
