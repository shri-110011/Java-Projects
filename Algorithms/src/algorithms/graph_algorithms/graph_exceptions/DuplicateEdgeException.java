package algorithms.graph_algorithms.graph_exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.HelperClass;

public class DuplicateEdgeException extends RuntimeException {

	static final Logger log = LogManager.getLogger(DuplicateEdgeException.class.getName());
	
	public DuplicateEdgeException(int idx1, int idx2, boolean isDirected) {
		super("Duplicate edge ("+idx1+","+idx2+") provided for "+(!isDirected? "un":"")+"directed graph.");
		String msg = "Duplicate edge ("+idx1+","+idx2+") provided for "+(!isDirected? "un":"")+"directed graph.";
		HelperClass.logException(log, msg, getStackTrace());
	}
	
	public DuplicateEdgeException(int idx1, int idx2, int index, boolean isDirected) {
		super("Duplicate edge ("+idx1+","+idx2+") provided at connections["+index+"] for "+(!isDirected? "un":"")+"directed graph.");
		String msg = "Duplicate edge ("+idx1+","+idx2+") provided at connections["+index+"] for "+(!isDirected? "un":"")+"directed graph.";
		HelperClass.logException(log, msg, getStackTrace());
	}
}
