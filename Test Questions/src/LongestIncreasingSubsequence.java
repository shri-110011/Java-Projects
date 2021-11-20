//WAP to find the length of the Longest Increasing Subsequence

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in); 	
		int n = sc.nextInt();
		int arr[] = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println("Max length of the subsequence is: "+ LongestIncreasingSubsequence.lengthOfLIS(arr, n));
		sc.close();
	}
	public static int lengthOfLIS(int arr[], int len) {
		int maxLength = 0;
		int arr1[]  = new int[len];
		Arrays.fill(arr1, 1);
		
		for(int j=1; j<len; j++) {
			for(int i=0; i<j; i++) {
				if(arr[j] > arr[i]) {
					if(arr1[i]+1 > arr1[j])
						arr1[j] = arr1[i]+1;
				}
			}
		}
		System.out.println(Arrays.toString(arr1));
		Arrays.sort(arr1);
		maxLength = arr1[len-1];
		return maxLength;
	}

}

// 10 22 9 33 21 50 41 60 80
//-1 3 4 5 2 2 2 2
//2 202 2 3 200 4 5
//5 6 8 20
