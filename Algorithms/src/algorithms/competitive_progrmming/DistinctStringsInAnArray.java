package algorithms.competitive_progrmming;

import java.util.Arrays;

/* WAP to compute the number of distinct strings in a strings 
 * array.
 * 
 */
public class DistinctStringsInAnArray {

	public static void main(String[] args) {
		String stringArray[] = {"aa", "aab", "a", "aab", "aa", "ad",
				"a", "aa"};
		
		Arrays.sort(stringArray);
		
		int distinctCount = 0;
		for(int i=0; i< stringArray.length; i++) {
			if(i==0) {
				distinctCount++;
			}
			else {
				if(!stringArray[i].equals(stringArray[i-1])) {
					distinctCount++;
				}
			}
		}
		System.out.println("Distinct strings count: "+distinctCount);
	}
}
