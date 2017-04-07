package main.ulam;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import math.number.vector.Vector;

public class KMean implements Info {
	
	private static final double DATA_DIAMETER = 5;
	private static final double OBSERVATION_DIAMETER = 10;

	private Vector[] observations;
	private Vector[] data;
	private State state;
	private int prevID;
	
	
	public KMean(int dataLength, int middlePointsLength){
		state = State.VORONOI;
		prevID = Ulam.mode;
		data = new Vector[dataLength];
		for(int i = 0; i < data.length; i++)
			data[i] = new Vector(Math.random(), Math.random());
		observations = new Vector[dataLength];
		for(int i = 0; i < data.length; i++)
			observations[i] = new Vector(Math.random(), Math.random());
		
	}
	
	
	@Override
	public int call(double x, double y, int n, double xRaw, double yRaw) {
		if(prevID != 0)
			nextStep();
		return 0;
	}
	@Override
	public void draw(Graphics2D g){
		try{
			for(int x = 0;x<Ulam.SIZEX;x++)
				for(int y = 0;y<Ulam.SIZEY;y++){
					g.setColor(Ulam.data[x][y]);
					g.drawRect(x, y, 1, 1);
				}
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Whoops");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	private void nextStep(){
		if(state == State.VORONOI){
			doVoronoi();
			state = State.MEAN;
		} else {
			doMean();
			state = State.VORONOI;
		}
	}
	
	private void doVoronoi(){
		
	}
	
	private void doMean(){
		
	}

	private enum State{
		VORONOI, MEAN;
	}
}
