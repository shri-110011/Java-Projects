package LambdaBasics;

public class ThisReferenceExample2 {
	
	public void doProcess(int i, Process p) {
		p.process(i);
	}
	
	public void doExecute() {
		doProcess(10, (i)-> {
			System.out.println(this); // No compiler error
			/* this reference doesn't get overwritten in the 
			 * lambda expression.
			 * 
			 */
		});
	}

	public static void main(String[] args) {
		ThisReferenceExample2 thisReferenceExample2 = new ThisReferenceExample2();
		/* The this reference in the lambda expression doesn't
		 * represents the instance of the lambda expression.
		 * 
		 * It represents the this reference of the outer 
		 * context.
		 */
		thisReferenceExample2.doProcess(10, (i)-> {
//			System.out.println(this); // compiler error
		});
		
		thisReferenceExample2.doExecute();
	}
	
	public String toString()  {
		return "This is the main ThisReferenceExample2 instance.";
	}

}
