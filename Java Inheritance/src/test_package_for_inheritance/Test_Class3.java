package test_package_for_inheritance;

public class Test_Class3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A obj = new A();
		//Classes with access specifier public and default are visible inside classes within the same package.
		//Data members with access specifier public, default or protected are accessible inside other classes within the same package.
		System.out.println("a1: "+obj.a1);
		System.out.println("a3: "+obj.a3);
		System.out.println("a4: "+obj.a4);
		Test_Class2 obj2 = new Test_Class2();
		System.out.println(obj2.msg);
	}

}
