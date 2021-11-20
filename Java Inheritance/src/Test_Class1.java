import test_package_for_inheritance.Test_Class2;

public class Test_Class1 extends Test_Class2{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test_Class1 obj = new Test_Class1();
		//Protected or default data members of a class are not accessible in classes outside the same package.
		//Protected members of a base class are accessible inside the subclass residing in any package as far as base class is declared public.
		//Note: If the protected data member in the base class is not static then without using the object of the subclass you can't access it inside the subclass's main().
		//Note: If the protected data member in the base class is static then we can directly access it without having to create an object of the subclass and then using it to access that protected member.
		//Note: If the data member in the base class has a default access specifier then you can't access it in subclass residing in another package.
//		System.out.println("msg: "+obj.msg);
		
		Test_Class2 obj2 = new Test_Class2();
		
		
		System.out.println("msg: "+obj.msg);
		
		
		/*Key points of inheritance:
		                                 default     private     protected     public
		Same class 		                 Yes         Yes         Yes           Yes
		Same package subclass            Yes         No          Yes           Yes
		Same package non-subclass 		 Yes         No          Yes           Yes
		Different package subclass       No          No          Yes           Yes
		Different package non-subclass   No          No          No            Yes
		                  */            
	}

}
