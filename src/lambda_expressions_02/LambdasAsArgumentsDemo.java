package lambda_expressions_02;

//Use a lambda expressions as argument to a method.

interface StringFunc {
	String func(String str);
}

public class LambdasAsArgumentsDemo {

	/*
	 * This method has a functional interface as the type of its first parameter.
	 * Thus, it can be passed a reference to any instance of that interface,
	 * including the instance created by a lambda expression. The second parameter
	 * specifies the string to operate on.
	 */
	static String stringOp(StringFunc sf, String s) {
		return sf.func(s);
	}

	public static void main(String[] args) {
		String inStr = "Lambdas add power to Java";
		String outStr;

		System.out.println("Here is input string: " + inStr);

		/*
		 * Here, a simple expression lambda uppercases a string that is passed to
		 * stringOp().
		 */
		outStr = stringOp(str -> str.toUpperCase(), inStr);
		System.out.println("The string in uppercase: " + outStr);
		
		// This passes a block lambda that removes white spaces.
		outStr = stringOp(str -> {
			String result = "";
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) != ' ') result += str.charAt(i);
			}
			return result;
		}, inStr);
		
		System.out.println("The string with white spaces removed: " + outStr);
		
		/* When a block lambda seems overly long to embed in a method call, it is 
		 * an easy matter to assign that lambda to a functional interface 
		 * variable. */
		
		/* Of course, it is also possible to pass a StringFunc instance created
		 * by an earlier lambda expression. For example, after this declaration
		 * executes, reverse refers to an instance of StringFunc. Then, you can 
		 * simply pass that reference to the method. */
		StringFunc reverse = (str) -> {
			String result = "";
			for(int i=str.length()-1; i>=0; i--) {
				result += str.charAt(i);
			}
			return result;
		};
		
		/* Now, reverse can be passed as the first parameter to stringOp()
		 * because it refers to StringFunc object. */
		System.out.println("The string reversed: " + stringOp(reverse, inStr));

	}

}
