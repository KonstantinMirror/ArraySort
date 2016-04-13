package epamlab;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ParallelSort<T extends Comparable<T>> {

	private T[] array;
	int cores;

	public ParallelSort(T[] array) {
		this.array = array;
		cores = Runtime.getRuntime().availableProcessors();
	}

	public T[] getArray() {
		return array;
	}

	private T getMedian(int left, int right) {
		right--;
		int center = (right - left) / 2;
		if (compare(left, center)) {
			swap(left, center);
		}
		if (compare(left, right)) {
			swap(left, right);
		}
		if (compare(center, right)) {
			swap(center, right);
		}
		swap(center, right);
		return array[right];
	}

	public T[] quickSort() throws InterruptedException {
		if (array.length <= 3) { // if small array
			manualSort(); // used simpler sorting
			return array;
		}
		if (cores > 1) { // if processor multicore , sort with multithreading
			List<T[][]> toSort = new ArrayList<>();
			List<Thread> threads = new ArrayList<>();
			toSort.add(getPaiArray(array));
			
			if ((cores / 2) >= 2) {
				toSort.add(getPaiArray(toSort.get(0)[0]));
				toSort.add(getPaiArray(toSort.get(0)[1]));
			}
			Iterator<T[][]> iterator = toSort.iterator();
			while (iterator.hasNext()) {
				for (T[] currentArrayToSort : iterator.next()) {
					threads.add(new Thread(new SortArray<T>(currentArrayToSort)));
				}
			}
			Iterator<Thread> threadsIterator  =threads.iterator();
			while(threadsIterator.hasNext()){
				threadsIterator.next().start();
			}
			threadsIterator = threads.iterator();
			while(threadsIterator.hasNext()){
				threadsIterator.next().join();
			}
			
			

			Thread thread_1 = new Thread(new SortArray<T>(partitionArrays[0])); // sort
																				// by
																				// different
																				// threads
			Thread thread_2 = new Thread(new SortArray<T>(partitionArrays[1]));
			thread_1.start();
			thread_2.start();
			thread_1.join();
			thread_2.join(); // Concatenate sorted arrays
			array = concatenate(partitionArrays[0], partitionArrays[1]);
		} else {
			Arrays.sort(array);
		}

		return array;
	}

	private T[][] getPaiArray(T[] divArray) {
		T median = getMedian(0, array.length);
		int medianPosition = partition(0, array.length, median);
		T[] sortArray_1 = Arrays.copyOf(array, medianPosition + 1);
		T[] sortArray_2 = Arrays.copyOfRange(array, medianPosition + 1, array.length);
		@SuppressWarnings("unchecked")
		T[][] partitionArrays = (T[][]) Array.newInstance(divArray.getClass().getComponentType(), 2, 0);
		partitionArrays[0] = sortArray_1;
		partitionArrays[1] = sortArray_2;
		return partitionArrays;
	}

	private T[] concatenate(T[] A, T[] B) {
		int aLen = A.length;
		int bLen = B.length;
		@SuppressWarnings("unchecked")
		T[] C = (T[]) Array.newInstance(A.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

	private int partition(int left, int right, T pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;
		while (true) {
			while (compare(pivot, array[++leftPtr]))
				;
			while (compare(array[--rightPtr], pivot))
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right - 1);
		return leftPtr;
	}

	private void swap(int index_1, int index_2) {
		T temp = array[index_1];
		array[index_1] = array[index_2];
		array[index_2] = temp;
	}

	private void manualSort() {
		int size = array.length;
		if (size <= 1) {
			return;
		}
		if (size == 2) {
			if (compare(0, 1)) {
				swap(0, 1);
			}
		} else {
			if (compare(0, 1)) {
				swap(0, 1);
			}
			if (compare(0, 2)) {
				swap(0, 2);
			}
			if (compare(1, 2)) {
				swap(1, 2);
			}
		}
	}

	private boolean compare(int index_1, int index_2) {
		if (array[index_1].compareTo(array[index_2]) > 0) {
			return true;
		}
		return false;
	}

	private boolean compare(T o1, T o2) {
		if (o1.compareTo(o2) > 0) {
			return true;
		}
		return false;
	}

}
