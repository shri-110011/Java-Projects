package interThreadCommunication;

class Q2 {
	int n;
	
	boolean valueSet = false;
	
	synchronized int get() {
		while(!valueSet)
			try {
				wait();
			}
			catch(InterruptedException e) {
				System.err.println("InterruptedException caught");
			}

		System.out.println("Got: "+n);
		valueSet = false;
		notify();
		
		return n;
	}
	
	synchronized void put(int n) {
		while(valueSet)
			try {
				wait();
			}
			catch(InterruptedException e) {
				System.err.println("InterruptedException caught");
			}
		
		System.out.println("@@1: "+this.n);
		this.n = n;
		valueSet = true;
		System.out.println("Put: "+n);
		notify();
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
			q.put(i++);
			noOfTimes++;
		}
		System.out.println("Producer ran: "+noOfTimes+" times.");
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
			System.out.println("#1 "+q.n);
			int val = q.get();
			System.out.println("#2 "+q.n);
			noOfTimes++;
		}
		System.out.println("Consumer ran: "+noOfTimes+" times.");
		System.out.println("n: "+q.n);
		
	}
}

public class PCFixed {

	public static void main(String[] args) {
		Q2 q = new Q2();
		new Producer2(q);
		new Consumer2(q); 
	}

}
