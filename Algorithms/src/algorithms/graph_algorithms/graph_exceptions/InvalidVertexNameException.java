package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class InvalidVertexNameException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(InvalidVertexNameException.class.getName());
	
	public InvalidVertexNameException(String vertexName) {
		super("Invalid vertex name: "+vertexName+", vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
		String msg = "Invalid vertex name: "+vertexName+", vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public InvalidVertexNameException(String vertexName, int index) {
		super("Invalid vertex name: \""+vertexName+"\" at verticesNames["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
		String msg = "Invalid vertex name: \""+vertexName+"\" at verticesNames["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public InvalidVertexNameException(String vertexName, String edge, int index) {
		super("Invalid vertex name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
		String msg = "Invalid vertex name: \""+vertexName+"\" in the connection \""+edge+"\" at connections["+index+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.";
		HelperClass.logException(log, msg, getStackTrace());
	}
}
