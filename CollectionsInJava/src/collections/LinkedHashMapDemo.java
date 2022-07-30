package collections;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapDemo {

	public static void main(String[] args) {
		// Create a hash map.
		LinkedHashMap<String, Double> lhm = new LinkedHashMap<>() {
			protected boolean removeEldestEntry(Map.Entry<String, Double> eldest) {
				return size()>4;
			}
		};
		lhm.put("John Doe", 3434.34);
		lhm.put("Tom Smith", 123.22);
		lhm.put("Jane Baker", 1378.00);
		lhm.put("Tod Hall", 99.22);
		System.out.println(lhm);
		lhm.put("Ralph Smith", -19.08);
		
		System.out.println(lhm);
		
		// Get a set of entries.
		Set<Map.Entry<String, Double>> set = lhm.entrySet();
		
		// Display the set.
		for(Map.Entry<String, Double> me: set) {
			System.out.print(me.getKey()+": ");
			System.out.print(me.getValue()+"\n");
		}
		System.out.println();

	}

}
