package gui.utils.components;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class DoubleSlider extends JSlider{

	private static final long serialVersionUID = 3914710609039639021L;
	private double tick;
	private int digits;
	
	public DoubleSlider(){
		setTick(0.01);
		setMinimum((int) (0/tick));
		setMaximum((int) (1/tick));
	}
	
	
	public void setTick(double tick){
		this.tick = tick;
	}
	public double getDoubleValue(){
		return getValue()*tick;
	}
	public void setDoubleMaximum(double max){
		setMaximum((int) (max/tick));
	}
	public void setDoubleMinimum(double min){
		setMinimum((int) (min/tick));
	}
	public double getDoubleMinimum(){
		return getMinimum()*tick;
	}
	public double getDoubleMaximum(){
		return getMaximum()*tick;
	}
	public void setMajorTickSpacing(double spacing){
		super.setMajorTickSpacing((int) (spacing/tick));
		updateLabels();
	}
	public void setMinorTickSpacing(double spacing){
		super.setMinorTickSpacing((int) (spacing/tick));
	}
	
	public void setLabelDigits(int i){
		digits = i;
		updateLabels();
	}
	//TODO fix labels
	private void updateLabels(){
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		for(double i = getDoubleMinimum(); i <= getDoubleMaximum(); i += getMajorTickSpacing()*tick){
			//System.out.printf("%s\t%<.2f%n", i);
			table.put((int) (i*tick), new JLabel(String.format("%."+digits+"f", i)));
		}
		setLabelTable(table);
	}
}
