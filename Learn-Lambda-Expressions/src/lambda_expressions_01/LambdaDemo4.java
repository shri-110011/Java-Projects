package lambda_expressions_01;

// A block lambda that computes the factorial of an int value.

interface NumericFunc {
	int func(int n);
}

public class LambdaDemo4 {

	public static void main(String[] args) {
		// This block lambda computes the factorial of an int value.
		NumericFunc factorial = (n) -> {
			int result = 1;
			for(int i = 2; i<=n; i++) {
				result *= i;
			}
			return result;
		};
		System.out.println("The factorial of 8 is: " + factorial.func(8));
		System.out.println("The factorial of 9 is: " + factorial.func(9));
	}
}
