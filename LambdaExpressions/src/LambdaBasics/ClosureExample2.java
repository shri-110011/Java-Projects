package LambdaBasics;

public class ClosureExample2 {
	
	static int x;
	public static void main(String[] args) {
		
		int a=10;
		
		int b=25;
		
		/*
		 * A closure is a combination of a function bundled 
		 * together (enclosed) with references to the variable 
		 * in the enclosing scope.
		 * 
		 * A closure gives you access to an outer function’s 
		 * scope from an inner function.
		 * 
		 * We can also write x in place of b because it is 
		 * accessible from the enclosing scope.
		 */
		doProcess(a, i->System.out.println(i+b));
	}
	
	public static void doProcess(int i, Process p) {
		p.process(i);
	}

}
