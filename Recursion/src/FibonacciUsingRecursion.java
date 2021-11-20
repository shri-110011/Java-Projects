import java.util.Scanner;

public class FibonacciUsingRecursion {
	
	public static int calculateNthFibonacci(int n) {
		if(n == 1) {
			return 0;
		}
		if(n == 2) {
			return 1;
		}
		else {
			return calculateNthFibonacci(n-1) + calculateNthFibonacci(n-2);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=1; i<=n; i++) {
			System.out.println(calculateNthFibonacci(i));
		}
		sc.close();
	}
}
