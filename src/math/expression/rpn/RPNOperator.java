package math.expression.rpn;

import math.structure.BinaryOperator;

public class RPNOperator<N> {
	
	
	private double precedence;
	private BinaryOperator<N, N, N> operator;
	
	
	public double getPrecedence(){
		return precedence;
	}
}
