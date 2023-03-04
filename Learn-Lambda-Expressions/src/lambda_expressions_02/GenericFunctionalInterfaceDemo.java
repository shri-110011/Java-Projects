package lambda_expressions_02;

// Use a generic functional interface with lambda expressions.

/* Instead of having two functional interfaces whose methods differ only in their 
 * data types, it is possible to declare one generic interface that can be used 
 * to handle both circumstances. */

// A generic functional interface.
interface SomeFunc<T> {
	T func(T t);
}

public class GenericFunctionalInterfaceDemo {

	public static void main(String[] args) {
		// Use a String based version of SomeFunc.
		SomeFunc<String> reverse = (str) -> {
			String result = "";
			for(int i=str.length()-1; i>=0; i--) {
				result += str.charAt(i);
			}
			return result;
		};
		
		System.out.println("Lambda reversed is: " + reverse.func("Lambda"));
		System.out.println("Expression reversed is: " + reverse.func("Expression"));
		
		// Now use an Integer based version of SomeFunc.
		SomeFunc<Integer> factorial = (n) -> {
			int result = 1;
			for(int i = 2; i<=n; i++) {
				result *= i;
			}
			return result;
		};
		System.out.println("The factorial of 8 is: " + factorial.func(8));
		System.out.println("The factorial of 8 is: " + factorial.func(9));

	}

}
