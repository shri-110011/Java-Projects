package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class InvalidVertexIndexException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(InvalidVertexIndexException.class.getName());
	
	public InvalidVertexIndexException(int noOfVertices, int nodeIdx) {
		super("Number of vertices in the graph is: "+noOfVertices+" but provided out of range index: "+nodeIdx+".");
		String msg = "Number of vertices in the graph is: "+noOfVertices+" but provided out of range index: "+nodeIdx+".";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
}
