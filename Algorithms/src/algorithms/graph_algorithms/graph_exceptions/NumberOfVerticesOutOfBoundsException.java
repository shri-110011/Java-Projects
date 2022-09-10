package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class NumberOfVerticesOutOfBoundsException extends RuntimeException {

	static final Logger log = LogManager.getLogger(NumberOfVerticesOutOfBoundsException.class.getName());
	
	public NumberOfVerticesOutOfBoundsException(int noOfVerticesLimit, int noOfVertices) {
		super("Value for 'noOfVertices' must be in the range of [0-"+noOfVerticesLimit+"], provided value is: "+noOfVertices+".");
		String msg = "Value for 'noOfVertices' must be in the range of [0-"+noOfVerticesLimit+"], provided value is: "+noOfVertices+".";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
}
