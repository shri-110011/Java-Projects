package test_algorithms.test_sorting_algorithms;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import algorithms.sorting_algorithms.MergeSort;
import utilities.HelperClass;

public class MergeSort_Test {
	
	@Test
	public void testMergeSortMethod() {
		// Create an array
		int a[] = {6,3,2,4,1,8,5};
		int b[] = a.clone();
		Arrays.sort(b);
		
		MergeSort.mergeSort(a, 0, a.length-1);
		
		Assert.assertArrayEquals("Arrays a[] and b[] do not contain same elements.", b, a);
	}
	
	@Test
	public void testMergeSortedArraysMethod_1() {
		// Create two sorted arrays
		int a1[] = {2, 7, 11, 17, 19};
		int a2[] = {1, 5, 6, 12, 14, 18};
		
		int a3[] = MergeSort.mergeSortedArrays(a1, a2);
		
		int a4[] = HelperClass.concatenateArrays(a1, a2);
		Arrays.sort(a4);
		
		Assert.assertArrayEquals("Arrays a3[] and a4[] do not contain same elements.", a4, a3);
		
	}
	
	@Test
	public void testMergeSortedArraysMethod_2() {
		// Create two sorted arrays
		int a1[] = {2, 7, 11, 17, 19, 1, 5, 6, 12, 14, 18};
		
		int a2[] = MergeSort.mergeSortedArrays(a1, 0, 5);
		
		Arrays.sort(a1);
		
		Assert.assertArrayEquals("Arrays a1[] and a2[] do not contain same elements.", a1, a2);
		
	}
	
	@Test
	public void testMergeSortedArraysMethod_3() {
		// Create two sorted arrays
		int a1[] = {2, 7, 11, 17, 19, 1, 5, 6, 12, 14, 18};
		
		int a2[] = MergeSort.mergeSortedArrays(a1, 0, 5, 10);
		
		Arrays.sort(a1);
		
		Assert.assertArrayEquals("Arrays a1[] and a2[] do not contain same elements.", a1, a2);
		
	}

}
