package alogorithms.math_expression_parser;

import java.util.HashMap;

import alogorithms.math_expression_parser.OperatorProperties.Associativity;

public class MathematicalOperator implements Comparable<MathematicalOperator> {
	String op;
	static private HashMap<String, OperatorProperties> lhm =  new HashMap<>();
	
	public MathematicalOperator(String op) {
		this.op = op;
		loadOperators();
	}
	
	public void loadOperators() {
		lhm.put("/", new OperatorProperties(50, Associativity.LEFT_TO_RIGHT));
		lhm.put("*", new OperatorProperties(50, Associativity.LEFT_TO_RIGHT));
		lhm.put("%", new OperatorProperties(50, Associativity.LEFT_TO_RIGHT));
		lhm.put("+", new OperatorProperties(45, Associativity.LEFT_TO_RIGHT));
		lhm.put("-", new OperatorProperties(45, Associativity.LEFT_TO_RIGHT));
		lhm.put("^", new OperatorProperties(60, Associativity.RIGHT_TO_LEFT));
	}
	
	@Override
	public int compareTo(MathematicalOperator o) {
		return lhm.get(op).precedence - lhm.get(o.op).precedence;
	}
	
	public int getAssociativity(String op) {
		if(lhm.get(op).associativity == Associativity.LEFT_TO_RIGHT) return -1;
		else return 1;
	}
	
	public boolean isValidOpearator(String op) {
		if(lhm.containsKey(op)) return true;
		else return false;
	}
}
