package gui.graphic;

import java.awt.Color;
import java.util.function.Function;

public class DrawableFunction {

	private Function<Double, Double> function;
	private Color color;
	
	public DrawableFunction(Function<Double, Double> function, Color color){
		this.function = function;
		this.color = color;
	}
	public DrawableFunction(Function<Double, Double> function, int color){
		this.function = function;
		this.color = new Color(color);
	}
	
	public Color getColor(){
		return color;
	}
	
	public double eval(double x){
		return function.apply(x);
	}
	
}
