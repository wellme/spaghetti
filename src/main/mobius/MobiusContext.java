package main.mobius;

import static math.prime.NumberTheory.*;

import java.util.Iterator;

import math.Pair;
import math.prime.NumberTheory;
import utils.ArrayList;
import utils.concurency.SynchronizedCounter;
import utils.multithreading.Context;
import utils.multithreading.ExecutionResult;
import utils.multithreading.Task;

public class MobiusContext extends Context<MobiusContext, ArrayList<ArrayList<Pair<Integer, Boolean>>>>{

	private ArrayList<Pair<Integer, Boolean>> list = new ArrayList<>();
	
	public MobiusContext(int max) {
		super(new MobiusIterator(max));
		((MobiusIterator) getIterator()).setContext(this);
	}
	
	public ArrayList<Pair<Integer, Boolean>> getList() {
		return list;
	}
	
	public static class MobiusIterator implements Iterator<Task<MobiusContext, ArrayList<ArrayList<Pair<Integer, Boolean>>>>> {

		private SynchronizedCounter counter = new SynchronizedCounter();
		private int max;
		private MobiusContext context;
		
		
		public MobiusIterator(int max) {
			this.max = max;
		}
		
		@Override
		public boolean hasNext() {
			return counter.get() < max;
		}

		@Override
		public synchronized Task<MobiusContext, ArrayList<ArrayList<Pair<Integer, Boolean>>>> next() {
			return new MobiusTask(context, counter.next());
		}
		
		public void setContext(MobiusContext context) {
			this.context = context;
		}
		
	}
	
	private static class MobiusTask extends Task<MobiusContext, ArrayList<ArrayList<Pair<Integer, Boolean>>>> {

		private static final int primoralSquare = (int)square(primoralPn(5));
		private static final int[] squareFreeList = MobiusTest.generateListOfNonSquareFreeIntegersLessThan(primoralSquare);
		
		private long n;
		
		public MobiusTask(MobiusContext context, long n) {
			super(context);
			this.n = n;
		}

		@Override
		public Pair<ArrayList<ArrayList<Pair<Integer, Boolean>>>, ExecutionResult> execute() {
			try {
				ArrayList<ArrayList<Pair<Integer, Boolean>>> list = new ArrayList<ArrayList<Pair<Integer, Boolean>>>();
				list.add(new ArrayList<Pair<Integer, Boolean>>());
				System.out.println("n : " + n);
				for(int i = 0, j = 0; i < squareFreeList.length; i++, j++) {
					if(Thread.interrupted())
						return new Pair<ArrayList<ArrayList<Pair<Integer, Boolean>>>, ExecutionResult>(list, ExecutionResult.INTERRUPTED);
					int mobius = mobius(squareFreeList[i] + primoralSquare * n);
					//System.out.println("lmao " + (squareFreeList[i] + primoralSquare * n) + " " + mobius);
					if(j == 10000) {
						j = 0;
						//System.out.println((squareFreeList[i] + primoralSquare * n) + " " + mobius);
					}
					if(mobius != 0)
						list.get(0).add(new Pair<Integer, Boolean>((int)(squareFreeList[i] + primoralSquare * n), mobius == 1));
				}
				
				return new Pair<ArrayList<ArrayList<Pair<Integer, Boolean>>>, ExecutionResult>(list, ExecutionResult.SUCCESS);
			} catch(Exception e) {
				e.printStackTrace();
				return new Pair<ArrayList<ArrayList<Pair<Integer, Boolean>>>, ExecutionResult>(null, ExecutionResult.FAILURE);
			}
			
		}
		
	}
}
