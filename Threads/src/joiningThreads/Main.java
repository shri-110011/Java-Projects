package joiningThreads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException  {
		List<Long> numbers = Arrays.asList(0L, 1000105L, 23L, 417L);
		List<FactorialThread> factorialThreads = new ArrayList<>();
		
		for(long number: numbers) {
			factorialThreads.add(new FactorialThread(number));
		}
		for(FactorialThread thread: factorialThreads) {
			thread.setDaemon(true);
			thread.start();
		}
		for(Thread thread: factorialThreads) {
			thread.join(100);
		}
//		for(FactorialThread factorialThread: factorialThreads) {
//			if(factorialThread.isFinished()) {
//				Thread.sleep(5000);
//				System.out.println("Factorial of "+ factorialThread.inputNumber+" is: "+factorialThread.getResult());
//			}
//			else {
//				System.out.println("Computation of factorial for "+factorialThread.inputNumber+" is still in progress..."+factorialThread.isFinished());
//			}
//		}
		for(int i=0; i<numbers.size(); i++) {
			FactorialThread factorialThread = factorialThreads.get(i);
//			Thread.sleep(1000);
			if(factorialThread.isFinished()) {
				System.out.println("Factorial of "+ factorialThread.inputNumber+" is: "+factorialThread.getResult());
			}
			else {
				System.out.println("Computation of factorial for "+factorialThread.inputNumber+" is still in progress...");
			}
		}
	}
	public static class FactorialThread extends Thread {
		private long inputNumber;
		private BigInteger result;
		private boolean isFinished;
		public FactorialThread(long n) {
			this.inputNumber = n;
		}
		public void run() {
			this.result = factorial(inputNumber);
			this.isFinished = true;
//			System.out.println("##"+ inputNumber+" "+result+" "+isFinished());
		}
		public BigInteger factorial(long n) {
			BigInteger tempResult = BigInteger.ONE;
			for(long i=n; i>1 ; i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
			}
			return tempResult;
		}
		public boolean isFinished() {
			return isFinished;
		}
		public BigInteger getResult() {
			return result;
		}
	}
}
