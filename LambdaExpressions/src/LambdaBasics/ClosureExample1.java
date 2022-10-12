package LambdaBasics;

public class ClosureExample1 {

	public static void main(String[] args) {
		
		int a=10;
		
		/* Prior to Java 8 the final keyword was necessary for 
		 * local variable in enclosing scope that is being used 
		 * in the anonymous inner class but not in Java 8 
		 * onwards. 
		 * 
		 */
		int b=20;
		
		doProcess(a, new Process() {

			@Override
			public void process(int i) {
//				b=20; // This will give compiler error because b is effectively final.
				System.out.println(i+b);
			}
			
		});
	}
	
	public static void doProcess(int i, Process p) {
		p.process(i);
	}

}

interface Process {
	void process(int i);
}
