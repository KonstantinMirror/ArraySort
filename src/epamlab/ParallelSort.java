package epamlab;

import java.util.Arrays;

public class ParallelSort {
	// private static final int core =
	// Runtime.getRuntime().availableProcessors();

	public static void sort(Integer[] array) {
		Integer[] array_1 = Arrays.copyOf(array, array.length / 2);
		Integer[] array_2 = Arrays.copyOfRange(array, array.length / 2, array.length);
		Thread thread_1 = new Thread(new ArraySort<Integer>(array_1));
		Thread thread_2 = new Thread(new ArraySort<Integer>(array_2));
		thread_1.start();
		thread_2.start();
		try {
			thread_2.join();
			thread_1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(array_1));
		System.out.println(Arrays.toString(array_2));
	}
	
	
	private Integer[] merge(Integer[]... arraySort) {
		int countElement = 0;
		for (Integer[] integers : arraySort) {
			countElement+=integers.length;
		}
		Integer[] result = new Integer[countElement];
		
		for (int i = 0; i < result.length; i++) {
			Integer minelement;
			for (Integer[] integer : arraySort) {
				
				
			}
		}
		
		
		return null;
	}

}
