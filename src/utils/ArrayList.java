package utils;

import utils.multithreading.Result;

public class ArrayList<T> extends java.util.ArrayList<T> implements Saveable<ArrayList<T>>, Result<ArrayList<T>> {

	private static final long serialVersionUID = -1848883790928767308L;

	@Override
	public ArrayList<T> combine(ArrayList<T> result) {
		addAll(result);
		return this;
	}

}
