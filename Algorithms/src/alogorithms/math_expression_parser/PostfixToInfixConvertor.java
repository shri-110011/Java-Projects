package alogorithms.math_expression_parser;

import java.util.Stack;

public class PostfixToInfixConvertor {
	
	public String convertPostfixToInfix(String postfixExp) {
		
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		String postFixExp_elements[] = postfixExp.split(" ");
		
		for(int i=0; i<postFixExp_elements.length; i++) {
			String element = postFixExp_elements[i];
			// If the element is an operand put it into the stack
			if(MathExpressionUtilities.isOperand(element))
				stack.push(element);
			
			/* If the element is an operator pop 2 items from the stack
			 * and build a new string which is:
			 * (operand1 operator operand2)
			 * and push it to the stack.
			 * operand2 is the item obtained on 1st pop
			 * operand1 is the item obtained on 2nd pop
			 */
			else if(MathExpressionUtilities.isOperator(element)) {
				if(stack.empty())
					throw new ExpressionError("Invalid postfix expression: \""+postfixExp+"\" while solving for the operator \""+element+"\" at index: "+i);
				String operand2 = stack.pop();
				
				if(stack.empty())
					throw new ExpressionError("Invalid postfix expression: \""+postfixExp+"\" while solving for the operator \""+element+"\" at index: "+i);
				String operand1 = stack.pop();
				stack.push("("+operand1+element+operand2+")");
			}
			
		}
		if(stack.size() != 1)
			if(stack.empty())
				throw new ExpressionError("Invalid postfix expression: \""+postfixExp+"\"");
		
		sb.append(stack.pop());
		return sb.toString();
		
	}

	public static void main(String[] args) {
	
		PostfixToInfixConvertor pti = new PostfixToInfixConvertor();
		String postfixExp = "K L + M N * - O P ^ W * U / V / T * + Q +";
		// d e + c * b / a +
		// K L + M N * - O P ^ W * U / V / T * + Q +
		
		System.out.println(pti.convertPostfixToInfix(postfixExp));
		

	}

}
