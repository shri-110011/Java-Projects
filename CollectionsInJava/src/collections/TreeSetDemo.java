package collections;

import java.util.TreeSet;

public class TreeSetDemo {

	public static void main(String[] args) {
		// Create a tree set.
		TreeSet<String> ts = new TreeSet<>();
		
		// Add elements to the tree list.
		ts.add("B");
		ts.add("N");
		ts.add("H");
		ts.add("O");
		ts.add("C");
		ts.add("Z");
		
		for(int i=0; i<20; i++)
			System.out.println(ts);
		
		System.out.println("-----------------");
		
		System.out.println(ts.subSet("A", "i"));
		
		System.out.println(ts.floor("L"));
		
		System.out.println(ts.ceiling("N"));
		
		System.out.println(ts.lower("N"));
		
		System.out.println(ts.higher("H"));
		
		System.out.println(ts.headSet("M"));
		
		System.out.println(ts.tailSet("M"));

	}

}
