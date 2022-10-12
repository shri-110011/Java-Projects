package LambdaBasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {
	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
			);
			
		Comparator<Person> personComparator = 
				(person1, person2) -> 
				person1.getFirstName()
				.compareTo(person2.getFirstName());
				
		// Step 1: Sort list by name.
		Collections.sort(list, personComparator);

		/* Step 2: Create a method that prints all the elements
		 * in the list.
		 */
		printPersons(list, person->true, System.out::println);
		
		
	}
	
	/* Here we use a Consumer functional interface to pass the 
	 * operation that is to be performed when the predicate
	 * evaluates to true.
	 * 
	 * Note: The consumer has the abstract method:
	 * void accept(T t); 
	 * 
	 */
	public static void printPersons(List<Person> list, Predicate<Person> predicate, Consumer<Person> consumer) {
		System.out.println("Printing persons conditionally ...");
		
		for(Person person: list) {
			if(predicate.test(person))
				consumer.accept(person);
		}
	}
}
