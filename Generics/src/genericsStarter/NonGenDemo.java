package genericsStarter;

class NonGen {
	Object ob;// Declaring an object of type T.
	
	// Passing the constructor a reference to an object of type T.
	NonGen(Object o) {
		ob = o;
	}
	
	// Return ob.
	Object getob() {
		return ob;
	}
	
	void showType() {
		System.out.println("Type of ob is: "+ob.getClass().getName());
	}
}
public class NonGenDemo {

	public static void main(String[] args) {
		NonGen iOb;
		
		// Create a NonGen object and assign its reference to iOb.
		// Notice that autoboxing still occurs to encapsulate the value 88 within an Integer object.
		iOb = new NonGen(88);
		
		// Show the type of data used by iOb.
		iOb.showType();
		
		// Get the value in iOb. Notice that no cast is needed.
		int v = (int)iOb.getob();
		System.out.println("value: "+ v);
		
		System.out.println();
		
		// Create a NonGen object for Strings.
		NonGen strOb = new NonGen("Generics Test");
		
		// Show the type of data used by strOb.
		strOb.showType();
		
		// Get the value in strOb. Again, notice that no cast is needed.
		String str = (String)strOb.getob();
		System.out.println("value: "+ str);	
		
		iOb = strOb;
		v = (Integer)iOb.getob();// This will cause run time exception:java.lang.ClassCastException 
		// because iOb holds a reference to a String object and we are type casting it to an Integer object, 
		// which is wrong.
	}

}
