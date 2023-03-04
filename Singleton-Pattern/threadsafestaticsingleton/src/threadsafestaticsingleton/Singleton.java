package threadsafestaticsingleton;

public class Singleton {
	private static Singleton uniqueInstance = new Singleton();
	
	private int val;
 
	private Singleton() {}
 
	public static Singleton getInstance() {
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
		return "I'm a statically initialized Singleton!";
	}
}
