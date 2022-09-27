package LambdaBasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* This class contains the solution to the todos using
 * pre-JDK 8 features.
 */
public class ExerciseSolutionUsingJava7 {

	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
			new Person("Charles", "Dickens", 60),
			new Person("Lewis", "Carroll", 42),
			new Person("Thomas", "Carlyle", 51),
			new Person("Charlotte", "Bronte", 45),
			new Person("Mathew", "Arnold", 39)
		);
		
		// Step 1: Sort list by name.
		Collections.sort(list, new ComparePersonByFirstName());
		
		/* Step 2: Create a method that prints all the elements
		 * in the list.
		 */
		printPersons(list);
		
		/* Step 3: Create a method that prints all people that
		 * have last name beginning with C.
		 */
		printPersons(list, 'C');
	}
	
	public static void printPersons(List<Person> list) {
		for(Person person: list)
			System.out.println(person);
	}
	
	public static void printPersons(List<Person> list, char lastNameBegWith) {
		System.out.println("Printing persons whose last name starts with: '"+lastNameBegWith+"'.");
		for(Person person: list) {
			if(person.getLastName().charAt(0) == lastNameBegWith)
				System.out.println(person);
		}
	}
}
class ComparePersonByFirstName implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getFirstName()
				.compareTo(o2.getFirstName());
	}
	
}
