package gui.utils;

import java.awt.geom.AffineTransform;

public class MatrixTransform {

	private AffineTransform matrix;
	
	public MatrixTransform(double xMin, double xMax, double yMin, double yMax, double xOrigin, double yOrigin, double xSize, double ySize){
		matrix = new AffineTransform();
		matrix.translate(xOrigin - (xMax - xMin)/2, -yOrigin - (yMax - yMin)/2);
		matrix.scale((xMax - xMin)/xSize, (yMax - yMin)/ySize);
		/*System.out.printf("Translation : [%.3f, %.3f]%nScale : [%.3f, %.3f]%n",
							-xOrigin - (xMax - xMin)/2, yOrigin - (yMax - yMin)/2,
							(xMax - xMin)/xSize, (yMax - yMin)/ySize);*/
	}
	
	public AffineTransform getMatrix(){
		return new AffineTransform(matrix);
	}
}
