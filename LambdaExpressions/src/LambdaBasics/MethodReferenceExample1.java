package LambdaBasics;

public class MethodReferenceExample1 {
	
	public static void main(String[] args) {
//		Thread t = new Thread(()-> printMessage());
		
		/* Method reference is an alternative syntax to the lambda 
		 * expression.
		 * 
		 * When we have a lambda having the structure:
		 * () -> method() or (p1, p2, ...) -> method(p1, p2, ...)
		 * We can use method reference.
		 * p1, p2, .. are the parameters in the lambda expression.
		 * 
		 * For lambdas having the structure:
		 * () -> method()
		 * we could write:
		 * className::methodName in case the method is static
		 * or
		 * instance::methodName in case the method is an instance
		 * method.
		 */
		MethodReferenceExample1 obj1 = new MethodReferenceExample1();
		Thread t = new Thread(obj1::printMessage);
		t.start();
	}
	
	public void printMessage() {
		System.out.println("Hello");
	}

}
