
class A {
	void m1() throws Exception {
		System.out.println("Inside m1() of A");
		throw new ArrayIndexOutOfBoundsException();
	}
}
class B extends A {
	void m1() {
		System.out.println("Inside m1() of B");
	}
}
public class LearnExceptionHandling2 {

	static int divide(int a, int b) {
		try {
			int res = a/b;
			return res;
		}
		catch(ArithmeticException e) {
			return 0;
		}
		catch(Exception e) {
			return 0;
		}
	}
	public static void main(String[] args) {
		int a = 2, b = 0;
		System.out.println(divide(a,b));
		A a1 = new A();
		try {
			a1.m1();
		}
		catch (Exception e) {
			System.out.println("Exception occcurred in m1() of A");
		}
		
		B b1 = new B();
		b1.m1();
		
		A a2 = new B();
		try {
			a2.m1();
		}
		catch (Exception e) {
			System.out.println("Exception occcurred in m1() of A");
		}

	}

}
