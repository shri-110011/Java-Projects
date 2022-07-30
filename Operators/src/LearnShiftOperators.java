
public class LearnShiftOperators {

	public static void main(String[] args) {
		//Right Shift
		// Note: From here on I have used just 4 bits or 8 bits to keep things simple to read 
		// but internally java uses 32 bits or 4 bytes to store an int 
		int a = 4;//a in binary is 0100
		System.out.println(a+" after right shift with highest (sign) bit on the left-hand side is: "
		+(a>>1));// Output after right shift is: 0010
		System.out.println(a+" after left shift is: "+(a<<1));// Output after left shift is: 1000
		System.out.println(a+" after right shift by filling with zero bits on the left-hand side is: "
		+(a>>>1));// Output after right shift is: 0010
		
		int b = -8;//b in binary is 
		// 1111 1111 1111 1111 1111 1111 1111 0111(1's complement of magnitude b) + 0001 = 
		// 1111 1111 1111 1111 1111 1111 1111 1000
		System.out.println(b+" after right shift with highest (sign) bit on the left-hand side is: "
		+(b>>1));// Output after right shift is: -4 = 1111 1111 1111 1111 1111 1111 1111 1100
		// This 1111 1111 1111 1111 1111 1111 1111 1100 is in 2's complement form so we know the result is -ve and 
		// to get its magnitude we need to find the 2's complement of 1111 1111 1111 1111 1111 1111 1111 1100 which is:
		// 0000 0000 0000 0000 0000 0000 0000 0011 + 0001 = 0000 0000 0000 0000 0000 0000 0000 0100 which is 2 in decimal
		// So our final result is -4
		System.out.println(b+" after left shift is: "+(b<<1));
		System.out.println(b+" after right shift by filling with zero bits on the left-hand side is: "
		+(b>>>1));
		
		System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(-2));//Output: 1111 1111 1111 1111 1111 1111 1111 1110
		System.out.println(Integer.toBinaryString(-5));
		// 5 in binary is 0000 0101
		// For -5 to be represented in binary we take the 2's Complement of 5
		// 1's Complement of 5 is: 1111 1010
		// 1111 1010 + 0000 0001 = 1111 1011
		System.out.println(5<<1);// Output after left shift is: 10
		System.out.println(5>>>1);// Output after right shift is: 2
		
		
		String str="11111111111111111111111111111110";
		System.out.println(str.length());
		
		System.out.println(0x00000085 & 455);
		// 0x00000085 is hexadecimal equivalent of 16*8+5 = 133
		System.out.println("455 in hexadecimal is: "+Integer.toHexString(455));//Output is 1c7
		//0x00000085 & 1c7 is:
		// 0000 1000 0101 -> 85 in hex
		// 0001 1100 0111 -> 1c7 in hex
		// 0000 1000 0101 -> 85 in hex
		
		System.out.println(Integer.toHexString((0x1a4d5185 & 0x00ff0000)>>16));
		System.out.println((0x1a4d5185 & 0x00ff0000)>>16);

	}

}
