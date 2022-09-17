package algorithms.subsequences_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumSumIncreasingSubsequence {
	
	private int noOfRecursiveCalls, forLoopCount,
	innerForLoopCount, maxSum, maxSumIdx, dp[];
	
	/*
	 * findMSISUsingRecursion(int arr[], int msisEndIdx) has a time
	 * complexity of O(2^(N-1)) for all cases.
	 * 
	 * findMSISUsingRecursion(int arr[], int msisEndIdx) returns 
	 * the maximum sum of the increasing subsequence ending 
	 * at position msisEndIdx.
	 * 
	 */
	public int findMSISUsingRecursion(int arr[], int msisEndIdx) {
		noOfRecursiveCalls++;
		
		if(msisEndIdx == 0) return arr[msisEndIdx];
		
		int maxSumHere = arr[msisEndIdx];
		for(int i=1; i<=msisEndIdx; i++) {
			forLoopCount++;
			int res = findMSISUsingRecursion(arr, i-1);
			if(arr[i-1] < arr[msisEndIdx] && maxSumHere < (res+arr[msisEndIdx])) {
				maxSumHere = res + arr[msisEndIdx];	
			}
			if(maxSum < maxSumHere) {
				maxSum = maxSumHere;
				maxSumIdx = msisEndIdx;
			}
		}
//		System.out.println("msisEndIdx: "+msisEndIdx+" maxSumHere: "+maxSumHere);
		return maxSumHere;
	}
	
	/*
	 * findMSISUsingRecursion(int arr[], int msisEndIdx, int dp[]) has a time
	 * complexity of O(N(N-1)/2 + N) which is around O(N^2) and
	 * space complexity of O(N) for all cases.
	 */
	public int findMSISUsingRecursionAndTabulation(int arr[], int msisEndIdx, int dp[]) {
		noOfRecursiveCalls++;
		
		if(msisEndIdx == 0) return dp[msisEndIdx] = arr[msisEndIdx];
		
		int maxSumHere = arr[msisEndIdx];
		for(int i=1; i<=msisEndIdx; i++) {
			forLoopCount++;
			int res;
			if(dp[i-1] != 0) res = dp[i-1];
			else res = findMSISUsingRecursionAndTabulation(arr, i-1, dp);
			if(arr[i-1] < arr[msisEndIdx] && maxSumHere < (res+arr[msisEndIdx])) {
				maxSumHere = res + arr[msisEndIdx];	
			}
			if(maxSum < maxSumHere) {
				maxSum = maxSumHere;
				maxSumIdx = msisEndIdx;
			}
		}
//		System.out.println("pos: "+pos+" maxSumHere: "+maxSumHere);
		return dp[msisEndIdx] = maxSumHere;
	}
	
	/*
	 * findMSISUsingIteration(int arr[]) has a time
	 * complexity of O(N(N-1)/2) which is around O(N^2) and
	 * space complexity of O(N) for all cases.
	 * 
	 * findMSISUsingIteration(int arr[]) returns the 
	 * maximum sum increasing subsequence in the integer
	 * array arr[].
	 */
	public int findMSISUsingIterationAndTabulation(int arr[]) {
		resetMetrics();
		int arrLen = arr.length, dp[] = new int[arrLen];
		
		if(arrLen == 0) {
			maxSumIdx = -1;
		}
		
		for(int i=0;i<arrLen;i++) {
			int maxSumHere = arr[i];
			for(int j=0; j<i; j++) {
				innerForLoopCount++;
				if(arr[j] < arr[i] && 
						maxSumHere < dp[j] + arr[i]) {
					maxSumHere = dp[j] + arr[i];
				}
			}
			dp[i] = maxSumHere;
			if(maxSum < maxSumHere) {
				maxSum = maxSumHere;
				maxSumIdx = i;
			}
		}
		this.dp = dp;
		System.out.println("innerForLoopCount: "+innerForLoopCount);
		System.out.println("dp: "+Arrays.toString(dp));
		System.out.println("maxSum: "+maxSum);
		System.out.println("maxSumIdx: "+maxSumIdx);
		return maxSum;
	}
		
	public int getMSISUsingRecursion(int arr[]) {
		resetMetrics();
		int arrLen = arr.length;
		if(arrLen != 0) findMSISUsingRecursion(arr, arr.length-1);
		else {
			maxSum = 0;
			maxSumIdx = -1;
		}
		System.out.println("Input array: "+Arrays.toString(arr));
		System.out.println("Array length: "+arrLen);
		System.out.println("forLoopCount: "+forLoopCount);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		System.out.println("maxSum: "+maxSum);
		System.out.println("maxSumIdx: "+maxSumIdx);
		return maxSum;
	}
	
	public int getMSISUsingRecursionAndTabulation(int arr[]) {
		resetMetrics();
		int arrLen = arr.length, dp[] = new int[arr.length];
		if(arrLen != 0) findMSISUsingRecursionAndTabulation(arr, arr.length-1, dp);
		else {
			maxSum = 0;
			maxSumIdx = -1;
		}
		this.dp = dp;
		System.out.println("Input array: "+Arrays.toString(arr));
		System.out.println("Array length: "+arrLen);
		System.out.println("dp: "+Arrays.toString(dp));
		System.out.println("forLoopCount: "+forLoopCount);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		System.out.println("maxSum: "+maxSum);
		System.out.println("maxSumIdx: "+maxSumIdx);
		return maxSum;
	}
	
	public List<Integer> printMSIS(int arr[]) {
		findMSISUsingIterationAndTabulation(arr);
		
		List<Integer> list = new ArrayList<>();
		if(arr.length == 0) return list;
		
		list.add(arr[maxSumIdx]);
		int nextIdx = maxSumIdx;
		for(int i=maxSumIdx-1; i>=0; i--) {
			if(dp[i] == dp[nextIdx] - arr[nextIdx]) {
				list.add(arr[i]);
				nextIdx = i;
			}
		}
		Collections.reverse(list);
		System.out.println(list);
		return list;
	}
	
	private void resetMetrics() {
		noOfRecursiveCalls = 0;
		forLoopCount = 0;
		innerForLoopCount = 0;
		maxSum = 0;
		maxSumIdx = 0;
	}

	public static void main(String[] args) {
		
		int arr[] = {8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6};
		// {1, 101, 2, 100}
		// {1, 6, 4, 8, 7, 7, 13, 9}
		// {8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6}
		
		MaximumSumIncreasingSubsequence msis = new MaximumSumIncreasingSubsequence();
		
//		msis.findMSIS(arr);
//		msis.findMSISUsingTabulation(arr);
		
//		int maxSum = msis.maxSum;
		
//		int maxSum = msis.findMSISUsingIterationAndTabulation(arr);
//
//		System.out.println("maxSum: "+maxSum);
		msis.printMSIS(arr);
	}

}
