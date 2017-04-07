package math.expression.symbolic;

public class Value<N> extends SymbolicExpression<N, N>{
	
	private final N value;
	
	
	public Value(N n){
		value = n;
	}
	
	public N getValue(){
		return value;
	}
	
}
