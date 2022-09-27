package algorithms.data_structures;

public class HeapIndexOutOfBoundsException extends RuntimeException {

	public HeapIndexOutOfBoundsException(int outOfBoundIndex, int heapSize) {
		super("Index: "+outOfBoundIndex+" is out of bounds for heapSize: "+heapSize+".");
	}
}
