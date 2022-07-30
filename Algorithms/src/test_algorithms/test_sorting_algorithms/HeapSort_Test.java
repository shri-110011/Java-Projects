package test_algorithms.test_sorting_algorithms;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import algorithms.sorting_algorithms.HeapSort;
import algorithms.sorting_algorithms.HeapSort.HEAP_TYPE;
import utilities.HelperClass;

public class HeapSort_Test {

	@Test
	public void testHeapSortMethod() {
		// Create an array
		int a[] = {6,3,14,2,4,19,1,8,-1,5,-9,14,2};
//		{14, 25, 14, 4, 6, 2}
//		{10, 20, 15, 12, 40, 25, 18}
		int b[] = a.clone();
		Arrays.sort(b);
		
		HeapSort.heapSort(a, HEAP_TYPE.MAX_HEAP);
		
		HelperClass.displayArray(a);
		HelperClass.displayArray(b);
		System.out.println("------------------");
		
		Assert.assertArrayEquals("Arrays a[] and b[] do not contain same elements.", b, a);
	}
	
	@Test
	public void testHeapifyMethod() {
		// Create an array
		int a[] = {10, 20, 15, 12, 40, 25, 18};
		int b[] = {40, 20, 25, 12, 10, 15, 18};
		
		HeapSort.heapify(a, HEAP_TYPE.MAX_HEAP);
		
		HelperClass.displayArray(a);
		HelperClass.displayArray(b);
		System.out.println("------------------");
		
		Assert.assertArrayEquals("Arrays a[] and b[] do not contain same elements.", b, a);
		
	}
}
