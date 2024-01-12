package lambda_expressions_02;

/* A functional interface that takes two reference arguments and return a boolean 
result. */
interface MyFunc1<T> {
	boolean func(T v1, T v2);
}

// A class that stores the temperature high for a day.
class HighTemp {
	private int hTemp;
	
	HighTemp(int ht) {
		hTemp = ht;
	}
	
	/* Return true if the invoking HighTemp object has same temperature as ht2. */
	boolean sameTemp(HighTemp ht1, HighTemp ht2) {
		return hTemp == ht2.hTemp;
	}
	/* Return true if the invoking HighTemp object has a temperature that is less 
	 * than ht2. */
	boolean lessThanHighTemp(HighTemp ht2) {
		return hTemp < ht2.hTemp;
	}
	
	
}

public class InstanceMethWithObjRefDemo {
	
	/* A method that returns the number of occurrences of an object for which 
	 * some criteria, as specified by the MyFunc parameter, is true.*/
	static <T> int counter(T[] vals, MyFunc1<T> f, T v) {
		int count = 0;
		
		for(int i=0; i<vals.length; i++) {
			if(f.func(vals[i], v)) count++;
		}
		
		return count;
	}

	public static void main(String[] args) {
		int count;
		
		// Create an array of HighTemp objects.
		HighTemp[] weekDayHighs = {new HighTemp(89), new HighTemp(82), 
				new HighTemp(90), new HighTemp(89),
				new HighTemp(89), new HighTemp(91),
				new HighTemp(84), new HighTemp(83)};
		
		HighTemp ht = new HighTemp(12);
		
		// Use counter() with the array of HighTemp.
		/* Notice a reference to an instance method sameTemp() is passed as the 
		 * second argument. */
		count = counter(weekDayHighs, ht::sameTemp, new HighTemp(89));
		System.out.println(count + " days had a high of 89");

		
		// Now, create and use another array of HighTemp objects.
		HighTemp[] weekDayHighs2 = {new HighTemp(32), new HighTemp(12), 
				new HighTemp(24), new HighTemp(19),
				new HighTemp(18), new HighTemp(12),
				new HighTemp(-1), new HighTemp(13)};
		
		// Use counter() with the array of HighTemp.
		/* Notice a reference to an instance method sameTemp() is passed as the 
		 * second argument. */
		count = counter(weekDayHighs2, ht::sameTemp, new HighTemp(12));
		System.out.println(count + " days had a high of 12");
		
		/* Now use lessThanTemp() to find when temperature was less than a 
		 * specified value. */
		count = counter(weekDayHighs, HighTemp::lessThanHighTemp, new HighTemp(89));
		System.out.println(count + " days had a high less than 89");
		
		count = counter(weekDayHighs2, HighTemp::lessThanHighTemp, new HighTemp(19));
		System.out.println(count + " days had a high less than 19");
	
	}

}
