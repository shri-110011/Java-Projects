package threadunsafesingleton;

public class Singleton {
	private static Singleton uniqueInstance;
	
	private int val;
	 
	private Singleton() {}
 
	public static Singleton getInstance() {
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
		return "I'm a classic Singleton!";
	}
}
