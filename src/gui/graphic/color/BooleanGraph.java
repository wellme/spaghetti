package gui.graphic.color;

import java.util.function.Function;

import math.Couple;
import utils.Colors;

public class BooleanGraph extends ColorGraph {

	
	public static final Colors COLOR_TRUE = new Colors.RGB(0x000000);
	public static final Colors COLOR_FALSE = new Colors.RGB(0xffffff);
	
	public static final Function<Boolean, Colors> CONVERT_BOOLEAN_TO_COLOR = x -> x ? COLOR_TRUE : COLOR_FALSE;
	
	public BooleanGraph(Function<Couple<Double>, Boolean> function){
		super(CONVERT_BOOLEAN_TO_COLOR.compose(function));
		
	}
}
