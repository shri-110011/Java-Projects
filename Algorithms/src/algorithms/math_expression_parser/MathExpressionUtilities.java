package algorithms.math_expression_parser;

public class MathExpressionUtilities {
	
	/*This method checks the precedence of operator 1(op1) wrt 
	 * operator 2(op2).
	 */
	static public int check(String op1, String op2) {
		MathematicalOperator m_op1 = new MathematicalOperator(op1);
		MathematicalOperator m_op2 = new MathematicalOperator(op2);
		
		if(m_op1.compareTo(m_op2) > 0 ) {
			return 1;
		}
		else if(m_op1.compareTo(m_op2) < 0 ) {
			return -1;
		}
		else return 0;
	}
	
	static public boolean hasLeftAssociativity(String op) {
		MathematicalOperator m_op1 = new MathematicalOperator(op);
		if(m_op1.getAssociativity(op) == -1) return true;
		else return false;
	}
	
	static public boolean isOperator(String op) {
		MathematicalOperator m_op1 = new MathematicalOperator(op);
		return m_op1.isValidOpearator(op);
	}
	
	/*This method checks if the string specified by operand is an operand or
	 * not. An operand is a digit or a variable whose name starts with
	 * an alphabet. And a negative number is also considered as an operand.
	 */
	static public boolean isOperand(String operand) {
		int operandLength = operand.length();
		boolean decimalEncountered = false;
		for(int i=0; i<operandLength; i++) {
			char ch = operand.charAt(i);
			
			if(i == 0 && ch == '-') {
				if(operandLength > 1 && Character.isDigit(operand.charAt(1)))
				continue;
			}
			
			if(ch == '.' && !decimalEncountered) {
				decimalEncountered = true;
				continue;
			}
			else if(ch == '.' && decimalEncountered) {
				return false;
			}
			
			if(!Character.isAlphabetic(ch) && !Character.isDigit(ch)) 
				return false;
		}
		return true;
	}
	
	/* This method takes 2 operands and one binary operator and then 
	 * performs the binary operation on them.
	 */
	static public double solveBinaryExpression(double operand1, double operand2, String operator) {
		Calculate division = (double a, double b) -> a/b;
		Calculate multiplication = (double a, double b) -> a*b;
		Calculate remainder = (double a, double b) -> a%b;
		Calculate addition = (double a, double b) -> a+b;
		Calculate subtraction = (double a, double b) -> a-b;
		Calculate exponentiation = (double a, double b) -> Math.pow(a, b);
		double result;
		switch(operator) {
			case "/":
				result = division.perform(operand1, operand2);
				break;
			case "*":
				result = multiplication.perform(operand1, operand2);
				break;
			case "%":
				result = remainder.perform(operand1, operand2);
				break;
			case "+":
				result = addition.perform(operand1, operand2);
				break;
			case "-":
				result = subtraction.perform(operand1, operand2);
				break;
			case "^":
				result = exponentiation.perform(operand1, operand2);
				break;
			default:
				throw new IllegalArgumentException("Invalid operator: "+operator+" provided!");
		}
		return result;
	}

}
