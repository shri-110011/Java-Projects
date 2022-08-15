package algorithms.math_expression_parser;

import java.util.Stack;

public class InfixToPostfixConvertor {
	
	/* This covertInfixToPostfix(String exp) method doesn't check the 
	 * validity of the given infix expression except for the invalid
	 * operand and invalid character that may appear in the infix 
	 * expression.
	 * 
	 */
	public String covertInfixToPostfix(String exp) {
		
		/* Here we removing any space characters from the infix 
		 * expression exp.
		 */
		exp = exp.replace(" ", "");
		
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		boolean operandEncountered = false, operandIsVariable = true;
		
		String temp = null;
		for(int i=0; i<exp.length(); i++) {
			String  ch = Character.toString(exp.charAt(i));
			
			// If the character is a "(" then push that to stack.
			if(ch.equals("(")) {
				stack.push(ch);
			}
			// If the character is a ")" then pop the stack until a 
			// "(" is found or the stack becomes empty
			else if(ch.equals(")")){	
				while(!stack.empty() &&  !(temp = stack.pop()).equals("(")) {
					if(temp.equals(")") || temp.equals("(")) continue;
					sb.append(temp+" ");
				}
			}
			else if(MathExpressionUtilities.isOperand(ch)) {
				// Here we are sure that the character is an operand.
				do {
					if(!operandEncountered) {
						operandEncountered = true;
						if(Character.isAlphabetic(ch.charAt(0)))
							operandIsVariable = true;
						else
							operandIsVariable = false;
					}
					else {
						if(Character.isAlphabetic(ch.charAt(0)) && !operandIsVariable) {
							throw new ExpressionError("Invalid operand: '"+ch+"' at index: "+i+" in the expression: \""+exp+"\"");
						}
					}
					sb.append(ch);
					i++;
					if(i == exp.length()) break;
					ch = Character.toString(exp.charAt(i));
				}while(MathExpressionUtilities.isOperand(ch));
				i--;
				sb.append(" ");
				operandEncountered = false;
			}
			// Here we check if the character is an operator or not.
			else if(MathExpressionUtilities.isOperator(ch)){
				/* If stack is empty or the top of stack contains "(" and 
				 * an operator is encountered then push it to the stack 
				 * and read the next character.
				 */
				if((!stack.empty() && stack.peek().equals("(")) || stack.empty()) { 
					stack.push(ch);
					continue;
				}
				/* Here we check the precedence of the current operator 
				 * with the top element in stack. Also at this point it is
				 * confirmed that the top of stack is an operator.
				 * 
				 */
				int currentOperatorRelativePrecedence = MathExpressionUtilities.check(ch, stack.peek());
				
				/* currentOperatorRelativePrecedence tells the precedence 
				 * of current operator with respect to the top element in 
				 * stack.
				 * 
				 * currentOperatorRelativePrecedence = 1 means the current
				 * operator has higher precedence than top element in stack.
				 */
				if(currentOperatorRelativePrecedence == 1) stack.push(ch);
				
				/* currentOperatorRelativePrecedence = -1 means the current
				 * operator has lower precedence than top element in stack.
				 */
				else if (currentOperatorRelativePrecedence == -1) {
					/* 1. Here we know that the current operator has lower 
					 * precedence than top element in stack so we pop the 
					 * stack and then check if the stack is empty or not.
					 * 
					 * 2. If the stack is empty then we stop otherwise we 
					 * again check the current operator's precedence with 
					 * the new top element in stack.
					 * 
					 * 3. If the current operator precedence is lower than 
					 * top element in stack we repeat step 1 through 3 
					 * otherwise we stop.
					 * 
					 */
					do {
						sb.append(stack.pop()+" ");
						
						if(stack.empty()) break;
						
						if(stack.peek().equals("("))
							break;
//							stack.pop();
						
						if(stack.empty()) break;
						
						currentOperatorRelativePrecedence = MathExpressionUtilities.check(ch, stack.peek());
						
					} while(currentOperatorRelativePrecedence == -1);
					
					/* Here we know that the either 
					 * currentOperatorRelativePrecedence = 0 or stack is 
					 * empty.
					 * 
					 * If currentOperatorRelativePrecedence = 0 then we
					 * check the associativity of the top element in stack.
					 * If its associativity is left to right we pop the 
					 * stack.
					 */
					if(!stack.empty() && currentOperatorRelativePrecedence == 0) {
						if(MathExpressionUtilities.hasLeftAssociativity(stack.peek())) {
							sb.append(stack.pop()+" ");
						}
					}
					// Finally we push the current operator to the stack.
					stack.push(ch);
				}
				
				/* currentOperatorRelativePrecedence = 0 means the current
				 * operator has same precedence as the top element in stack.
				 */
				else {
					if(MathExpressionUtilities.hasLeftAssociativity(stack.peek())) {
						sb.append(stack.pop()+" ");
					}
					stack.push(ch);
				}
			}
			else {
				/* Here we are allowing the delimiter space(" ") to separate
				 * variables and operators.
				 */
				if(ch.equals(" ")) continue;
				throw new ExpressionError("Invalid character: '"+ch+"' at index: "+i+" in the expression: \""+exp+"\"");
				
			}
		}
		/* Here we are checking if the stack has any elements even after 
		 * scanning through the whole expression exp and if it does then 
		 * we just keep popping it and append the each popped element to 
		 * StrigBuilder sb.
		 */
		if(!stack.empty()) {
			while(!stack.empty()) {
				if(stack.peek().equals("(")) {
					stack.pop();
					continue;
				}
				sb.append(stack.pop()+" ");
			}
		}
		
		// Here we return the trimmed version of StrigBuilder sb. 
		return sb.toString().trim();
	}
	
