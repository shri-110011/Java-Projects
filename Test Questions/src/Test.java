
interface I1 {
	void m1();
}

interface I2 {
	void m2();
}

abstract class A implements I1, I2 {
	public void m1() {
		System.out.println("Inside A: m1()");
	}
}

class B extends A {
	public void m1() {
		super.m1();
		System.out.println("Inside B: m1()");
	}
	public void m2() {
		System.out.println("Inside B: m2()");
	}
}

public class Test {
	
	public static void main(String[] args) {
		
		// invoke A's m1()
//		A a = new B();
//		a.m1();
		
		A a = new A() {

			@Override
			public void m2() {
				System.out.println("Inside B: m1()");
			}};
		
		a.m2();
	}
}


