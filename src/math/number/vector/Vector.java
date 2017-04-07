package math.number.vector;

public class Vector {

	private final double x;
	private final double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	public double getY() {
		return y;
	}
	
	public Vector add(Vector v){
		return new Vector(x + v.x, y + v.y);
	}
	public Vector sub(Vector v){
		return new Vector(x - v.x, y - v.y);
	}
	public double norm(){
		return Math.hypot(x, y);
	}
}
