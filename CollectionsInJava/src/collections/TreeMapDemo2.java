package collections;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class TComp implements Comparator<String > {

	@Override
	public int compare(String o1, String o2) {
		int result = o2.substring(o2.lastIndexOf(" "))
				.compareTo(o1.substring(o1.lastIndexOf(" ")));
		
		// Checking if last names match.
		if(result == 0) {
			return o2.compareTo(o1);
		}
		return result;
	}
	
}
public class TreeMapDemo2 {

	public static void main(String[] args) {
		// Create a tree map.
		// Note: TreeMap<K, V>() Constructs a new, empty tree map, using the 
		// natural ordering of its keys.
		TreeMap<String, Double> tm = new TreeMap<>(new TComp());
		tm.put("John Doe", 3434.34);
		tm.put("Tom Smith", 123.22);
		tm.put("Jane Baker", 1378.00);
		tm.put("Tod Hall", 99.22);
		tm.put("Ralph Smith", -19.08);
		
		// Get a set of entries.
		Set<Map.Entry<String, Double>> set = tm.entrySet();
		
		// Display the set.
		for(Map.Entry<String, Double> me: set) {
			System.out.print(me.getKey()+": ");
			System.out.print(me.getValue()+"\n");
		}
		System.out.println();

	}

}
