package collections;

import java.util.ArrayDeque;

public class ArrayDequeueDemo {

	public static void main(String[] args) {
		ArrayDeque<String> adq = new ArrayDeque<>();
		
		// Add elements to the ArrayDeque.
		adq.add("B");
		adq.add("N");
		adq.add("H");
		adq.add("O");
		adq.add("C");
		adq.add("Z");
		
		System.out.println(adq);
		
		System.out.println("Popping the stack: ");
		
		while(adq.peek() != null) {
			System.out.print(adq.pop());
		}
		System.out.println(adq);

	}

}
