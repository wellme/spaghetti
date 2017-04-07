package gui.graphic.color;

import java.util.function.Function;

import math.Couple;
import math.number.Complex;
import utils.Colors;
import utils.Util;

public class ComplexGraph extends ColorGraph {
	//		Math.exp(-x.r/128)
	//		1/(x.r/32+1)
	public static final Function<Complex, Colors> CONVERT_COMPLEX_TO_COLOR = x -> new Colors.HSV(
						x.getPhi(),
						Util.uniformIsomorphismMod(Math.log(x.getR())/Math.log(2), 1, 0.7, 1),
						1/(x.getR()/32+1)
					);
	public static final Function<Couple<Double>, Complex> CONVERT_PAIR_TO_COMPLEX = p -> new Complex(p.t1, p.t2);
	
	
	public ComplexGraph(Function<Complex, Complex> f){
		super(CONVERT_COMPLEX_TO_COLOR.compose(f.compose(CONVERT_PAIR_TO_COMPLEX)));
	}
	
}
