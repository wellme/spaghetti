package utils.multithreading;

import java.util.Iterator;
import java.util.LinkedList;

public class Context<C extends Context<C, R>, R extends Result<R>> {

	private TaskIterator tasks;
	private Iterator<Task<C, R>> iterator;
	
	public Context(Iterator<Task<C, R>> iterator) {
		tasks = new TaskIterator(iterator);
		this.iterator = iterator;
	}
	
	protected Iterator<Task<C, R>> getIterator() {
		return iterator;
	}
	
	protected Context<C, R> getContext() {
		return this;
	}
	
	public final boolean hasNext() {
		return tasks.hasNext();
	}
	
	public final void reschedule(Task<C, R> task) {
		tasks.add(task);
	}
	
	private class TaskIterator implements Iterator<Task<C, R>> {
		
		private LinkedList<Task<C, R>> list = new LinkedList<Task<C, R>>();
		private Iterator<Task<C, R>> iterator;
		
		
		public TaskIterator(Iterator<Task<C, R>> iterator) {
			this.iterator = iterator;
		}
		
		
		private synchronized void add(Task<C, R> task) {
			list.add(task);
		}

		@Override
		public synchronized boolean hasNext() {
			if(!list.isEmpty())
				return true;
			return iterator.hasNext();
		}

		@Override
		public synchronized Task<C, R> next() {
			if(!list.isEmpty())
				return list.removeFirst();
			return iterator.next();
		}
	}
}
