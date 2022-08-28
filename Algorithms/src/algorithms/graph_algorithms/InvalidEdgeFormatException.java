package algorithms.graph_algorithms;

public class InvalidEdgeFormatException extends RuntimeException {

	public InvalidEdgeFormatException(String edge, int index) {
		super("Invalid edge: \""+edge+"\" at connections["+index+"], the string representation of the edge should be of the format \"vertex1Name vertex2Name\".");
	}
	
}
