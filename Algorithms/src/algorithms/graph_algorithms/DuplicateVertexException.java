package algorithms.graph_algorithms;

public class DuplicateVertexException extends RuntimeException {
	
	public DuplicateVertexException(String duplicateVertexName) {
		super("Duplicate vertex with name \""+duplicateVertexName+"\" provided.");
	}
	
	public DuplicateVertexException(String duplicateVertexName, int index) {
		super("Found duplicate vertex \""+duplicateVertexName+"\" at verticesNames["+index+"].");
	}
}
