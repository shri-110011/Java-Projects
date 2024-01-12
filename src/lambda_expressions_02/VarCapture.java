package lambda_expressions_02;

interface MyFunc {
	int myFunc(int n);
}

/*	Variables defined by the enclosing scope of a lambda expression are accessible
	within the lambda expression. For example, a lambda expression can use an
	instance or static variable defined by its enclosing class. A lambda expression
	also has access to this (both explicitly and implicitly), which refers to the
	invoking instance of the lambda expression’s enclosing class. Thus, a lambda
	expression can obtain or set the value of an instance or static variable and 
	call a method defined by its enclosing class.
	However, when a lambda expression uses a local variable from its enclosing
	scope, a special situation is created that is referred to as a variable 
	capture. In this case, a lambda expression may only use local variables that 
	are effectively final. An effectively final variable is one whose value does 
	not change after it is first assigned.
*/
public class VarCapture {

	public static void main(String[] args) {
		// A local variable that can be captured.
		int num = 10;
		
		MyFunc myLambda = (n) -> {
			// This use of num is OK. It does not modify num.
			int v = num + n;
			
			/* The following is illegal because it attempts to modify the value
			 * of num. */
//			 num++;
			
			return v;
		};
		
		/* The following line would cause an error, because it would remove the 
		 * effectively final status from num. */
//		num = 9;
	}

}
