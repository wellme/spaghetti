package gui.graphic;

import gui.utils.components.DoubleSlider;

public class GraphicController extends DoubleSlider{
	
	
	private static final long serialVersionUID = 4536248003690679300L;


	public GraphicController(){
		this(0, 1);
	}
	
	public GraphicController(double min, double max){
		this(min, max, 0.01);
	}
	
	public GraphicController(double min, double max, double tick){
		setTick(tick);
		setDoubleMinimum(min);
		setDoubleMaximum(max);
	}
	
	
	public static class AngleSlider extends GraphicController{

		private static final long serialVersionUID = -8669439390306157182L;
		private static final double TICK = 0.0001;
		public AngleSlider(){
			super(-Math.PI, Math.PI, TICK);
			/*Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			for(double i = -Math.PI; i <= Math.PI; i += Math.PI/4){
				System.out.printf("%s\t%<.2f\t38%n", i);
				table.put((int) (i*TICK), new JLabel(String.format("%.2f", i)));
			}*/
			setMajorTickSpacing(Math.PI/4);
			//setLabelTable(table);
			//setSnapToTicks(true);
			//setPaintLabels(true);
			setPaintTicks(true);
			//setLabelDigits(2);
		}
	}
}
