
class Student{
	int rn;
	String name;
	
	static String schoolName = "DTEA";
	
	public void setRn(int rn) {
		this.rn = rn;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRn() {
		return this.rn;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void printStudentDetails() {
		System.out.println("Roll No: "+this.rn+" Name: "+this.name+" School: "+Student.schoolName);
		//Note: We can call a static method from inside a non-static method but can't access the non-static attributes and methods without using an object inside the static method
		Student.greet();
	}
	
	public static void greet() {
		//Note: You can't call non-static attributes using the 'this' keyword from inside a static method
		//System.out.println("Roll No: "+this.rn+" Name: "+this.name);
		
		Student s1 = new Student();
		s1.setRn(15);
		s1.setName("Billy");
		System.out.println("Roll No: "+s1.rn+" Name: "+s1.name+" School: "+Student.schoolName);
		
		System.out.println("Hello there!");
	}
	
}


public class LearnStaticMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		s1.setRn(12);
		s1.setName("John");
		
		s1.printStudentDetails();
		
		LearnStaticMethods lsm1  = new LearnStaticMethods();
		
		lsm1.doSomething();
		
		//Note: using an object of a class to access the static method of that class is not wrong but java throws a note
		//s1.greet();
		
		//Note: You can't define methods within methods directly
//		public void doSomething(){
//			
//		}
		
	}
	
	public void doSomething(){
		Student.greet();
	}

}
