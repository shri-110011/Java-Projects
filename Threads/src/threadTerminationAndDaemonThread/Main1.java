package threadTerminationAndDaemonThread;

public class Main1 {

	public static void main(String[] args) {
		Thread thread = new Thread(new BlockingTask());
		thread.start();
		
		thread.interrupt();
	}
	
	public static class BlockingTask implements Runnable {

		@Override
		public void run() {
			//Here the thread code is executing a method that throws an
			//InterruptedException.
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				System.out.println("Exiting blocking thread");
//				return;
			}
		}
		
	}

}
