
class Adder{
	public int add(int a, int b) {
		return a+b;
	}
	public int add(int a, int b, int c) {
		return a+b+c;
	}
	public int add(int a[]) {
		int sum = 0;
		for(int i=0; i<a.length; i++)
			sum += a[i];
		return sum;
	}
	
	//We cannot overload two methods in Java if they differ only by static keyword.
//	public static void foo() { 
//		System.out.println("Test.foo() called "); 
//    } 
//	public void foo() { // Compiler Error: cannot redefine foo() 
//		System.out.println("Test.foo(int) called "); 
//	} 
	
}

public class LearnMethodOverLoading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Adder obj = new Adder();
		System.out.println(obj.add(4,6));
		System.out.println(obj.add(4,6,8));
		
		int arr[] = {1,2,3,4,5};
		System.out.println(arr.length);
		System.out.println(obj.add(arr));

	}

}
