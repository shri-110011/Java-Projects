package joiningThreads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculation {
	public static void main(String[] args) throws InterruptedException {
		BigInteger base1 = new BigInteger("23"); 
		BigInteger power1 = new BigInteger("381"); 
		BigInteger base2 = new BigInteger("31"); 
		BigInteger power2 = new BigInteger("372"); 
		ComplexCalculation cc = new ComplexCalculation();
		System.out.println(cc.calculateResult(base1, power1, base2, power2));
		
	}
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result = BigInteger.ZERO;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        List<PowerCalculatingThread> powerCalculatingThreads = new ArrayList<>();
        System.out.println("##");
        
        powerCalculatingThreads.add(new PowerCalculatingThread(base1, power1));
        powerCalculatingThreads.add(new PowerCalculatingThread(base2, power2));
      
        for(PowerCalculatingThread powerCalculatingThread: powerCalculatingThreads) {
        	powerCalculatingThread.start();
        }
        for(PowerCalculatingThread powerCalculatingThread: powerCalculatingThreads) {
        	powerCalculatingThread.join();
        }
        for(PowerCalculatingThread powerCalculatingThread: powerCalculatingThreads) {
        	result  = result.add(powerCalculatingThread.getResult());
//        	System.out.println("@"+result);
        }
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
        	this.result = pow(base, power);
        	System.out.println(base+"^"+power+" = "+result);
        }
        
        public BigInteger pow(BigInteger base, BigInteger power) {
        	BigInteger tempResult = BigInteger.ONE;
        	for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!= 0; i=i.add(BigInteger.ONE)) {
        		tempResult = tempResult.multiply(base);
        	}
        	return tempResult;
        }
    
        public BigInteger getResult() { return result; }
    }
}
