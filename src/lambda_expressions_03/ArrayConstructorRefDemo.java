package lambda_expressions_03;

interface MyFunc6<T> {
	T func(int n);
}

class MyClass6 {
	private int val;
	
	MyClass6(int v) {
		val = v;
	}
	
	MyClass6() {
		val = 0;
	}
	
	int getVal() {
		return val; 
	}
}

public class ArrayConstructorRefDemo {

	public static void main(String[] args) {
		/* To create a constructor reference for an array, use this construct: 
		 * type[]::new 
		 * Here, type specifies the type of object being created.
		 * 
		 * Here, the call to func(2) causes a two-element array to be created. 
		 * In general, a functional interface must contain a method that takes a 
		 * single int parameter if it is to be used to refer to an array 
		 * constructor.
		 * */
		MyFunc6<MyClass6[]> mcArrayCons = MyClass6[]::new;
		MyClass6[] a = mcArrayCons.func(2);
		
		a[0] = new MyClass6(1);
		a[1] = new MyClass6(2);
		
		System.out.println("Length of a[]: " + a.length);
		System.out.println("a[0]: " + a[0].getVal());
		System.out.println("a[1]: " + a[1].getVal());
		
		MyClass6[] arr = new MyClass6[5];
		System.out.println("Length of arr[]: " + arr.length);
	}

}
