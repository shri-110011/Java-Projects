package lambda_expressions_03;

// MyFunc is a functional interface whose method returns a MyClass2 reference.
interface MyFunc2 {
	MyClass2 func(int n);
}

class MyClass2 {
	private int val;
	
	MyClass2(int v) {
		val = v;
	}
	
	MyClass2() {
		val = 0;
	}
	
	int getVal() {
		return val; 
	}
}

public class ConstructorRefDemo {

	public static void main(String[] args) {
		
		/* The expression MyClass::new creates a constructor reference to a MyClass
		 * constructor. In this case, because MyFunc’s func( ) method takes an 
		 * int parameter, the constructor being referred to is MyClass(int v) 
		 * because it is the one that matches. Also notice that the reference to 
		 * this constructor is assigned to a MyFunc reference called myClassCons. 
		 * After this statement executes, myClassCons can be used to create an 
		 * instance of MyClass. */
		
		// Create a reference to the MyCLass2 constructor.
		/* Because func() in MyFunc takes an argument, new refers to the 
		 * parameterized constructor in MyClass2, not the default constructor. */
		MyFunc2 myClass2Cons = MyClass2::new;
		
		// Create an instance of MyClass2 via the constructor reference.
		MyClass2 mc = myClass2Cons.func(100);
		
		// Use the instance of MyClass2 just created. 
		System.out.println("val in mc is: " + mc.getVal());

	}

}
