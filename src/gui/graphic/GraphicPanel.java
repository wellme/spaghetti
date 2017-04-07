package gui.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import gui.utils.translation.DragTranslation;
import gui.utils.translation.TranslationData;
import utils.Coordinate;

public class GraphicPanel extends JPanel {
	
	private static final long serialVersionUID = -4012583298711492381L;

	private static final Color LINE_COLOR = new Color(0x777777);
	private DrawableFunction[] functions = {new DrawableFunction(x -> x*x, 0x000000)};
	private ArrayList<LinkedList<Coordinate>> points = new ArrayList<LinkedList<Coordinate>>();

	private TranslationData translation = new TranslationData(this);
	
	private int steps;
	private double zoom = 1;
	
	public GraphicPanel(){
		setBackground(new Color(0xf8f8f8));
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paint((Graphics2D)g);
	}
	
	private void paint(Graphics2D g){
		System.out.printf("%s\t%s\t\t%s\t%s%n", getMinX(), getMaxX(), getMinY(), getMaxY());
		AffineTransform matrix = g.getTransform();
		g.setColor(LINE_COLOR);
		
		g.translate(getWidth()/2.0, getHeight()/2.0);
		g.scale(zoom, zoom);
		g.scale(1, 1);
		g.translate(-getWidth()/2.0, -getHeight()/2.0);
		
		translation.apply(g, zoom);
		
		g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
		g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
		
		for(DrawableFunction function : functions){
			g.setColor(function.getColor());
			Math.sin(steps);
		}
		
		g.setTransform(matrix);
	}
	
	public double getMinX(){
		return -translation.getTranslationX() - getWidth()/(zoom * 2);
	}
	
	public double getMaxX(){
		return -translation.getTranslationX() + getWidth()/(zoom * 2);
	}
	
	public double getMinY(){
		return translation.getTranslationY() - getHeight()/(zoom * 2);
	}
	
	public double getMaxY(){
		return translation.getTranslationY() + getHeight()/(zoom * 2);
	}
	
	
	public void setZoom(double zoom){
		this.zoom = zoom;
		repaint();
	}
	public double getZoom(){
		return zoom;
	}
	
	public DragTranslation newDragTranslation(){
		return translation.newDragTranslation();
	}
	
	
}
