package LambdaBasics;

import java.util.function.BiConsumer;

public class ExceptionHandlingWhileUsingLamdas {

	public static void main(String[] args) {
		int someNumbers[] = {1,2,3,4,5}, key = 0;
		
		/* Note we should not use try-catch inside the 
		 * process method where the call to accept method is 
		 * occurring because the exception to be caught depends
		 * on the operation being done inside the accept method.
		 * 
		 * And we can't have a long list of catch blocks
		 * to cover all the possibilities in process method.
		 * 
		 * Also this shown approach of handling lambda takes
		 * away the quality of lambda being elegant and terse.
		 * 
		 */
		process(someNumbers, key, (u, v)->{
			try {
				System.out.println(u/v);
			}
			catch(ArithmeticException e) {
				System.out.println("Arithmetic exception occurred!");
			}
		});

	}

	private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		for(int i: someNumbers) {
			consumer.accept(i, key);
		}
	}

}
