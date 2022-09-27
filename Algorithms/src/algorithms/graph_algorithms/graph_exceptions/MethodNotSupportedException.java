package algorithms.graph_algorithms.graph_exceptions;

public class MethodNotSupportedException extends RuntimeException {

	public MethodNotSupportedException(String methodName, String notSupportedFor) {
		super("The method '"+methodName+"' is not supported for "+notSupportedFor+".");
	}
	
}
