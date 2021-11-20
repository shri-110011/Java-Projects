package multithreading;

public class LearnNamingAndIdOfThreads extends Thread {
	
	public void run(){  
		System.out.println("Thread Id: "+Thread.currentThread().getId()+" Thread Priority: "+Thread.currentThread().getPriority()+" Thread Name: "+Thread.currentThread().getName()+" running...");  
		
	}  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LearnNamingAndIdOfThreads t1 = new LearnNamingAndIdOfThreads();
		LearnNamingAndIdOfThreads t2 = new LearnNamingAndIdOfThreads();
		LearnNamingAndIdOfThreads t3 = new LearnNamingAndIdOfThreads();
		LearnNamingAndIdOfThreads t4 = new LearnNamingAndIdOfThreads();
		
		
		t1.start();
		try{  
			t1.join();
			System.out.println("#");
			for(int i=0; i<5; i++) {
				System.out.println(i);
			}
			t2.setName("Movement Tracker");
		}catch(Exception e){System.out.println(e);}  
		t2.start();
		t3.start();
		//Note: isAlive() is used to check if a thread has died or not.
		//We can't start a thread that has died if we do so then we get an IllegalThreadStateException.
		System.out.println(t1.isAlive());
//		t1.start();
		t4.start();
	}

}
