package stream_api_01;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class StreamDemo3 {

	public static void main(String[] args) {
		// Create a list of integer values.
		ArrayList<Double> myList = new ArrayList<>();
		myList.add(1.0);
		myList.add(2.0);
		myList.add(3.0);
//		myList.add(4.0);
//		myList.add(5.0);
//		myList.add(6.0);
		
		/* When using parallel streams, you might find the following version of reduce( ) especially 
		 * helpful. It gives you a way to specify how partial results are combined.
		 * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
		 * In this version, combiner defines the function that combines two values that have been 
		 * produced by the accumulator function. */

		double productOfSquareRoots = 
				myList.parallelStream()
				.reduce(1.0, 
						(a, b) -> a * Math.sqrt(b),
						(a, b) -> a * b);
		
		System.out.println("Product of the square roots: " + productOfSquareRoots);
		
		/* In this version of reduce( ), the accumulator and the combiner function are one and the same. This results in 
		 * an error because when two partial results are combined, their square roots are multiplied together rather 
		 * than the partial results, themselves. */
		// This won't work.
		double productOfSquareRoots2 = 
				myList.parallelStream()
				.reduce(1.0, 
						(a, b) -> {
							System.out.println("Inside accumulator function: a: " + a + " b: " + b);
							return a * Math.sqrt(b);
						},
						(a, b) -> {
							System.out.println("Inside combiner function: a: " + a + " b: " + b);
							return a * b;
						});
		System.out.println("Product of the square roots: " + productOfSquareRoots2);
		
		double productOfSquareRoots3 = 
				myList.stream()
				.reduce(1.0, 
						(a, b) -> a * Math.sqrt(b));
		
		/* As a point of interest, if the stream in the preceding call to reduce( ) had 
		 * been changed to a sequential stream, then the operation would yield the correct answer because there would 
		 * have been no need to combine two partial results. The problem occurs when a parallel stream is used. */
		System.out.println("Product of the square roots: " + productOfSquareRoots3);
		
		/* In general, a stream can be switched between parallel and sequential on an as needed basis. There is one 
		 * other aspect of a stream to keep in mind when using parallel execution: the order of the elements. Streams 
		 * can be either ordered or unordered. In general, if the data source is ordered, then the stream will also 
		 * be ordered. However, when using a parallel stream, a performance boost can sometimes be obtained by allowing 
		 * a stream to be unordered. When a parallel stream is unordered, each partition of the stream can be operated 
		 * on independently, without having to coordinate with the others. In cases in which the order of the operations 
		 * does not matter, it is possible to specify unordered behavior by calling the unordered( ) method, shown here: 
		 * S unordered( ) 
		 * One other point: the forEach( ) method may not preserve the ordering of a parallel stream. If you want to 
		 * perform an operation on each element in a parallel stream while preserving the order, consider using 
		 * forEachOrdered( ). It is used just like forEach( ).
*/
		
		// forEach() here does not guarantee to preserve the order of the data source when parallel streams are used.
		Stream.of("AAA, BBB, CCC, DDD, EEE, FFF, GGG, HHH, III, JJJ, KKK, LLL").parallel().forEach(val -> {
			System.out.println(val);
		});
		
		/* forEachOrdered() here will guarantee to preserve the order of the data source even when parallel streams are 
		used. */
		Stream.of("AAA, BBB, CCC, DDD, EEE, FFF, GGG, HHH, III, JJJ, KKK, LLL").parallel().forEachOrdered(val -> {
			System.out.println(val);
		});

	}

}
