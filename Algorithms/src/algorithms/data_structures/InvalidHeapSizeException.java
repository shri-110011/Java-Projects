package algorithms.data_structures;

public class InvalidHeapSizeException extends RuntimeException {
	
	public InvalidHeapSizeException(int invalidHeapSize, int listSize) {
		super("HeapSize: "+invalidHeapSize+" is out of bounds for the array list size: "+listSize+".");
	}
}
