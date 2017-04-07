package math.number;

import utils.Util;

public class Complex{

	private double re;
	private double im;
	private double r;
	private double phi;
	private byte generation = 0b000;
	
	private Complex(double re, double im, double r, double phi){
		this.re = re;
		this.im = im;
		this.r = r;
		this.phi = fixAngle(phi);
		generation = 0b111;
	}
	public Complex(double re, double im){
		this.re = re;
		this.im = im;
		generation = 0b100;
	}
	
	public Complex(double re){
		this(re, 0, re, (re < 0) ? Math.PI : 0);
	}
	
	public String toString(){
		return String.format("[%s, %s, %s, %s]", getRe(), getIm(), getR(), getPhi());
	}
	
	public Complex add(Complex c2){
		return new Complex(getRe() + c2.getRe(), getIm() + c2.getIm());
	}
	public Complex sub(Complex c2){
		return new Complex(getRe() - c2.getRe(), getIm() - c2.getIm());
	}
	public Complex mul(Complex c2){
		return new Complex(getRe()*c2.getRe() - getIm()*c2.getIm(), getIm()*c2.getRe() + getRe()*c2.getIm(), getR()*c2.getR(), getPhi()+c2.getPhi());
	}
	public Complex div(Complex c2){
		return new Complex((getRe()*c2.getRe()+getIm()*c2.getIm())/(c2.getRe()*c2.getRe()+c2.getIm()*c2.getIm()),
							(getRe()*c2.getRe()+getIm()*c2.getIm())/(c2.getRe()*c2.getRe()+c2.getIm()*c2.getIm()),
							getR()/c2.getR(), getPhi()-c2.getPhi());
	}
	public Complex pow(Complex c2){
		return ln().mul(c2).exp();
	}	
	public Complex mul(double d){
		if(d > 0)
			return new Complex(getRe()*d, getIm()*d, getR()*d, getPhi());
		else
			return new Complex(getRe()*d, getIm()*d, getR()*-d, getPhi()+Math.PI);
	}
	public Complex div(double d){
		return mul(1/d);
	}
	public Complex add(double d){
		return new Complex(getRe() + d, getIm());
	}
	public Complex sub(double d){
		return add(-d);
	}
	public Complex pow(double d){
		return ln().mul(d).exp();
	}
	public Complex exp(){
		return new Complex.Polar(Math.exp(getRe()), getIm());
	}
	public Complex ln(){
		return new Complex(Math.log(getR()), getPhi());
	}

	private double fixAngle(double angle){
		angle %= Math.PI*2;
		if(angle < 0)
			angle += Math.PI*2;
		return angle;
	}
	public static class Cartesian extends Complex{
		public Cartesian(double re, double im) {
			super(re, im);
		}
	}
	
	public static class Polar extends Complex{
		public Polar(double r, double phi){
			super(Math.cos(phi)*r, Util.sin(phi)*r, r, phi);
		}
	}

	private void generateReIm(){
		re = Util.sin(phi) * r;
		im = Math.sqrt(r*r - re*re);
		generation += 0b100;
	}
	public double getRe() {
		if((generation & 0b100) == 0)
			generateReIm();
		return re;
	}
	public double getIm() {
		if((generation & 0b100) == 0)
			generateReIm();
		return im;
	}
	public double getR() {
		if((generation & 0b010) == 0){
			r = Util.hypot(re, im);
			generation += 0b010;
		}
		return r;
	}
	public double getPhi() {
		if((generation & 0b001) == 0){
			phi = Math.atan2(re, im);
			generation += 0b001;
		}
		return phi;
	}
}
