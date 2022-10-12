package LambdaBasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* This class contains the solution to the todos using
 * JDK 8 feature lambda expressions.
 */
public class ExerciseSolutionUsingJava8 {

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
//		Collections.sort(list, personComparator);
		Collections.sort(list, (p1, p2)-> p1.getFirstName().compareTo(p2.getFirstName()));
		
		/* Step 2: Create a method that prints all the elements
		 * in the list.
		 */
		printPersons(list);
		
		/* Step 3: Create a method that prints all people that
		 * have last name beginning with C.
		 */
		printPersons(list, person->person.getLastName().startsWith("C"));
		printPersons(list, person->person.getFirstName().startsWith("C"));
	}
	
	public static void printPersons(List<Person> list) {
		for(Person person: list)
			System.out.println(person);
	}
	
	public static void printPersons(List<Person> list, Condition condition) {
		System.out.println("Printing persons conditionally ...");
		
		for(Person person: list) {
			if(condition.test(person))
				System.out.println(person);
		}
	}
	
	interface Condition {
		boolean test(Person person);
	}

}
