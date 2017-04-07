package gui.graphic.color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.function.Function;

import javax.swing.JPanel;

import gui.utils.GraphicModel;
import gui.utils.MatrixTransform;
import math.Couple;
import utils.Colors;
import utils.Coordinate;

public class ColorGraph extends JPanel{
	
	private static final long serialVersionUID = 3077903067615675837L;
	private Function<Couple<Double>, Colors> function = x -> new Colors.RGB(0);
	private Colors[][] data;
	private boolean updateRequested = true;
	private MatrixTransform transform = null;
	
	
	private GraphicModel model;
	
	
	public ColorGraph(Function<Couple<Double>, Colors> function){
		this.function = function;
	}
	
	private void generate(){
		updateRequested = false;
		long time = System.currentTimeMillis();
		transform = new MatrixTransform(model.getXMin(), model.getXMax(), model.getYMin(),
										model.getYMax(), (model.getXMax() + model.getXMin()) / 2,
										(model.getYMax() + model.getYMin()) / 2,
										getWidth(), getHeight());
		data = new Colors[getWidth()][getHeight()];
		if(function == null)
			for(int x = 0; x < getWidth(); x++)
				for(int y = 0; y < getHeight(); y++)
					data[x][y] = new Colors.RGB(0xffffff);
		else
			for(int x = 0; x < getWidth(); x++)
				for(int y = 0; y < getHeight(); y++)
					data[x][y] = function.apply(Coordinate.fromPoint2D(transform.getMatrix().transform(new Point2D.Double(x, y), null)));
		System.out.printf("Generating : %s%n", System.currentTimeMillis() - time);
	}
	
	long sum = 0;
	int count = 0;
	
	public void paintComponent(Graphics g){
		long time = System.currentTimeMillis();
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		if(updateRequested)
			generate();
		time = System.currentTimeMillis();
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++){
				g2d.setColor(data[x][y].toColor());
				g2d.drawRect(x, y, 1, 1);
			}
		/*repaint();
		sum += System.currentTimeMillis()-time;
		count++;
		System.out.println((double)sum/count + "\t" + (System.currentTimeMillis()-time));*/
	}

	public void requestUpdate(){
		updateRequested = true;
	}
	
	public void setModel(GraphicModel model){
		this.model = model;
		requestUpdate();
		repaint();
	}
	
}
