package test_package_for_inheritance;

class A{
	int a1;
	
	private int a2;
	
	protected int a3;
	
	public int a4;
	
	public int a5 = 2;

	protected void print_a1() {
		System.out.println("a1: "+a1);
	}
	protected void print_a2() {
		System.out.println("a2: "+a2);
	}
	protected void print_a3() {
		System.out.println("a3: "+a3);
	}
	protected void print_a4() {
		System.out.println("a4: "+a4);
		greet();
	}
	public void greet() {
		System.out.println("Hello from public method!");
	}
	public static void m1() {
		System.out.println("m1() of A!");
	}
	void m2() {
		System.out.println("m2() of A!");
//		return Double.parseDouble(""+a);
	}
	public A() {
		System.out.println("Inside default constructor A()");
	}
}
class B extends A{
	public static void m1() {
		System.out.println("m1() of B!");
	}
	protected void m2() {
		System.out.println("m2() of B!");
//		return Integer.parseInt(""+a);
	}
	public int a5 = 3;
	protected void print() {
		System.out.println("a1: "+a1);
		System.out.println("a3: "+a3);
		System.out.println("a4: "+a4);
		new B().greet();
	}
}

public class Test_Class2 {

	protected String msg = "hello";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B obj1 = new B();
		
		//A class can't have an access specifier as private or protected
		
		//We can access a protected method of a base class by using the object of the sub class   
		obj1.print_a1();
		
		//A protected method of a class can access the private members of the same class. 
		obj1.print_a2();
		//A protected method of a class can access the  members of the same class having default access specifier. 
		obj1.print_a3();
		//A protected method of a class can access the  public members of the same class.
		obj1.print_a4();
		//Conclusion a protected or a public method of a class can access any type of data members inside the same class.
		
		//Private methods of a base class are not accessible using the object of the sub class.
//		obj1.greet();
		
		A obj2 = new A();
//		System.out.println("a1: "+obj2.a1);
		//You can't access a private variable or a private method of a class directly using that's class's object.
//		System.out.println("a2: "+obj2.a2);
//		obj2.greet();
//		System.out.println("a3: "+obj2.a3);
//		System.out.println("a4: "+obj2.a4);
		
		System.out.println("a1: "+obj1.a1);
		//Private data members and methods of the super class are not accessible inside the child class
//		System.out.println("a2: "+obj1.a2);
		
		System.out.println("a3: "+obj1.a3);
		System.out.println("a4: "+obj1.a4);
		
		//A protected method of a subclass can access protected, public and default data members and methods inside the super class.
		obj1.print();
		
		obj2.m1();
		
		A obj3 = new B();
		obj3.m1();
		
		System.out.println(obj3.a5);
		
		obj1.m2();
		obj3.m2();
	}

}
