import java.util.concurrent.Phaser;

public class PhaserDemo2 {

	public static void main(String[] args) {
		MyPhaser3 phsr = new MyPhaser3(1, 4);
		
		System.out.println("Starting \n");
		
		new MyThread4(phsr, "A");
		new MyThread4(phsr, "B");
		new MyThread4(phsr, "C");
		
		// Wait for the specified number of phases to complete.
		while(!phsr.isTerminated()) {
			phsr.arriveAndAwaitAdvance();
		}
		System.out.println("The Phaser is terminated");
	}

}

/* Extend MyPhaser3 to allow only a specific number of phases to be executed. */
class MyPhaser3 extends Phaser {
	
	int numPhases;
	
	MyPhaser3(int parties, int phaseCount) {
		super(parties);
		numPhases = phaseCount - 1;
	}
	
	protected boolean onAdvance(int p, int regParties) {
		/* This println() statement is for illustration only.
		 * Normally, onAdvance() will not display output. */
		System.out.println("Phase " + p + " completed.\n");
		
		// If all phases have completed, return true
		if(p == numPhases || regParties == 0) return true;
		
		// Otherwise, return false
		return false;
	}
	
}

class MyThread4 implements Runnable {
	Phaser phsr;
	String name;
	
	MyThread4(Phaser p, String n) {
		phsr = p;
		name = n;
		phsr.register();
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(!phsr.isTerminated()) {
			System.out.println("Thread " + name + " Beginning Phase " + phsr.getPhase());
			
			phsr.arriveAndAwaitAdvance();
			
			/* Pause a bit to prevent jumbled output. This is for illustration only.
			 * It is not required for the proper operation of the Phaser.  */
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
}