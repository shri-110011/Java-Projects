package test_algorithms.test_sorting_algorithms;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import algorithms.sorting_algorithms.QuickSort;

public class QuickSort_Test {
	
	@Test
	public void testQuickSortMethod() {
		// Create an array
		int a[] = {6,3,14,2,4,19,1,8,-1,5,-9,14,2};
		int b[] = a.clone();
		Arrays.sort(b);
		
		QuickSort.quickSort(a, 0, a.length-1);
		
//		HelperClass.displayArray(a);
//		HelperClass.displayArray(b);
		
		Assert.assertArrayEquals("Arrays a[] and b[] do not contain same elements.", b, a);
	}

}
