package LambdaBasics;

public class TypeInferenceExample {

	public static void main(String[] args) {
//		StringLengthLambda myLambda = (String s)->s.length();
		
		/* Here we see that the type of the argument in the
		 * lambda is not specified because the java compiler
		 * can infer that from the type of the myLambda variable.
		 * 
		 * This is known as Type Inference.
		 */
//		StringLengthLambda myLambda = s->s.length();
		
//		System.out.println(myLambda.getLength("Hi Shri"));
		
		printLambda(s->s.length());
	}
	
	public static void printLambda(StringLengthLambda l) {
		System.out.println(l.getLength("Hi Shri"));
	}
	
	interface StringLengthLambda {
		int getLength(String s);
	}

}
