package learnSupendResumeStopThread;

//This program shows the modern way of suspending, resuming and stopping threads.
class NewThread2 implements Runnable{
	String name;
	Thread t;
	boolean suspendFlag;
	
	NewThread2(String threadName) {
		name = threadName;
		t = new Thread(this, name);
		System.out.println("New Thread: "+t);
		suspendFlag = false;
		t.start();
	}

	public void run() {
		try {
			for(int i=15; i>0; i--) {
				System.out.println(name+": "+i);
				Thread.sleep(200);
				
				synchronized (this) {
					if(suspendFlag) {
						wait();
					}
				}
			}
		}
		catch(InterruptedException e) {
			System.out.println(name+" Interrupted");
		}
		System.out.println(name+" exiting.");
	}
	
	synchronized void mySuspend() {
		suspendFlag = true;
	}
	
	synchronized void myResume() {
		suspendFlag = false;
		notify();
	}
	
	
}
public class SuspendResumeModernWay {
	public static void main(String[] args) {
		NewThread2 obj1 = new NewThread2("One");
		NewThread2 obj2 = new NewThread2("Two");
		
		try {
			Thread.sleep(1000);
			obj1.mySuspend();
			System.out.println("Suspending thread One");
			Thread.sleep(1000);
			obj1.myResume();
			System.out.println("Resuming thread One");
			obj2.mySuspend();
			System.out.println("Suspending thread Two");
			Thread.sleep(1000);
			obj2.myResume();
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
