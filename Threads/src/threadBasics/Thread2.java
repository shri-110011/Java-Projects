package threadBasics;

public class Thread2 {

	public static void main(String[] args) {
		Thread t = new NewThread();
		t.start();
	}
	private static class NewThread extends Thread {
		public void run() {
			//Note: When we use a class that extends the Thread class to create a thread we don't 
			//have to write Thread.currentThread().getName() rather we can write just this.getName()
			//whereas this was not possible when we were passing the object of a class that implements 
			//the runnable interface to the thread class constructor.
			System.out.println("Hello from thread "+this.getName());
		}
	}
}
