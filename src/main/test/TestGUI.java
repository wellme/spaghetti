package main.test;

import javax.swing.JFrame;

import math.number.Complex;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import gui.graphic.color.ComplexGraph;
import gui.utils.GraphicModel;

import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import gui.utils.components.DoubleSlider;


public class TestGUI extends JFrame {
	

	public static void main(String[] args){
		new TestGUI().setVisible(true);
	}
	
	private static final long serialVersionUID = 1329360556053357153L;
	
	private ComplexGraph complexGraph;
	
	public TestGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 1280, 720);
		complexGraph = new ComplexGraph(x -> x.sub(-2).mul(x.sub(-1)).mul(x).mul(x.sub(1)).mul(x.sub(2)).pow(-1));
		complexGraph.setModel(new GraphicModel(-1, 1, -1, 1));
		complexGraph.setBounds(10, 10, 500, 500);
		getContentPane().add(complexGraph);
		
		DoubleSlider doubleSlider = new DoubleSlider();
		doubleSlider.setDoubleMinimum(0);
		doubleSlider.setDoubleMaximum(5);
		doubleSlider.setValue(0);
		doubleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double value = doubleSlider.getDoubleValue();
				complexGraph.setModel(new GraphicModel(-value, value, -value, value));
			}
		});
		doubleSlider.setBounds(520, 10, 200, 23);
		getContentPane().add(doubleSlider);
	}
}
