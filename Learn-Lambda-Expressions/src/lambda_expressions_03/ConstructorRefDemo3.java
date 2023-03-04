package lambda_expressions_03;

interface MyFunc4<R, T> {
	R func(T n);
}

// A simple generic class.
class MyClass4<T> {
	private T val;
	
	MyClass4(T v) {
		val = v;
	}
	
	// This default constructor is not used by this program.
	MyClass4() {
		val = null;
	}
	
	T getVal() {
		return val; 
	}
}

// A simple non-generic class.
class MyClass5 {
	String str;
	
	MyClass5(String s) {
		str = s;
	}
	
	// This default constructor is not used by this program.
	MyClass5() {
		str = "";
	}
	
	String getVal() {
		return str;
	}
}


public class ConstructorRefDemo3 {
	
	/* A factory method for class objects. The class must have a constructor that
	 * takes one parameter of type T.
	 * R specifies the type of object being created. */
	static <R, T> R myClassFactory(MyFunc4<R, T> cons, T v) {
		return cons.func(v);
	}

	public static void main(String[] args) {
		/* Create a reference to a MyClass4<T> constructor. In this case new refers 
		 * to the constructor that takes an argument. */
		MyFunc4<MyClass4<Double>, Double> myClass4Cons1 = MyClass4::new;
		
		
		// Create an instance of MyClass4 by use of the factory method.
		MyClass4<Double> mc1 = myClassFactory(myClass4Cons1, 100.1);
		
		// Use the instance of MyClass4 just created. 
		System.out.println("val in mc1 is: " + mc1.getVal());
		
		// Now, create a different class by use of myClassFactory().
		MyFunc4<MyClass5, String> myClass5Cons1 = MyClass5::new;
		
		// Create an instance of MyClass5 by use of the factory method.
		MyClass5 mc2 = myClassFactory(myClass5Cons1, "Lambda");
		
		// Use the instance of MyClass4 just created. 
		System.out.println("str in mc2 is: " + mc2.getVal());	
	}

}
