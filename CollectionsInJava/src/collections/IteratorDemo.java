package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorDemo {

	public static void main(String[] args) {
		// Create an ArrayList
		ArrayList<String> al = new ArrayList<>();
		
		System.out.println("Initial size of al: "+al.size());
		
		// Add elements to the array list.
		al.add("C");
		al.add("A");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
		
		Iterator<String> itr = al.iterator();
		
		while(itr.hasNext()) {
			String element = itr.next();
			System.out.print(element+" ");
		}
		System.out.println();
		
		//Modify objects being iterated.
		ListIterator<String> litr = al.listIterator();
		while(litr.hasNext()) {
			String element = litr.next();
			litr.set(element + "+");
		}
		
		System.out.println("Modified contents of al: ");
		itr = al.iterator();
		
		while(itr.hasNext()) {
			String element = itr.next();
			System.out.print(element+" ");
		}
		System.out.println();
		
		while(litr.hasPrevious()) {
			String element = litr.previous();
			System.out.print(element+" ");
		}
		System.out.println();
		
	}

}
