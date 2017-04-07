package math.number.matrix;

//public class SquareMatrix<R extends MathematicalStructure<R>> extends Matrix<R> implements MathematicalStructure<SquareMatrix<R>>{
//
//	public final int size;
//	
//	public SquareMatrix(R[][] matrix) {
//		super(matrix);
//		size = matrix.length;
//	}
//
//	/*@Override
//	public SquareMatrix<R> getMultiplicativeIdentity() {
//		R additiveIdentity = matrix[0][0].getAdditiveIdentity();
//		R multiplicativeIdentity = matrix[0][0].getMultiplicativeIdentity();
//		@SuppressWarnings("unchecked")
//		R[][] temp = (R[][])(Array.newInstance(matrix[0][0].getClass(), rows, columns));
//		for(int i = 0; i < temp.length; i++)
//			for(int j = 0; j < temp[0].length; j++)
//				if(i == j)
//					temp[i][j] = multiplicativeIdentity;
//				else
//					temp[i][j] = additiveIdentity;
//		return new SquareMatrix<R>(temp);
//	}
//	@Override
//	public SquareMatrix<R> getMultiplicativeInverse() {
//		
//		return null;
//	}*/
//	
//	public SquareMatrix<R> adj(){
//		return null;
//	}
//	public SquareMatrix<R> cof(){
//		return null;
//	}
//	
//	public R determinant(){
//		return null;
//	}
//	public R cofactor(int i, int j){
//		return null;
//	}
//	public R minor(int i, int j){
//		return null;
//	}
//}
