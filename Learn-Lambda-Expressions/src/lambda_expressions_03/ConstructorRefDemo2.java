package lambda_expressions_03;

// Demonstrates a constructor reference with a generic class.

// MyFunc is now a generic functional interface.
interface MyFunc3<T> {
	MyClass3<T> func(T n);
}

class MyClass3<T> {
	private T val;
	
	MyClass3(T v) {
		val = v;
	}
	
	MyClass3() {
		val = null;
	}
	
	T getVal() {
		return val; 
	}
}

public class ConstructorRefDemo2 {

	public static void main(String[] args) {
		// Create a reference to the MyCLass3<T> constructor.
		MyFunc3<Integer> myClass3Cons1 = MyClass3::new;
		
		// Create an instance of MyClass3 via the constructor reference.
		MyClass3<Integer> mc1 = myClass3Cons1.func(101);
				
		// Use the instance of MyClass3 just created. 
		System.out.println("val in mc1 is: " + mc1.getVal());
		
		MyFunc3<String> myClass3Cons2 = MyClass3::new;
		
		MyClass3<String> mc2 = myClass3Cons2.func("Hello World");
		System.out.println("val in mc2 is: " + mc2.getVal());
	}

}
