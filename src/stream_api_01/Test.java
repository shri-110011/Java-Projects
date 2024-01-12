package stream_api_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Animal {
	private String name;
	
	public Animal(String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println("Name: " + name);
	}
}

class Mammal extends Animal {

	public Mammal(String name) {
		super(name);
	}
	
}

class Cat extends Mammal {
	
	public Cat(String name) {
		super(name);
	}
	
	public void playWithWool() {
		System.out.println("Playing with wool");
	}
	
}

class Dog extends Mammal {

	public Dog(String name) {
		super(name);
	}
	
}

public class Test {
	
	public static void main(String[] args) {
	
		/* list1 can refer to object which implements the List interface and have type parameter that are of type
		 * Mammal or of sub-type of Mammal. 
		 * list1 = new ArrayList<Mammal>(); is allowed
		 * list1 = new ArrayList<Cat>(); is allowed
		 * list1 = new ArrayList<Dog>(); is allowed
		 * 
		 * When we read from list1 then we get Mammal or a subclass of Mammal.
		 * 
		 * But we can't write any kind of object to list1 because we don't know to what kind of list is list1 pointing
		 * to. If list1 is pointing to an ArrayList<Cat> then we can't insert an object of type Mammal or of type Dog.
		 * 
		 * So when we say: 
		 * List<? extends Mammal> list1 
		 * what is guaranteed is that when we read we get a Mammal. 
		 *   
		 *   */
		List<? extends Mammal> list1 = new ArrayList<Cat>(Arrays.asList(new Cat("Cat1")));
//		list1.add(new Mammal()); // Compiler error
//		list1.add(new Cat()); // Compiler error
		
		Mammal mammal1 = list1.get(0);
		mammal1.printName();
		
		/* When we say: List<? super Mammal> list2
		 * we cannot read an object of type Mammal from the list2 because list2 can refer to an object which implements 
		 * the List interface and have type parameter that are of type Mammal or of super-type of Mammal. 
		 * list2 = new ArrayList<Animal>(); is allowed
		 * list2 = new ArrayList<Object>(); is allowed
		 * 
		 * So when we read list2 we can only expect to get an object of type Object.
		 * 
		 * When it comes to writing to list2 we can add an object which is of type Mammal or one of its sub-type.
		 * So:
		 * list2.add(new Cat("Cat2")); is allowed
		 * list2.add(new Dog("Dog1")); is allowed
		 * ist2.add(new Mammal("Mammal1")); is allowed
		 *  */
		List<? super Mammal> list2 = new ArrayList<Animal>();
		list2.add(new Cat("Cat2"));
		list2.add(new Dog("Dog1"));
		list2.add(new Mammal("Mammal1"));
		
		/* We can add an Animal instance to list2 because list2 can be pointing to an ArrayList with type parameter as 
		Mammal. */ 
//		list2.add(new Animal("Mammal1")); // Compiler error
		
		Cat cat1 = (Cat)list2.get(0);
		cat1.playWithWool();
		
//		Cat cat2 = (Cat)list2.get(1);
//		cat2.playWithWool(); // ClassCastException, because cat2 refers to list2.get(1) which is an object of type Dog.
		
		for(Object obj: list2) {
			((Animal)obj).printName();
		}
		
		
		/* Remember PECS: "Producer Extends, Consumer Super".

		"Producer Extends" - If you need a List to produce T values (you want to read Ts from the list), you need to 
		declare it with ? extends T, e.g. List<? extends Integer>. But you cannot add to this list.

		"Consumer Super" - If you need a List to consume T values (you want to write Ts into the list), you need to 
		declare it with ? super T, e.g. List<? super Integer>. But there are no guarantees what type of object you may 
		read from this list.

		If you need to both read from and write to a list, you need to declare it exactly with no wildcards, 
		e.g. List<Integer>.
		
		*/
		
		// Refer below url:
		// https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
	}

}
