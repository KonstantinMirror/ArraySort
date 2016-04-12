package epamlab;

import java.util.Arrays;

public class SortArray<T> implements Runnable {
	private T[] array;
	
	
	public SortArray(T[] array) {
		this.array = array;
	}


	@Override
	public void run() {
		Arrays.sort(array);
	}

	
	
	

}
