import java.util.Scanner;

public class PowerUsingRecursion {
	
	
	//This code can find only integral powers of any number num.
	public static double calPower(double num, double pow) {
		if(pow == 1) {
			return num;
		}
		else {
			return num*calPower(num, pow-1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double num, pow;
		Scanner sc = new Scanner(System.in);
		num = sc.nextDouble();
		pow = sc.nextDouble();
		System.out.println(calPower(num, pow));
	}

}
