package genericsRawType;

class Gen<T> {
	T ob;// Declaring an object of type T.
	
	// Passing the constructor a reference to an object of type T.
	Gen(T o) {
		ob = o;
	}
	
	// Return ob.
	T getob() {
		return ob;
	}
}

public class RawDemo {

	public static void main(String[] args) {
		
		Gen<Integer> iOb = new Gen<Integer>(88);
		
		Gen<String> strOb = new Gen<String>("Generics Test");
		
		Gen raw = new Gen(new Double(98.6));
		
		double d = (Double)raw.getob();
		System.out.println("value: "+d);
		
		
		int i = (Integer)raw.getob();// runtime error: ClassCastException
		
//		strOb = raw;
//		String str = strOb.getob();// runtime error: ClassCastException
		
//		raw = iOb;
//		d = (Double)raw.getob(); // runtime error: ClassCastException
	}

}
