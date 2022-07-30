package genericsStarter;

class Gen<T> {
	T ob;// Declaring an object of type T.
	
	// Passing the constructor a reference to an object of type T.
	Gen(T o) {
		ob = o;
	}
	
	// Return ob.
	T getob() {
		return ob;
	}
	
	void showType() {
		System.out.println("Type of T is: "+ob.getClass().getName());
	}
}

public class GenDemo {

	public static void main(String[] args) {
		// Create a Gen reference for Integer.
		Gen<Integer> iOb;
		
		// Create a Gen<Inteegr> object and assign its reference to iOb.
		// Notice that autoboxing is being used to encapsulate the value 88 within an Integer object.
		iOb = new Gen<Integer>(88);
		
		// Show the type of data used by iOb.
		iOb.showType();
		
		// Get the value in iOb. Notice that no cast is needed.
		int v = iOb.getob();
		System.out.println("value: "+ v);
		
		System.out.println();
		
		// Create a Gen object for Strings.
		Gen<String> strOb = new Gen<String>("Generics Test");
		
		// Show the type of data used by strOb.
		strOb.showType();
		
		// Get the value in strOb. Again, notice that no cast is needed.
		String str = strOb.getob();
		System.out.println("value: "+ str);	

	}

}
