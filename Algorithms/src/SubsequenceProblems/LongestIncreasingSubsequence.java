package SubsequenceProblems;

import java.util.ArrayDeque;
import java.util.Arrays;

class LIV {
	private int len;
	private int idx;
	private int value;
	private String psf;//psf->path so far
	
	public LIV(int len, int idx, int value, String psf) {
		this.len = len;
		this.idx = idx;
		this.value = value;
		this.psf = psf;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public int getLen() {
		return len;
	}
	
	public int getVal() {
		return value;
	}
	
	public String getPsf() {
		return psf;
	}
	
	public void setPsf(String psf) {
		this.psf = psf;
	}
}
public class LongestIncreasingSubsequence {
	
	private int lengthOfLIS;
	private int idxOfMaxLength;
	
	public int getLengthOfLIS() {
		return lengthOfLIS;
	}
	
	public int[] findLIS(int arr1[]) {
		
		int len = arr1.length;
		
		int arr2[] = new int[len];
		
		Arrays.fill(arr2, 1);
		
		for(int j=1; j<len; j++) {
			for(int i=0; i<j; i++) {
				if(arr1[j]>=arr1[i]) {
					if(arr2[i]+1 > arr2[j]) {
						arr2[j]=arr2[i]+1;
					}
				}
			}
		}
		
		int max=0;
		for(int i=0; i<len; i++) {
			if(arr2[i]>arr2[max]) {
				max=i;
			}
		}
		
		lengthOfLIS = arr2[max];
		
		idxOfMaxLength = max;
		
		return arr2;
		
	}
	
	public void displayLIS(int arr1[], int arr2[]) {
		
		//Code to print just one of the LIS in arr1
//		int prev=0;
//		for(int i=0; i<arr2.length; i++) {
//			if(i==0) {
//				System.out.print(arr1[i]+" ");
//			}
//			else {
//				if(arr2[i]>arr2[prev]) {
//					System.out.print(arr1[i]+" ");
//					prev=i;
//				}
//			}
//		}
		
		ArrayDeque<LIV> queue = new ArrayDeque<>(); 
		
		//Code to add multiple LIV objects if there are multiple elements in arr2 which are the maximum in arr2
		for(int i=0; i<arr2.length; i++) {
			if(arr2[i] == lengthOfLIS) {
				queue.add(new LIV(lengthOfLIS,i,arr1[i],""+arr1[i]));
			}
		}
		
		//Time complexity of this code is 3*length of LIS
		while(queue.size()>0) {
			LIV liv = queue.remove();
//			System.out.println(liv.getVal());
			if(liv.getLen() == 1) {
				System.out.println(liv.getPsf());
				continue;
			}
			
			for(int i=liv.getIdx()-1; i>=0; i--) {
				if(arr2[i] == liv.getLen()-1) {
					if(arr1[i]<=liv.getVal()) {
						queue.add(new LIV(arr2[i], i, arr1[i], arr1[i]+"->"+liv.getPsf()));
					}
				}
			}
		}
	}
	
	public void displayArray(int arr[]) {
		for(int i:arr) {
			System.out.print(i+" ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
	
		int arr1[] = {10, 22, 42, 33, 21, 50, 41, 60, 59, 3};
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		
		int arr2[] = lis.findLIS(arr1);

		lis.displayLIS(arr1, arr2);
		
		System.out.println("\nLength of LIS is:"+lis.getLengthOfLIS());
		System.out.println("arr1:");
		lis.displayArray(arr1);
		System.out.println("arr2:");
		lis.displayArray(arr2);
	}

}


/*
 * {1, 4, 2, 7, 10, 5, 11, 9, 8, 12, 2, 2, 2, 2, 2}
 * {10, 22, 42, 33, 21, 50, 41, 60, 80, 3}
 * {10, 22, 42, 33, 21, 50, 41, 60, 59, 3} 
 * 
*/