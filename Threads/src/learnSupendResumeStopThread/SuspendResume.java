package learnSupendResumeStopThread;

//This program shows the old way of suspending, resuming and stopping threads.
class NewThread implements Runnable{
	String name;
	Thread t;
	
	NewThread(String threadName) {
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New Thread: "+t);
		t.start();
	}

	public void run() {
		try {
			for(int i=15; i>0; i--) {
				System.out.println(name+": "+i);
				Thread.sleep(200);
			}
		}
		catch(InterruptedException e) {
			System.out.println(name+" Interrupted");
		}
		System.out.println(name+" exiting.");
	}
	
}

public class SuspendResume {

	public static void main(String[] args) {
		NewThread obj1 = new NewThread("One");
		NewThread obj2 = new NewThread("Two");
		
		try {
			Thread.sleep(1000);
			obj1.t.suspend();
			System.out.println("Suspending thread One");
			Thread.sleep(1000);
			obj1.t.resume();
			System.out.println("Resuming thread One");
			obj2.t.suspend();
			System.out.println("Suspending thread Two");
			Thread.sleep(1000);
			obj2.t.resume();
			System.out.println("Resuming thread Two");
		}
		catch(InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		
		try {
			System.out.println("Waiting for threads to finish.");
			obj1.t.join();
			obj2.t.join();
		}
		catch(InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread exiting.");
	}

}
