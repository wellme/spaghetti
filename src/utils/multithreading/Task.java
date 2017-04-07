package utils.multithreading;

import math.Pair;

public abstract class Task<C extends Context<C, R>, R extends Result<R>> {
	
	protected final C context;

	public Task(C context) {
		this.context = context;
	}
	
	public abstract Pair<R, ExecutionResult> execute();
}
