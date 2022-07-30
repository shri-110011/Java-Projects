package threadSynchronization;

class CallMe {

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

class Caller implements Runnable {
	String msg;
	Thread t;
	CallMe target;
	
	public Caller(CallMe targ, String s) {
		target = targ;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		target.call(msg);
	}
}

class Synch {
	
	public static void main(String[] args) {
		CallMe target = new CallMe();
		Caller obj1 = new Caller(target, "Hello");
		Caller obj2 = new Caller(target, "Synchronized");
		Caller obj3 = new Caller(target, "World");
		
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
