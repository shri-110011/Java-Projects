
class Apple implements Runnable{
	public void run() {
		for(int i=0; i<5; i++) {
			try{Thread.sleep(500);}catch(InterruptedException e) {System.out.println(e);}
			System.out.println("Apple");
		}
	}
}
class Orange implements Runnable{
	public void run() {
		for(int i=0; i<5; i++) {
			try{Thread.sleep(500);}catch(InterruptedException e) {System.out.println(e);}
			System.out.println("Orange");
		}
	}
}
public class LearnRunnableInterface {

	public static void main(String[] args) {
		Apple obj1 = new Apple();
		Orange obj2 = new Orange();
	
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		
		t1.start();
		t2.start();

	}

}
