// This program shows the Collections.binarySearch() methods.

package collections_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Person {
	int age;
	String name;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "[name: "+name+", age: "+age+"]";
	}
	
}
class MyComp implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
	
}
public class CollectionsAlgo2 {

	public static void main(String[] args) {
		// Create an ArrayList
		ArrayList<String> al = new ArrayList<>();
		
		// Add elements to the array list.
		al.add("C");
		al.add("A29");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
		al.add(1, "A21");
		
		System.out.println("al: "+al);
		Collections.sort(al);
		System.out.println("al after sorting: "+al);
		MyComp myComp = new MyComp();
		int pos = Collections.binarySearch(al, "B");
		System.out.println(myComp.compare(al.get(1), al.get(0)));
		System.out.println("pos: "+pos);
		
		System.out.println("-----------------------");
		
		ArrayList<Person> al2 = new ArrayList<>();
		al2.add(new Person("Bheem", 12));
		al2.add(new Person("Arjun", 10));
		al2.add(new Person("Yudhistir", 15));
		al2.add(new Person("Nakul", 8));
		al2.add(new Person("Sehadev", 6));
		
		Person personToBeSearched = new Person("Yudhistir", 15);
		Comparator<Person> personComp = new Comparator<Person>() {
			
			@Override
			public int compare(Person o1, Person o2) {
				return o1.age-o2.age;
			}
		};
		Collections.sort(al2, personComp);
		System.out.println("al2 after sorting: "+al2);
		int pos1 = Collections.binarySearch(al2, personToBeSearched, personComp);
		System.out.println("pos1: "+pos1);

	}

}
