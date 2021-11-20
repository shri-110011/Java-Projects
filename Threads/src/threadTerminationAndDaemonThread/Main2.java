package threadTerminationAndDaemonThread;

import java.math.BigInteger;

public class Main2 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new LongComputationClass(new BigInteger("4975"), new BigInteger("891")));
	
		thread.start();
		
		int timeLimit = 1;
		for(int i=0; i<timeLimit; i++) {
			Thread.sleep(1);
		}
		thread.interrupt();
		System.out.println("##");
	}
	
	public static class LongComputationClass implements Runnable {
		private BigInteger base;
		private BigInteger power;
		
		public LongComputationClass(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}
		@Override
		public void run() {
			System.out.println("Inside run()");
			System.out.println(base+"^"+power+" = "+ pow(base, power));
		}
		
		public BigInteger pow(BigInteger base, BigInteger power) {
			BigInteger result = BigInteger.ONE;
			System.out.println(result);
			int j=0;
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i=i.add(BigInteger.ONE)) {
				//Here we are explicitly checking whether the 
				//interrupt signal was sent to this thread.
//				System.out.println(i);
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Prematurely computation got interrupted");
					return BigInteger.ZERO;
				}
				result = result.multiply(base);
				j++;
				if(j==8741) {
					System.out.println(i+"\n"+result);
				}
			}
			return result;
		}
	}

}
