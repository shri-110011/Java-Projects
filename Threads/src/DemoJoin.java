

class NewThread4 implements Runnable {
	
	Thread t;
	String name;
	
	NewThread4(String threadName) {
		//Crate a new second thread
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New thread: "+t);
		t.start();// start the thread
	}

	//This is the entry point for thread
	public void run() {
		try {
			for(int i=5; i>0; i--) {
				System.out.println(name+": "+i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {
			System.out.println(name+" interrupted.");
		}
		System.out.println(name+" exiting.");
	}
	
}
public class DemoJoin {

	public static void main(String[] args) {
		
		NewThread4 obj1 = new NewThread4("One");
		NewThread4 obj2 = new NewThread4("Two");
		NewThread4 obj3 = new NewThread4("Three");
		
		System.out.println("Thread one is alive: "+obj1.t.isAlive());
		System.out.println("Thread two is alive: "+obj2.t.isAlive());
		System.out.println("Thread three is alive: "+obj3.t.isAlive());
		
		//waiting for threads to finish
		try {
			System.out.println("Waiting for threads to finish...");
			obj1.t.join();
			obj2.t.join();
			obj3.t.join();
		}
		catch(InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Thread one is alive: "+obj1.t.isAlive());
		System.out.println("Thread two is alive: "+obj2.t.isAlive());
		System.out.println("Thread three is alive: "+obj3.t.isAlive());
		
		System.out.println("Main thread exiting.");

	}

}
