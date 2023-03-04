package lambda_expressions_01;

// Demonstrates a single lambda expression.
interface MyNumber {
	double getValue();
}
public class LambdaDemo1 {

	public static void main(String[] args) {
		MyNumber myNum; // declare an interface reference
		
		// Here the lambda expression is a constant expression.
		// When it is assigned to myNum, a class instance is constructed in
		// which the lambda expression implements the getValue() in MyNumber.
		myNum = () -> 123.45;
		
		// Call getValue(), which is provided by the previously assigned lambda
		// expression.
		System.out.println("A fixed value:" + myNum.getValue());
		
		// Here a more complex expression is used.
		myNum = () -> Math.random()*100;
		System.out.println("A random value:" + myNum.getValue());
		System.out.println("Another random value:" + myNum.getValue());
		
		// A lambda expression must be compatible with the method defined by the 
		// functional interface. Therefore this won't work.
//		myNum = () -> "123.04"; // Error
	}

}
