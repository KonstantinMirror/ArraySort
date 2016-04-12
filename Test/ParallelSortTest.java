import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import epamlab.ParallelSort;

public class ParallelSortTest {

	@Test
	public void test() {
		Integer[] testArray = new Integer[100000];
		for (int i = 0; i < testArray.length; i++) {
			 testArray[i] =(int) (Math.random()*10000);
		}
		ParallelSort<Integer> sortUtil = new ParallelSort<>(testArray);
		Integer[] compareArray = Arrays.copyOf(testArray, testArray.length);
		Arrays.sort(compareArray);
		try {
			sortUtil.quickSort();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertArrayEquals(compareArray, sortUtil.getArray());
	}
	
	
	
	@Test
	public void testSimple3Elem() {
		Double[] testArray = new Double[]{80D,9D,1D};
		ParallelSort<Double> sortUtil = new ParallelSort<>(testArray);
		try {
			sortUtil.quickSort();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertArrayEquals(new Double[]{1D,9D,80D}, sortUtil.getArray());
	}
	
	@Test
	public void testSimple2Elem() {
		Double[] testArray = new Double[]{80D,9D};
		ParallelSort<Double> sortUtil = new ParallelSort<>(testArray);
		try {
			sortUtil.quickSort();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertArrayEquals(new Double[]{9D,80D}, sortUtil.getArray());
	}
	
	
	
	@Test
	public void testSimple1Elem() {
		Double[] testArray = new Double[]{80D};
		ParallelSort<Double> sortUtil = new ParallelSort<>(testArray);
		try {
			sortUtil.quickSort();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertArrayEquals(new Double[]{80D}, sortUtil.getArray());

	}
	
	

}
