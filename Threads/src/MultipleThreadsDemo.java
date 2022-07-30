
class NewThread3 implements Runnable {
	
	Thread t;
	String name;
	
	NewThread3(String threadName) {
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
public class MultipleThreadsDemo {

	public static void main(String[] args) {
	
		new NewThread3("One");
		new NewThread3("Two");
		new NewThread3("Three");
		
		try {
			Thread.sleep(10000);
		}
		catch(InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");

	}

}
