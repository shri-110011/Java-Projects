
// This class contain methods that will help to perform the following actions:
// Convert integer to binary string | public String convertToBinary(int num)
// Convert string representation of an integer to binary string | public String convertToBinary(String num)
// Convert double to binary string | public String convertToBinary(double num)
// Convert binary string to decimal | public double convertBinStringToDecimal(String str)
// Get 1's complement of a binary string |public String findOnesComplement(String str)
// Get 1's complement of an integer | public String getOnesComplement(int num)
// Get 2's complement of a binary string | public String findTwosComplement(String str)
// Get 2's complement of an integer | public String getTwosComplement(int num)
// Get integer part of a double | public int getIntegerPart(double num) {
// Get fractional part of a double | public double getFractionalPart(double num)

public class NumberSystem {

	public String convertToBinary(int num) {
		String binaryString="";
		boolean isNegative = false;

		if(num == 0) return "0";

		//This will change the -ve number into a +ve number
		if(num < 0) {
			isNegative = true;
			num = Math.abs(num);
		}
		
		//This will find the binary equivalent of the magnitude of num which is an integer in this case.
		while(num>0) {
			if(num%2 == 0) {
				binaryString+="0";
			}
			else {
				binaryString+="1";
			}
			num/=2;
		}
		
		if(!isNegative) {
			//reversing the binaryString's content and storing the result in binaryString
			binaryString = new StringBuffer(binaryString).reverse().toString();
			return binaryString;
		}
		else {
			//reversing the binaryString's content and storing the result in binaryString
			binaryString = new StringBuffer(binaryString).reverse().toString();
			return findTwosComplement(binaryString);
		}
	}
	
	public String convertToBinary(String num) {
		try {
			return convertToBinary(Integer.parseInt(num));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String convertToBinary(double num) {
		int intPart = getIntegerPart(num);
		double fracPart = getFractionalPart(num);
		
		//binAbsIntPart is the binary representation of the absolute value of integer part.
		String binAbsIntPart = (num<0)?convertToBinary(Math.abs(intPart)):convertToBinary(intPart);
		
		//binAbsFracPart is the binary representation of the absolute value of fractional part.
		String binFracPart = findBinaryOfFractionalPart(Math.abs(fracPart));
		
//		System.out.println("binAbsIntPart: "+ binAbsIntPart);
//		System.out.println("binFracPart: "+ binFracPart);
		
		//Here we are taking the 2's complement of the binary representation of num in case when num is -ve.  
		if(num<0) {
			String concatStr = binAbsIntPart+binFracPart;
				
			String result = new StringBuffer(findTwosComplement(concatStr)).toString();
			
			return result;
		}
		
		return binAbsIntPart+binFracPart;
	}
	
	public String findTwosComplement(String str) {
		String onesComplementString = findOnesComplement(str);
		String twosComplementString = "";
		
		byte carry = 1;
		for(int i=onesComplementString.length()-1; i>=0; i--) {
			if(onesComplementString.charAt(i) == '1' && carry == 1) {
				twosComplementString+="0";
			}
			else if(onesComplementString.charAt(i) == '1' && carry == 0) {
				twosComplementString+="1";
			}
			else if(onesComplementString.charAt(i) == '0' && carry == 1) {
				twosComplementString+="1";
				carry = 0;
			}
			else if(onesComplementString.charAt(i) == '0' && carry == 0) {
				twosComplementString+="0";
			}
			else {
				twosComplementString+=".";
			}
		}
		
		twosComplementString = new StringBuffer(twosComplementString).reverse().toString();
		
//		System.out.println("twosComplementString: "+twosComplementString);
		
		return twosComplementString;
	}
	
	public String findOnesComplement(String str) {
		if(!checkIfValidBinaryString(str)) {
			throw new IllegalArgumentException("The input binary string: "+str+" is invalid! The input binary string "
					+ "should consists of just '0' and '1'.");
		}
		
		boolean isFloatingPointBinaryString = false;
		
		isFloatingPointBinaryString = str.contains(".");
		
		int binIntPartLen = str.indexOf(".");
		
		
		String onesComplementString = "";
		
		//Appending extra 1's to the beginning of the passed binary string so as to make the binary string length 32. 
		//Also this addition of 1's is sort of like partially taking the ones complement if we consider the 32 bit 
		//representation of str.
		
//		System.out.println(isFloatingPointBinaryString);
		if(!isFloatingPointBinaryString) {
			for(int i=0; i<32-str.length(); i++) {
				onesComplementString+="1";
			}
		}
		else {
			for(int i=0; i<32-binIntPartLen; i++) {
				onesComplementString+="1";
			}
		}
	
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='0') {
				onesComplementString+="1";
			}
			else if(str.charAt(i)=='1') {
				onesComplementString+="0";
			}
			else {
				onesComplementString+=".";
			}
			
		}
		
//		System.out.println("onesComplementString: "+onesComplementString);
		
		return onesComplementString;
	}
	
