package threadunsafesingleton;

class NewThread implements Runnable {
	
	private int v;
	
	public NewThread(int val, String name) {
		v = val;
		Thread t = new Thread(this, name);
		t.start();
	}

	@Override
	public void run() {
		Singleton singleton = Singleton.getInstance();
		System.out.println(Thread.currentThread().getName() + ", Before setVal() v: " + v);
		singleton.setVal(v);
		int i=0;
		
		System.out.println(Thread.currentThread().getName() + ", After setVal() val: " + singleton.getVal());
		while(i<5) {
			System.out.println("Inside " + Thread.currentThread().getName() + ", val: " + singleton.getVal());
			i++;
		}
	}
}

public class SingletonClient {

	public static void main(String[] args) {
		/* This program demonstrates the thread safety issue with this version of Singleton class. As you can
		 * see quite often two separate instances of Singleton class gets created which is apparent from the
		 * separate values for val being printed inside the while loop.
		 * 
		 */
		NewThread t1 = new NewThread(37, "Thread 1");
		NewThread t2 = new NewThread(41, "Thread 2");
		
	}

}
