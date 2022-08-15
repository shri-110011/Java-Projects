package alogorithms.math_expression_parser;

import java.util.Stack;

public class InfixToPrefixConvertor {
	
	/* This covertInfixToPrefix(String exp) method doesn't check the 
	 * validity of the given infix expression except for the invalid
	 * operand and invalid character that may appear in the infix 
	 * expression.
	 * 
	 */
	public String covertInfixToPrefix(String exp) {
		StringBuilder sb_exp = new StringBuilder();
		
		/* Here we removing any space characters from the infix 
		 * expression exp.
		 */
		exp = exp.replace(" ", "");
		
		/* Here we are scanning the infix expression(exp) from right to
		 * left and converting each ')' to '(' and each '(' to ')' and 
		 * then appending the character to the StringBuilder sb_exp.
		 * 
		 */
		for(int i=exp.length()-1; i>=0; i--) {
			char ch = exp.charAt(i);
			if(ch == '(') sb_exp.append(")");
			else if(ch == ')') sb_exp.append("(");
			else sb_exp.append(ch);
		}
//		System.out.println(sb_exp.toString());
		
		String orig_exp = exp;
		exp = sb_exp.toString();
		
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int sb_size = 0;
		boolean alphabetEncountered = false;
		
		String temp = null;
		int expLen = exp.length();
		
		/* Here we are scanning the sb_exp which is the reverse of orig_exp
		 * and from left to right.
		 */
		for(int i=0; i<expLen; i++) {
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
					sb_size+=2;
				}
			}
			else if(MathExpressionUtilities.isOperand(ch)) {
				// Here we are sure that the character is an operand.
				do {
					if(Character.isAlphabetic(ch.charAt(0)) 
							&& !alphabetEncountered) {
						alphabetEncountered = true;
					}
					sb.append(ch);
					sb_size++;
					i++;
					if(i == exp.length()) break;
					ch = Character.toString(exp.charAt(i));
				}while(MathExpressionUtilities.isOperand(ch));
				i--;
				
				/* Here we are checking if the last character of the 
				 * operand is a digit or not and then using the 
				 * alphabetEncountered(which tells if an alphabet has been 
				 * encountered before while scanning the current operand)
				 * variable to decide if this operand is a valid one or 
				 * not.
				 * Eg: a+w1 -> 1w+a w1 is a valid operand because w1 
				 * represents a variable.
				 *     or
				 *     a+1w -> w1+a 1w is an invalid operand
				 */
				char curChar = exp.charAt(i);
				if(Character.isDigit(curChar) && alphabetEncountered == true)
					throw new ExpressionError("Invalid operand: '"+curChar+"' at index: "+(expLen - i -1)+" in the expression: \""+orig_exp+"\"");
				
				sb.append(" ");
				sb_size++;
				alphabetEncountered = false;
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
				
				/* Here we take care of the case when infix expression is:
				 * -7+8 then the prefix should be + -7 8 and not + - 7 8
				 * 
				 */
				if(ch.equals("-")) {
					if(i == expLen-1) {
						sb.replace(sb_size-1, sb_size, "- ");
						continue;
					}
					else {
						ch = Character.toString(exp.charAt(i+1));
						if(MathExpressionUtilities.isOperator(ch)) {
							sb.replace(sb_size-1, sb_size, "- ");
							continue;
						}
						ch = "-";
					}
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
						sb_size+=2;
						
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
						if(!MathExpressionUtilities.hasLeftAssociativity(stack.peek())) {
							sb.append(stack.pop()+" ");
							sb_size+=2;
						}
					}
					// Finally we push the current operator to the stack.
					stack.push(ch);
				}
				
				/* currentOperatorRelativePrecedence = 0 means the current
				 * operator has same precedence as the top element in stack.
				 */
				else {
					if(!MathExpressionUtilities.hasLeftAssociativity(stack.peek())) {
						sb.append(stack.pop()+" ");
						sb_size+=2;
					}
					stack.push(ch);
				}
			}
			else {
				throw new ExpressionError("Invalid character: '"+ch+"' at index: "+(expLen-i-1)+" in the expression: \""+orig_exp+"\"");
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
				sb_size+=2;
			}
		}
		
		/* Here we return the reverse and trimmed version of 
		 * StrigBuilder sb. 
		 */
		return sb.reverse().toString().trim();
	}
	
	public double solvePrefix(String exp) {
		
		Stack<Double> stack = new Stack<>();
		String exp_arr[] = exp.split(" ");
		
		/* idx is used to keep track of the element in the exp_arr[]
		 * so that we can provide rich ExpressionError messages.
		 */
		
		int idx=0, len = exp_arr.length;
		
		for(int j=len-1; j>=0; j--) {
			String element = exp_arr[j];
			if(MathExpressionUtilities.isOperand(element)) {
				
				/* We are looping through the operand to check if the 
				 * operand contains any alphabet or not since variable
				 * are not allowed in the prefix expression.
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
					throw new ExpressionError("Invalid prefix expression: \""+exp+"\"");
				operand1 = stack.pop();
				
				if(stack.isEmpty()) {
					/* We are here accounting for the case when prefix 
					 * expression is say: + - 7 8
					 */
					if(element.equals("-")) {
						operand2 = -1;
						element = "*";
					}
					else
						throw new ExpressionError("Invalid prefix expression: \""+exp+"\"");
				}
				else
					operand2 = stack.pop();
				
				boolean isOperand1_Valid, isOperand2_Valid;
				isOperand1_Valid = MathExpressionUtilities.isOperand(""+operand1);
				isOperand2_Valid = MathExpressionUtilities.isOperand(""+operand2);
				
				if(!isOperand1_Valid || !isOperand2_Valid) {
					if(!isOperand2_Valid)
						throw new ExpressionError("Invalid prefix expression: Found "+operand2+" at index: "+(idx-1)+" in the expression: \""+exp+"\"");
					else if(!isOperand1_Valid)
						throw new ExpressionError("Invalid prefix expression: Found "+operand1+" at index: "+(idx-2)+" in the expression: \""+exp+"\"");
				}
				
				result = MathExpressionUtilities.solveBinaryExpression(operand1, operand2, element);
				
				stack.push(result);
			}
			else {
				throw new ExpressionError("Invalid prefix expression: \""+exp+"\"");
			}
			idx++;
		}
		
		/*Here we check the validity of prefix expression. If even after 
		 * scanning the entire expression the stack is not empty this means
		 * the prefix expression is wrong.
		 */
		if(stack.size() > 1 || stack.empty())
			throw new ExpressionError("Invalid prefix expression: \""+exp+"\"");
		
		return stack.pop();
	}

	public static void main(String[] args) {
		String exp = "a/b*(c+d)/e/f*g-h";
		// A+(B+C*D-(H-I/J))*E/F+G
		// K+L-M*N+(O^P)*W/U/V*T+Q
		
		InfixToPrefixConvertor ipc = new InfixToPrefixConvertor();
		
		System.out.println(ipc.covertInfixToPrefix(exp));
//		System.out.println(ipc.solvePrefix(exp));

	}

}
