package lambda_expressions_03;

//Demonstrate a method reference for a static method.

//A functional interface for string operations.
interface StringFunc2 {
	String func(String n);
}

//This class defines a static method strReverse(). 
class MyStringOps2 {
	int secret = 9;
	// A static method that reverses a string
	String strReverse(String str) {
		String result = "";
		System.out.println(secret);
		for(int i=str.length()-1; i>=0; i--) {
			result += str.charAt(i);
		}
		return result;
	}
}

public class MethodRefDemo2 {
	static String stringOp(StringFunc sf, String s) {
		return sf.func(s);
	}

	public static void main(String[] args) {
		String inStr = "Lambdas add power to Java";
		String outStr;
		
		// Create a MyStrinOps object.
		MyStringOps2 strOps = new MyStringOps2();
		
		// Now a method reference to an instance method is passed to stringOp().
		outStr = stringOp(strOps::strReverse, inStr);
		System.out.println("Original string: " + inStr);
		System.out.println("String reversed: " + outStr);
	}
}
