package test_package_for_interface;

class E {
	void m1() {
		System.out.println("Inside m1() of C!");
	}
}
interface F {
	
}
interface A extends F {
	int a = 9;
	int b = 6;
	int c = 5;
	void display();
}
interface B extends A {
	void sound();
	default void m1() {
		
	}
	default void m2() {
		
	}
	static void m3() {
		
	}
}
interface C extends A, B {
	void start();
}

class D implements B {

	@Override
	public void display() {
		System.out.println("Displaying!");
	}

	@Override
	public void sound() {
		System.out.println("Playing!");
	}

//	@Override
//	public void start() {
//		System.out.println("Starting!");
//
//	}
	
}

public class LearnInterface {

	public static void main(String[] args) {
		D d = new D();
		d.display();
		d.sound();
//		d.a = 4;
//		d.b = 2;
		B.m3();
//		d.start();
	}

}
