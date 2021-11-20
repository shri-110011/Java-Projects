
class Vehicle{
	public void run() {
		System.out.println("Vehicle is running!");
	}
	public void stop() {
		System.out.println("Vehicle has stopped!");
	}
	public static void print() {
		System.out.println("Inside Vehicle class!");
	}
}

class Bike extends Vehicle{
	public void run() {
		System.out.println("Bike is running!");
	}
	public static void print() {
		System.out.println("Inside Bike class!");
	}
}

public class LearnMethodOverRiding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
		Vehicle obj = new Vehicle();
		Bike obj1 = new Bike();
		obj.run();
		//Here the subclass's method run() is overriding its superclass's run()
		//Note: the return type of the overriding method and the method which is getting overridden must be same otherwise you will get exception.
		
		obj1.run();
		obj1.stop();
//		obj.print();
//		obj1.print();
		
		//We can declare static methods with the same signature in the subclass, but it is not considered overriding as there won’t be any run-time polymorphism.
		Vehicle.print();
		Bike.print();
	}

}
