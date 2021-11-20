class Vehicle{
	public void run() {
		System.out.println("Vehicle is running!");
	}
}

class Bike extends Vehicle{
	public void run() {
		System.out.println("Bike is running!");
	}
}

class Car extends Vehicle{
	public void run() {
		System.out.println("Car is running!");
	}
}


public class LearnHierarchicalInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle obj1 = new Vehicle();
		Bike obj2 = new Bike();
		Car obj3 = new Car();
		obj1.run();
		obj2.run();
		obj3.run();
	}

}
