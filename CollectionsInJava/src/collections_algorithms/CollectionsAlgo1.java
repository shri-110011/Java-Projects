// This program shows the Collections.addAll() and Collections.asLifoQueue()
// methods.

package collections_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class CollectionsAlgo1 {

	public static void main(String[] args) {
		
		List<String> list1 = new ArrayList<>();
		Set<String> set = new HashSet<>();
		
		set.add("Red");
		set.add("Green");
		set.add("Blue");
		set.add(null);
		
		System.out.println("Set: "+set);
		list1.addAll(set);
		System.out.println("List: "+list1);
		
		List<String> list2 = new ArrayList<>();
		String s[] = {"A", "H", "B", "D"};
		Collections.addAll(list2, s);
		System.out.println("List: "+list2);
		
		System.out.println("-----------------------------");
		
		Queue<String> queue1 = new PriorityQueue<>();
		
		queue1.add("G");
		queue1.add("L");
		queue1.add("C");
		queue1.add("D");
		queue1.add("B");
//		queue.add(null); // Null Value are not allowed in Queue and Dequeue.
		
		// Note: While printing the queue the insertion order nor the natural
		// is maintained.
		// Because the Iterator provided in method iterator() is not guaranteed 
		// to traverse the elements of the priority queue in any particular 
		// order.
		System.out.println("Queue: "+queue1);
		
		Iterator<String> itr = queue1.iterator();
		System.out.println("Printing the queue elements using iterator:");
		while(itr.hasNext()) {
			System.out.print(itr.next()+" ");
		}
		System.out.println();
		
		// We can't use enhanced for-loops to modify the collection as well 
		// as to cycle through it. It will cause: 
		// java.util.ConcurrentModificationException
//		for(String str: queue) {
//			System.out.println(queue1.remove(str));
//		}
		
		System.out.println("Removing the queue elements using iterator:");
		itr = queue1.iterator();
		while(itr.hasNext()) {
			System.out.print(queue1.poll()+" ");
		}
		System.out.println(queue1.poll()+" ");
		
		System.out.println("-----------------------------");
		
		Deque<String> dq = new ArrayDeque<>();
		
		// Add elements to the ArrayDeque.
		dq.add("B");
		dq.add("N");
		dq.add("H");
		dq.add("O");
		dq.add("C");
		dq.add("Z");
		
		System.out.println("dq: "+dq);
		Queue<String> queue2 = Collections.asLifoQueue(dq);
		System.out.println("queue2: "+queue2);
		queue2.add("A");
		System.out.println("queue2: "+queue2);
		System.out.println(queue2.remove());
		System.out.println("queue2: "+queue2);
		System.out.println(dq.remove());
		System.out.println("dq: "+dq);
	}

}
