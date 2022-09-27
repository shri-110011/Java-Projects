package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class InvalidEdgeFormatException extends RuntimeException {

	static final Logger log = LogManager.getLogger(InvalidEdgeFormatException.class.getName());
	
	public InvalidEdgeFormatException(String edge, int index) {
		super("Invalid edge: \""+edge+"\" at connections["+index+"], the string representation of the edge should be of the format \"vertex1Name vertex2Name\" for unweighted graph.");
		String msg = "Invalid edge: \""+edge+"\" at connections["+index+"], the string representation of the edge should be of the format \"vertex1Name vertex2Name\".";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public InvalidEdgeFormatException(String edge, int index, boolean hasWeight) {
		super("Invalid edge: \""+edge+"\" at connections["+index+"], the string representation of the edge should be of the format \"vertex1Name vertex2Name weight\" for weighted graph.");
		String msg = "Invalid edge: \""+edge+"\" at connections["+index+"], the string representation of the edge should be of the format \"vertex1Name vertex2Name\".";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
}
