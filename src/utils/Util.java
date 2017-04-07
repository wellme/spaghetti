package utils;

import java.util.function.Function;

public class Util {

	
	public static double hypot(double x, double y){
		return Math.sqrt(x*x + y*y);
	}
	public static double cos(double x){
		return Math.cos(x);
	}
	public static double sin(double x){
		double cos = Math.cos(x);
		return Math.sqrt(1 - cos*cos);
	}
	
	
	public static Function<Double, Double> uniformIsomorphism(double xMin, double xMax, double yMin, double yMax){
		return (x) -> (x - xMin) / (xMax - xMin) * (yMax - yMin) + yMin;
	}
	
	public static double uniformIsomorphism(double x, double xMin, double xMax, double yMin, double yMax){
		return (x - xMin) / (xMax - xMin) * (yMax - yMin) + yMin;
	}
	
	public static double uniformIsomorphismMod(double x, double mod, double yMin, double yMax){
		return ((x % mod + mod) % mod) / mod * (yMax - yMin) + yMin;
	}
}
