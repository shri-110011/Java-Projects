package algorithms.graph_algorithms;

public class NumberOfVerticesOutOfBoundsException extends RuntimeException {

	public NumberOfVerticesOutOfBoundsException(int noOfVerticesLimit, int noOfVertices) {
		super("Value for 'noOfVertices' must be in the range of [0-"+noOfVerticesLimit+"], provided value is: "+noOfVertices+".");
	}
	
}
