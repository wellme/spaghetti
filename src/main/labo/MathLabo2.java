package main.labo;

import javax.swing.JOptionPane;

import math.number.Modulo;
import math.number.matrix.Matrix;
import math.number.matrix.Table;
public class MathLabo2 {

	/*@SuppressWarnings("unused")
	private static final Matrix<Modulo.Integer> G;
	private static final Matrix<Modulo.Integer> H;
	private static final Matrix<Modulo.Integer> D;
	private static final Matrix<Modulo.Integer> S;

	private static final Modulo mod2 = new Modulo(2);
	private static final Modulo mod8 = new Modulo(8);
	
	private static final String input = JOptionPane.showInputDialog("Binaire : ");
	
	static {
		Integer[][] g = {
				{1, 1, 1, 0, 0, 0, 0},
				{1, 0, 0, 1, 1, 0, 0},
				{0, 1, 0, 1, 0, 1, 0},
				{1, 1, 0, 1, 0, 0, 1}
		};
		Integer[][] h = {
				{0, 0, 0, 1, 1, 1, 1},
				{0, 1, 1, 0, 0, 1, 1},
				{1, 0, 1, 0, 1, 0, 1}
		};
		Integer[][] d = {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{1, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		};
		Integer[][] s = {
				{4},
				{2},
				{1}
		};
		
		G = new Table<Integer>(g).<Modulo.Integer>cast(x -> mod2.new Integer(x));
		H = new Table<Integer>(h).<Modulo.Integer>cast(x -> mod2.new Integer(x));
		D = new Table<Integer>(d).<Modulo.Integer>cast(x -> mod2.new Integer(x));
		S = new Table<Integer>(s).<Modulo.Integer>cast(x -> mod8.new Integer(x));
	}
	
	public static void main(String[] args){
		Modulo.Integer[][] temp = new Modulo.Integer[input.length()/7][7];
		for(int i = 0; i < input.length(); i += 7)
			for(int j = 0; j < 7; j++)
				temp[i/7][j] = mod2.new Integer(input.charAt(i+j)-'0');
		Matrix<Modulo.Integer> inputMatrix = new Matrix<Modulo.Integer>(temp);
		
		Table<Integer> errorMatrix = inputMatrix.mul(H.transpose()).<Modulo.Integer>cast(x -> mod8.new Integer(x.value)).mul(S).<Integer>castTable(x -> x.value);
		
		Integer[][] c = new Integer[errorMatrix.getRows()][errorMatrix.getColumns()];
		for(int i = 0; i < c.length; i++){
			Integer[] emptyLine = {0,0,0,0,0,0,0};
			c[i] = emptyLine;
			if(errorMatrix.get(i, 0) != 0)
				c[i][errorMatrix.get(i, 0) - 1] = 1;
		}
		Matrix<Modulo.Integer> correctionMatrix = new Table<Integer>(c).<Modulo.Integer>cast(x -> mod2.new Integer(x));
		Matrix<Modulo.Integer> outputMatrix = inputMatrix.add(correctionMatrix).mul(D);
		String s = "";
		for(int i = 0; i < outputMatrix.rows; i += 2){
			char ch = (char)outputMatrix.get(i, 0).value;
			for(int j = 1; j < 8; j++){
				ch <<= 1;
				ch += outputMatrix.get(i + j/4, j%4).value;
			}
			s += ch;
		}
		JOptionPane.showMessageDialog(null, s);
	}*/
}
