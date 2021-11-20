import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class LearnHashSet {

	public static void main(String[] args) {
		HashSet<String> hs1 = new HashSet<String>();
		hs1.add("Apple");
		hs1.add("Orange");
		hs1.add("Banana");
		System.out.println(hs1);
		//HashSet is unordered and it removes duplicate values.
		hs1.addAll(Arrays.asList(new String[] {"Orange", "Mango", "Guava"}));
		System.out.println(hs1);
		
		for(String str:hs1) {
			System.out.println(str);
		}
		HashSet<String> hs2 = new HashSet<String>();
		hs1.add("Pomegranate");
		hs1.add("Grapes");
		hs1.add("Banana");
		
		ArrayList<String> al1 = new ArrayList<String>();
		al1.add("Grapes");
		al1.add("Pear");
		
		//Using addAll() you can add any type of collection to another collection.
		hs2.addAll(hs1);
		System.out.println(hs2);
		
		hs2.addAll(al1);
		System.out.println(hs2);
		
	}

}
