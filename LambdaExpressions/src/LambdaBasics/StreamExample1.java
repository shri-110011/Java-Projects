package LambdaBasics;

import java.util.Arrays;
import java.util.List;

public class StreamExample1 {

	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
			);
		
		/* A stream is a sequence of elements that supports
		 * sequential and parallel aggregate operations.
		 * 
		 * Aggregate operations are those which process elements
		 * from the stream not directly from a collection.
		 * 
		 * Here forEach and filter are aggregate operations.
		 */
		list.stream()
		.filter(p-> p.getLastName().startsWith("C"))
		.forEach(p-> System.out.println(p.getFirstName()));
	}

}
