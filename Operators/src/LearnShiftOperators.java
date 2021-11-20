
public class LearnShiftOperators {

	public static void main(String[] args) {
		//Right Shift
		// Note: From here on I have used just 4 bits or 8 bits to keep things simple to read 
		// but internally java uses 32 bits or 4 bytes to store an int 
		int a = 4;//a in binary is 0100
		System.out.println(a+" after right shift with highest (sign) bit on the left-hand sideis: "
		+(a>>1));// Output after right shift is: 0010
		System.out.println(a+" after left shift is: "+(a<<1));// Output after left shift is: 1000
		System.out.println(a+" after right shift by filling with zero bits on the left-hand sideis: "
		+(a>>>1));// Output after right shift is: 0010
		
		int b = -8;//b in binary is 1011(1's complement of magnitude b) + 0001 = 1100
		System.out.println(b+" after right shift with highest (sign) bit on the left-hand sideis: "
		+(b>>1));// Output after right shift is: 1110
		// This 1110 is in 2's complement form so we know the result is -ve and to get its magnitude
		// we need to find the 2's complement of 1110 which is:
		// 0001 + 0001 = 0010 which is 2 in decimal
		// So our final result is -2
		System.out.println(b+" after left shift is: "+(b<<1));
		System.out.println(b+" after right shift by filling with zero bits on the left-hand sideis: "
		+(b>>1));
		
		
		System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(-2));//Output: 11111111111111111111111111111110
		System.out.println(Integer.toBinaryString(-5));
		// 5 in binary is 00000101
		// For -5 to be represented in binary we take the 2's Complement of 5
		// 1's Complement of 5 is: 11111010
		// 11111010 + 00000001 = 11111011
//		System.out.println(a<<1);// Output after left shift is: 1000
//		System.out.println(a>>>1);// Output after right shift is: 0010
		
		
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
