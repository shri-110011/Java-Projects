package multithreading;

public class LearnJoiningOfThreads extends Thread {
	public void run(){  
		for(int i=1;i<=5;i++){  
		    try{  
		    	Thread.sleep(500);  
		    }catch(Exception e){System.out.println(e);}  
	    	System.out.println(i+" "+Thread.currentThread().getName());  
	    }  
	}  
	public static void main(String args[]){ 
		// TODO Auto-generated method stub
		LearnJoiningOfThreads t1=new LearnJoiningOfThreads();  
		LearnJoiningOfThreads t2=new LearnJoiningOfThreads();  
		LearnJoiningOfThreads t3=new LearnJoiningOfThreads();  
		t1.start();  
		//The join() method waits for a thread to die. In other words, it causes the currently 
		//running threads to stop executing until the thread it joins with completes its task.
		try{  
			t1.join();  
		}catch(Exception e){System.out.println(e);}  
		t2.start();  
		t3.start(); 
		  
		 
	 }  
}
