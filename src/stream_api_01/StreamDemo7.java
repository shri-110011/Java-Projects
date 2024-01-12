package stream_api_01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo7 {

	public static void main(String[] args) {
		// A list of names, phone numbers and e-mail addresses.
		ArrayList<NamePhoneEmail> myList = new ArrayList<>();
		myList.add(new NamePhoneEmail("Larry", "555-5555", "Larry@HerbSchildt.com"));
		myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchildt.com"));
		myList.add(new NamePhoneEmail("Mary", "555-3333", "Mary@HerbSchildt.com"));

		System.out.println("Original values in myList: ");
		myList.forEach(el -> {
			System.out.println(el.name + " " + el.phonenum + " " + el.email);
		});
		System.out.println();

		// Map just the names and phone numbers to a new stream.
		Stream<NamePhone> nameAndPhone = myList.stream().map(el -> new NamePhone(el.name, el.phonenum));

		List<NamePhone> npList = nameAndPhone.collect(Collectors.toList());
		System.out.println("Names and phone numbers in a list: ");

		for (NamePhone el : npList) {
			System.out.println(el.name + " " + el.phonenum);
		}

		System.out.println();

		// Obtain another mapping of the names and phone numbers.
		nameAndPhone = myList.stream().map(el -> new NamePhone(el.name, el.phonenum));

		/*
		 * Sometimes it is desirable to obtain a collection from a stream. To perform
		 * such an action, the stream API provides the collect( ) method. It has two
		 * forms. The one we will use first is shown here: <R, A> R collect(Collector<?
		 * super T, A, R> collectorFunc)
		 * 
		 * Here, R specifies the type of the result, and T specifies the element type of
		 * the invoking stream. The internal accumulated type is specified by A. The
		 * collectorFunc specifies how the collection process works. The collect( )
		 * method is a terminal operation.
		 * 
		 * The Collector interface is declared in java.util.stream. Collector specifies
		 * several methods, but we won�t need to implement them. Instead, we will use
		 * two of the predefined collectors that are provided by the Collectors class,
		 * which is packaged in java.util.stream.
		 */
		// Now create a set by use of collect().
		Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());

		System.out.println("Names and phone numbers in a set: ");

		for (NamePhone el : npSet) {
			System.out.println(el.name + " " + el.phonenum);
		}
		
		System.out.println();

		/*
		 * There is a second version of collect() that gives you more control over the
		 * collection process. It is shown here: <R> R collect(Supplier<R> targer,
		 * BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
		 * 
		 * Here, target specifies how the object that holds the result is created. For
		 * example, to use a LinkedList as the result collection, you would specify its
		 * constructor. The accumulator function adds an element to the result and
		 * combiner combines two partial results. Thus, these functions work similarly
		 * to the way they do in reduce( ). For both, they must be stateless and
		 * noninterfering. They must also be associative.
		 * 
		 * Note that the target parameter is of type Supplier. It is a functional
		 * interface declared in java.util.function. It specifies only the get( )
		 * method, which has no parameters and, in this case, returns an object of type
		 * R. Thus, as it relates to collect( ), get( ) returns a reference to a mutable
		 * storage object, such as a collection.
		 * 
		 * Note also that the types of accumulator and combiner are BiConsumer. This is
		 * a functional interface defined in java.util.function. It specifies the
		 * abstract method accept( ) that is shown here: void accept(T obj, U obj2)
		 * 
		 * This method performs some type of operation on obj and obj2. As it relates to
		 * accumulator, obj specifies the target collection, and obj2 specifies the
		 * element to add to that collection. As it relates to combiner, obj and obj2
		 * specify two collections that will be combined.
		 * 
		 */
		// Obtain another mapping of the names and phone numbers.
		nameAndPhone = myList.stream().map(el -> new NamePhone(el.name, el.phonenum));

		/*
		 * Notice that the first argument to collect( ) is a lambda expression that
		 * returns a new LinkedList. The second argument uses the standard collection
		 * method add( ) to add an element to the list. The third element uses addAll( )
		 * to combine two linked lists. As a point of interest, you can use any method
		 * defined by LinkedList to add an element to the list. For example, you could
		 * use addFirst( ) to add elements to the start of the list, as shown here:
		 * (list, element) -> list.addFirst(element)
		 */
		LinkedList<NamePhone> npLinkedList = nameAndPhone.collect(() -> new LinkedList<NamePhone>(),
				(list, element) -> list.add(element), (list1, list2) -> list1.addAll(list2));

		System.out.println("Names and phone numbers in a linked list: ");

		for (NamePhone el : npLinkedList) {
			System.out.println(el.name + " " + el.phonenum);
		}
		
		System.out.println();
		
		/* As you may have guessed, it is not always necessary to specify a lambda expression for the arguments to 
		 * collect( ). Often, method and/or constructor references will suffice. For example, again assuming the 
		 * preceding program, this statement creates a HashSet that contains all of the elements in the nameAndPhone 
		 * stream: 
		 * Notice that the first argument specifies the HashSet constructor reference. The second and third specify 
		 * method references to HashSet�s add( ) and addAll( ) methods. 
		 * 
		 * One last point: In the language of the stream API, the collect( ) method performs what is called a mutable 
		 * reduction. This is because the result of the reduction is a mutable (i.e., changeable) storage object, such 
		 * as a collection.
		 * 
		 *  */
		nameAndPhone = myList.stream().map(el -> new NamePhone(el.name, el.phonenum));
		HashSet<NamePhone> npSet2 = nameAndPhone.collect(HashSet::new, HashSet::add, HashSet::addAll);
		System.out.println("Names and phone numbers in a hash set: ");

		for (NamePhone el : npSet) {
			System.out.println(el.name + " " + el.phonenum);
		}
	}

}
