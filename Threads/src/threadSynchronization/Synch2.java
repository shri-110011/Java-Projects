package threadSynchronization;

class CallMe2 {

	// When a thread is in a synchronized method then all other thread that want access to that synchronized  
	// method on the same instance have to wait.
	synchronized void call(String msg) {
		System.out.print("["+msg);
		try {
			Thread.sleep(1000);
			System.out.println("]");
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
}

class Caller2 implements Runnable {
	String msg;
	Thread t;
	CallMe2 target;
	
	public Caller2(CallMe2 target2, String s) {
		target = target2;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		target.call(msg);
	}
}

public class Synch2 {

	public static void main(String[] args) {
		CallMe2 target = new CallMe2();
		Caller2 obj1 = new Caller2(target, "Hello");
		Caller2 obj2 = new Caller2(target, "Synchronized");
		Caller2 obj3 = new Caller2(target, "World");
		
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
