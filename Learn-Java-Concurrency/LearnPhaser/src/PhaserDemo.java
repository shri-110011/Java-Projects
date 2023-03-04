import java.util.concurrent.Phaser;

public class PhaserDemo {

	public static void main(String[] args) {
		/* When we instantiate a Phaser and pass 1 to the constructor as an argument we are self-registering 
		 * this calling thread i.e. the Main thread here. */

		Phaser phsr = new Phaser(1);
		int curPhase;
		
		System.out.println("Starting");
		
		new MyThread(phsr, "A");
		new MyThread(phsr, "B");
		new MyThread(phsr, "C");
		
		/* At this point the total number of registered parties to the Phaser phsr are 4 and thore are:
		 * Main thread, Thread A, Thread B, Thread C */
		
		// Wait for all threads to complete phase one
		curPhase = phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phase " + curPhase + " Complete");
		
		// Wait for all threads to complete phase two
		curPhase = phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phase " + curPhase + " Complete");
		
		// Wait for all threads to complete phase three
		curPhase = phsr.getPhase();
		phsr.arriveAndAwaitAdvance();
		System.out.println("Phase " + curPhase + " Complete");
		
		// At this point all the 3 threads A, B and C have deregistered themselves from the Phaser phsr.
		System.out.println("Registered parties: " + phsr.getRegisteredParties());
		
		// Deregister the main thread
		int num = phsr.arriveAndDeregister();
		System.out.println("Arrival phase number for the thread " + Thread.currentThread().getName() + ": " + num );
		System.out.println("Registered parties: " + phsr.getRegisteredParties());
		
		/* Here the phaser is terminated and when we try to call arriveAndDeregister() on the Phaser instance then 
		 * we would get -ve value. */
		num = phsr.arriveAndDeregister();
		System.out.println("Arrival phase number for the thread " + Thread.currentThread().getName() + ": " + num );
		
		if(phsr.isTerminated()) {
			System.out.println("The phaser is terminated!");
		}
		
	}

}

class MyThread implements Runnable {
	Phaser phsr;
	String name;
	
	public MyThread(Phaser p, String n) {
		phsr = p;
		name = n;
		phsr.register();
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + name +  " Beginning Phase One");
		phsr.arriveAndAwaitAdvance(); //Signal arrival
		
		/* Pause a bit to avoid jumbled output. This is for illustration purpose only and is not required for the
		proper operation of the phaser. */
//		try {
//			Thread.sleep(10);
//		}
//		catch(InterruptedException exc) {
//			System.out.println(exc);
//		}
		
		System.out.println("Thread " + name +  " Beginning Phase Two");
		phsr.arriveAndAwaitAdvance(); //Signal arrival
		
		/* Pause a bit to avoid jumbled output. This is for illustration purpose only and is not required for the
		proper operation of the phaser. */
//		try {
//			Thread.sleep(10);
//		}
//		catch(InterruptedException exc) {
//			System.out.println(exc);
//		}
		
		System.out.println("Thread " + name +  " Beginning Phase Three");
		System.out.println("Thread " + name +  " about to deregister");
		phsr.arriveAndDeregister(); //Signal arrival and deregister
		
	}
	
}
