package alogorithms.math_expression_parser;

public class OperatorProperties {
	int precedence;
	Enum<Associativity> associativity;
	enum Associativity {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT
	}
	
	public OperatorProperties(int precedence, Enum<Associativity> associativity) {
		this.precedence = precedence;
		this.associativity = associativity;
	}
	
}
