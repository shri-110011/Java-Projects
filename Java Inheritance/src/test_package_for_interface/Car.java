package test_package_for_interface;

interface Vehicle {
	public default void m1() {
		System.out.println("Inside Vehicle!");
	}
}
interface MotorVehicle extends Vehicle {
	@Override
	public default void m1() {
		System.out.println("Inside MotorVehicle!");
	}
}

class  MyVehicle implements Vehicle, MotorVehicle {
	
}

public class Car {
	public static void main(String[] args) {
		Vehicle v = new MyVehicle();
		v.m1();
	}
}

