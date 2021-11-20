interface A{
	public void method1();
	public void method2(String name);
	int val = 2;
}
interface B extends A{
	public int method3(int x, int y);
}
interface Drawable {
	public void draw();
}

class ImplementInterfaces implements B{
	public void method1() {
		System.out.println("Method 1 implemented");
	}
	public void method2(String name) {
		System.out.println("Method 2 implemented: "+name);
	}
	public int method3(int a, int b) {
		System.out.println("Method 3 implemented");
		return a+b;
	}
	
	
}

public class LearnInterfaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementInterfaces obj = new ImplementInterfaces();
		obj.method1();
		obj.method2("A.Shrikant");
		System.out.println(obj.method3(34, 56));
	
		//An interface variable is by default static, constant and public, we can even change 
		//the access specifier to private.
		System.out.println(A.val);
		
		//Here we implement an interface without using the 'implements' keyword. This is done
		//using the anonymous function.
		Drawable d = new Drawable() {
			public void draw() {
				System.out.println("Drawing");
			}
		};
		d.draw();
		
	}

}
