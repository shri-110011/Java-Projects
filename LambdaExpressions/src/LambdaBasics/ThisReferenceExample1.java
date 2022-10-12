package LambdaBasics;

public class ThisReferenceExample1 {
	
	public void doProcess(int i, Process p) {
		p.process(i);
	}

	public static void main(String[] args) {
		
		ThisReferenceExample1 thisReferenceExample1 = new ThisReferenceExample1();
		/* The this reference in the anonymous inner class 
		 * represents the anonymous inner class object.
		 * 
		 * So the this reference gets overwritten in the 
		 * anonymous inner class.
		 */
		thisReferenceExample1.doProcess(10, new Process() {

			@Override
			public void process(int i) {
				System.out.println(this);
			}
			
			public String toString()  {
				return "This is the anonymous inner class.";
			}
			
		});

	}

}
