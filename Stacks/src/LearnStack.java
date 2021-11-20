import java.util.Stack;

public class LearnStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<Integer>();
		System.out.println("Empty: "+stack.isEmpty());
		
		stack.push(1);
		stack.push(2);
		stack.push(3); 
		
		System.out.println("peek() result: "+stack.peek());
		
		System.out.println(stack);
		
		System.out.println("pop() result: "+stack.pop());
		
		System.out.println("peek() result: "+stack.peek());
		
		System.out.println("size: "+stack.size());
	}

}
