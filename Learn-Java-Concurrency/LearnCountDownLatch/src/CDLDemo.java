import java.util.concurrent.CountDownLatch;

public class CDLDemo {
	
	public static void main(String args[]) {
		CountDownLatch cdl = new CountDownLatch(5);
		
		System.out.println("Starting");
		
		new MyThread(cdl);
		
		try {
			cdl.await();
		}
		catch(InterruptedException exec) {
			System.out.println(exec);
		}
		
		System.out.println("Done");
	}

}

class MyThread implements Runnable {

	CountDownLatch latch;
	
	public MyThread(CountDownLatch cdl) {
		latch = cdl;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(i);
			latch.countDown(); // decrement count
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
