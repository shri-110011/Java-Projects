package algorithms.math_expression_parser;

import java.util.Stack;

public class PrefixToPostfixConvertor {
	
public String convertPrefixToPostfix(String prefixExp) {
		
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		String preFixExp_elements[] = prefixExp.split(" ");
		
		
		for(int i=preFixExp_elements.length-1; i>=0; i--) {
			String element = preFixExp_elements[i];
			// If the element is an operand put it into the stack
			if(MathExpressionUtilities.isOperand(element))
				stack.push(element);
			
			/* If the element is an operator pop 2 items from the stack
			 * and build a new string which is:
			 * (operand1 operator operand2)
			 * and push it to the stack.
			 * operand1 is the item obtained on 1st pop
			 * operand2 is the item obtained on 2nd pop
			 */
			else if(MathExpressionUtilities.isOperator(element)) {
				if(stack.empty())
					throw new ExpressionError("Invalid prefix expression: \""+prefixExp+"\" while solving for the operator \""+element+"\" at index: "+i);
				String operand1 = stack.pop();
				
				if(stack.empty())
					throw new ExpressionError("Invalid prefix expression: \""+prefixExp+"\" while solving for the operator \""+element+"\" at index: "+i);
				String operand2 = stack.pop();
				stack.push(operand1+" "+operand2+" "+element);
			}
			
		}
		if(stack.size() != 1)
			if(stack.empty())
				throw new ExpressionError("Invalid prefix expression: \""+prefixExp+"\"");
		
		sb.append(stack.pop());
		return sb.toString();
		
	}

	public static void main(String[] args) {
		
		PrefixToPostfixConvertor ptp = new PrefixToPostfixConvertor();
		String postFixExp = "+ + - + K L * M N * / / * ^ O P W U V T Q";
		
		System.out.println(ptp.convertPrefixToPostfix(postFixExp));
		
	}

}
// K L + M N * - O P ^ W * U / V / T * + Q +
