package lambda_expressions_03;

// Demonstrate a method reference for a static method.

// A functional interface for string operations.
interface StringFunc {
	String func(String n);
}

// This class defines a static method strReverse(). 
class MyStringOps {
	// A static method that reverses a string
	static String strReverse(String str) {
		String result = "";
		for(int i=str.length()-1; i>=0; i--) {
			result += str.charAt(i);
		}
		return result;
	}
}

/* There is an important feature related to lambda expressions called the 
 * method reference. A method reference provides a way to refer to a method 
 * without executing it. It relates to lambda expressions because it, too, 
 * requires a target type context that consists of a compatible functional 
 * interface. When evaluated, a method reference also creates an instance 
 * of the functional interface.
 * */
public class MethodRefDemo {
	
	/* This method has a functional interface as the type of its first parameter.
	 * Thus, it can be passed any instance of that interface, including a
	 * method reference.
	 * */
	static String stringOp(StringFunc sf, String s) {
		return sf.func(s);
	}

	public static void main(String[] args) {
		String inStr = "Lambdas add power to Java";
		String outStr;
		/* To create a static method reference, use this general syntax:
		ClassName::methodName
		Notice that the class name is separated from the method name by a double
		colon. The :: is a separator that was added to Java by JDK 8 expressly for 
		this purpose. This method reference can be used anywhere in which it is 
		compatible with its target type.
		
		Here, a reference to the static method strReverse( ), declared inside
		MyStringOps, is passed as the first argument to stringOp( ). This works
		because strReverse is compatible with the StringFunc functional interface.
		Thus, the expression MyStringOps::strReverse evaluates to a reference to an
		object in which strReverse provides the implementation of func( ) in
		StringFunc.

		*/
		
		outStr = stringOp(MyStringOps::strReverse, inStr);
		System.out.println("Original string: " + inStr);
		System.out.println("String reversed: " + outStr);
	}

}
