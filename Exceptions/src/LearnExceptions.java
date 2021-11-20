
public class LearnExceptions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Exceptions can be classified into two types: 1.Checked 2.Unchecked
		
		//1.Checked Exceptions are those which are detected at compile time. 
		//Eg: FileNotFoundException, IOException
		
		//2. Unchecked Exceptions are those which are not detected at compile time. 
		//Eg: ArithmeticException, StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException 
		//NullPointerException, IllegalArgumentException, NumberFormatException.
		
		
		int a=25, b=0;
		//This will cause ArithmeticException / by zero
//		System.out.println("a/b: "+a/b);
		
		String str="Hello";
		//This will cause StringIndexOutOfBoundsException
//		System.out.println("str.charAt(5): "+str.charAt(5));
		
		//This will cause NumberFormatException
//		System.out.println(Integer.parseInt("Hi"));
		
		int arr[] = new int[3];
		//This will cause ArrayIndexOutOfBoundsException
//		arr[3] = 4;
		
		
		String str2 = null;
		//This will cause NullPointerException
//		System.out.println(str2.length());
		
		Test t  = null;
		//This will cause NullPointerException
//		t.greet();
		
	}

}

class Test {
	public void greet() {
		System.out.println("Hello World!");
	}
}
