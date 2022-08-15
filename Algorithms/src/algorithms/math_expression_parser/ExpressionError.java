package algorithms.math_expression_parser;

public class ExpressionError extends RuntimeException {
	private String str;
	public ExpressionError(String str) {
		super(str);
	}
	
//	public String toString() {
//		return str;
//	}
}