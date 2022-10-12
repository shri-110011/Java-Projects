package LambdaBasics;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {

	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
			);
		System.out.println("Using for-loop");
		for(int i=0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("Using for-in loop");
		for(Person p: list) {
			System.out.println(p);
		}
		
		System.out.println("Using for-each loop");
		/* In java 8 onwards each collection object has a forEach
		 * method that takes a Consumer and performs the operation
		 * for each element in the collection.
		 * 
		 */
//		list.forEach(p-> System.out.println(p));
		list.forEach(System.out::println);

	}

}
