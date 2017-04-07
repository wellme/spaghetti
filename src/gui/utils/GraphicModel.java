package gui.utils;

public class GraphicModel {

	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private double componentWidth;
	private double componentHeight;
	
	public GraphicModel(double xMin, double xMax, double yMin, double yMax, double componentWidth, double componentHeight) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.componentWidth = componentWidth;
		this.componentHeight = componentHeight;
	}
	
	public GraphicModel(double xMin, double xMax, double yMin, double yMax){
		this(xMin, xMax, yMin, yMax, 0, 0);
	}
	
	public double getXMin() {
		return xMin;
	}
	public void setXMin(double xMin) {
		this.xMin = xMin;
	}
	public double getXMax() {
		return xMax;
	}
	public void setXMax(double xMax) {
		this.xMax = xMax;
	}
	public double getYMin() {
		return yMin;
	}
	public void setYMin(double yMin) {
		this.yMin = yMin;
	}
	public double getYMax() {
		return yMax;
	}
	public void setYMax(double yMax) {
		this.yMax = yMax;
	}
	public double getComponentWidth() {
		return componentWidth;
	}
	public void setComponentWidth(double componentWidth) {
		this.componentWidth = componentWidth;
	}
	public double getComponentHeight() {
		return componentHeight;
	}
	public void setComponentHeight(double componentHeight) {
		this.componentHeight = componentHeight;
	}
	
}
