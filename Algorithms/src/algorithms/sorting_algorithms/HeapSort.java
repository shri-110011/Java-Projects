package algorithms.sorting_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import algorithms.data_structures.HEAP_TYPE;
import algorithms.data_structures.Heap;
import utilities.HelperClass;

public class HeapSort {
	
	private int a[], aLen, heapSize, whileLoopCountInAdjustHeapUpwards, 
	whileLoopCountInAdjustHeapDownwards;
	
	private HEAP_TYPE ht;
	
	public HeapSort(int a[], HEAP_TYPE ht) {
		this.a = a;
		this.ht = ht;
		this.aLen = a.length;
	}
	
	public int getHeapSize() {
		return heapSize;
	}
	
	public int getArrayLen() {
		return aLen;
	}
	
	static final Logger log = LogManager.getLogger(HeapSort.class.getName());
	
	/* This adjustDownwards() will find the suitable 
	 * position for the node at adjust_beg_idx in the 
	 * heap and return it.
	 * 
	 * And this adjustment process depends on the 
	 * value for ht(HEAP_TYPE).
	 * 
	 * adjustHeapDownwards()  has TC of log(N)
	 */
	private int adjustHeapDownwards(int adjust_beg_idx) {
		
		int child_pos;
		boolean endOfHeapReached = false;
		
		resetMetrics();
		while((child_pos = getChildPos(adjust_beg_idx)) != -1) {
			whileLoopCountInAdjustHeapDownwards++;
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a[child_pos] > a[adjust_beg_idx]) {
					if(child_pos < heapSize) {
						HelperClass.swap(a, adjust_beg_idx, child_pos);
						adjust_beg_idx = child_pos;
					}
					else
						endOfHeapReached = true;
				}
				else
					break;
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a[child_pos] < a[adjust_beg_idx]) {
					if(child_pos<heapSize) {
						HelperClass.swap(a, adjust_beg_idx, child_pos);
						adjust_beg_idx = child_pos;
					}
					else 
						endOfHeapReached = true;
				}
				else
					break;
			}
			
			if(endOfHeapReached) break;
		}
		log.debug("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
		return adjust_beg_idx;
	}
	
	/* This getChildPos() will return the position of 
	 * the relevant child based on the type of heap.
	 * 
	 * If ht == HEAP_TYPE.MAX_HEAP then the max_child_pos 
	 * of the node at nodePos is returned.
	 * If ht == HEAP_TYPE.MIN_HEAP then the min_child_pos of the node at 
	 * nodePos is returned.
	 * 
	 * In case no child are there for the node at nodePos then -1 is returned.
	 *
	 * getChildPos() has TC of O(1).
	 */
	private int getChildPos(int nodePos) {
		int left_child_pos = 2*nodePos+1, 
				child_pos;
		
		child_pos = left_child_pos;
		
		//Checking if left child of current node exists.
		if(left_child_pos < heapSize) {
			int right_child_pos = 2*nodePos+2;
			
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(right_child_pos < heapSize) {
					if(a[right_child_pos] > a[child_pos])
						child_pos = right_child_pos;
				}
			}
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MIN_HEAP) {
				if(right_child_pos < heapSize) {
					if(a[right_child_pos] < a[child_pos])
						child_pos = right_child_pos;
				}
			}
		}
		else {
			// child_pos = -1 means current node has no children.
			child_pos = -1;
		}
		
		return child_pos;
	}
	
	/* This will convert the array a[] into a max or 
	 * min heap.
	 * 
	 * heapify(int heapSize) has a TC of O(N) if the sift down i.e.
	 * adjustHeapDownwards() is used.
	 * 
	 * If the sift up i.e.adjustUpward() is used then 
	 * the TC of heapify is O(N*log(N)).
	 * 
	 * This heapify(int heapSize) method takes the heapSize as an argument 
	 * because it gives the flexibility to heapify a portion of the array 
	 * a[].
	 */
	public void heapify(int heapSize) {
		log.info("Heapify started ...");
		
		if(heapSize < 0 && heapSize >= aLen)
			throw new IllegalArgumentException("heapSize: "+heapSize+" is out of bounds for the array size: "+aLen+".");
		
		int pos = heapSize-1;
		
		this.heapSize = heapSize;
		
		while(pos >= 0) {
			int child_pos = getChildPos(pos);
			if(child_pos != -1) {
				if(ht == HEAP_TYPE.MAX_HEAP) {
					if(a[child_pos] > a[pos]) {
						HelperClass.swap(a, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				else if(ht == HEAP_TYPE.MIN_HEAP) {
					if(a[child_pos] < a[pos]) {
						HelperClass.swap(a, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				
			}
			pos--;
		}
		log.info("Heapify finished ...");
	}
	
	public void heapify() {
		heapify(aLen);
	}
	
	/* insert() has a time complexity of O(log N) where
	 * N is the number of elements in the heap.
	 */
	public int insert(int element) {
		log.info("Insert process started ...");
		log.info("element to be inserted: "+element);
		int insertedIdx = -1;
		if(a.length == heapSize) 
			throw new IndexOutOfBoundsException("Heap is full!");
		else {
			a[heapSize] = element;
			heapSize++;
			insertedIdx = adjustHeapUpwards(heapSize-1);
		}
		log.info("Insert process finished ...");
		return insertedIdx;
	}
	
	/* This adjustUpwards() will find the suitable 
	 * position for the node at adjust_beg_idx in the 
	 * heap and return it.
	 * 
	 * The while loop- will run h times where h is the 
	 * height of the binary heap.
	 * Note: A binary heap is a complete binary tree.
	 * 
	 * adjustHeapUpwards() has a TC of log(N).
	 */
	private int adjustHeapUpwards(int adjust_beg_idx) {
		int parent_idx, new_pos = adjust_beg_idx;
		resetMetrics();
		while((parent_idx = (int)Math.floor(((double)adjust_beg_idx-1)/2)) != -1) {
			whileLoopCountInAdjustHeapUpwards++;
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a[adjust_beg_idx] > a[parent_idx]) {
					HelperClass.swap(a, adjust_beg_idx, parent_idx);
					new_pos = parent_idx;
				}
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a[adjust_beg_idx] < a[parent_idx]) {
					HelperClass.swap(a, adjust_beg_idx, parent_idx);
					new_pos = parent_idx;
				}
			}
			adjust_beg_idx = parent_idx;
		}
		log.debug("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
		return new_pos;
	}
	
	/* delete() will delete the root element from the 
	 * binary heap and return it and this could be the 
	 * maximum or the minimum element in the heap 
	 * depending on the type of the heap.
	 */
	public int delete() {
		return delete(0);
	}
	
	/* delete(int idxOfNodeToBeDeleted) will delete the 
	 * element at index specified by 
	 * idxOfNodeToBeDeleted from the binary heap 
	 * and return it.
	 * 
	 * delete() has TC of log(N).
	 */
	public int delete(int idxOfNodeToBeDeleted) {
		resetMetrics();
		if(idxOfNodeToBeDeleted < 0 || idxOfNodeToBeDeleted >= heapSize) {
			throw new IllegalArgumentException("The index: "+idxOfNodeToBeDeleted+" is out of bounds for heapSize: "+heapSize+".");
		}
		
		if(heapSize == 0) {
			log.info("Heap is empty!");
			if(ht.equals(HEAP_TYPE.MAX_HEAP)) return Integer.MIN_VALUE;
			else return Integer.MAX_VALUE;
		}
		else {
			int temp = a[idxOfNodeToBeDeleted];
			a[idxOfNodeToBeDeleted] = a[heapSize-1];
			a[heapSize-1] = temp;
			heapSize--;
				
			adjustHeapDownwards(idxOfNodeToBeDeleted);
			return temp;
		}
	}
	
	/* decreaseKey(int index, int newValue) has a TC of log(N)
	 * and it will return the new position of the reduced value
	 * in the heap.
	 */
	public int decreaseKey(int index, int newValue) {
		if(index < 0 || index >= heapSize) {
			throw new IllegalArgumentException("The index: "+index+" is out of bounds for heapSize: "+heapSize+".");
		}
		
		if(newValue > a[index]) {
			throw new IllegalArgumentException("The newValue: "+newValue+" is greater than the value: "+a[index]+" at pos: "+index+" of the heap for the decrease key operation!");
		}
		
		int newPos = -1;
		
		a[index] = newValue;
		
		log.info("Decrease key process started ...");
		
		if(ht.equals(HEAP_TYPE.MAX_HEAP)) {
			if(getChildPos(index) == -1)
				newPos = adjustHeapUpwards(index);
			else
				newPos = adjustHeapDownwards(index);
		}
		else {
			if(index == 0)
				newPos = adjustHeapDownwards(index);
			else
				newPos = adjustHeapUpwards(index);
		}
		log.debug("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
		log.debug("whileLoopCountInAdjustHeapUpwards: "+whileLoopCountInAdjustHeapUpwards);
		log.info("Decrease key process finished ...");
		return newPos;
	}

	/* heapSort() has a TC of O(N*log(N)).
	 */
	public void heapSort() {
		// Covert the array a[] into a max heap or min heap based 
		// on ht(HEAP_TYPE)
		log.info("Heap sort process started ...");
		heapify();
		
		log.info("Deletion of all elements from heap started ...");
		for(int i=0; i<a.length; i++) {
			delete();
		}
		log.info("Deletion of all elements from heap finished ...");
		log.info("Heap sort process finished ...");
	}
	
	private void resetMetrics() {
		whileLoopCountInAdjustHeapDownwards = 0;
		whileLoopCountInAdjustHeapUpwards = 0;
	}
	
	public void printHeap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<getHeapSize(); i++) {
			sb.append(a[i]);
			if(i != getHeapSize()-1) sb.append(", ");
		}
		log.info(sb);
	}
	
	public static void main(String[] args) {
		int a[] = {6,3,14,2,4,19,1,8,-1,5,-9,14,2};
		
		HeapSort hs = new HeapSort(a, HEAP_TYPE.MIN_HEAP);
		hs.heapify();
		hs.printHeap();
		System.out.println(hs.decreaseKey(0, -15));
		hs.printHeap();
	}
}
//[19, 8, 14, 4, 5, 14, 1, 2, -1, 3, -9, 6]
// 6 3 14 2 4 19 1 8 -1 5 -9 14 2