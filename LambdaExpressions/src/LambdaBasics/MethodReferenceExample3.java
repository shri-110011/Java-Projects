package LambdaBasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MethodReferenceExample3 {

	public static void main(String[] args) {
		String[] stringArray = { "Barbara", "James", "Mary", 
				"John", "Patricia", "Robert", "Michael", "Linda" };
		
		Comparator<String> stringComparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		
		/* Passing an instance of Comparator to sort the string 
		 * array.
		 */
//		Arrays.sort(stringArray, stringComparator);
		
		/* Using a lambda expression to implement the Comparator.
		 * 
		 */
		Arrays.sort(stringArray, (String o1, String o2)->
		o2.compareTo(o1));
		
		/* Equivalent method reference expression for the above
		 * lambda expression. 
		 * 
		 * The following is an example of a reference to an 
		 * instance method of an arbitrary object of a particular 
		 * type:
		 */
//		Arrays.sort(stringArray, String::compareToIgnoreCase);
		
		System.out.println(Arrays.asList(stringArray));
		
		List<String> colors = new ArrayList<>();
		colors.add("Green");
		colors.add("Red");
		colors.add("Blue");
		System.out.println(colors);
		
		Collections.sort(colors, String::compareTo);
		System.out.println(colors);

	}

}
