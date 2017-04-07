package utils.multithreading;

import java.util.ArrayList;

import math.Pair;

public class Job<C extends Context<C, R>, R extends Result<R>> {
	
	private final ArrayList<Thread> threads = new ArrayList<Thread>();
	private final C context;
	private R result;
	private int threadCount = 3;
	
	private Object lock = new Object(); //?
	
	public Job(C context) {
		this.context = context;
	}
	
	public Job(C context, int threadCount){
		this(context);
		this.threadCount = threadCount;
	}
	
	private void refreshThreads() {
		threads.removeIf(t -> !t.isAlive() || t.isInterrupted());
	}
	
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
		refreshThreads();
		lock.notifyAll();
	}
	
	public R execute() {
		newThreads(threadCount);
		while(context.hasNext() && threads.size() != 0) {
			try {
				System.out.println("Oh noes!2 ");
				synchronized (lock) {
					lock.wait();
				}
				System.out.println("Oh yas!2 ");
				refreshThreads();
				if(context.hasNext())
					newThreads(threadCount - threads.size());
				removeThreads(threads.size() - threadCount);
				refreshThreads();
			} catch (InterruptedException e) {
				
			}
		}
		return result;
	}
	
	private void removeThreads(int n) {
		refreshThreads();
		for(int i = 0; i < Integer.min(n, threads.size()); i++)
			threads.get(i).interrupt();
	}
	
	private void newThreads(int n) {
		for(int i = 0; i < n; i++) {
			Thread t = new Thread(() -> {
				
				if(!context.getIterator().hasNext()) {
					threads.remove(this);
					System.out.println("Oh noes!");
					synchronized (lock) {
						lock.notifyAll();
					}
					System.out.println("Oh yas!");
					return;
				}
				
				Task<C, R> task = context.getIterator().next();
				System.out.println("Executing");
				Pair<R, ExecutionResult> results = task.execute();
				System.out.println("Executed");
				switch (results.t2) {
					case INTERRUPTED:
						context.reschedule(task);
						break;
					case FAILURE:
						System.err.println("Error while processing task : " + task);
						break;
					case SUCCESS:
					default:
						R temp = results.t1;
						if(result == null)
							result = temp;
						else
							synchronized (result) {
								result.combine(temp);
							}
				}
				threads.remove(this);
				synchronized (lock) {
					lock.notifyAll();
				}
			});
			
			threads.add(t);
			t.start();
		}
	}
}
