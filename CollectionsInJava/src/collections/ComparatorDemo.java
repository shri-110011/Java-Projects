package collections;

import java.util.Comparator;
import java.util.TreeSet;

class MyComp implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o2.compareTo(o1);
	}
	
}

public class ComparatorDemo {

	public static void main(String[] args) {
		
		// Create a TreeSet.
		TreeSet<String> ts = new TreeSet<>(new MyComp());
		
		
		// Add elements to the TreeSet.
		ts.add("C");
		ts.add("A");
		ts.add("B");
		ts.add("E");
		ts.add("F");
		ts.add("D");
		
		//Display the elements.
		for(String el : ts)
			System.out.print(el+" ");
		System.out.println();

	}

}
