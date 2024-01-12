package stream_api_01;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class StreamDemo6 {

	public static void main(String[] args) {
		// A list of double values.
		ArrayList<Double> myList = new ArrayList<>();
		myList.add(1.1);
		myList.add(3.6);
		myList.add(9.2);
		myList.add(4.7);
		myList.add(12.1);
		myList.add(5.0);

		System.out.println("Original values in myList: ");
		myList.forEach(el -> System.out.println(el));
		System.out.println();
		
		// Map the ceiling of the elements in myList to an IntStream.
		IntStream cStrm = myList.stream().mapToInt(el -> (int)Math.ceil(el));
		System.out.println("The ceiling of the values in myList: ");
		cStrm.forEach(el -> {
			System.out.println(el);
		});
	}

}
