import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {

	public static void main(String[] args) {
		
		//summaryStatistics() works for only IntStream
		IntSummaryStatistics summary  = IntStream.of(1, 2, 3, 4)
										.summaryStatistics();
		System.out.println("Example 1");
		System.out.println(summary);
		
		//------------------------------------------------------------
		
		Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
		
		//<Class name>::<method name>
		//:: is the method reference operator
		//Method reference or double colon operator can be used to refer:
		//a static method,
		//an instance method, or
		//a constructor.
		
//		System.out.println("Example 2");
//		intStream
//		.map(x-> x*x)
//		.forEach(System.out::println);
		
		//Note: Whenever a terminal operation is called on a Stream object, the instance gets consumed and 
		//closed.

		//Therefore, we're only allowed to perform a single operation that consumes a Stream, otherwise, 
		//we'll get an exception(llegalStateException) that states that the Stream has already been operated 
		//upon or closed.
		
		List<Integer> list1 = new ArrayList<>();
		list1 = intStream
						.map(x-> x*x)
						.collect(Collectors.toList());
		
		System.out.println("Example 3");
		for (Integer integer : list1) {
			System.out.println(integer);
		}
		
		List<Integer> list2 = new ArrayList<>();
		int[] intArr = new int[] {1,2,3};
		
		list2 = Arrays.stream(intArr)
		.map(x-> x*x)
		.boxed()
		.collect(Collectors.toList());
		
		System.out.println("Example 4");
		for (Integer integer : list2) {
			System.out.println(integer);
		}
		
		List<Integer> list3 = new ArrayList<>();
		
		list3 = IntStream.of(intArr)
				.map(x-> x*x)
				.mapToObj(Integer::valueOf)
				.collect(Collectors.toList());
		
//		list3 = IntStream.of(1,2,3,4)
//				.map(x-> x*x)
//				.boxed()
//				.collect(Collectors.toList());
							
		System.out.println("Example 5");
		for (Integer integer : list3) {
			System.out.println(integer);
		}
		
		//------------------------------------------------------------
		
		Stream<String> strStream = Stream.of("Apple", "Banana", "Mango");
		Map<String, Integer> data = new HashMap<>();
		
//		System.out.println("Example 6");
//		strStream
//		.map(x-> x.length())
//		.forEach(System.out::println);
		
		
		data = 
			strStream.
			collect(
			Collectors.toMap(
				x-> x, 
				(String x)-> {
					return x.length();
				}
			)
		);
		
		System.out.println("Example 7");
		for (Map.Entry<String, Integer> e : data.entrySet()) {
			System.out.println(e.getKey()+":"+e.getValue());
		}
	}
	
	

}
