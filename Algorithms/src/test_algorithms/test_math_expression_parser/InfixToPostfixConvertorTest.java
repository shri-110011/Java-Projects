package test_algorithms.test_math_expression_parser;

import org.junit.Test;

import alogorithms.math_expression_parser.InfixToPostfixConvertor;
import org.junit.Assert;

public class InfixToPostfixConvertorTest {

	@Test
	public void testCovertInfixToPostfixMethod() {
		String expressions[] = {
				"A+C/B*D/E", "(A+C/B*D/E)",
				"A*B+C*D", "(A*B+C*D)",
				"A+B/C*D^E", "(A+B/C*D^E)",
				"A+(B+C*D-(H-I/J))*E/F+G", "(A+(B+C*D-(H-I/J))*E/F+G)",
				"A1+B2*C/D", "A*C/(D+B2)",
				"12+4/2", "(12+4/2)", "12 + 4 / 2", "(12 + 4 / 2)"
		};
		
		String results[] = {
				"A C B / D * E / +", "A C B / D * E / +",
				"A B * C D * +", "A B * C D * +",
				"A B C / D E ^ * +", "A B C / D E ^ * +",
				"A B C D * + H I J / - - E * F / + G +", "A B C D * + H I J / - - E * F / + G +",
				"A1 B2 C * D / +", "A C * D B2 + /",
				"12 4 2 / +", "12 4 2 / +", "12 4 2 / +", "12 4 2 / +"
				
		};
		int i=0;
		
		InfixToPostfixConvertor ipc = new InfixToPostfixConvertor();
		
		for(String expresion: expressions) {
			String actual = ipc.covertInfixToPostfix(expresion);
//			System.out.println(actual);
			Assert.assertEquals(results[i], actual);
			i++;
		}
	}
	
	@Test
	public void testSolvePostfixMethod() {
		String expressions[] = {
			"7 15 4 * +", "7 8 -", "4 9 5 * 15 - 2 3 ^ / +",
			"7 - 8 +"
		};
		
		double results[] = {67, -1, 7.75, 1};
		int i=0;
		
		InfixToPostfixConvertor ipc = new InfixToPostfixConvertor();
		for(String expresion: expressions) {
			double actual_result = ipc.solvePostfix(expresion);
			Assert.assertEquals(results[i], actual_result, 0);
			i++;
		}
		
	}
	
}
