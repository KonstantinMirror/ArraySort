import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import epamlab.ParallelSort;

public class ParallelSortTest {

	@Test
	public void test() {
		Integer[] testArray = new Integer[10];
		for (int i = 0; i < testArray.length; i++) {
			 testArray[i] =(int) (Math.random()*100);
		}
		ParallelSort<Integer> sortUtil = new ParallelSort<>(testArray);
		sortUtil.quicSort();
		
		
	}

}
