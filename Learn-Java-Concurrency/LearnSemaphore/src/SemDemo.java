import java.util.concurrent.Semaphore;

public class SemDemo {
	
	public static void main(String args[]) {
		/* A Semaphore object is a synchronization object that allows synchronization b/w multiple
		 * threads. */
		Semaphore sem = new Semaphore(1);
		
		new IncThread(sem, "A");
		new DecThread(sem, "B");
		
		/* If we remove the acquire() and release() calls on the Semaphore objects in classes IncThread and
		 * DecThread then we see that the results get intermixed. */
	}

}

class Shared {
	static int count = 0;
}

class IncThread implements Runnable {
	
	String name;
	Semaphore sem;
	
	IncThread(Semaphore s, String n) {
		sem = s;
		name = n;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("Starting " + name);
		
		try {
			// First geta a permit
			System.out.println(name + " is waiting for a permit.");
			sem.acquire();
			System.out.println(name + " gets a permit.");
			
			// Now access shared resources
			for(int i=0; i<5; i++) {
				Shared.count++;
				System.out.println(name + ": " + Shared.count);
				
				// Now, allow a context switch if possible
				Thread.sleep(10);
			}
		}
		catch (InterruptedException exec) {
			System.out.println(exec);
		}
		
		// Release the permit
//		sem.release();
	}
}

class DecThread implements Runnable {
	
	String name;
	Semaphore sem;
	
	DecThread(Semaphore s, String n) {
		sem = s;
		name = n;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("Starting " + name);
		
		try {
			// First geta a permit
			System.out.println(name + " is waiting for a permit.");
			sem.acquire();
			System.out.println(name + " gets a permit.");
			
			// Now access shared resources
			for(int i=0; i<5; i++) {
				Shared.count--;
				System.out.println(name + ": " + Shared.count);
				
				// Now, allow a context switch if possible
				Thread.sleep(10);
			}
		}
		catch (InterruptedException exec) {
			System.out.println(exec);
		}
		
		// Release the permit
		sem.release();
	}
}
