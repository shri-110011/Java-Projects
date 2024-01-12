package lambda_expressions_02;

interface DoubleNumericArrayFunc {
	double fun(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
	EmptyArrayException() {
		super("Array is Empty");
	}
}

public class LambdaExceptionDemo {

	public static void main(String[] args) throws EmptyArrayException {
		double[] values = {1.0, 2.0, 3.0, 4.0};
		
		// This block lambda computes the average of an array of doubles.
		/* Remember, the inclusion of the throws clause in func() is necessary.
		Without it, the program will not compile because the lambda expression 
		will no longer be compatible with func().
		
		Notice that the parameter specified by func() in the functional
		interface DoubleNumericArrayFunc is an array. However, the parameter to
		the lambda expression is simply n, rather than n[]. Remember, the type of 
		a lambda expression parameter will be inferred from the target context. 
		In this case, the target context is double[], thus the type of n will be 
		double[]. It is not necessary (or legal) to specify it as n[]. It would be 
		legal to explicitly declare it as double[ ] n, but doing so gains nothing 
		in this case.
		*/
		
		DoubleNumericArrayFunc average = (n) -> {
			
			if(n.length == 0)
				throw new EmptyArrayException();
			
			double sum = 0;
			for(int i=0; i<values.length; i++) {
				sum += values[i];
			}
			
			return sum/values.length;
		};
		
		System.out.println("The average is: " + average.fun(values));
		
		// This causes an exception to be thrown.
		System.out.println("The average is: " + average.fun(new double[0]));
	}

}
