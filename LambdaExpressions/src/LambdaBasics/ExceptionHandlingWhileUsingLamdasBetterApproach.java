package LambdaBasics;

import java.util.function.BiConsumer;

public class ExceptionHandlingWhileUsingLamdasBetterApproach {

	public static void main(String[] args) {
		int someNumbers[] = {1,2,3,4,5}, key = 0;
		
		/* Here we have externalized the try-catch block for the 
		 * lambda expression inside a method i.e. we have used
		 * a lambda wrapper to achieve exception handling.
		 * 
		 * In this approach the lambda expression still looks
		 * elegant and terse.
		 */
		process(someNumbers, key, wrapperLambda((u, v)-> System.out.println(u/v)));

	}

	private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		for(int i: someNumbers) {
			consumer.accept(i, key);
		}
	}
	
	private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (u, v)-> {
			try {
				consumer.accept(u, v);
			}
			catch(ArithmeticException e) {
				System.out.println("Arithmetic exception occurred!");
			}
		};
	}

}
