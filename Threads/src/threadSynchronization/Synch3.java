package threadSynchronization;

class CallMe3 {

	// When a thread is in a synchronized method then all other thread that want access to that synchronized  
	// method on the same instance have to wait.
	void call(String msg) {
		System.out.print("["+msg);
		try {
			Thread.sleep(1000);
			System.out.println("]");
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
}

class Caller3 implements Runnable {
	String msg;
	Thread t;
	CallMe3 target;
	
	public Caller3(CallMe3 target2, String s) {
		target = target2;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		synchronized(target) {
			target.call(msg);
		}
	}
}


public class Synch3 {

	public static void main(String[] args) {
		CallMe3 target = new CallMe3();
		Caller3 obj1 = new Caller3(target, "Hello");
		Caller3 obj2 = new Caller3(target, "Synchronized");
		Caller3 obj3 = new Caller3(target, "World");
		
		//wait for the threads to end
		try {
			obj1.t.join();
			obj2.t.join();
			obj3.t.join();
		}
		catch(InterruptedException e) {
			System.out.println("Main Interrupted");
		}
	}

}
