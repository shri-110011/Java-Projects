package lambda_expressions_03;

import java.util.function.Function;

// Use the Function built in functional interface.
public class UseFunctionInterfaceDemo {

	public static void main(String[] args) {
		/* This block lambda computes the factorial of an int value but this time
		 * uses the Function functional interface. */
		
		Function<Integer, Integer> factorial = (n) -> {
			int result = 1;
			
			for(int i = 2; i<=n; i++) {
				result *= i;
			}
			
			return result;
		};
		
		System.out.println("The factorial of 8 is: " + factorial.apply(8));
		System.out.println("The factorial of 9 is: " + factorial.apply(9));
	}

}
