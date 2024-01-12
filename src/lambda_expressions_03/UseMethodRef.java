package lambda_expressions_03;

import java.util.ArrayList;
import java.util.Collections;

/* Use method reference to help find the maximum value in a collection. */
class MyClass {
	private int val;
	
	MyClass(int v) {
		val = v;
	}
	
	int getVal() {
		return val;
	}
}

public class UseMethodRef {
	
	/* A compare() compatible with the one defined by Comparator<T> */
	static int compare(MyClass a, MyClass b) {
		return a.getVal() - b.getVal();
	}
	
	/* In the program, notice that MyClass neither defines any comparison method 
	 * of its own, nor does it implement Comparator. However, the maximum value 
	 * of a list of MyClass items can still be obtained by calling max( ) because 
	 * UseMethodRef defines the static method compareMC( ), which is compatible 
	 * with the compare( ) method defined by Comparator. Therefore, there is no
	 * need to explicitly implement and create an instance of Comparator. */

	public static void main(String[] args) {
		ArrayList<MyClass> al = new ArrayList<>();
		al.add(new MyClass(1));
		al.add(new MyClass(4));
		al.add(new MyClass(2));
		al.add(new MyClass(9));
		al.add(new MyClass(3));
		al.add(new MyClass(7));
		
		UseMethodRef obj = new UseMethodRef();
		
		MyClass maxValObj = Collections.max(al, UseMethodRef::compare);
		System.out.println("Maximum value is: " + maxValObj.getVal());
	}

}
