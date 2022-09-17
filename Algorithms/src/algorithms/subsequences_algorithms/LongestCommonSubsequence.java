package algorithms.subsequences_algorithms;

import java.util.Arrays;

import utilities.HelperClass;

public class LongestCommonSubsequence {
	
	private int noOfRecursiveCalls, outerForLoopCount, 
	innerForLoopCount;

	/* The worst case time complexity of findLCSUsingRecursion()
	 * is O(2^(m+n)-1) and this occurs when all the characters
	 * in both strings mismatch.
	 * 
	 * The best case time complexity of findLCSUsingRecursion()
	 * is O(min(m, n)).
	 * 
	 * Note: m is the length of string 1.
	 * n is the length of string 2.
	 * 
	 * findLCSUsingRecursion(char ch1[], char ch2[], int lcsStartPos1, int lcsStartPos2)
	 * indicates the lcs starting at lcsStartPos1  in string 1
	 * and lcsStartPos2 in string 2.
	 * 
	 * 
	 */
	public int findLCSUsingRecursion(char ch1[], char ch2[], int lcsStartPos1, int lcsStartPos2) {
		noOfRecursiveCalls++;
		
		if(lcsStartPos1==ch1.length || lcsStartPos2==ch2.length) {
			return 0;
		}
		else {
//			System.out.println("i:"+i+" ch1[i]:"+ch1[i]);
			if(ch1[lcsStartPos1]==ch2[lcsStartPos2]) {
				return 1+findLCSUsingRecursion(ch1, ch2, lcsStartPos1+1, lcsStartPos2+1);
			}
			else {
				return HelperClass.max(findLCSUsingRecursion(ch1, ch2, lcsStartPos1+1, lcsStartPos2), findLCSUsingRecursion(ch1, ch2, lcsStartPos1, lcsStartPos2+1));
			}
		}
		
	}
	
	/* findLCSUsingRecursion(char ch1[], char ch2[], int lcsStartPos1, int lcsStartPos2, int dp[][])
	 * has time complexity of O((m+1)(n+1)).
	 * 
	 * Note: m is the length of string 1.
	 * n is the length of string 2.
	 * 
	 */
	public int findLCSUsingRecursionAndMemoization(char ch1[], char ch2[], int lcsStartPos1, int lcsStartPos2, int dp[][]) {
		
		noOfRecursiveCalls++;
		
		if(lcsStartPos1==ch1.length || lcsStartPos2==ch2.length) {
			return dp[lcsStartPos1][lcsStartPos2]=0;
		}
		else {
//			System.out.println("i:"+i+" ch1[i]:"+ch1[i]);
//			if(dp[lcsStartPos1][lcsStartPos2] != -1) return dp[lcsStartPos1][lcsStartPos2];
			if(ch1[lcsStartPos1]==ch2[lcsStartPos2]) {
				if(dp[lcsStartPos1+1][lcsStartPos2+1] != -1) return dp[lcsStartPos1][lcsStartPos2] = 1+dp[lcsStartPos1+1][lcsStartPos2+1];
				
				return dp[lcsStartPos1][lcsStartPos2] = 1+findLCSUsingRecursionAndMemoization(ch1, ch2, lcsStartPos1+1, lcsStartPos2+1, dp);
			}
			else {
				int l1=-1, l2=-1;
				if(dp[lcsStartPos1+1][lcsStartPos2] != -1) {
					l1 = dp[lcsStartPos1+1][lcsStartPos2];
				}
				if(dp[lcsStartPos1][lcsStartPos2+1] != -1) {
					l2 = dp[lcsStartPos1][lcsStartPos2+1];
				}
				
				if(l1 != -1 && l2 != -1)
					return dp[lcsStartPos1][lcsStartPos2] = HelperClass.max(l1,l2);
				else {
					if(l1 != -1) {
						return dp[lcsStartPos1][lcsStartPos2] = HelperClass.max(l1, findLCSUsingRecursionAndMemoization(ch1, ch2, lcsStartPos1, lcsStartPos2+1, dp));
					}
					else if(l2 != -1) {
						return dp[lcsStartPos1][lcsStartPos2] = HelperClass.max(findLCSUsingRecursionAndMemoization(ch1, ch2, lcsStartPos1+1, lcsStartPos2, dp), l2);
					}
					else {
						return dp[lcsStartPos1][lcsStartPos2] = HelperClass.max(
								findLCSUsingRecursionAndMemoization(ch1, ch2, lcsStartPos1+1, lcsStartPos2, dp), 
								findLCSUsingRecursionAndMemoization(ch1, ch2, lcsStartPos1, lcsStartPos2+1, dp)
								);
					}
				}
			}
		}
		
	}
	
	/* findLCSUsingIterationAndTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][])
	 * has a time complexity of O(m*n) in all cases and 
	 * space complexity O(m*n).
	 * 
	 * 
	 * findLCSUsingIterationAndTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][])
	 * returns the length of LCS ending at position pos1 
	 * in string 1 and at pos2 in string 2.
	 */
	public int findLCSUsingIterationAndTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][]) {
		
		for(int i=0; i<=lcsEndPos1; i++) {
			outerForLoopCount++;
			for(int j=0; j<=lcsEndPos2; j++) {
				innerForLoopCount++;
				int prevRow = (i-1)<0?0:dp[i-1][j],
						prevCol = (j-1)<0?0:dp[i][j-1];
				
				if(arr1[i] == arr2[j]) {
					if(prevRow == prevCol)
						dp[i][j] = 1+prevRow;
					else
						dp[i][j] = HelperClass.max(prevRow, prevCol);
				}
				else
					dp[i][j] = HelperClass.max(prevRow, prevCol);
			}
		}
		
		if(lcsEndPos1 == -1 || lcsEndPos2 == -1) return 0;
		return dp[lcsEndPos1][lcsEndPos2];
	}
	
	/* findLCSUsingSpaceOptimizedTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][])
	 * has a time complexity of O(m*n) in all cases and 
	 * space complexity O(2*max(m, n)) .
	 * 
	 * 
	 * findLCSUsingSpaceOptimizedTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][])
	 * returns the length of LCS ending at position pos1 
	 * in string 1 and at pos2 in string 2.
	 * 
	 * This method is space optimized because to fill any
	 * row in dp[][] we need to know just the previous 
	 * row entries in dp[][].
	 */
	public int findLCSUsingIterationAndSpaceOptimizedTabulation(char arr1[], char arr2[], int lcsEndPos1, int lcsEndPos2, int dp[][]) {
		
		/*
		 * These 2 variables curRowIdx, prevRowIdx
		 * are there to switch between the 2 rows of 
		 * the dp[2][].
		 */
		int curRowIdx = 1, prevRowIdx = 0;
		for(int i=0; i<=lcsEndPos1; i++) {
			outerForLoopCount++;
			if(curRowIdx == 0) {
				curRowIdx = 1;
				prevRowIdx = 0;
			}
			else {
				curRowIdx = 0;
				prevRowIdx = 1;
			}
			for(int j=0; j<=lcsEndPos2; j++) {
				innerForLoopCount++;
				int prevRow = (i-1)<0?0:dp[prevRowIdx][j],
						prevCol = (j-1)<0?0:dp[curRowIdx][j-1];
				
				if(arr1[i] == arr2[j]) {
					if(prevRow == prevCol)
						dp[curRowIdx][j] = 1+prevRow;
					else
						dp[curRowIdx][j] = HelperClass.max(prevRow, prevCol);
				}
				else
					dp[curRowIdx][j] = HelperClass.max(prevRow, prevCol);
			}
		}
		
		if(lcsEndPos2 == -1) return 0;
		return dp[curRowIdx][lcsEndPos2];
	}
	
	public int getLCSUsingRecursion(String str1, String str2) {
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();
		
		System.out.println("String 1:|"+str1+"| Len: "+str1.length());
		System.out.println("String 2:|"+str2+"| Len: "+str2.length());
		
		resetMetrics();
		int lenOfLCS =  findLCSUsingRecursion(ch1, ch2, 0, 0);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		
		return lenOfLCS;
	}
	
	public int getLCSUsingRecursionAndMemoization(String str1, String str2) {
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();
		
		int dp[][] = new int[ch1.length+1][ch2.length+1];
		
		for(int i=0; i<ch1.length+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println("String 1:|"+str1+"| Len: "+str1.length());
		System.out.println("String 2:|"+str2+"| Len: "+str2.length());
		
		resetMetrics();
		
		int lenOfLCS =  findLCSUsingRecursionAndMemoization(ch1, ch2, 0, 0, dp);
		System.out.println("noOfRecursiveCalls: "+noOfRecursiveCalls);
		
		System.out.println("Printing dp[][] ...");
		for(int row[]: dp) {
			System.out.println(Arrays.toString(row));
		}
		
		return lenOfLCS;
	}
	
	public int getLCSUsingIterationAndTabulation(String str1, String str2) {
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();
		
		int str1Len = str1.length(), str2Len = str2.length();
		
		int dp[][] = new int[str1Len][str2Len];
		
		System.out.println("String 1:|"+str1+"| Len: "+str1.length());
		System.out.println("String 2:|"+str2+"| Len: "+str2.length());
		
		resetMetrics();
		
		int lenOfLCS =  findLCSUsingIterationAndTabulation(ch1, ch2, str1Len-1, str2Len-1, dp);
		System.out.println("outerForLoopCount: "+outerForLoopCount);
		System.out.println("innerForLoopCount: "+innerForLoopCount);
		
		System.out.println("Printing dp[][] ...");
		for(int row[]: dp) {
			System.out.println(Arrays.toString(row));
		}
		
		return lenOfLCS;
	}
	
	public int getLCSUsingIterationAndSpaceOptimizedTabulation(String str1, String str2) {
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();
		
		int str1Len = str1.length(), str2Len = str2.length(),
				maxLen = HelperClass.max(str1Len, str2Len);
		
		int dp[][] = new int[2][maxLen];
		
		System.out.println("String 1:|"+str1+"| Len: "+str1.length());
		System.out.println("String 2:|"+str2+"| Len: "+str2.length());
		
		resetMetrics();
		
		int lenOfLCS;
		if(str1Len > str2Len)
			lenOfLCS = findLCSUsingIterationAndSpaceOptimizedTabulation(ch2, ch1, str2Len-1, str1Len-1, dp);
		else
			lenOfLCS = findLCSUsingIterationAndSpaceOptimizedTabulation(ch1, ch2, str1Len-1, str2Len-1, dp);
	
		System.out.println("outerForLoopCount: "+outerForLoopCount);
		System.out.println("innerForLoopCount: "+innerForLoopCount);
		System.out.println("Printing dp[][] ...");
		for(int row[]: dp) {
			System.out.println(Arrays.toString(row));
		}
		
		return lenOfLCS;
	
	}
	
	private void resetMetrics() {
		noOfRecursiveCalls = 0;
		innerForLoopCount = 0;
		outerForLoopCount = 0;
	}

	
	public static void main(String[] args) {
		
		String str1 = "abcd";
		String str2 = "ijac";
		// abghcef aghef
		// abbcdebbcd bcaaedcd

		LongestCommonSubsequence lcs = new LongestCommonSubsequence(); 
		
//		int lengthOfLCS = lcs.findLCS(str1, str2);
		int lengthOfLCS = lcs.getLCSUsingRecursionAndMemoization(str1, str2);
		
		System.out.println("lengthOfLCS: "+lengthOfLCS);
		System.out.println("noOfRecursiveCalls: "+lcs.noOfRecursiveCalls);

	}

}

/*
 * "bd", "abcd";
 * 
 * 
 * 
 */