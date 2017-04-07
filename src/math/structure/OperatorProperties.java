package math.structure;

public class OperatorProperties<S> {
	
	private final byte independantProperties;
	private final BinaryOperator<?, S, ?>[] distributivity;
	
	private final BinaryOperator<?, S, ?>[] absorption;
	
	public static final byte ASSOCIATIVITY = 1 << 0;
	public static final byte COMMUTATIVITY = 1 << 1;
	public static final byte INVERTIBILITY = 1 << 2;
	public static final byte IDEMPOTENCE = 1 << 3;
	
	public OperatorProperties(boolean associativity, boolean commutativity, boolean inversability, boolean idempotence,
			BinaryOperator<?, S, ?>[] distributivity, BinaryOperator<?, S, ?>[] absorption){
		byte temp = 0;
		if(associativity)
			temp += ASSOCIATIVITY;
		if(commutativity)
			temp += COMMUTATIVITY;
		if(inversability)
			temp += INVERTIBILITY;
		if(idempotence)
			temp += IDEMPOTENCE;
		
		independantProperties = temp;
		this.absorption = absorption;
		this.distributivity = distributivity;
	}
	public OperatorProperties(byte properties, BinaryOperator<?, S, ?>[] distributivity, BinaryOperator<?, S, ?>[] absorption){
		independantProperties = properties;
		this.distributivity = distributivity;
		this.absorption = absorption;
		
	}
	
	public boolean distributesOn(BinaryOperator<?, S, ?> op){
		for(BinaryOperator<?, S, ?> operator : distributivity)
			if(operator.equals(op))
				return true;
		return false;
	}
	public boolean absorbs(BinaryOperator<?, S, ?> op){
		for(BinaryOperator<?, S, ?> operator : absorption)
			if(operator.equals(op))
				return true;
		return false;
	}
	
	public boolean isAssociative(){
		return (independantProperties & ASSOCIATIVITY) != 0;
	}
	public boolean isCommutative(){
		return (independantProperties & COMMUTATIVITY) != 0;
	}
	public boolean isInvertible(){
		return (independantProperties & INVERTIBILITY) != 0;
	}
	public boolean isIdempotent(){
		return (independantProperties & IDEMPOTENCE) != 0;
	}
	
	
	public byte getIndependantProperties(){
		return independantProperties;
	}
}
