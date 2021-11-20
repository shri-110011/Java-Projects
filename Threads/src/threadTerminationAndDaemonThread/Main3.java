package threadTerminationAndDaemonThread;

import java.math.BigInteger;

public class Main3 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new LongComputationClass(new BigInteger("5975"), new BigInteger("7197")));
	
		thread.setDaemon(true);
		thread.start();
		
		int timeLimit = 1;
		for(int i=0; i<timeLimit; i++) {
			Thread.sleep(10);
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
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i=i.add(BigInteger.ONE)) {
				result = result.multiply(base);
			}
			return result;
		}
	}

}
