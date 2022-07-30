package algorithms.sorting_algorithms;

public class MergeSort {
	
	
	// If array a[] and array b[] are sorted then this method will return
	// a reference to a sorted array obtained after merging those two sorted
	// arrays.
	
	public static int[] mergeSortedArrays(int a[], int b[]) {
		int i, j, k;
		i=j=k=0;
		
		int a_len = a.length, b_len = b.length;
		
		int merged_array_length = a_len + b_len;	
		
		int c[] = new int[merged_array_length];
		
		while(i < a_len && j < b_len) {
			if(a[i] <= b[j]) {
				c[k] = a[i];
				i++;
			}
			else {
				c[k] = b[j];
				j++;
			}
//			System.out.println("i: "+i+" j: "+j+" k: "+k);
			k++;
		}
		if(i == a_len) {
			while(j < b_len) {
				c[k] = b[j];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				j++;
				k++;
			}
		}
		else {
			while(i < a_len) {
				c[k] = a[i];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				i++;
				k++;
			}
		}
		
//		System.out.println("Sorted arrays merged!");
		return c;
	}
	
	// This method will take the sub-arrays of a[]: 
	// start_1 to start_2-1 -> sub-array 1
	// start_2 to a.length-1 -> sub-array 2
	// If sub-array 1 and sub-array 2 are sorted then this method will return
	// a reference to a sorted array obtained after merging sub-array 1 
	// and sub-array 2.
	
	public static int[] mergeSortedArrays(int a[], int start_1, int start_2) {
		int i = start_1, j = start_2, k = 0;
		
		int first_sorted_subarray_len = start_2 - start_1, 
				second_sorted_subarray_len = a.length-start_2, a_len = a.length;
//		System.out.println("first_sorted_subarray_len: "+first_sorted_subarray_len
//				+", second_sorted_subarray_len: "+second_sorted_subarray_len);
		
//		System.out.println("start_1: "+start_1+", start_2: "+start_2);
		
		int merged_array_length = first_sorted_subarray_len + second_sorted_subarray_len;	
		
		int c[] = new int[merged_array_length];
		
		while(i < first_sorted_subarray_len && j < a_len) {
			if(a[i] <= a[j]) {
				c[k] = a[i];
				i++;
			}
			else {
				c[k] = a[j];
				j++;
			}
//			System.out.println("i: "+i+" j: "+j+" k: "+k);
			k++;
		}
		if(i == first_sorted_subarray_len) {
			while(j < a_len) {
				c[k] = a[j];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				j++;
				k++;
			}
		}
		else {
			while(i < first_sorted_subarray_len) {
				c[k] = a[i];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				i++;
				k++;
			}
		}
		
//		System.out.println("Sorted arrays merged!");
		
		System.arraycopy(c, 0, a, 0, a_len);
		return a;
	}
	
	// This method will take the sub-arrays of a[]: 
	// start to mid -> sub-array 1
	// mid+1 to end -> sub-array 2
	// If sub-array 1 and sub-array 2 are sorted then this method will return
	// a reference to a sorted array obtained after merging sub-array 1 
	// and sub-array 2.
	public static int[] mergeSortedArrays(int a[], int start, int mid, int end) {
		int i=start, j=mid+1, k=0;
		
//		System.out.println("start: "+start+", mid: "+mid+", end: "+end);
		
		int merged_array_length = end-start+1;
		
		int c[] = new int[merged_array_length];
		
		while(i < mid+1 && j < end+1) {
			if(a[i] <= a[j]) {
				c[k] = a[i];
				i++;
			}
			else {
				c[k] = a[j];
				j++;
			}
//			System.out.println("i: "+i+", j: "+j+", k: "+k);
			k++;
		}
		if(i == mid+1) {
			while(j < end+1) {
				c[k] = a[j];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				j++;
				k++;
			}
		}
		else {
			while(i < mid+1) {
				c[k] = a[i];
//				System.out.println("i: "+i+" j: "+j+" k: "+k);
				i++;
				k++;
			}
		}
		
//		System.out.println("Sorted arrays merged!");
		
		System.arraycopy(c, 0, a, start, end-start+1);
		return a;
		
	}
	
	
	public static void mergeSort(int a[], int first_idx, int last_idx) {
		
//		System.out.println("first_idx: "+first_idx+" last_idx: "+last_idx);
		if(last_idx > first_idx) {
			int mid = (last_idx+first_idx)/2;
			mergeSort(a, first_idx, mid);
			mergeSort(a, mid+1, last_idx);
			mergeSortedArrays(a, first_idx, mid, last_idx);
			
		}
		else {
			return;
		}
		
	}

	public static void main(String[] args) {
		
	}

}
