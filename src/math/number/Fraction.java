package math.number;

import math.prime.NumberTheory;

public class Fraction {

	public static final Fraction MINUS_ONE = new Fraction(-1);
	public static final Fraction ZERO = new Fraction(0);
	public static final Fraction ONE = new Fraction(1);
	public static final Fraction TWO = new Fraction(2);
	
	public final long p;
	public final long q;
	
	
	public Fraction(long p) {
		this.p = p;
		q = 1;
	}
	
	public Fraction(long p, long q){
		this.p = p;
		this.q = q;
	}
	
	public double getValue(){
		return (double)p/q;
	}
	
	public String toString(){
		return p + "/" + q;
	}
	
	
	public Fraction add(Fraction f){
		return new Fraction(p * f.q + q * f.p, q * f.q);
	}
	
	public Fraction sub(Fraction f) {
		return add(f.mul(MINUS_ONE));
	}
	
	public Fraction mul(Fraction f) {
		return new Fraction(p * f.p, q * f.q);
	}
	
	public Fraction simplify() {
		long n = NumberTheory.gcd(q, p);
		return new Fraction(q/n, p/n);
	}
	
	public Fraction inverse(){
		return new Fraction(q, p);
	}
}
