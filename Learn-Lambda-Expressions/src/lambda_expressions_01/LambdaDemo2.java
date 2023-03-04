package lambda_expressions_01;

// Demonstrates a lambda expression that takes a parameter.

interface NumericTest {
	boolean test(int n);
}
public class LambdaDemo2 {

	public static void main(String[] args) {
		// A lambda expression that tests if a number is even.
		/* Notice that the type of n is not specified. Rather, its type is inferred
		 * from the context. In this case, its type is inferred from the parameter 
		 * type of test() as defined by the NumericTest interface, which is 
		 * int. */
		NumericTest isEven = (n) -> (n % 2) == 0;
		
		if(isEven.test(10)) System.out.println("10 is even");
		if(!isEven.test(9)) System.out.println("9 is not even");
		
		// Now use a lambda expression that tests if a number is non-negative
		NumericTest isNonNeg = (n) -> n >= 0;
		
		if(isNonNeg.test(1)) System.out.println("1 is non-negative");
		if(!isNonNeg.test(-1)) System.out.println("-1 is negative");
	}

}
