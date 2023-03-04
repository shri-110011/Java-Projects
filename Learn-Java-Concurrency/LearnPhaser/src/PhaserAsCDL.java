import java.util.concurrent.Phaser;

public class PhaserAsCDL {
	
	public static void main(String args[]) {
		/* To use Phaser as a CountDownLatch we must know in advance the number of events that must occur before 
		 * the latch opens. */
		Phaser phsr = new Phaser(2);
		
		System.out.println("Using Phaser as a Count Down Latch");
		System.out.println("Starting");
		
		new MyThread2(phsr);
		
//		new MyThread2(phsr);
		
		/* This awaitAdvance() on the Phaser instance is blocking. 
		 * And it will wait until the arrive() or any of its other versions are
		 * called twice. */
		phsr.awaitAdvance(phsr.getPhase());
		
		System.out.println("Done");
		
		if(phsr.isTerminated()) {
			System.out.println("The phaser is terminated!");
		}
	}

}

class MyThread2 implements Runnable {
	
	Phaser phsr;
	
	MyThread2(Phaser p) {
		phsr = p;
		
		/* We should not be registering the MyThread2 instance here because of the following reasons:
		 * 1. We won't be able to know after exactly after how many events the latch will open.
		 * 2. The one shot action of the CountDownLatch won't be obtained.  */
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		for(int i=0; i<2; i++) {
			System.out.println(i);
			
			/* This arriveAndDeregister() on the Phaser instance is non-blocking and here it works like the countDown() on the
			 * CountDownLatch instance. */
			phsr.arriveAndDeregister();
			
			System.out.println("phaseIndex: " + phsr.getPhase() + " unarrivedParties: " + phsr.getUnarrivedParties());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}