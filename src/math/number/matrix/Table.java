package math.number.matrix;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class Table<T> {

	
	final T[][] matrix;
	public final int rows;
	public final int columns;
	
	public Table(T[][] matrix){
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
	}
	
	public T get(int i, int j){
		return matrix[i][j];
	}
	
	public Table<T> transpose(){
		@SuppressWarnings("unchecked")
		T[][] temp = (T[][])(Array.newInstance(matrix[0][0].getClass(), matrix[0].length, matrix.length));
		for(int i = 0; i < temp.length; i++)
			for(int j = 0; j < temp[0].length; j++)
				temp[i][j] = matrix[j][i];
		return new Table<T>(temp);
	}
	
	public <S> Table<S> castTable(Function<T, S> converter){
		@SuppressWarnings("unchecked")
		S[][] temp = (S[][])(Array.newInstance(converter.apply(matrix[0][0]).getClass(), matrix.length, matrix[0].length));
		for(int i = 0; i < temp.length; i++)
			for(int j = 0; j < temp[0].length; j++)
				temp[i][j] = converter.apply(matrix[i][j]);
		return new Table<S>(temp);
	}
	public <R> Matrix<R> cast(Function<T, R> converter){
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(converter.apply(matrix[0][0]).getClass(), matrix.length, matrix[0].length));
		for(int i = 0; i < temp.length; i++)
			for(int j = 0; j < temp[0].length; j++)
				temp[i][j] = converter.apply(matrix[i][j]);
		return new Matrix<R>(temp);
	}
	
	public String toString(){
		String ans = "";
		for(int i = 0; i < matrix.length; i++)
			ans += Arrays.toString(matrix[i])+",\n";
		ans += "";
		return ans;
	}
	
	public int getColumns() {
		return columns;
	}
	public int getRows() {
		return rows;
	}
	
	public Dimension getDimension(){
		return new Dimension(rows, columns);
	}

}
