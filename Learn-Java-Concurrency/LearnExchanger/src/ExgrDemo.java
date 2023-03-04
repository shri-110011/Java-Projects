import java.util.concurrent.Exchanger;

public class ExgrDemo {

	public static void main(String[] args) {
		Exchanger<String> exgr = new Exchanger<>();
		
		new MakeString(exgr);
		new UseString(exgr);
	}

}

// A thread that constructs a string.
class MakeString implements Runnable {
	
	Exchanger<String> ex;
	String str;
	
	public MakeString(Exchanger<String> c) {
		ex = c;
		str = new String();
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		char ch = 'A';
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<5; j++) {
				str += ch++;
			}
			
			try {
				str = ex.exchange(str);
				System.out.println("Inside MakeString: run(): str: |" + str + "|");
			}
			catch(InterruptedException exc) {
				System.out.println(exc);
			}
		}
	}
}

//A thread that uses a string.
class UseString implements Runnable {
	
	Exchanger<String> ex;
	String str;
	
	public UseString(Exchanger<String> c) {
		ex = c;
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		
		for(int i=0; i<3; i++) {
			try {
				str = ex.exchange(new String());
				System.out.println("Got: " + str);
			}
			catch(InterruptedException exc) {
				System.out.println(exc);
			}
		}
	}
}
