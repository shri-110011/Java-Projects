
class Demo{
	int y;
	float z;
	public int print(int a, int  c) {
		return c;
	}
	public double print(double a, float  c) {
		return c;
	}
	//You can't declare an uninitialized variable inside a function(static or non-static) and use it there itself.
	//This problem is more severe if there is an uninitialized variable inside the main()
	//But if you use the uninitialized variable of another class by using that class's object then only you will get the default value for that type.  
	//If an uninitialized variable is there inside another class's function then until we return that variable's value or try to print its value from there inside the function itself where the uninitialized variable is declared then java will not throw error but will show warnings 
//	public int func() {
//		int p;
//		System.out.println("p: "+p);
//		return p;
//	}
}

public class LearnMethodOverLoadingWithTypePromotion {
	
	double w;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo obj = new Demo();
		float x = 2.0f;
		
		//Here x which is of type float is promoted to double and char is promoted to float 
		System.out.println(obj.print(x, 'a'));
		//Here x which is of type char promoted to int 
		System.out.println(obj.print(1, 'a'));
		LearnMethodOverLoadingWithTypePromotion obj1 = new LearnMethodOverLoadingWithTypePromotion(); 
		System.out.println("x: "+x+" y: "+obj.y+" z: "+obj.z+" w: "+obj1.w);
	}

}