	public String getOnesComplement(int num) {
		return findOnesComplement(convertToBinary(num));
	}
	
	public String getTwosComplement(int num) {
		return findTwosComplement(convertToBinary(num));
	}
	
	public double convertBinStringToDecimal(String str) {
		if(!checkIfValidBinaryString(str)) {
			throw new IllegalArgumentException("The input binary string: "+str+" is invalid!");
		}
		
		int binIntPartLen = str.indexOf(".");
		
		if(binIntPartLen>32) {
			throw new IllegalArgumentException("\nInput string: "+str+" \nhas integer part having more than 32 bits whereas "
					+ "only 32 bits are allowed for the integer part.");
		}
		
		double decimalNumber = 0;
		boolean isNegative = false;
		boolean isFloatingPointNumber = str.contains(".");
		
		
		if(!isFloatingPointNumber) {
			//We are finding if the binary string in str is -ve or not by looking at its MSB. 
			if(str.length() == 32) {
				isNegative = str.charAt(0)=='1'?true:false;
			}
		}
		else {
			if(binIntPartLen == 32) {
				isNegative = str.charAt(0)=='1'?true:false;
			}
		}
		
		
		//If the binary string in str represents a -ve integer we are taking its 2's complement to get 
		// the unsigned integer.
		if(isNegative) {
			str = findTwosComplement(str);
		}
		
		//This if block will be entered when the input binary string represents an integer.
		if(!isFloatingPointNumber) {
			for(int i=str.length()-1; i>=0; i--) {
				if(str.charAt(i) == '1')
					decimalNumber += Math.pow(2, str.length()-1-i);
			}
		}
		else {
			
//			System.out.println("binAbsIntPartLen:"+binIntPartLen);
//			System.out.println("length of str:"+str.length());
			
			//Here we are finding decimal equivalent for the integer part of the binary string.
			for(int i=binIntPartLen-1; i>=0; i--) {
				if(str.charAt(i) == '1')
					decimalNumber += Math.pow(2, binIntPartLen-1-i);
			}
			
//			System.out.println("decimalNumber:"+decimalNumber);
			
			//Here we are finding decimal equivalent for the fractional part of the binary string.
			for(int i=binIntPartLen+1; i<str.length(); i++) {
				if(str.charAt(i) == '1') {
					decimalNumber += Math.pow(2, binIntPartLen-i);
				}
			}
		}
		
		if(!isNegative) return decimalNumber;
		else return decimalNumber*(-1);
	}
	
