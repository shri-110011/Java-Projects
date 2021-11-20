import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
	
	public static int findMaximumSumOfIS(int a[]) {
		int size = a.length;
		int b[] = new int[size];
		Arrays.fill(b, 1);
		
		int c[] = a.clone();
		int i, j;
		
		for(j=1; j<size; j++) {
			for(i=0; i<j; i++) {
				if(a[j] > a[i]) {
					if(b[i] + 1 > b[j] && a[j] + c[i] > c[j]) {
						b[j] = b[i] + 1;
						c[j] = a[j] + c[i];
					}
				}
			}
		}
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(c));
		Arrays.sort(c);
		
		return c[size-1];
	}
	
	public static int findLengthOfLIS(int a[]) {
		int size = a.length;
		int b[] = new int[size];
		Arrays.fill(b, 1);
		
		int i, j;
		
		for(j=1; j<size; j++) {
			for(i=0; i<j; i++) {
				if(a[j] > a[i]) {
					if(b[i] + 1 > b[j]) {
						b[j] = b[i] + 1;
					}
				}
			}
		}
		System.out.println(Arrays.toString(b));
		Arrays.sort(b);
		
		return b[size-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the array size");
		size = sc.nextInt();
		if(size>0) {
			int a[] = new int[size];
			System.out.println("Enter the array");
			for(int i=0;i<size;i++) {
				a[i] = sc.nextInt();
			}
//			int length = findLengthOfLIS(a);
			int sum = findMaximumSumOfIS(a);
			
//			System.out.println("Length of LIS is: "+length);
			System.out.println("Length of LIS is: "+sum);
		}
		else {
			System.out.println("Size of an array must be greater than 0.");
		}
		sc.close();
	}
}
