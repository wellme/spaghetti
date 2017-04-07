package gui.utils.translation;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class TranslationData {

	private double translationX = 0;
	private double translationY = 0;
	
	private JComponent component;
	
	private ArrayList<DragTranslation> drags = new ArrayList<DragTranslation>();
	
	public TranslationData(JComponent c){
		component = c;
	}
	
	public void apply(Graphics2D g, double scale){
		double x = 0, y = 0;
		for(DragTranslation drag : drags){
			x += drag.getResultingTranslationX();
			y += drag.getResultingTranslationY();
		}
		g.translate((translationX + x)/scale, (translationY + y)/scale);
	}
	
	public double getTranslationX(){
		double x = 0;
		for(DragTranslation drag : drags)
			x += drag.getResultingTranslationX();
		return x;
	}
	public double getTranslationY(){
		double y = 0;
		for(DragTranslation drag : drags)
			y += drag.getResultingTranslationY();
		return y;
	}
	
	public DragTranslation newDragTranslation(){
		DragTranslation translation = new DragTranslation(component);
		drags.add(translation);
		return translation;
	}
	
	public void translate(double x, double y){
		translationX += x;
		translationY += y;
		component.repaint();
	}
}
