package lambda_expressions_03;

/* Demonstrates a method reference to a generic method declared inside a 
 * non-generic class.   */

/* A functional interface that operates on an array and a value, and returns
 * an int result. */
interface MyFunc<T> {
	int func(T[] vals, T v);
}

/* This class defines a method called countMatching() that returns the number of 
 * items in an array that are equal to a specified value. Notice that 
 * countMatching() is generic, but MyArrayOps is not.
 *  */
class MyArrayOps {
	
	static<T> int countMatching(T[] vals, T v ) {
		int count = 0;
		
		for(int i=0; i<vals.length; i++) {
			if(vals[i] == v) count++;
		}
		
		return count;
	}
	
}

public class GenericMethodRefDemo {
	
	/* This method has MyFunc functional interface as the type of its first 
	 * parameter. The other two receive an array and value, both of type T. */
	static<T> int myOp(MyFunc<T> f, T[] vals, T v) {
		return f.func(vals, v);
	}

	public static void main(String[] args) {
		Integer[] vals = {1, 2, 3, 4, 2, 3, 4, 4, 5};
		String[] strs = {"One", "Two", "Three", "Two"};
		int count;
		
		/* In the program, MyArrayOps is a non-generic class that contains a 
		 * generic method called countMatching( ). The method returns a count of 
		 * the elements in an array that match a specified value. Notice how the 
		 * generic type argument is specified. For example, its first call in 
		 * main( ), shown here: 
		 * count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
		 * passes the type argument Integer. Notice that it occurs after the ::. 
		 * This syntax can be generalized: When a generic method is specified as a 
		 * method reference, its type argument comes after the :: and before the 
		 * method name. It is important to point out, however, that explicitly 
		 * specifying the type argument is not required in this situation (and 
		 * many others) because the type argument would have been automatically 
		 * inferred. In cases in which a generic class is specified, the type 
		 * argument follows the class name and precedes the ::. */
		count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
		System.out.println("vals contains " + count + " 4s");
		
		count = myOp(MyArrayOps::countMatching, strs, "Two");
		System.out.println("strs contains " + count + " Twos");
	}

}
