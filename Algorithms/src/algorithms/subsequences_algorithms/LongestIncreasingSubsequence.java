package algorithms.subsequences_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
	
	private int noOfRecursiveCalls, forLoopCountInDisplayLIS, 
	whileLoopCountInDisplayLIS, outerForLoopCountInLIS,
	innerForLoopCountInLIS;
	private static int lengthOfLIS;
	
	public int getLengthOfLIS() {
		return lengthOfLIS;
	}
	
	/* findLISLenUsingSmartRecursion() has a time complexity of 
	 * O(2^(n+1)) in the case when the input sequence is already
	 * sorted in increasing order and contain distinct elements.
	 * 
	 * findLISLenUsingSmartRecursion(int arr[], int n, int prevIdx, int curIdx)
	 * will return the longest increasing subsequence starting
	 * at position cur.
	 */
	
	public int findLISLenUsingSmartRecursion(int arr[], int n, int prevIdx, int curIdx) {
		
		noOfRecursiveCalls++;
		
		if(curIdx == n)  return 0;
		
		int l1 = 0;
		if(prevIdx == -1 || arr[prevIdx] < arr[curIdx]) {
			l1 = 1+findLISLenUsingSmartRecursion(arr, n, curIdx, curIdx+1);
		}
		
		int l2 = findLISLenUsingSmartRecursion(arr, n, prevIdx, curIdx+1);
		
		return Math.max(l1, l2);
	}
	
	/* findLISLenUsingRecursion(int arr[], int n, int prevIdx, int curIdx, int dp[][])
	 * has time complexity of O(N^2 + N + 1).
	 * 
	 * findLISLenUsingRecursion(int arr[], int n, int prevIdx, int curIdx, int dp[][])
	 * will return the longest increasing subsequence starting
	 * at position cur.
	 * 
	 * Because the function calls will be made around that many
	 * times as the number of elements in dp[][]. 
	 * 
	 */
	public int findLISLenUsingSmartRecursionAndMemoization(int arr[], int n, int prevIdx, int curIdx, int dp[][]) {
		
		noOfRecursiveCalls++;
		if(curIdx == n) { return 0; }
		
//		noOfRecusiveCalls++;
		
		if(prevIdx != -1 && dp[prevIdx][curIdx]!=-1) return dp[prevIdx][curIdx];
		
		int l1 = 0;
		if(prevIdx == -1 || arr[prevIdx] < arr[curIdx]) {
			l1 = 1+findLISLenUsingSmartRecursionAndMemoization(arr, n, curIdx, curIdx+1, dp);
		}
		
		int l2 = findLISLenUsingSmartRecursionAndMemoization(arr, n, prevIdx, curIdx+1, dp);
		
		if(prevIdx!= -1) dp[prevIdx][curIdx] = Math.max(l1, l2);
		return Math.max(l1, l2);
	}
	
	/* findLISLenUsindRecursion() has a time complexity of 
	 * O(2^(N-1)) in all cases.
	 * 
	 * findLISLenUsingRecursion(int arr[], int lisEndPos) will return
	 * the length of the longest increasing subsequence ending 
	 * at position lisEndPos.
	 */
	public int findLISLenUsingRecursion(int arr[], int lisEndPos) {
		
		noOfRecursiveCalls++;
		if(lisEndPos==0) return 1;
		
		int max_length_here = 1;
		for(int i=1;i<=lisEndPos;i++) {
			int res = findLISLenUsingRecursion(arr, i-1);
			if(arr[i-1]<arr[lisEndPos] && (res+1)>max_length_here) {
				max_length_here = res+1;
			}
		}
		
		if(lengthOfLIS < max_length_here) {
			lengthOfLIS = max_length_here;
		}
		return max_length_here;
	}
	
	/* findLISLenUsingRecursion() has a time complexity of 
	 * O(N(N-1)/2 - 1) in all cases.
	 */
	public int findLISLenUsingRecursionAndTabulation(int arr[], int lcsEndPos, int dp[]) {
		
		noOfRecursiveCalls++;
		
		if(lcsEndPos == -1) return 0;
		
		if(lcsEndPos==0) return dp[lcsEndPos] = 1;
		
		if(dp[lcsEndPos] != -1) return dp[lcsEndPos];
		
		int max_length_here = 1;
		for(int i=1;i<=lcsEndPos;i++) {
			int res = findLISLenUsingRecursionAndTabulation(arr, i-1, dp);
			if(arr[i-1]<arr[lcsEndPos] && (res+1)>max_length_here) {
				max_length_here = res+1;
			}
		}
		
		if(lengthOfLIS < max_length_here) {
			lengthOfLIS = max_length_here;
		}
		return dp[lcsEndPos] = max_length_here;
	}
	
	/* findLISLenUsingIterationAndTabulation() has a time 
	 * complexity of O(N^2+N) where N is the number of 
	 * elements in the original sequence.
	 * 
	 * findLISLenUsingIterationAndTabulation(int arr[])
	 * returns the array containing length of LIS at each
	 * position i where 0<=i<N. 
	 * 
	 */
	public int[] findLISLenUsingIterationAndTabulation(int arr[]) {
		
		resetMetrics();
		int len = arr.length;
		
		int lisLen[] = new int[len];
		
		if(len == 0) return lisLen;
		
		Arrays.fill(lisLen, 1);
		
		for(int j=1; j<len; j++) {
			outerForLoopCountInLIS++;
			for(int i=0; i<j; i++) {
				innerForLoopCountInLIS++;
				if(arr[j]>arr[i]) {
					if(lisLen[i]+1 > lisLen[j]) {
						lisLen[j]=lisLen[i]+1;
					}
				}
			}
		}
		
		int max=0;
		for(int i=0; i<len; i++) {
			if(lisLen[i]>lisLen[max]) {
				max=i;
			}
		}
		
		lengthOfLIS = lisLen[max];
		
		return lisLen;
	}
	
	
	/* getLISLenUsingSmartRecursion(int arr[]) will return the length of the 
	 * longest increasing subsequence in arr[].
	 * 
	 */
	public int getLISLenUsingSmartRecursion(int arr[]) {
		int arrLen = arr.length;
		
		resetMetrics();
		
		System.out.println("arr[]:"+Arrays.toString(arr));
		System.out.println("arrLen: "+arrLen);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		
		lengthOfLIS = findLISLenUsingSmartRecursion(arr, arrLen, -1, 0);
	
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		return lengthOfLIS;
	}
	
	public int getLISLenUsingSmartRecursionAndMemoization(int arr[]) {
		int arrLen = arr.length, 
				dp[][] = new int[arrLen][arrLen];
		
		for(int i=0; i<arrLen; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		resetMetrics();
		
		System.out.println("arr[]:"+Arrays.toString(arr));
		System.out.println("arrLen: "+arrLen);
		
		lengthOfLIS =  findLISLenUsingSmartRecursionAndMemoization(arr, arr.length, -1, 0, dp);
		
		System.out.println("Printing dp[][] ...");
		
		for(int row[]: dp) {
			System.out.println(Arrays.toString(row));
		}
		
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		return lengthOfLIS;
	}
	
	/* getLISLenUsingRecursion(int arr[]) will return the 
	 * length of the longest increasing subsequence in 
	 * arr[].
	 * 
	 */
	public int getLISLenUsingRecursion(int arr[]) {
		int arrLen = arr.length;
		
		resetMetrics();
		
		if(arrLen == 0) {
			findLISLenUsingRecursion(arr, 0);
		}
		else {
			findLISLenUsingRecursion(arr, arrLen-1);
		}
		
		System.out.println("arr[]:"+Arrays.toString(arr));
		System.out.println("arrLen: "+arrLen);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		
		return lengthOfLIS;
	}
	
	public int getLISLenUsingRecursionAndTabulation(int arr[]) {
		int arrLen = arr.length,
				dp[] = new int[arrLen];
		Arrays.fill(dp,  -1);
		
		resetMetrics();
		
		
		findLISLenUsingRecursionAndTabulation(arr, arrLen-1, dp);

		System.out.println("arr[]:"+Arrays.toString(arr));
		System.out.println("arrLen: "+arrLen);
		System.out.println("dp[]: "+Arrays.toString(dp));
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		
		return lengthOfLIS;
	}
	
	public int getLISLenUsingIterationAndTabulation(int arr[]) {
		resetMetrics();
		
		System.out.println("arr[]: "+Arrays.toString(arr));
		System.out.println("arrLen: "+arr.length);
		
		findLISLenUsingIterationAndTabulation(arr);
		
		System.out.println("outerForLoopCountInLIS: "+outerForLoopCountInLIS);
		System.out.println("innerForLoopCountInLIS: "+innerForLoopCountInLIS);
		return getLengthOfLIS();
		
	}
	
	public ArrayList<ArrayList<Integer>> displayLIS(int arr[], int lisLen[]) {
		ArrayDeque<LIV> queue = new ArrayDeque<>();
		ArrayList<ArrayList<Integer>> lisList = 
				new ArrayList<>();
		
		System.out.println("arr[]: "+Arrays.toString(arr));
		System.out.println("arrLen: "+arr.length);
		
		//Code to add multiple LIV objects if there are multiple elements in arr2 which are the maximum in arr2
		for(int i=0; i<lisLen.length; i++) {
			forLoopCountInDisplayLIS++;
			if(lisLen[i] == lengthOfLIS) {
				queue.add(new LIV(lengthOfLIS,i,arr[i],""+arr[i]));
			}
		}
		
		/* Time complexity of this code is 
		 * O(no_of_solutions*length of LIS).
		 * 
		 * In fact this is the time complexity of the while 
		 * loop.
		 * 
		 * And the time complexity of the inner for-loop 
		 * is O(N(N-1)/2) and this worst case will occur when the
		 * input array already is in increasing order and 
		 * contains distinct elements.
		 */
		while(queue.size()>0) {
			whileLoopCountInDisplayLIS++;
			LIV liv = queue.remove();
//			System.out.println(liv.getVal());
			if(liv.getLen() == 1) {
				addLIS(lisList, liv.getPsf());
				System.out.println(liv.getPsf());
				continue;
			}
			
			for(int i=liv.getIdx()-1; i>=0; i--) {
				innerForLoopCountInLIS++;
				if(lisLen[i] == liv.getLen()-1) {
					if(arr[i]<liv.getVal()) {
						queue.add(new LIV(lisLen[i], i, arr[i], arr[i]+"->"+liv.getPsf()));
					}
				}
			}
		}
		
		System.out.println("forLoopCountInDisplayLIS: "+forLoopCountInDisplayLIS);
		System.out.println("whileLoopCountInDisplayLIS: "+whileLoopCountInDisplayLIS);
		System.out.println("innerForLoopCountInLIS: "+innerForLoopCountInLIS);
	
		return lisList;
	
	}
	
	
	
	private void addLIS(ArrayList<ArrayList<Integer>> lisLengthsList, String psf) {
		
		String elements[] = psf.split("->");
		ArrayList<Integer> lisLengths = new ArrayList<>();
		
		for(String el: elements) {
			lisLengths.add(Integer.parseInt(el));
		}
		
		lisLengthsList.add(lisLengths);
		
	}

	private void resetMetrics() {
		noOfRecursiveCalls = 0;
		forLoopCountInDisplayLIS = 0;
		whileLoopCountInDisplayLIS = 0;
		outerForLoopCountInLIS = 0;
		innerForLoopCountInLIS = 0;
		lengthOfLIS = 0;
	}

	public static void main(String[] args) {
		
		int arr[] = {4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18};
		// {1, 2, 5, 3, 4}
		// {4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18}
		// {10, 22, 42, 33, 21, 50, 41, 60, 80, 3}
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		
//		int lengthOfLIS = lis.getLISLenUsingSmartRecursion(arr);
//		int lengthOfLIS = lis.getLISLenUsingSmartRecursionAndMemoization(arr);
		
//		System.out.println("lengthOfLIS: "+lengthOfLIS);
//		System.out.println("noOfRecursiveCalls: "+lis.noOfRecursiveCalls);
		int lisLen[] = lis.findLISLenUsingIterationAndTabulation(arr);
		System.out.println("lisLen: "+Arrays.toString(lisLen));
		System.out.println("Length of LIS is: "+lis.getLengthOfLIS());
		System.out.println("lisLen: "+Arrays.toString(lisLen));
		System.out.println(lis.displayLIS(arr, lisLen));
	}

}
