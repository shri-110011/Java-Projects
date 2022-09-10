package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class InvalidEdgeException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(InvalidEdgeException.class.getName());
	
	public InvalidEdgeException(int idx, String endpointType) {
		super("Index: "+idx+" for "+endpointType+" out of bounds.");
		String msg = "Index: "+idx+" for "+endpointType+" out of bounds.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
}
