import java.util.concurrent.Phaser;

public class PhaserAsCyclicBarrier {

	public static void main(String[] args) {
		MyPhaser2 phsr = new MyPhaser2(3, new BarAction());
		
		System.out.println("Starting");
		
		new MyThread3(phsr, "A");
		new MyThread3(phsr, "B");
		/* arriveAndAwaitAdvance() is to be called on Main thread because otherwise 
		 * creation of more threads while creating MyThread3 instances may cause in 
		 * calling of arriveAndAwaitAdvance() while the onAdvance() is being 
		 * executed as a result of completion of a previous phase, and this would
		 * cause IllegalStateException. */
		phsr.arriveAndAwaitAdvance();
		
		new MyThread3(phsr, "C");
		new MyThread3(phsr, "D");
		phsr.arriveAndAwaitAdvance();

	}

}

class MyPhaser2 extends Phaser {
	
	Runnable runnable;
	int partiesCount1, partiesCount2;
	
	public MyPhaser2(int p, Runnable r) {
		super(p);
		partiesCount1 = p;
		partiesCount2 = p;
		runnable = r;
	}

//	@Override
//	public int awaitAdvance(int phase) {
//		partiesCount1--;
//		
//		System.out.println("Inside MyPhaser2: awaitAdvance(): on thread: " + Thread.currentThread().getName());
//		
//		if(partiesCount1 == 0) {
//			partiesCount1 = partiesCount2;
//			
//			for(int i=0; i<partiesCount1; i++) {
//				arrive();
//			}
//		}
//		
//		return super.awaitAdvance(phase);
//	}
//
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
//		System.out.println("Inside MyPhaser2: onAdvance(): on thread: " + Thread.currentThread().getName());
		
		runnable.run();
		
//		System.out.println("phaseIndex: " + getPhase() + " unarrivedParties: " + getUnarrivedParties());
//		System.out.println("Registered parties: " + getRegisteredParties());
		
		if(registeredParties == 0) return true;
		else return false;
	}

}

class MyThread3 implements Runnable {
	
	Phaser phsr;
	String name;
	
	public MyThread3(Phaser p, String n) {
		phsr = p;
		name = n;
		
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		
		// Simulating some delay caused because of some task.
		try {
			System.out.println("Thread " + name + " doing some work");
			Thread.sleep(10);
		}
		catch(InterruptedException exc) {
			exc.printStackTrace();
		}
		
		phsr.arriveAndAwaitAdvance();
		
		System.out.println("Thread " + name + " completed execution");

	}
	
}


class BarAction implements Runnable {

	@Override
	public void run() {
		System.out.println("Inside BarAction on thread: " + Thread.currentThread().getName());
		System.out.println("Barrier Reached!");
	}
	
}