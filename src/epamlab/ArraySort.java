package epamlab;

import java.util.Arrays;

public class ArraySort<T extends Comparable<T>> implements Runnable {

	public ArraySort(T[] t) {
		this.t = t;
	}

	T[] t;

	@Override
	public void run() {
		Arrays.sort(t);
	}	
	
}
