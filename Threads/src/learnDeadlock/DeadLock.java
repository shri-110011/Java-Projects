package learnDeadlock;

class A {
	synchronized void foo(B b) {
		String name = Thread.currentThread().getName();
		
		System.out.println(name+" entered A.foo");
		
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			System.out.println("A Interrupted");
		}
		
		System.out.println(name+" trying to call B.last()");
		b.last();
	}
	synchronized void last() {
		System.out.println("Inside A.last()");
	}
	
}

class B {
	synchronized void bar(A a) {
		String name = Thread.currentThread().getName();
		
		System.out.println(name+" entered B.foo");
		
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			System.out.println("B Interrupted");
		}
		
		System.out.println(name+" trying to call A.last()");
		a.last();
		
	}
	
	synchronized void last() {
		System.out.println("Inside B.last()");
	}
}

public class DeadLock implements Runnable {
	A a = new A();
	B b = new B();
	
	DeadLock() {
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this, "Racing Thread");
		t.start();
		
		a.foo(b);// getting lock of a in this thread
		System.out.println("Back in main thread");
	}

	public static void main(String[] args) {
		new DeadLock();
	}

	public void run() {
		b.bar(a);// getting lock of a in other thread
		System.out.println("Back in other thread");
	}

}
