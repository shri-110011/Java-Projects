package stringMethods;

public class LearnSplitMethod {

	public static void main(String[] args) {
		String str = "ss";
//		The limit parameter controls the number of times the pattern is applied and therefore affects 
//		the length of the resulting array. 
//
//		•If the limit is positive then the pattern will be applied at most limit - 1 times, the array's 
//		length will be no greater than limit, and the array's last entry will contain all input beyond 
//		the last matched delimiter.
//
//
//		•If the limit is zero then the pattern will be applied as many times as possible, the array can 
//		have any length, and trailing empty strings will be discarded.
//		
//		
//		•If the limit is negative then the pattern will be applied as many times as possible and the 
//		array can have any length


	    String[] arrOfStr = str.split("s", 5);
	    
	    System.out.println("Length of arrOfStr: "+arrOfStr.length);
	  
	    for (String a : arrOfStr) {
	    	System.out.print(a+" ");
	    }
	}

}
