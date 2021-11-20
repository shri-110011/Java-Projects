
public class LearnSwitch {
	static void m1() {
		final int x;
	}

	public static void main(String[] args) {
		int x = 3, y=2;
		final int z = 5;
		int a = 2;
		switch(y) {
			default: 
				System.out.println("Default");
			case 1+1:
				System.out.println("1");
			break;
			case z:
				System.out.println("2");
		}
		
	}

}
