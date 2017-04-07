package math;

public class Couple<T> {

	public final T t1;
	public final T t2;
	
	public Couple(T t1, T t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	/*@SuppressWarnings("unchecked")
	public Couple(T... t){
		t1 = t[0];
		t2 = t[1];
	}*/
}
