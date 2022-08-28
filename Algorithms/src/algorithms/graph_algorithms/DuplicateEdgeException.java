package algorithms.graph_algorithms;

public class DuplicateEdgeException extends RuntimeException {

	public DuplicateEdgeException(int idx1, int idx2) {
		super("Duplicate edge ("+idx1+","+idx2+") provided.");
	}
	
	public DuplicateEdgeException(int idx1, int idx2, int index) {
		super("Duplicate edge ("+idx1+","+idx2+") provided at connections["+index+"].");
	}
	
}
