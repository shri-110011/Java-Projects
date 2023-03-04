
@FunctionalInterface
interface StringFunction {
	String run(String str);
	default void m1() {}
	default void m2() {}
}
public class LearnLambdaExpressions {
	
	/* The LambdaExpression is used to provide implementation to a functional interface.
	
	Lambda expression provides implementation of functional interface. An interface which 
	has only one abstract method is called functional interface. Java provides an 
	anotation @FunctionalInterface, which is used to declare an interface as functional 
	interface. 
	
	We use LambdaExpression because:
	1. To provide the implementation of Functional interface.
	2. Less coding. */

	public static void main(String[] args) {
		StringFunction exclaim = (s)->s+"!";
		StringFunction ask = (s)->s+"?";
		printFormatted("Hello", exclaim);
		printFormatted("Hello", ask);
	}
	public static void printFormatted(String str, StringFunction format) {
		String result = format.run(str);
		System.out.println(result);
	}

}
