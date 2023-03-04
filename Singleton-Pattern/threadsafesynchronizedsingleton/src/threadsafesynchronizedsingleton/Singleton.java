package threadsafesynchronizedsingleton;


public class Singleton {
	private static Singleton uniqueInstance;
	
	private int val;
	 
	// other useful instance variables here
 
	private Singleton() {}
 
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
	
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
 
	// other useful methods here
	public String getDescription() {
		return "I'm a thread safe Singleton!";
	}
}
