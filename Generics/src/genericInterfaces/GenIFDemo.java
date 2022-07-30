package genericInterfaces;

interface MinMax<T extends Comparable<T>> {
	T min();
	T max();
}

// Note that the interface MinMax requires a type parameter which implements the Comparable interface therefore
// MyClass which implements MinMax requires the same upper bound.
// Also the upper bound is set to Comparable because we want to ensure that the min() and max() functions can be used
// with objects that can be compared.
class MyClass<T extends Comparable<T>> implements MinMax<T> {

	T[] vals;
	
	MyClass(T[] o) {
		vals = o;
	}
	
	public T min() {
		T v = vals[0];
		
		for(int i=1; i<vals.length; i++)
			if(vals[i].compareTo(v)<0)
				v=vals[i];
		return v;
	}

	public T max() {
		T v = vals[0];
		
		for(int i=1; i<vals.length; i++)
			if(vals[i].compareTo(v)>0)
				v=vals[i];
		return v;	}
	
}

public class GenIFDemo {
	
	public static void main(String[] args) {
		Integer inums[] = {3, 6, 2, 8 ,6};
		Character chs[] = {'b', 'r', 'p', 'w'};
		String strs[] = {"rose", "marigold", "sunflower", "daisy" , "habiscus"};
	
		
		MyClass<Integer> iob = new MyClass<Integer>(inums);
		MyClass<Character> cob = new MyClass<Character>(chs);
		MyClass<String> sob = new MyClass<String>(strs);
		
		System.out.println("Max value in inums: "+iob.max());
		System.out.println("Min value in inums: "+iob.min());
		
		System.out.println("Max value in chs: "+cob.max());
		System.out.println("Min value in ichs: "+cob.min());
		
		System.out.println("Max value in strs: "+sob.max());
		System.out.println("Min value in strs: "+sob.min());
	}

}
