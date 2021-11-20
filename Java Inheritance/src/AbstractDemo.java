
public class AbstractDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//You can't make an object of abstract class 
		//AbstractClass obj = new AbstractClass("A.Shrikant", "a.shrikant1@tcs.com", 1234);
	
		Employee obj1 = new Employee("A.Shrikant", "a.shrikant1@tcs.com", 1234, "Developer");
		System.out.println(obj1.toString());
	}

}
