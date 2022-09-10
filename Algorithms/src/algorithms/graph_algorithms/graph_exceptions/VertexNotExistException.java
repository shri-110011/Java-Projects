package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class VertexNotExistException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(VertexNotExistException.class.getName());
	
	public VertexNotExistException(String vertexName) {
		super("vertexName1: \""+vertexName+"\" does not exist in the graph.");
		String msg = "vertexName1: \""+vertexName+"\" does not exist in the graph.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public VertexNotExistException(String vertexName, String edge, int index) {
		super("Vertex with name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"] does not exist in the graph.");
		String msg = "Vertex with name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"] does not exist in the graph.";
		HelperClass.logException(log, msg, getStackTrace());
	}

}
