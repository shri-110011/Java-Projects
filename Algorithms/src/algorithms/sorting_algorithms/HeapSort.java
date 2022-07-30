package algorithms.sorting_algorithms;

import java.util.Scanner;

import utilities.HelperClass;


public class HeapSort {
	
	public enum HEAP_TYPE {
		MAX_HEAP, MIN_HEAP
	}
	
	public HeapSort() {
		
	}
	
	private static int size = 0;
	
	// This adjustDownwards() will find the suitable position for the node 
	// at adjust_beg_idx in the sub-tree for which it is the root node.
	
	// And this adjustment process depends on the value for ht(HEAP_TYPE).
	private static void adjustDownwards(int a[], int adjust_beg_idx, 
			HEAP_TYPE ht, int ...sizeOfHeap) {
		
		int child_pos;
		boolean endOfHeapReached = false;
		
		if(sizeOfHeap.length>0 && sizeOfHeap[0]<0)
			throw new IllegalArgumentException("Value for sizeOfHeap parameter"
					+ "must be greater than or equal to 0.");
		
		while((child_pos = getChildPos(a, adjust_beg_idx, ht)) != -1) {
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a[child_pos] > a[adjust_beg_idx]) {
					if(sizeOfHeap.length==0)
						HelperClass.swap(a, adjust_beg_idx, child_pos);
					else {
						if(child_pos<sizeOfHeap[0])
							HelperClass.swap(a, adjust_beg_idx, child_pos);
						else
							endOfHeapReached = true;
					}
					adjust_beg_idx = child_pos;
				}
				else
					break;
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a[child_pos] < a[adjust_beg_idx]) {
					if(sizeOfHeap.length==0)
						HelperClass.swap(a, adjust_beg_idx, child_pos);
					else {
						if(child_pos<sizeOfHeap[0])
							HelperClass.swap(a, adjust_beg_idx, child_pos);
						else 
							endOfHeapReached = true;
					}
					adjust_beg_idx = child_pos;
				}
				else
					break;
			}
			
			if(endOfHeapReached) break;
			
		}
		
	}
	
	// This getChildPos() will return the position of the relevant child based 
	// on the type of heap the user wants.
	
	// If ht == HEAP_TYPE.MAX_HEAP then the max_child_pos of the node at 
	// nodePos is returned.
	
	// If ht == HEAP_TYPE.MIN_HEAP then the min_child_pos of the node at 
	// nodePos is returned.
	
	// In case no child are there for the node at nodePos then -1 is returned.
	private static int getChildPos(int a[], int nodePos, HEAP_TYPE ht) {
		int left_child_pos = 2*nodePos+1, 
				child_pos, min_child_pos;
		
		child_pos = left_child_pos;
		
		//Checking if left child of current node exists.
		if(left_child_pos < size) {
			int right_child_pos = 2*nodePos+2;
			
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(right_child_pos < size) {
					if(a[right_child_pos] > a[child_pos])
						child_pos = right_child_pos;
				}
			}
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MIN_HEAP) {
				if(right_child_pos < size) {
					if(a[right_child_pos] < a[child_pos])
						child_pos = right_child_pos;
				}
			}
		}
		else {
			// max_child_pos = -1 means current node has no children.
			child_pos = -1;
		}
		
		return child_pos;
	}
	
	// This will convert the array a[] into a max or min heap.
	public static void heapify(int a[], HEAP_TYPE ht) {
		
		int pos = a.length-1;
		
		if(size == 0)
			size = a.length;
		
		while(pos >= 0) {
			
			int child_pos = getChildPos(a, pos, ht);
			if(child_pos != -1) {
				if(ht == HEAP_TYPE.MAX_HEAP) {
					if(a[child_pos] > a[pos]) {
						HelperClass.swap(a, pos, child_pos);
						adjustDownwards(a, child_pos, ht);
					}
				}
				else if(ht == HEAP_TYPE.MIN_HEAP) {
					if(a[child_pos] < a[pos]) {
						HelperClass.swap(a, pos, child_pos);
						adjustDownwards(a, child_pos, ht);
					}
				}
				
			}
			pos--;
			
		}
		
	}
	
	public static void insert(int a[], int element, HEAP_TYPE ht) {
		
		if(a.length == size) 
			throw new IndexOutOfBoundsException("Heap is full!");
		else {
			a[size] = element;
			size++;
			adjustHeapUpwards(a, size-1, ht);
		}
		
	}
	
	private static void adjustHeapUpwards(int a[], int adjust_beg_idx, 
			HEAP_TYPE ht) {
		int parent_idx;
		while((parent_idx = (int)Math.floor(((double)adjust_beg_idx-1)/2)) != -1) {
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a[adjust_beg_idx] > a[parent_idx]) {
					HelperClass.swap(a, adjust_beg_idx, parent_idx);
				}
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a[adjust_beg_idx] < a[parent_idx]) {
					HelperClass.swap(a, adjust_beg_idx, parent_idx);
				}
			}
			adjust_beg_idx = parent_idx;
		}
		
	}
	
	public static void delete(int a[], HEAP_TYPE ht) {
		
		if(size == 0) {
			System.out.println("Heap is empty!");
		}
		else {
			int temp = a[0];
			a[0] = a[size-1];
			a[size-1] = temp;
			size--;
			
			adjustDownwards(a, 0, ht, size);
		}
		
	}
	
	public static void displayHeap(int a[]) {
		
		System.out.println("Displaying heap: ");
		System.out.print("[");
		for(int i=0; i<size; i++) {
			System.out.print(a[i]);
			if(i!=size-1)
				System.out.print(",");
		}
		System.out.println("]");
	}

	public static void heapSort(int a[], HEAP_TYPE ht) {
		
		if(size == 0)
			size = a.length;
		
		// Covert the array a[] into a max heap or min heap based 
		// on ht(HEAP_TYPE)
		heapify(a, ht);
		
		for(int i=0; i<a.length; i++) {
			delete(a, ht);
		}
		
	}
	
	public static void main(String[] args) {
		int a[] = {10, 20, 15, 12, 40, 25, 18};
//		{10, 20, 15, 12, 40, 25, 18}
//		{14, 25, 14, 4, 6, 2}
		heapify(a, HEAP_TYPE.MAX_HEAP);
		HelperClass.displayArray(a);
//		
//		int b[] = new int[7], element;
//		Scanner sc = new Scanner(System.in);
//		for(int i=0; i<7; i++) {
//			element = sc.nextInt();
//			insert(b, element, HEAP_TYPE.MIN_HEAP);
//			HelperClass.displayArray(b);
//		}
//		sc.close();
		
//		heapSort(a, HEAP_TYPE.MAX_HEAP);
//		
//		HelperClass.displayArray(a);
		
		
//		System.out.println(Math.floor(-1/2));
		
		
		
	}

}
