package algorithms.math;

public class MathUtilities {
	
	public int gcd(int num1, int num2) {
		/* r is remainder and it can be set to any non-zero value
		 * as its initial  value.
		 * 
		 * Note: gcd(a,b) = gcd(a, -b) = gcd(-a, b) = gcd(-a, -b).
		 */
		int temp, r=1;
		
		if(num1 < 0) num1=-num1;
		if(num2 < 0) num2=-num2;
		
		// Forcing num1 to greater than num2.
		if(num1 < num2) {
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		if(num2 == 0) return num1;

		while(r != 0) {
			r = num1%num2;
			num1 = num2;
			num2 = r;
		}
		
		return num1;
	}
	
	public int moduloInv(int num, int N) {
		
		int arr[] = new int[7], gcdVal;
		
		/* arr[0] = D2M current dividend multiplier
		 * arr[1] = d2M current divisor multiplier
		 * arr[2] = D1M previous dividend multiplier
		 * arr[3] = d1M previous divisor multiplier
		 * arr[4] = r2 current remainder
		 * arr[5] = N(original modulo)
		 * arr[6] = sign(either +1 or -1)
		 */
		arr[5] = N;
		if((gcdVal = gcd(num, N)) == 1) {
			int modInv;
			
			/* We are forcing abs(num1) > abs(num2) in the 
			 * getModuloInv(int num1, int num2, int arr[])
			 * except for the case when abs(num) < abs(N)
			 * and num = 1 or -1 because:
			 * inverse of 1 modulo N 
			 * is equal to 1
			 * and
			 * inverse of -1 modulo N 
			 * is equal to N-1
			 * for all N greater than or equal to 1.
			 */
			if(Math.abs(num) < Math.abs(N)) {
				if(num == 1 || num == -1) {
					getModuloInv(num, N, arr);
					modInv = arr[6]*arr[0];
					if(arr[4] == -1) {
						modInv = -1*modInv;
					}
				}
				else {
					getModuloInv(N, num, arr);
				
					modInv = -1*arr[6]*arr[1];
					if(arr[4] == -1) {
						modInv = -1*modInv;
					}
				}
				
			}
			else {
				getModuloInv(num, N, arr);
				
				/* r = arr[4] will be 0 if num = 1 or -1
				 * and N = 1 or -1.
				 * 
				 * In such case modInv = 0 because:
				 * 1 mod 1 = 0, -1 mod = 0
				 */
				if(arr[4] == 0)
					modInv = 0;
				else {
					modInv = arr[6]*arr[0];
					if(arr[4] == -1) {
						modInv = -1*modInv;
					}
				}
			}
			
			System.out.println("modInv: "+modInv);
			System.out.println("D2M: "+arr[0]+", d2M: "+arr[1]+", D1M: "+arr[2]+", d1M: "+arr[3]+", sign: "+arr[6]);
			
			/* If modInv is -ve then we need to add N to get the
			 * correct result because our modulo is N and it is 
			 * +ve. 
			 * 
			 */
			if(modInv < 0) return modInv+N;
			return modInv;
		}
		else
			throw new IllegalArgumentException("gcd("+num+","+N+") is: "+gcdVal+", expected 1 for modular multiplicative inverse to exist!");
	}
	
	private int[] getModuloInv(int num1, int num2, int arr[]) {
		/* Here we are sure that num1 and num2 are relatively 
		 * prime and num1 > num2.
		 */
		
		/* If arr[5] = N is -ve or 0 then we can't compute the 
		 * modular multiplicative inverse.
		 * 
		 * If N is 0 then num can be either +ve or -ve and in 
		 * either case getModuloInv(num, N, arr) will be called 
		 * and will cause: 
		 * java.lang.ArithmeticException: / by zero
		 * 
		 * If N is -ve then there will be no integer x such that
		 * num*x is congruent to 1 modulo N because num*x mod N 
		 * will always give a result in the range [0,N) and 1
		 * isn't there in that interval.
		 */
		if(arr[5]<0 || arr[5]==0)
			throw new IllegalArgumentException("Modulo must be a positve integer, provided: "+arr[5]);
		
		/* The base case will run when r = arr[4] = 1 or -1
		 * and at that time we set:
		 * D1M = arr[2] = 0
		 * d1M = arr[3] = 0
		 * sign = arr[6] = 1 
		 * 
		 */
		if(Math.abs(arr[4]) == 1) {
			arr[2] = 0; arr[3] = 1; arr[6] = 1;
		}
		else {
			int r = num1 % num2;
			arr[4] = r;
			
			/* When r=0 in case num1 = 1 and num2 = 1
			 * then we set: 
			 * D2M = arr[0] = 1
			 * d2M = arr[1] = 0
			 * sign = arr[6] = 1
			 * 
			 * because we want to express 1 as a linear 
			 * combination of dividend and divisor i.e.
			 * we want sign(D2M * D - d2M * d) = 1. 
			 *  
			 */
			if(r == 0) {
				arr[0] = 1;
				arr[1] = 0;
				arr[6] = 1;
				return arr;
			}

			getModuloInv(num2, r, arr);
			int q2 = num1/num2;
			
			if(r==1 || r==-1) {
				arr[0] = arr[3];
				arr[1] = q2*arr[3]+arr[2];
			}
			else if(r!=1 && r!=-1) {
				arr[6] = arr[6]*-1;
				arr[2] = arr[0];
				arr[3] = arr[1];
				arr[0] = arr[3];
				arr[1] = q2*arr[3]+arr[2];
			}
			System.out.println("D2M: "+arr[0]+", d2M: "+arr[1]+", D1M: "+arr[2]+", d1M: "+arr[3]+", q2: "+q2+", sign: "+arr[6]);
		}
		
		return arr;
	}
	
	public int modulo(int a, int b) {
		/* a mod b = rem(a,b) if both a and b are of same signs
		 * otherwise a mod b = rem(a,b)+b
		 * 
		 */
		if(a>0 && b<0 || a<0 && b>0) {
			int r = a%b;
			/* If r=0 and a,b are of different signs then we 
			 * don't need to add b to r because on adding b to r
			 * will give b which doesn't lie in the interval
			 * [0,b).
			 * Eg: 4 mod -1 = 0
			 * 4 mod -4 = 0
			 * 
			 */
			if(r==0)
				return r;
			return r + b;
		}
		else
			return a%b;
	}
	
	public boolean checkIfPrime(int num) {
		/* count = 1 because every number divides itself.
		 * 
		 */
		int count = 1;
		
		if(num<=0 || num == 1)
			throw new IllegalArgumentException("Value of num: "+num+", prime numbers are integers greater than 1!");
			
		for(int i=1; i<=Math.sqrt(num); i++) {
			if(num%i == 0) {
				System.out.println(i);
				count++;
			}
			if(count>2) {
				break;
			}
		}
		if(count == 2)
			return true;
		else
			return false;
	}
	
	/*
	 * The hash function used to compute the hash of a string is:
	 * hash_val = s[0]*p^0+s[1]*p^1+s[2]*p^3+...+s[n-1]*p^(n-1) mod m
	 * 
	 * where s[i] is the ascii equivalent of the character at index i
	 * and p is a prime number and m is also  prime number.
	 * 
	 * Here m = 1000000009 and p = 31
	 * 
	 */
	public long hash(String str) {
		
		int p = 31, m = 1000000009, p_pow = 1, temp;
		int sum = 0;
		char charArray[] = str.toCharArray();
		for(int i=0; i<charArray.length; i++) {
			int s_i = charArray[i];
			temp = (sum + s_i*p_pow);
			System.out.println("temp: "+temp);
			System.out.println("p_pow: "+p_pow);
			sum = modulo(temp, m);
			p_pow = modulo((p_pow*p), m);
			System.out.println("sum: "+sum);
		}
		if(sum<0) return sum+m;
		return sum;
	}

	public static void main(String[] args) {
		
		MathUtilities obj = new MathUtilities();
//		System.out.println(obj.gcd(24, 48));
//		System.out.println(obj.modulo((-1278440899), 1000000007));
//		System.out.println(obj.moduloInv(-1278440899, 1000000007));
//		System.out.println(obj.checkIfPrime(1000000009));
		System.out.println("hash value: " +obj.hash("How are you?"));

	}

}
// 16526376
// 2147483647
// 3016526397
// -1278440899
// 721559115