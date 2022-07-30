
// Demonstrate HashSet and LinkedHashSet.
package collections;

import java.util.LinkedHashSet;

public class HashSetDemo {

	public static void main(String[] args) {
		// Create a hash set.
		LinkedHashSet<String> hs = new LinkedHashSet<String>();
		
		// Add elements to the linked list.
		hs.add("B");
		hs.add("A");
		hs.add("D");
		hs.add("E");
		hs.add("C");
		hs.add("F");
		hs.add(null);
		
		for(int i=0; i<20; i++)
			System.out.println(hs);
		
		System.out.println(hs);

	}

}
