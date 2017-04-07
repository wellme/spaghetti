package utils.multithreading;

public interface Result<R extends Result<R>> {

	public static final class NoResult implements Result<NoResult> {
		
		public static final NoResult instance = new NoResult();
		
		private NoResult() { }

		@Override
		public NoResult combine(NoResult result) {
			return instance;
		}
		
	}
	
	public R combine(R result);
}
