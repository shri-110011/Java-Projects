
public class LearnStartingThreadTwice extends Thread {
	public void run(){  
		System.out.println("running...");  
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LearnStartingThreadTwice t1=new LearnStartingThreadTwice();  
		//Note: On starting a thread twice we will get an IllegalThreadStateException.
		t1.start();  
//		t1.start();
		System.out.println(t1.isAlive());
	}

}
