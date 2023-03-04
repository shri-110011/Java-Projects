import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {

	public static void main(String[] args) {
		/* Note: This Runnable action run method(here it is the BarAction's run())specified in CyclicBarrier() is 
		 * called on the last thread calling the await().
		 */
		CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
		
		System.out.println("Starting");
		
		new MyThread(cb, "A");
		new MyThread(cb, "B");
		new MyThread(cb, "C");
		new MyThread(cb, "X");
		new MyThread(cb, "Y");
		new MyThread(cb, "Z");
	}

}

class MyThread implements Runnable {
	
	CyclicBarrier cbar;
	String name;
	
	public MyThread(CyclicBarrier c, String n) {
		cbar = c;
		name = n;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		System.out.println(name);
		try {
			/* await() on the CyclicBarrier instance returns the arrival index. The thread which first
			 * entered the barrier will have the arrival index as getParties() - 1 and the one to arrive
			 * last will have arrival index as 0. */
			int num = cbar.await();
			System.out.println("Thread " + name + " arrival index: " + num);
		}
		catch(BrokenBarrierException exec) {
			System.out.println(exec);
		}
		catch(InterruptedException exec) {
			System.out.println(exec);
		}
	}
	
}

class BarAction implements Runnable {

	@Override
	public void run() {
		System.out.println("Inside BarAction on thread: " + Thread.currentThread().getName());
		System.out.println("Barrier Reached!");
	}
	
}
