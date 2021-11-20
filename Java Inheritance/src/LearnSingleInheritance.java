
class Animal{
	public void eat() {
		System.out.println("Animal is eating!");
	}
}

class Dog extends Animal{
	public void sound() {
		System.out.println("Bow Bow!");
	}
}


public class LearnSingleInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog obj1 = new Dog();
		obj1.eat();
		obj1.sound();

	}

}
