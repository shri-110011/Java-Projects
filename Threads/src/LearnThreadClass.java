
class Hi extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println("Hi");
		}
	}
}

class Hello extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println("Hello");
		}
	}
}

public class LearnThreadClass {

	public static void main(String[] args) {
		Hi obj1 = new Hi();
		Hello obj2 = new Hello();

		obj1.start();
		obj2.start();

	}

}
