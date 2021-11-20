//This program was created to check if we can call a method of a class from another method of the same class without using the class 
//object.

//Result: Yes we can do so.
class Student {
	String name;
	int rn;
	Student() {
		initialize();
	}
	private void initialize() {
		name = "A.Shrikant";
		rn = 1;
	}
	public void display() {
		System.out.println("{\"name\":"+name+",\"rn\":"+rn+"}");
	}
}
public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student(); 
		s1.display();
	}

}