	public double solvePostfix(String exp) {
		
		Stack<Double> stack = new Stack<>();
		String exp_arr[] = exp.split(" ");
		
		/* idx is used to keep track of the element in the exp_arr[]
		 * so that we can provide rich ExpressionError messages.
		 */
		
		int idx=0;
		
		for(String element: exp_arr) {
			if(MathExpressionUtilities.isOperand(element)) {
				
				/* We are looping through the operand to check if the 
				 * operand contains any alphabet or not since variable
				 * are not allowed in the postfix expression.
				 */
				for(int i=0; i<element.length(); i++) {
					int ch = element.charAt(i);
					if(Character.isAlphabetic(ch)) {
						throw new ExpressionError("Invalid expression: \""+exp+"\", the provided expression shouldn't contain any variable!");
					}
				}
				
				// Here we are sure that the operand is a digit.
				stack.push(Double.parseDouble(element));
				
			}
			else if(MathExpressionUtilities.isOperator(element)) {
				
				// Here we are sure that the element is an operator.
				double operand1, operand2, result;
				
				/* Checking if stack is empty or not before any pop 
				 * operation in order to avoid the EmptyStackException.
				 */
				if(stack.isEmpty())
					throw new ExpressionError("Invalid postfix expression: \""+exp+"\"");
				operand2 = stack.pop();
				
				if(stack.isEmpty()) {
					/* We are here accounting for the case when postfix 
					 * expression is say: 7 - 8 +
					 */
					if(element.equals("-")) {
						operand1 = -1;
						element = "*";
					}
					else
						throw new ExpressionError("Invalid postfix expression: \""+exp+"\"");
				}
				else
					operand1 = stack.pop();
				
				boolean isOperand1_Valid, isOperand2_Valid;
				isOperand1_Valid = MathExpressionUtilities.isOperand(""+operand1);
				isOperand2_Valid = MathExpressionUtilities.isOperand(""+operand2);
				
				if(!isOperand1_Valid || !isOperand2_Valid) {
					if(!isOperand2_Valid)
						throw new ExpressionError("Invalid postfix expression: Found "+operand2+" at index: "+(idx-1)+" in the expression: \""+exp+"\"");
					else if(!isOperand1_Valid)
						throw new ExpressionError("Invalid postfix expression: Found "+operand1+" at index: "+(idx-2)+" in the expression: \""+exp+"\"");
				}
				
				result = MathExpressionUtilities.solveBinaryExpression(operand1, operand2, element);
				
				stack.push(result);
			}
			else {
				throw new ExpressionError("Invalid postfix expression: \""+exp+"\"");
			}
			idx++;
		}
		
		/*Here we check the validity of postfix expression. If even after 
		 * scanning the entire expression the stack is not empty this means
		 * the postfix expression is wrong.
		 */
		if(stack.size() > 1 || stack.empty())
			throw new ExpressionError("Invalid postfix expression: \""+exp+"\"");
		
		return stack.pop();
	}

	public static void main(String[] args) {
		
		String exp = "((((K+L)-(M*N))+(((((O^P)*W)/U)/V)*T))+Q)";
		//4 9 5 * 15 - 2 3 ^ / +
		//4+(9*5-15)/2^3
		// A+C/B*D/E (A*B+C*D) (A+B/C*D^E) A+(B+C*D-(H-I/J))*E/F+G
		InfixToPostfixConvertor itpo = new InfixToPostfixConvertor();
		System.out.println(itpo.covertInfixToPostfix(exp));
		
//		System.out.println(itpo.solvePostfix(exp));

	}

}
