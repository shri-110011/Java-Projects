import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(3);
		Future<Integer> f;
		Future<Double> f2;
		Future<Integer> f3;
		
		System.out.println("Starting");
		
		f = es.submit(new Sum(10));
		f2 = es.submit(new Hypotenuse(12, 5.0));
		f3 = es.submit(new Factorial(8));
		
		try {
			System.out.println(f.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		es.shutdown();
		System.out.println("Done");
		
	}

}

// Following are three computational threads.
class Sum implements Callable<Integer> {
	int stop;
	
	Sum(int v) {
		stop = v;
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=1; i<=stop; i++) {
			sum += i;
		}
		
		Thread.sleep(30);
		return sum;
	}

}

class Hypotenuse implements Callable<Double> {
	
	double side1, side2;
	
	Hypotenuse(double s1, double s2) {
		side1 = s1;
		side2 = s2;
	}

	@Override
	public Double call() throws Exception {
		return Math.sqrt((side1*side1)+(side2*side2));
	}
	
}

class Factorial implements Callable<Integer> {
	int stop;
	
	Factorial(int v) {
		stop = v;
	}

	@Override
	public Integer call() throws Exception {
		int fact = 1;
		for(int i=2; i<=stop; i++) {
			fact *= i;
		}
		
		Thread.sleep(20);
		return fact;
	}
	
}