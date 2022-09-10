package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class VerticesAndEdgesGroupsLengthMismatchException extends RuntimeException {
	
	static final Logger log = LogManager.getLogger(VerticesAndEdgesGroupsLengthMismatchException.class.getName());
	
	public VerticesAndEdgesGroupsLengthMismatchException(int vgsLen, int egsLen) {
		super("Length of verticesGropus: "+vgsLen+" , length of edgesGroups: "+egsLen);
		String msg = "Length of verticesGropus: "+vgsLen+" , length of edgesGroups: "+egsLen;
		HelperClass.logException(log, msg, getStackTrace());
	}
}
