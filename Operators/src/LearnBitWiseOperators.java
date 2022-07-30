
public class LearnBitWiseOperators {

	public static void main(String[] args) {
		int a = 5,  b = 6;
		
		//Bitwise NOT
		//5 in binary is 0000 0000 0000 0000 0000 0000 0000 0101
		//After bitwise NOT we get: 1111 1111 1111 1111 1111 1111 1111 1010 which is a negative number so we take the 2's
		//complement of it and we get 0000 0000 0000 0000 0000 0000 0000 0110 which is 6 but we need to take the -ve
		//of this number so it is -6.
		// In general ~a = -(a+1)
		System.out.println(~a);
		
		//Bitwise AND
		//5 in binary is 0101
		//6 in binary is 0110
		//After bitwise AND we get: 0100 which is 4 in decimal
		System.out.println(a&b);
		
		//Bitwise OR
		//After bitwise OR we get: 0111 which is 7 in decimal
		System.out.println(a|b);
		
		//Bitwise XOR
		//After bitwise XOR we get: 0011 which is 3 in decimal
		System.out.println(a^b);
		
	}

}
