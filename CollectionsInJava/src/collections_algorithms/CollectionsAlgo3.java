package collections_algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsAlgo3 {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		
		// Add elements to the array list.
		al.add("C");
		al.add("A29");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
//		al.add(1); // Cause compiler error.
		
//		Note: Returns a dynamically type safe view of the specified collection.
//		Any attempt to insert an element of the wrong type will result in 
//		an immediate ClassCastException.
		
		// We need checked collection/type safe view of a specific collection
		// for situations like: 
		// When we are passing a collection which is of raw type to a 
		// third-party library. In order to prevent the third-party library 
		// from putting incompatible elements into the collection we require
		// some run-time check of the type of elements being inserted into 
		// the collection. This is what checked collection help us to achieve.
		
		Collection<String> collec1 = Collections.checkedCollection(al, String.class);
		System.out.println("collec1: "+collec1);
		System.out.println("---------------------------");
		
		List l1 = new ArrayList();
		
		l1.add("A");
		l1.add("D");
		l1.add(1);
		System.out.println("l1: "+l1);
		
		System.out.println("---------------------------");
		
		List l2 = Collections.checkedList(l1, String.class);
		l2.add(2);// Cause ClassCastException.
		
	}

}
