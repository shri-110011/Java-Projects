package interThreadCommunication;

class Q2 {
	volatile int n;
	
	boolean valueSet = false;
	
	synchronized int get() {
		while(!valueSet)
			try {
				System.out.println("Consumer waiting ...");
				wait();
			}
			catch(InterruptedException e) {
				System.err.println("InterruptedException caught");
			}
		System.out.println("Consumer awakened");
		System.out.println("Got: "+n);
		valueSet = false;
		notify();
		System.out.println("Consumer called notify()");
		return n;
	}
	
	synchronized void put(int n) {
		while(valueSet)
			try {
				System.out.println("Producer waiting ...");
				wait();
			}
			catch(InterruptedException e) {
				System.err.println("InterruptedException caught");
			}
		System.out.println("Producer awakened");
		System.out.println("Before put n is: " + this.n);
		this.n = n;
		valueSet = true;
		System.out.println("Put: " + this.n);
		notify();
		System.out.println("Producer called notify()");
	}
}

class Producer2 implements Runnable {
	Q2 q;
	int noOfTimes;
	
	Producer2(Q2 q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int i=0;
		noOfTimes=0;
		
		while(q.n < 2) {
			q.put(++i);
			noOfTimes++;
		}
		System.out.println("Producer ran: " + noOfTimes + " times.");
	}
}

class Consumer2 implements Runnable {
	Q2 q;
	int noOfTimes;
	
	Consumer2(Q2 q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		int i=0;
		noOfTimes=0;
		
		while(q.n < 2) {
			System.out.println("Iteration " + (noOfTimes+1) + "; Before get() n is: " + q.n);
			int val = q.get();
			System.out.println("After get() n is: " + q.n);
			noOfTimes++;
		}
		System.out.println("Consumer ran: " + noOfTimes + " times.");
		System.out.println("n: " + q.n);
		
	}
}

public class PCFixed {

	public static void main(String[] args) {
		Q2 q = new Q2();
		new Producer2(q);
		new Consumer2(q); 
	}

}
