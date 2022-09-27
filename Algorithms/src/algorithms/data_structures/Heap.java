package algorithms.data_structures;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Heap<T extends Comparable<T>> {
	
	private ArrayList<T> a;
	
	private int heapSize, whileLoopCountInAdjustHeapUpwards, 
	whileLoopCountInAdjustHeapDownwards;
	
	private HEAP_TYPE ht;
	
	public Heap(ArrayList<T> a, HEAP_TYPE ht) {
		this.a = a;
		this.ht = ht;
	}
	
	public int getHeapSize() {
		return heapSize;
	}

	static final Logger log = LogManager.getLogger(Heap.class.getName());

	/* This adjustDownwards() will find the suitable 
	 * position for the node at adjust_beg_idx in the 
	 * sub-tree for which it is the root node.
	 * 
	 * And this adjustment process depends on the 
	 * value for ht(HEAP_TYPE).
	 * 
	 * adjustHeapDownwards()  has TC of log(N)
	 */
	private void adjustHeapDownwards(int adjust_beg_idx) {
		
		int child_pos;
		boolean endOfHeapReached = false;
		
		resetMetrics();
		while((child_pos = getChildPos(adjust_beg_idx)) != -1) {
			whileLoopCountInAdjustHeapDownwards++;
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a.get(child_pos).compareTo(a.get(adjust_beg_idx)) > 0) {
					if(child_pos < heapSize)
						swap(a, adjust_beg_idx, child_pos);
					else
						endOfHeapReached = true;
					adjust_beg_idx = child_pos;
				}
				else
					break;
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a.get(child_pos).compareTo(a.get(adjust_beg_idx)) < 0) {
					if(child_pos<heapSize)
						swap(a, adjust_beg_idx, child_pos);
					else 
						endOfHeapReached = true;
					adjust_beg_idx = child_pos;
				}
				else
					break;
			}
			
			if(endOfHeapReached) break;
		}
		log.info("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
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
					if(a.get(right_child_pos).compareTo(a.get(child_pos))>0)
						child_pos = right_child_pos;
				}
			}
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MIN_HEAP) {
				if(right_child_pos < heapSize) {
					if(a.get(right_child_pos).compareTo(a.get(child_pos))<0)
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
	
	/* This will convert the ArrayList a[] into a max or 
	 * min heap.
	 * 
	 * heapify() has a TC of O(N) if the sift down i.e.
	 * adjustHeapDownwards() is used.
	 * 
	 * If the sift up i.e.adjustUpward() is used then 
	 * the TC of heapify is O(N*log(N)).
	 */
	public void heapify() {
		log.info("Heapify started ...");
		int pos = a.size()-1;
		
		if(heapSize == 0)
			heapSize = a.size();
		
		while(pos >= 0) {
			int child_pos = getChildPos(pos);
			if(child_pos != -1) {
				if(ht == HEAP_TYPE.MAX_HEAP) {
					if(a.get(child_pos).compareTo(a.get(pos)) > 0) {
						swap(a, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				else if(ht == HEAP_TYPE.MIN_HEAP) {
					if(a.get(child_pos).compareTo(a.get(pos)) < 0) {
						swap(a, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				
			}
			pos--;
		}
		log.info("Heapify finished ...");
	}
	
	/* insert() has a time complexity of O(log N) where
	 * N is the number of elements in the heap.
	 */
	public void insert(T element) {
		log.info("Insert process started ...");
		log.info("element to be inserted: "+element);
		
		// We have no limit on the heap size over here.
		a.add(element);
		heapSize++;
		adjustHeapUpwards(heapSize-1);
		
		log.info("Insert process finished ...");
	}
	
	/* The while loop- will run h times where h is the 
	 * height of the binary heap.
	 * Note: A binary heap is a complete binary tree.
	 * 
	 * adjustHeapUpwards() has a TC of log(N).
	 */
	private void adjustHeapUpwards(int adjust_beg_idx) {
		int parent_idx;
		resetMetrics();
		while((parent_idx = (int)Math.floor(((double)adjust_beg_idx-1)/2)) != -1) {
			whileLoopCountInAdjustHeapUpwards++;
			if(ht == HEAP_TYPE.MAX_HEAP) {
				if(a.get(adjust_beg_idx).compareTo(a.get(parent_idx)) > 0) {
					swap(a, adjust_beg_idx, parent_idx);
				}
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(a.get(adjust_beg_idx).compareTo(a.get(parent_idx)) < 0) {
					swap(a, adjust_beg_idx, parent_idx);
				}
			}
			adjust_beg_idx = parent_idx;
		}
		log.info("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
	}
	
	/* delete(int a[], HEAP_TYPE ht) will delete the 
	 * root element from the binary heap and return it 
	 * and this could be the maximum or the minimum 
	 * element in the heap depending on the type of the 
	 * heap.
	 */
	public T delete() {
		return delete(0);
	}
	
	/* delete(int a[], HEAP_TYPE ht, 
	 * int idxOfNodeToBeDeleted) will delete the 
	 * element at index specified by 
	 * idxOfNodeToBeDeleted from the binary heap 
	 * and return it.
	 * 
	 * delete() has TC of log(N).
	 */
	public T delete(int idxOfNodeToBeDeleted) {
		resetMetrics();
		if(idxOfNodeToBeDeleted < 0 || idxOfNodeToBeDeleted >= heapSize) {
			throw new HeapIndexOutOfBoundsException(idxOfNodeToBeDeleted, heapSize);
		}
		
		if(heapSize == 0) {
			log.info("Heap is empty!");
			return null;
		}
		else {
			T temp = a.get(idxOfNodeToBeDeleted);
			a.set(idxOfNodeToBeDeleted, a.get(heapSize-1));
			a.set(heapSize-1, temp);
			heapSize--;
				
			adjustHeapDownwards(idxOfNodeToBeDeleted);
			return temp;
		}
	}
	
	/* decreaseKey() has a TC of log(N).
	 */
	public void decreaseKey(int index, T newElement) {
		if(index < 0 || index >= heapSize) {
			throw new HeapIndexOutOfBoundsException(index, heapSize);
		}
		
		a.set(index, newElement);
		
		log.info("Decrease key process started ...");
		
		if(ht.equals(HEAP_TYPE.MAX_HEAP)) {
			if(getChildPos(index) == -1)
				adjustHeapUpwards(index);
			else
				adjustHeapDownwards(index);
		}
		else {
			if(index == 0)
				adjustHeapDownwards(index);
			else
			adjustHeapUpwards(index);
		}
		log.info("whileLoopCountInAdjustHeapDownwards: "+whileLoopCountInAdjustHeapDownwards);
		log.info("whileLoopCountInAdjustHeapUpwards: "+whileLoopCountInAdjustHeapUpwards);
		log.info("Decrease key process finished ...");
	}
	
	/* heapSort() has a TC of O(N*log(N)).
	 */
	public void heapSort() {
		// Covert the array a[] into a max heap or min heap based 
		// on ht(HEAP_TYPE)
		log.info("Heap sort process started ...");
		heapify();
		
		log.info("Deletion of all elements from heap started ...");
		for(int i=0; i<a.size(); i++) {
			delete();
		}
		log.info("Deletion of all elements from heap finished ...");
		log.info("Heap sort process finished ...");
	}
	
	private void swap(ArrayList<T> al, int pos1, int pos2) {
		T el = al.get(pos1);
		al.set(pos1, al.get(pos2));
		al.set(pos2, el);
	}
	
	private void resetMetrics() {
		whileLoopCountInAdjustHeapDownwards = 0;
		whileLoopCountInAdjustHeapUpwards = 0;
	}
}
