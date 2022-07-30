package boundedTypes;

class Stats<T extends Number> {
	T[] nums;
	
	Stats(T[] o) {
		nums = o;
	}
	
	double average() {
		double sum = 0.0;
		for(int i=0;i <nums.length; i++)
			sum += nums[i].doubleValue();
		
		return sum/nums.length;
	}
}
public class BoundsDemo {

	public static void main(String[] args) {
		
		Integer inums[] = {1,2,3,4,5};
		Stats<Integer> iOb = new Stats<Integer>(inums);
		double v= iOb.average();
		System.out.println("iOb average: "+v);
		
		Double dnums[] = {1.1,2.2,3.3,4.4,5.5};
		Stats<Double> dOb = new Stats<Double>(dnums);
		double w= dOb.average();
		System.out.println("dOb average: "+w);
		
		// Below commented out code will cause compiler error:
		// Bound mismatch: The type String is not a valid substitute for the bounded parameter 
		// <T extends Number> of the type Stats<T>
		// This is because we have placed an upper bound Number on the type parameter T i.e. now the valid type 
		// arguments can be of subclasses of Number.
//		String strs[] = {"1","2","3","4","5"};
//		Stats<String> strOb = new Stats<String>(strs);
//		double x= strOb.average();
//		System.out.println("strOb average: "+x);
		
	}

}