	public int getIntegerPart(double num) {
		if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
			throw new IllegalArgumentException("The input number should lie b/w 2^31-1 to -2^31.");
		}
		return (int)num;
	}
	
	public double getFractionalPart(double num) {
		String numString = Double.toString(num);
		String fracPart = num>0?"0.":"-0.";
		int exponent = 0;
//		System.out.println(num);
		
		
		if(numString.contains("E")) {
			exponent = Integer.parseInt(numString.substring(numString.indexOf("E")+1));
//			System.out.println("exponent: "+exponent);
			if(exponent > 0) {
				int count = 0;
				for(int i=numString.indexOf("."); i<numString.indexOf("E"); i++) {
					if(count>exponent) {
						fracPart+=numString.charAt(i);
					}
					count++;
				}
			}
			else {
				for(int i=0; i<Math.abs(exponent)-1; i++) {
					fracPart += "0";
				}
				for(int i=0; i<numString.indexOf("E"); i++) {
					if(numString.charAt(i) != '.' && numString.charAt(i) != '-') fracPart+= numString.charAt(i);
				}
			}
		}
		else {
			fracPart += numString.substring(numString.indexOf(".")+1);
		}
//		System.out.println(fracPart);
		return Double.parseDouble(fracPart);
	}
	
	private String findBinaryOfFractionalPart(double num) {
		
		if(num<=-1 || num>=1) {
			throw new IllegalArgumentException("The input argument must lie b/w -1 and +1 and given value is: "+num);
		}
		
		String result = ".";
		int i=0;
		boolean isNegative = num<0;
		num = Math.abs(num);
//		System.out.println("num:"+num);
		while(i<52 && num!=0) {
			result +=  getIntegerPart(num*2);
			i++;
			num = getFractionalPart(num*2);
//			System.out.println("result: "+result);
		}
		if(isNegative) result = findTwosComplement(result);
//		System.out.println("result: "+result);
		return result;
	}
	
	private boolean checkIfValidBinaryString(String str) {
		
		int noOfDecimalPoints = 0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != '1' && str.charAt(i) != '0' && str.charAt(i) != '.') {
				return false;
			}
			if(str.charAt(i) == '.') noOfDecimalPoints++;
			if(noOfDecimalPoints > 1) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		NumberSystem ns = new NumberSystem();
		
//		System.out.println(ns.convertToBinary(13.000018658393));
//		System.out.println(ns.convertToBinary(-13.000018658393));
//		System.out.println(ns.convertToBinary(0.000018658393));
//		System.out.println(ns.convertToBinary(-0.000018658393));
//		
//		System.out.println(ns.convertBinStringToDecimal("111111111111111111111111111.1001000111101011100001010001111010111000010100011110"));
//
//		System.out.println(ns.findOnesComplement("11111111111111111111111101100110"));
//		System.out.println(ns.findOnesComplement("110"));
//		
//		System.out.println(ns.findTwosComplement("11111111111111111111111101100110.00101"));
//		System.out.println(ns.findTwosComplement("00001111111111111111111101100110.00101"));
//		
//		System.out.println(ns.getOnesComplement(154));
//		System.out.println(ns.getIntegerPart(-15999994.123));
		
//		System.out.println(ns.getFractionalPart(13.000018658393));
//		System.out.println(ns.getFractionalPart(-13.000018658393));
//		System.out.println(ns.getFractionalPart(0.0000005999));
//		System.out.println(ns.getFractionalPart(-0.00008658394));
//		System.out.println(ns.getFractionalPart(45896002.005999));
//		System.out.println(ns.getFractionalPart(-45896002.005999));
		
//		System.out.println(ns.findBinaryOfFractionalPart(.00008658394));
//		System.out.println(ns.findBinaryOfFractionalPart(-0.00008658394));
		
//		System.out.println(8.658394E-5 *2 );
//		System.out.println(Math.pow(2, -1074));
//		System.out.println(ns.convertToBinary(1.3421772757209778E8));
		System.out.println(ns.convertBinStringToDecimal("0.0000000000000000000000001001001001110101000000000000011100110110110101011111"));
	}
	
	//2.2250738585072014E-308 2^(-1022)
	//8.900295434028806E-308 2^(-1020)
	//4.9E-324 2^(-1024)
	//8.589934591E9
	//1.865839299952654E-5
	//111111111111111111111111111.10010010011101010
	//111111111111111111111111111.1001001001110101000000000000011100110110110101011111
}