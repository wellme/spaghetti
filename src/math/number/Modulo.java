package math.number;

public class Modulo {

	public final int modulus;
	
	public Modulo(int modulus){
		this.modulus = modulus;
	}
	
	public class Integer{
		public final int value;
		

		public Integer(int value){
			this.value = value;
		}
		
		public Modulo.Integer add(Modulo.Integer i){
			return new Modulo.Integer((value + i.value) % modulus);
		}
		public Modulo.Integer mul(Modulo.Integer i){
			return new Modulo.Integer((value * i.value) % modulus);
		}
		
		public Modulo.Integer getAdditiveIdentity(){
			return new Modulo.Integer(0);
		}
		public Modulo.Integer getAdditiveInverse() {
			return new Modulo.Integer(-value);
		}
		public Modulo.Integer getMultiplicativeIdentity() {
			return new Modulo.Integer(1);
		}
		
		public String toString(){
			return ""+value;
		}
		public boolean equals(Object o){
			if(o.getClass().equals(getClass()))
				return value == ((Modulo.Integer)o).value;
			return false;
		}
	}
}
