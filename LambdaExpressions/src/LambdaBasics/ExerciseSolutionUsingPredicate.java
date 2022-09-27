package LambdaBasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ExerciseSolutionUsingPredicate {
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
		/* By using the lambda expression: 'person->true' we 
		 * are printing all the persons in the list.
		 */
		printPersons(list, person->true);
		
		/* Step 3: Create a method that prints all people that
		 * have last name beginning with C.
		 */
		printPersons(list, person->person.getLastName().startsWith("C"));
		printPersons(list, person->person.getFirstName().startsWith("C"));
	}
	
	/* Predicate is a functional interface that has an abstract
	 * method: boolean test(T t); provided by java.
	 * 
	 * To know more about functional interfaces provided by java
	 * check Oracle docs.
	 * 
	 */
	public static void printPersons(List<Person> list, Predicate<Person> predicate) {
		System.out.println("Printing persons conditionally ...");
		
		for(Person person: list) {
			if(predicate.test(person))
				System.out.println(person);
		}
	}
}
