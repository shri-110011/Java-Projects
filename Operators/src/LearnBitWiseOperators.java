
public class LearnBitWiseOperators {

	public static void main(String[] args) {
		int a = 5,  b = 6;
		//Bitwise And
		//5 in binary is 0101
		//6 in binary is 0110
		//After bitwise And we get: 0100 which is 4 in decimal
		System.out.println(a&b);
		
		//Bitwise Or
		//After bitwise Or we get: 0111 which is 7 in decimal
		System.out.println(a|b);
		
		//Bitwise XOR
		//After bitwise XOR we get: 0011 which is 3 in decimal
		System.out.println(a^b);
		
	}

}
