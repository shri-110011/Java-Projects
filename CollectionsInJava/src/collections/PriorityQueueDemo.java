package collections;

import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		
		System.out.println(pq.size());
		
		// Add elements to the PriorityQueue list.
		pq.add("B");
		System.out.println(pq);
		pq.add("N");
		System.out.println(pq);
		pq.add("O");
		System.out.println(pq);
		pq.add("H");
		System.out.println(pq);
		pq.add("C");
		System.out.println(pq);
		pq.add("Z");
		
		// The elements of the priority queue are ordered according to their 
		// natural ordering, or by a Comparator provided at queue construction 
		// time, depending on which constructor is used.
		
		// The head of this queue is the least element with respect to the 
		// specified ordering.
		System.out.println(pq);

		System.out.println(pq.poll());

	}

}
