
public class PracticeStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = new String("My name");
		System.out.println(str);
//		System.out.println(str.length());
//		System.out.println(str.charAt(0));
//		System.out.println(str.substring(2));
		
		//Note: When we create a string object and initialize it using the default 
		//String() constructor we get an empty string as the default value for the 
		//string object.
		String str2 = new String();
//		str2 = "sun";
		String str3 = "earth";
		String str4 = "earth";
		//Note: Without initializing a string object if we try to use it then we will 
		//get an error.
		String str6;
//		str3 = "moon";
		System.out.println(str2);
		if(str2 == null)
			System.out.println(true);
		else
			System.out.println(false);
		System.out.println("Length of str2: "+str2.length());
		
		if(str3 == null)
			System.out.println(true);
		else
			System.out.println(false);
		System.out.println(str3.length());
		
		//Note: equals() compares for string objects values i.e whether the two string 
		//objects have equal values or unequal values.
		System.out.println(str4.equals(str3));
		
		System.out.println(args.length);
		
		for(String str5: args) {
			System.out.println(str5);
		}

	}

}
