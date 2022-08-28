package algorithms.graph_algorithms;

public class MaxLimitForVerticesReachedException extends RuntimeException {
	
	public MaxLimitForVerticesReachedException(String vertexName, int maximumAllowedVertices) {
		super("Cannot insert vertex \""+vertexName+"\" because noOfVertices has reached its limit which is "+maximumAllowedVertices+".");
	}
	
	public MaxLimitForVerticesReachedException(String vertexName, int maximumAllowedVertices, int index) {
		super ("Cannot insert vertex \""+vertexName+"\" in verticesNames["+index+"] to the graph because noOfVertices has reached its limit which is "+maximumAllowedVertices+".");
	}

}
