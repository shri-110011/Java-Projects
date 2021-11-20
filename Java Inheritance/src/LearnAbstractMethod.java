 abstract class Test {
	int i = 1;
	abstract public  void display();
	public static void greet() {
		System.out.println("Hello World");
	}
}
public class LearnAbstractMethod extends Test {
	 public void display() {
		System.out.println(i);
	}
	public static void main(String ...args) {
		LearnAbstractMethod lam1 = new LearnAbstractMethod();
//		System.out.println(lam1.i);
		lam1.display();
		lam1.i = 2;
		LearnAbstractMethod lam2 = new LearnAbstractMethod();
//		System.out.println(lam2.i);
		lam2.display();
	}
}
