package math.number.matrix;

import java.util.Arrays;

public class Matrix<R> extends Table<R> {

	public Matrix(R[][] matrix){
		super(matrix);
	}


	/*public Matrix<R> add(Matrix<R> mat){
		ClosedOperator<R> add = (ClosedOperator<R>)STRUCTURE_ELEMENTS.getAlgebraicStructure().getOperator().get("+");
		if(add == null)
			throw new NoSuchOperationException();
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, columns));
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				temp[i][j] = add.operation(matrix[i][j], (mat.matrix[i][j]));
		return new Matrix<R>(temp, STRUCTURE_ELEMENTS);
	}

	public Matrix<R> mul(Matrix<R> mat){
		ClosedOperator<R> add = STRUCTURE_ELEMENTS.getAlgebraicStructure().getOperator().get("+");
		ClosedOperator<R> mul = STRUCTURE_ELEMENTS.getAlgebraicStructure().getOperator().get("*");
		if(add == null || mul == null)
			throw new NoSuchOperationException();
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, mat.columns));
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < mat.columns; j++){
				R r = mul.operation(matrix[i][0], mat.matrix[0][j]);
				for(int c = 1; c < columns; c++)
					r = add.operation(r, mul.operation(matrix[i][c], mat.matrix[c][j]));
				temp[i][j] = r;
			}
		return new Matrix<R>(temp, STRUCTURE_ELEMENTS);
	}
	
	public Matrix<R> mul(R scal){
		ClosedOperator<R> mul = STRUCTURE_ELEMENTS.getAlgebraicStructure().getOperator().get("*");
		if(mul == null)
			throw new NoSuchOperationException();
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, columns));
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				temp[i][j] = mul.operation(scal, matrix[i][j]);
		return new Matrix<R>(temp, STRUCTURE_ELEMENTS);
	}
	
	@Override
	public Matrix<R> transpose(){
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), columns, rows));
		for(int i = 0; i < columns; i++)
			for(int j = 0; j < rows; j++)
				temp[i][j] = matrix[j][i];
		return new Matrix<R>(temp, STRUCTURE_ELEMENTS);
	}
	public <T extends Structure<T>> Matrix<T> cast(Function<R, T> converter){
		@SuppressWarnings("unchecked")
		T[][] temp = (T[][])(Array.newInstance(converter.apply(matrix[0][0]).getClass(), rows, columns));
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				temp[i][j] = converter.apply(matrix[i][j]);
		return new Matrix<T>(temp, null);
	}*/

	public String toString(){
		String ans = "";
		for(int i = 0; i < rows; i++)
			ans += Arrays.toString(matrix[i])+",\n";
		ans += "";
		return ans;
	}

	/*@Override
	public Matrix<R> getAdditiveIdentity() {
		R identity = matrix[0][0].getAdditiveIdentity();
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, columns));
		for(int i = 0; i < temp.length; i++)
			for(int j = 0; j < temp[0].length; j++)
				temp[i][j] = identity;
		return new Matrix<R>(temp, STRUCTURE_ELEMENTS);
	}


	@Override
	public Matrix<R> getAdditiveInverse() {
		@SuppressWarnings("unchecked")
		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, columns));
		for(int i = 0; i < temp.length; i++)
			for(int j = 0; j < temp[0].length; j++)
				temp[i][j] = matrix[i][j].getAdditiveInverse();
		return new Matrix<R>(temp);
	}*/
}
