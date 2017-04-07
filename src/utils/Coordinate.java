package utils;

import java.awt.geom.Point2D;

import math.Couple;

public class Coordinate extends Couple<Double>{

	public Coordinate(Double t1, Double t2) {
		super(t1, t2);
	}
	
	public Coordinate(double t1, double t2){
		super(t1, t2);
	}
	
	public static Coordinate fromPoint2D(Point2D p){
		return new Coordinate(p.getX(), p.getY());
	}
}
