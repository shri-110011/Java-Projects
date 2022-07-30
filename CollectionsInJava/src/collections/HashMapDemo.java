package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {

	public static void main(String[] args) {
		// Create a hash map.
		HashMap<String, Double> hm = new HashMap<>();
		hm.put("John Doe", 3434.34);
		hm.put("Tom Smith", 123.22);
		hm.put("Jane Baker", 1378.00);
		hm.put("Tod Hall", 99.22);
		hm.put("Ralph Smith", -19.08);
		
		// Get a set of entries.
		Set<Map.Entry<String, Double>> set = hm.entrySet();
		
		// Display the set.
		for(Map.Entry<String, Double> me: set) {
			System.out.print(me.getKey()+": ");
			System.out.print(me.getValue()+"\n");
		}
		System.out.println();

	}

}
