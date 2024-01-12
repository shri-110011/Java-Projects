package stream_api_01;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo2 {

	public static void main(String[] args) {
		// Create a list of integer values.
		ArrayList<Integer> myList = new ArrayList<>();
		myList.add(1);
		myList.add(2);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		myList.add(6);
		
		/* Here we see the reduction operations. A reduction operation is a terminal operation that 
		 * reduces the stream and produces a single result. Eg: of reduction operations are max(), min()
		 * , count etc. The stream API refers to these reduction operations as special case reductions 
		 * because they perform a specific function.  However, the stream API generalizes this concept by
		 * providing the reduce( ) method. By using reduce( ), you can return a value from a stream 
		 * based on any arbitrary criteria. 
		 * 
		 * Stream defines three versions of reduce( ). The two we will use first are shown here:
		 * Optional<T> reduce(BinaryOperator<T> accumulator)
		 * T reduce(T identityVal, BinaryOperator<T> accumulator)
		 * 
		 * In both forms, accumulator is a function that operates on two values and produces a result. 
		 * In the second form, identityVal is a value such that an accumulator operation involving 
		 * identityVal and any element of the stream yields that element, unchanged. For example, if the 
		 * operation is addition, then the identity value will be 0 because 0 + x is x. For 
		 * multiplication, the value will be 1, because 1 * x is x.
		 * 
		 * BinaryOperator is a functional interface declared in java.util.function that extends the 
		 * BiFunction functional interface. BiFunction defines this abstract method:
		 * R apply(T val, U val2)
		 * Here, R specifies the result type, T is the type of the first operand, and U is the
		 * type of second operand. Thus, apply( ) applies a function to its two operands (val and val2) 
		 * and returns the result. When BinaryOperator extends BiFunction, it specifies the same type for 
		 * all the type parameters. Thus, as it relates to BinaryOperator, apply( ) looks like this:
		 * T apply(T val, T val2)
		 * 
		 * Furthermore, as it relates to reduce( ), val will contain the previous result and val2 will 
		 * contain the next element. In its first invocation, val will contain either the identity value 
		 * or the first element, depending on which version of reduce( ) is used. 
		 * It is important to understand that the accumulator operation must satisfy three constraints. 
		 * It must be
		 * • Stateless
		 * • Non-interfering
		 * • Associative

		 * 
		 * */
		
		// Two ways to obtain the integer products of the elements in myList by use of reduce().
		Optional<Integer> prodObj = myList.stream().reduce((val1, val2) ->  val1*val2);
		if(prodObj.isPresent())
			System.out.println("Product as Optional: " + prodObj.get());
		
		int product = myList.stream().reduce(1, (val1, val2) ->  val1*val2);
		System.out.println("Product as int: " + product);
		
		
		/* The parallel execution of code via multicore processors can result in a substantial increase 
		 * in performance. One of the benefits that the stream library offers is the ability to 
		 * easily—and reliably—parallel process certain operations.
		 * Parallel processing of a stream is quite simple to request: just use a parallel stream. As 
		 * mentioned earlier, one way to obtain a parallel stream is to use the parallelStream( ) 
		 * method defined by Collection. Another way to obtain a parallel stream is to call the
		 *  parallel( ) method on a sequential stream. The parallel( ) method is defined by BaseStream, 
		 *  as shown here: 
		 *  S parallel()
		 *  It returns a parallel stream based on the sequential stream that invokes it. (If it is 
		 *  called on a stream that is already parallel, then the invoking stream is returned.) 
		 *  Understand, of course, that even with a parallel stream, parallelism will be achieved only 
		 *  if the environment supports it.
		 *  
		 *  Below we write the code to find the product of integers in the list but by using parallel 
		 *  stream.
		 *  
		 *  As a general rule, any operation applied to a parallel stream must be stateless. It should 
		 *  also be non-interfering and associative. This ensures that the results obtained by executing 
		 *  operations on a parallel stream are the same as those obtained from executing the same 
		 *  operations on a sequential stream.
		 */
		
		int product2 = myList.parallelStream().reduce(1, (a,b) -> a*b);
		System.out.println("Product obtained from parallel stream as int: " + product2);
		
		Stream<Integer> sequentialStream = myList.stream();
		System.out.println(sequentialStream.isParallel());
		
		Stream<Integer> parallelStream1 = sequentialStream.parallel(), parallelStream2 = myList.parallelStream();
		
		System.out.println(sequentialStream.isParallel());
		System.out.println(parallelStream1.isParallel());
		System.out.println(parallelStream2.isParallel());
		
		System.out.println(parallelStream1 == parallelStream1.parallel());
		System.out.println(parallelStream1 == parallelStream2);

	}

}
