import java.util.ArrayList;
import java.util.Collections;

public class LearnStrings {

	public static void main(String[] args) {
		String str = "A.Shrikant";
		String str1 = new String("Arjun");
		System.out.println(str1);
		System.out.println("Length of str: "+str.length());
		System.out.println("str[2]: "+str.charAt(2));
		System.out.println("Lower case str: "+str.toLowerCase());
		//Note: str.replace() will return a new string object if the character to 
		//replaced is found in the given string, otherwise it gives a reference to the 
		//same string object.
		String str2 = str.replace('v', 'y');
		System.out.println("str.replace('v', 'y'): "+ str2);
		System.out.println("str[7] "+str.charAt(7));
		System.out.println("str2[7] "+str2.charAt(7));
		if(str == str2) {
			System.out.println("str is same as str2");
		}
		else {
			System.out.println("str and str2 are different");
		}
		
		
		ArrayList<String> al1 = new ArrayList<String>();
		al1.add("Pineapple");
		al1.add("Guava");
		al1.add("Apple");
		al1.add("Orange");
		al1.add("Banana");
		Collections.sort(al1);
		System.out.println(al1);
		System.out.println(str);
		
		//str1 = "A.Siddharth";
		System.out.println(str1.length());
		//Note: Whenever we make any changes to the str or str1 which are string objects then a new string object is created that is why the value of the string appears to be changing.
		String str3 = str1;
		if(str3 == str1) {
			System.out.println("str3 is same as str1");
		}
		else {
			System.out.println("str3 and str1 are different");
		}
		str1 = str1+"12";
		if(str3 == str1) {
			System.out.println("str3 is same as str1");
		}
		else {
			System.out.println("str3 and str1 are different");
		}
		System.out.println(str1);
		
	}
}
