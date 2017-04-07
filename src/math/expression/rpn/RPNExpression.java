package math.expression.rpn;

import java.util.ArrayList;
import java.util.function.Function;

public class RPNExpression<N>{

	private RPNObject<N>[] objects;
	
	public RPNExpression(String expression, Function<String, N> parser){
		ArrayList<RPNObject<N>> list = new ArrayList<RPNObject<N>>();
		
	}
}
