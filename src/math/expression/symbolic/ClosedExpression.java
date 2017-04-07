package math.expression.symbolic;

import math.structure.BinaryOperator;

public class ClosedExpression<N> extends SymbolicExpression<N, N> {

	private final SymbolicExpression<?, N>[] lower;
	private final BinaryOperator<N, N, N> operator;
	
	public ClosedExpression() {
		lower = null;
		operator = null;
	}
	
	public N getValue(){
		N value = lower[0].getValue();
		for(int i = 1; i < lower.length; i++)
			value = operator.operation(value, lower[i].getValue());
		return value;
	}
}
