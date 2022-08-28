package algorithms.graph_algorithms;

public class InvalidVertexNameException extends RuntimeException {
	
	public InvalidVertexNameException(String vertexName) {
		super("Invalid vertex name: "+vertexName+", vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
	}
	
	public InvalidVertexNameException(String vertexName, int index) {
		super("Invalid vertex name: \""+vertexName+"\" at verticesNames["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
	}
	
	public InvalidVertexNameException(String vertexName, String edge, int index) {
		super("Invalid vertex name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
	}
}
