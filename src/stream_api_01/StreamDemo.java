package stream_api_01;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.*;

public class StreamDemo {

	public static void main(String[] args) {
		// Create a list of integer values.
		ArrayList<Integer> myList = new ArrayList<>();
		myList.add(7);
		myList.add(18);
		myList.add(10);
		myList.add(24);
		myList.add(27);
		myList.add(17);
		myList.add(5);
//		myList.add(null);
		
		System.out.println("Original list: " + myList);
		
		/* A stream represents a sequence of objects. A stream operates on a data source, such as an 
		 * array or a collection. A stream, itself, never provides storage for the data. It simply moves 
		 * data, possibly filtering, sorting, or otherwise operating on that data in the process. As a 
		 * general rule, however, a stream operation by itself does not modify the data source. 
		 * 
		 * A terminal operation consumes the stream. It is used to produce a result, such as finding the 
		 * minimum value in the stream, or to execute some action, as is the case with the forEach( ) 
		 * method. Once a stream has been consumed, it cannot be reused. Intermediate operations produce 
		 * another stream. Thus, intermediate operations can be used to create a pipeline that performs 
		 * a sequence of actions.
		 * One other point: intermediate operations do not take place immediately. Instead, the specified 
		 * action is performed when a terminal operation is executed on the new stream created by an 
		 * intermediate operation. This mechanism is referred to as lazy behavior, and the intermediate 
		 * operations are referred to as lazy.  
		 *  
		 *  */
		
		// Obtain a stream to the ArrayList
		Stream<Integer> myStream =  myList.stream();
		
		// Obtain the minimum and maximum value by use of min(), max(), isPresent(), get().
		/* We can also pass this lambda (i1, i2) -> i1-i2 that is compatible with the Comparator 
		 * interface instead of the static method reference.
		 * 
		 * myStream.min(Integer::compare); would throws NullPointerException if the ArrayList contain
		 * any null value.
		 * 
		 * Optional is a generic class packaged in java.util and declared like this:
		 * class Optional<T>
		 * Here, T specifies the element type. An Optional instance can either contain a value of type 
		 * T or be empty. You can use isPresent( ) to determine if a value is present. Assuming that a 
		 * value is available, it can be obtained by calling get( ), or if you are using JDK 10 or 
		 * later, orElseThrow( ). Here, get( ) is used. In this example, the object returned will hold 
		 * the minimum value of the stream as an Integer object.
		 *  
		 * min() on the Stream object is a terminal operation. 
		 */
		Optional<Integer> minVal = myStream.min(Integer::compare);
		if(minVal.isPresent()) {
			System.out.println("Minimum value: " + minVal.get());
		}
		
		/* Must obtain a new stream because previous call to min() is a terminal operation that consumed the 
		 * stream. */
		myStream =  myList.stream();
		Optional<Integer> maxVal = myStream.max(Integer::compare);
		if(maxVal.isPresent()) {
			System.out.println("Maximum value: " + maxVal.get());
		}
		
		// Sort the stream by use of sorted().
		Stream<Integer> sortedStream = myList.stream().sorted();
		sortedStream.forEach(n -> System.out.print(n + " "));
		System.out.println();
		
		// Display only the odd values by use of filter.
		Stream<Integer> oddVals = myList.stream().filter(n -> n%2 != 0);
		oddVals.forEach(n -> System.out.print(n + " "));
		System.out.println();
		
		/* Display only the odd values that are greater than 5. Notice that two filter operations 
		 * are pipelined.
		 */
		oddVals = myList.stream().filter(n -> n%2 != 0).filter(n -> n>5);
		System.out.println("Odd values greater than 5:");
		oddVals.forEach(n -> System.out.print(n + " "));
		System.out.println();
		
	}

}
