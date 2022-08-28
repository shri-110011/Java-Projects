package algorithms.graph_algorithms;

public class VertexNotExistException extends RuntimeException {
	
	public VertexNotExistException(String vertexName) {
		super("vertexName1: \""+vertexName+"\" does not exist in the graph.");
	}
	
	public VertexNotExistException(String vertexName, String edge, int index) {
		super("Vertex with name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"] does not exist in the graph.");
	}

}
