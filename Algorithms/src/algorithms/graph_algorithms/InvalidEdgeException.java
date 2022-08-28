package algorithms.graph_algorithms;

public class InvalidEdgeException extends RuntimeException {
	
	public InvalidEdgeException(int idx, String endpointType) {
		super("Index: "+idx+" for "+endpointType+" out of bounds.");
	}
	
}
