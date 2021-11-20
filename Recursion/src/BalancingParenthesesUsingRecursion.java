import java.util.Scanner;

public class BalancingParenthesesUsingRecursion {

	public static int balanceParentheses(int op, int cl, int n, String str) {
//		System.out.println("op:"+op+", cl:"+cl+", str:"+str);
		if(op == n && cl == n) {
			System.out.println(str);
			return 0;
		}
		else {
			if(op == cl) {
				balanceParentheses(op+1, cl, n, str.concat("("));
			}
			if(op > cl && op < n) {
				balanceParentheses(op+1, cl, n, str.concat("("));
			}
			if(op > cl) {

				balanceParentheses(op, cl+1, n, str.concat(")"));
			}
			return 0;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		balanceParentheses(0, 0, n, "");
		sc.close();
	}

}
