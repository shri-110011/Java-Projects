package genericsWildcardArguments;

class Stats<T extends Number> {
	T[] nums;
	
	Stats(T[] o) {
		nums = o;
	}
	
	double average() {
		double sum = 0;
		
		for(int i=0; i<nums.length; i++)
			sum += nums[i].doubleValue();
		
		return sum/nums.length;
		
	}
	
	//Determine if two averages are the same. Notice the use of wildcard.
	// The wildcard matches any valid Stats object.
	boolean sameAvg(Stats<?> ob) {
		if(average() == ob.average())
			return true;
		else
			return false;
	}
}
public class WildCardDemo {

	public static void main(String[] args) {
		Integer inums[] = {1,2,3,4,5};
		Stats<Integer> iOb = new Stats<Integer>(inums);
		double v = iOb.average();
		System.out.println("iOb average: "+v);
		
		Double dnums[] = {1.1,2.2,3.3,4.4,5.5};
		Stats<Double> dOb = new Stats<Double>(dnums);
		double w= dOb.average();
		System.out.println("dOb average: "+w);
		
		Float fnums[] = {1F,2.0F,3.0F,4.0F,5.0F};
		Stats<Float> fOb = new Stats<Float>(fnums);
		double x= fOb.average();
		System.out.println("fOb average: "+x);
		
		// See which arrays have same average
		System.out.print("Average of iOb and dOb ");
		if(iOb.sameAvg(dOb))
			System.out.println("are same.");
		else
			System.out.println("differ.");
		
		System.out.print("Average of iOb and fOb ");
		
		if((iOb.sameAvg(fOb))) 
			System.out.println("are same.");
		else
			System.out.println("differ.");
		
	}

}
