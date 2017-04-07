package math;

import java.io.Serializable;

public class Pair<T1, T2> implements Serializable {
	
	
	private static final long serialVersionUID = -2869086656378874205L;
	
	
	public final T1 t1;
	public final T2 t2;
	
	public Pair(T1 t1, T2 t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	
	
	public String toString() {
		return String.format("[%s, %s]", t1, t2);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Pair))
			return false;
		Pair<?, ?> pair = (Pair<?, ?>)o;
		return t1.equals(pair.t1) && t2.equals(pair.t2);
	}

}
