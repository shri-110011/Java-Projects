import java.util.LinkedList;
import java.util.Queue;
public class LearnQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Queue is an interface and needs a concrete class for the declaration and the most 
		//common classes are the PriorityQueue and LinkedList in Java. It is to be noted that 
		//both the implementations are not thread safe. PriorityBlockingQueue is one 
		//alternative implementation if thread safe implementation is needed.
		
		//Here the following methods have been used:
		//remove()
		//poll()
		//isEmpty()
		//add()
		//size()
		//peek()
		Queue<Integer> queue = new LinkedList<>();
		System.out.println("Empty: "+queue.isEmpty());
		//Note: remove() throws an exception if queue is empty but poll doesn't instead it 
		//returns null, otherwise remove() and poll() returns the head of the queue i.e. the 
		//first element in the queue.
		//System.out.println("remove() result: "+queue.remove());// NoSuchElementException is thrown.
		System.out.println("poll() result: "+queue.poll());
		
		queue.add(11);
		queue.add(12);
		queue.add(13);
		
		System.out.println(queue);
		
		int removedElement = queue.remove();
		System.out.println("remove() result: "+removedElement);
		
		System.out.println(queue);
		
		System.out.println("size() result: "+queue.size());
		
		//Note: peek() return the head of the queue but it doesn't remove it and it returns 
		//null if queue is empty.
		System.out.println("peek() result: "+queue.peek());
		
		System.out.println("poll() result: "+queue.poll());
		
		System.out.println(queue);
		
	}
}
