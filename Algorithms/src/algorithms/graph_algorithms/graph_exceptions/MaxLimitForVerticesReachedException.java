package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class MaxLimitForVerticesReachedException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(MaxLimitForVerticesReachedException.class.getName());
	
	public MaxLimitForVerticesReachedException(String vertexName, int maximumAllowedVertices) {
		super("Cannot insert vertex \""+vertexName+"\" because noOfVertices has reached its limit which is "+maximumAllowedVertices+".");
		String msg = "Cannot insert vertex \""+vertexName+"\" because noOfVertices has reached its limit which is "+maximumAllowedVertices+".";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public MaxLimitForVerticesReachedException(String vertexName, int maximumAllowedVertices, int index) {
		super ("Cannot insert vertex \""+vertexName+"\" in verticesNames["+index+"] to the graph because noOfVertices has reached its limit which is "+maximumAllowedVertices+".");
		String msg = "Cannot insert vertex \""+vertexName+"\" in verticesNames["+index+"] to the graph because noOfVertices has reached its limit which is "+maximumAllowedVertices+".";
		HelperClass.logException(log, msg, getStackTrace());
	}

}
