package algorithms.data_structures;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Heap<T extends Comparable<T>> {
	
	private ArrayList<T> al;
	
	private int heapSize, whileLoopCountInAdjustHeapUpwards, 
	whileLoopCountInAdjustHeapDownwards;
	
	private HEAP_TYPE ht;
	
	public Heap(ArrayList<T> al, HEAP_TYPE ht) {
		this.al = al;
		this.ht = ht;
	}
	
	public int getHeapSize() {
		return heapSize;
	}

	static final Logger log = LogManager.getLogger(Heap.class.getName());

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
				if(al.get(child_pos).compareTo(al.get(adjust_beg_idx)) > 0) {
					if(child_pos < heapSize) {
						swap(al, adjust_beg_idx, child_pos);
						adjust_beg_idx = child_pos;
					}
					else
						endOfHeapReached = true;
				}
				else
					break;
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(al.get(child_pos).compareTo(al.get(adjust_beg_idx)) < 0) {
					if(child_pos<heapSize) {
						swap(al, adjust_beg_idx, child_pos);
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
					if(al.get(right_child_pos).compareTo(al.get(child_pos))>0)
						child_pos = right_child_pos;
				}
			}
			//Checking if right child of current node exists.
			if(ht == HEAP_TYPE.MIN_HEAP) {
				if(right_child_pos < heapSize) {
					if(al.get(right_child_pos).compareTo(al.get(child_pos))<0)
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
	
	/* This will convert the ArrayList al into a max or 
	 * min heap.
	 * 
	 * heapify() has a TC of O(N) if the sift down i.e.
	 * adjustHeapDownwards() is used.
	 * 
	 * If the sift up i.e.adjustUpward() is used then 
	 * the TC of heapify is O(N*log(N)).
	 */
	public void heapify(int heapSize) {
		log.info("Heapify started ...");
		
		if(heapSize < 0 && heapSize >= al.size())
			new InvalidHeapSizeException(heapSize, al.size());
			
		int pos = heapSize-1;
		
		this.heapSize = heapSize;
		
		while(pos >= 0) {
			int child_pos = getChildPos(pos);
			if(child_pos != -1) {
				if(ht == HEAP_TYPE.MAX_HEAP) {
					if(al.get(child_pos).compareTo(al.get(pos)) > 0) {
						swap(al, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				else if(ht == HEAP_TYPE.MIN_HEAP) {
					if(al.get(child_pos).compareTo(al.get(pos)) < 0) {
						swap(al, pos, child_pos);
						adjustHeapDownwards(child_pos);
					}
				}
				
			}
			pos--;
		}
		log.info("Heapify finished ...");
	}
	
	public void heapify() {
		heapify(al.size());
	}
	
	/* insert() has a time complexity of O(log N) where
	 * N is the number of elements in the heap.
	 */
	public int insert(T element) {
		log.info("Insert process started ...");
		log.info("element to be inserted: "+element);
		int insertedIdx = -1;
		
		// We have no limit on the heap size over here.
		al.add(element);
		heapSize++;
		insertedIdx = adjustHeapUpwards(heapSize-1);
		
		log.info("Insert process finished ...");
		return insertedIdx;
	}
	
	/* This adjustUpwards() will find the suitable 
	 * position for the node at adjust_beg_idx in the 
	 * heap and return it.
	 * 
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
				if(al.get(adjust_beg_idx).compareTo(al.get(parent_idx)) > 0) {
					swap(al, adjust_beg_idx, parent_idx);
					new_pos = parent_idx;
				}
			}
			else if(ht == HEAP_TYPE.MIN_HEAP) {
				if(al.get(adjust_beg_idx).compareTo(al.get(parent_idx)) < 0) {
					swap(al, adjust_beg_idx, parent_idx);
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
	public T delete() {
		return delete(0);
	}
	
	/* delete(int idxOfNodeToBeDeleted) will delete the 
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
			T temp = al.get(idxOfNodeToBeDeleted);
			al.set(idxOfNodeToBeDeleted, al.get(heapSize-1));
			al.set(heapSize-1, temp);
			heapSize--;
				
			adjustHeapDownwards(idxOfNodeToBeDeleted);
			return temp;
		}
	}
	
	/* decreaseKey(int index, int newValue) has a TC of log(N)
	 * and it will return the new position of the reduced value
	 * in the heap.
	 */
	public int decreaseKey(int index, T newElement) {
		if(index < 0 || index >= heapSize) {
			throw new HeapIndexOutOfBoundsException(index, heapSize);
		}
		
		if(newElement.compareTo(al.get(index)) > 0) {
			throw new IllegalArgumentException("The newValue: "+newElement+" is greater than the value: "+al.get(index)+" at pos: "+index+" of the heap for the decrease key operation!");
		}
		
		int newPos = -1;
		
		al.set(index, newElement);
		
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
		for(int i=0; i<al.size(); i++) {
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
	
	public void printHeap() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<getHeapSize(); i++) {
			sb.append(al.get(i));
			if(i != getHeapSize()-1) sb.append(", ");
		}
		log.info(sb);
	}
}
