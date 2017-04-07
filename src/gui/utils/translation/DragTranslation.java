package gui.utils.translation;

import javax.swing.JComponent;

public class DragTranslation {
	
	private double translationX = 0;
	private double translationY = 0;

	private double tempTranslationX = 0;
	private double tempTranslationY = 0;
	
	private double initialTempTranslationX = 0;
	private double initialTempTranslationY = 0;
	
	private JComponent component;
	
	public DragTranslation(JComponent c){
		component = c;
	}
	
	public void startTranslation(double x, double y){
		tempTranslationX = x;
		tempTranslationY = y;
		initialTempTranslationX = x;
		initialTempTranslationY = y;
	}
	
	public void endTranslation(){
		translationX += tempTranslationX - initialTempTranslationX;
		translationY += tempTranslationY - initialTempTranslationY;
		tempTranslationX = 0;
		tempTranslationY = 0;
		initialTempTranslationX = 0;
		initialTempTranslationY = 0;
		component.repaint();
	}
	
	public void translate(double x, double y){
		tempTranslationX = x;
		tempTranslationY = y;
		component.repaint();
	}
	
	public double getResultingTranslationX(){
		return translationX + tempTranslationX - initialTempTranslationX;
	}
	public double getResultingTranslationY(){
		return translationY + tempTranslationY - initialTempTranslationY;
	}
}
