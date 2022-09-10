package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class DuplicateVertexException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(DuplicateVertexException.class.getName());
	
	public DuplicateVertexException(String duplicateVertexName) {
		super("Duplicate vertex with name \""+duplicateVertexName+"\" provided.");
		String msg =  "Duplicate vertex with name \""+duplicateVertexName+"\" provided.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public DuplicateVertexException(String duplicateVertexName, int index) {
		super("Found duplicate vertex \""+duplicateVertexName+"\" at verticesNames["+index+"].");
		String msg = "Found duplicate vertex \""+duplicateVertexName+"\" at verticesNames["+index+"].";
		HelperClass.logException(log, msg, getStackTrace());
	}
}
