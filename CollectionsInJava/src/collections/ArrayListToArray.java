package collections;

import java.util.ArrayList;

public class ArrayListToArray {

	public static void main(String[] args) {
		// Create an ArrayList
		ArrayList<Integer> al = new ArrayList<>(20);
		
		// Add elements to the array list.
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		
		System.out.println("Size of al after additions: "+al.size());
		
		// Get the array.
		Integer ia[] = new Integer[4];
		ia = al.toArray(ia);
		
		System.out.println("ia.length: "+ia.length);
		
		int sum = 0;
		
		// Sum the array.
		for(int i: ia) {
			System.out.println(i);
			sum += i;
		}
		
		System.out.println("Sum is: "+sum);

	}

}
