package test_algorithms.test_math_expression_parser;

import org.junit.Assert;
import org.junit.Test;

import algorithms.math_expression_parser.PostfixToPrefixConvertor;

public class PostfixToPrefixConvertorTest {
	
	@Test
	public void testCovertPostFixToInfixMethod() {
		String expressions[] = {
				"K L + M N * - O P ^ W * U / V / T * + Q +",
				"a b / c d + * e / f / g * h -", "-7 8 +"
		};
		String results[] = {
				"+ + - + K L * M N * / / * ^ O P W U V T Q",
				"- * / / * / a b + c d e f g h", "+ -7 8"
		};
		
		int i=0;
		
		PostfixToPrefixConvertor ipc = new PostfixToPrefixConvertor();
		
		for(String expresion: expressions) {
			String actual = ipc.convertPostfixToPrefix(expresion);
//			System.out.println(actual);
			Assert.assertEquals(results[i], actual);
			i++;
		}	
	}


}
