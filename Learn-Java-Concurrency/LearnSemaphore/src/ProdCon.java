import java.util.concurrent.Semaphore;

public class ProdCon {
	
	public static void main(String args[]) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}

}

class Q {
	int n;
	
	static Semaphore semProd = new Semaphore(1);
	static Semaphore semCon = new Semaphore(0);
	
	void get() {
		try {
			semCon.acquire();
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		System.out.println("Got " + this.n);
		
		semProd.release();
	}
	
	void put(int n) {
		try {
			semProd.acquire();
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		this.n = n;
		System.out.println("Put " + n);
		
		semCon.release();
	}
	
}

class Producer implements Runnable {
	Q q;
	
	Producer(Q q) {
		this.q = q;
		new Thread(this, "Producer").start();;
	}
	
	@Override
	public void run() {
		for(int i=0; i<20; i++) {
			q.put(i);
		}
	}
}


class Consumer implements Runnable {
	Q q;
	
	Consumer(Q q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}
	
	@Override
	public void run() {
		for(int i=0; i<20; i++) {
			q.get();
		}
	}
}
