package algorithms.sorting_algorithms;

import utilities.HelperClass;

public class QuickSort {
	private static int partition(int a[], int start_idx, int end_idx) {
		int i=start_idx+1, j= end_idx, pivot_idx = start_idx;
		
		// This flag will check if the execution flow entered the while loop.
		boolean flag = false;
		
		System.out.println("pivot_idx: "+pivot_idx);
		System.out.println("#i: "+i+", j: "+j);
		while(i<j) {
			flag = true;
			System.out.println("i: "+i+", j: "+j);
			while(i<end_idx) {
				if(a[i] > a[pivot_idx])
					break;
				i++;
			}
			System.out.println("i: "+i);
			while(j>start_idx) {
				if(a[j] <= a[pivot_idx])
					break;
				j--;
			}
			System.out.println("j: "+j);
			
			if(i<j) {
				HelperClass.swap(a, i, j);
			}
			
		}
		if(flag) HelperClass.swap(a, j, pivot_idx);
		HelperClass.displayArray(a);
		
		return j;
	}
	
	public static void quickSort(int a[], int start_idx, int end_idx) {
		
		if(start_idx >= end_idx) {
			return;
		}
		else {
			int correct_pivot_position = partition(a, start_idx, end_idx);
			quickSort(a, start_idx, correct_pivot_position-1);
			quickSort(a, correct_pivot_position+1, end_idx);
			
		}
	}

	public static void main(String[] args) {
		
		int a1[] = {6,3,14,2,4,19,1,8,-1,5,-9,14,2};
		quickSort(a1, 0, a1.length-1);
		HelperClass.displayArray(a1);

	}
}
