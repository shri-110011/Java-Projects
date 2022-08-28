package algorithms.graph_algorithms;

public class InvalidVertexIndexException extends RuntimeException {
	
	public InvalidVertexIndexException(int noOfVertices, int nodeIdx) {
		super("Number of vertices in the graph is: "+noOfVertices+" but provided out of range index: "+nodeIdx+".");
	}
	
}
