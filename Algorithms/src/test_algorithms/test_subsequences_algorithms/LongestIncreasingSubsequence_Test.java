package test_algorithms.test_subsequences_algorithms;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.subsequences_algorithms.LongestIncreasingSubsequence;

public class LongestIncreasingSubsequence_Test {
	
	@Test
	public void testGetLISLenUsingRecursion() {
		
		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[] = {4, 0, 6, 8};
		
		System.out.println("Inside testGetLISLenUsingRecursion():");
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int actualLISLen = 
					lis.getLISLenUsingRecursion(inputSequences[i]),
					expectedLISLen = expectedResults[i];
			System.out.println("actualLISLen: "+actualLISLen);
			System.out.println("expectedLISLen: "+expectedLISLen);
			
			Assert.assertEquals(expectedLISLen, actualLISLen);
			System.out.println();
		}
	}
	
	@Test
	public void testGetLISLenUsingRecursionAndTabulation() {
		
		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[] = {4, 0, 6, 8};
		
		System.out.println("Inside testGetLISLenUsingRecursionAndTabulation():");
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int actualLISLen = 
					lis.getLISLenUsingRecursionAndTabulation(inputSequences[i]),
					expectedLISLen = expectedResults[i];
			System.out.println("actualLISLen: "+actualLISLen);
			System.out.println("expectedLISLen: "+expectedLISLen);
			
			Assert.assertEquals(expectedLISLen, actualLISLen);
			System.out.println();
		}
	}
	
	@Test
	public void testGetLISLenUsingSmartRecursion() {
		
		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[] = {4, 0, 6, 8};
		
		System.out.println("Inside testGetLISLenUsingSmartRecursion():");
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int actualLISLen = 
					lis.getLISLenUsingSmartRecursion(inputSequences[i]),
					expectedLISLen = expectedResults[i];
			System.out.println("actualLISLen: "+actualLISLen);
			System.out.println("expectedLISLen: "+expectedLISLen);
			
			Assert.assertEquals(expectedLISLen, actualLISLen);
			System.out.println();
		}
	}

	@Test
	public void testGetLISLenUsingSmartRecursionAndMemoization() {
		
		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[] = {4, 0, 6, 8};
		
		System.out.println("Inside testGetLISLenUsingSmartRecursionAndMemoization():");
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int actualLISLen = 
					lis.getLISLenUsingSmartRecursionAndMemoization(inputSequences[i]),
					expectedLISLen = expectedResults[i];
			System.out.println("actualLISLen: "+actualLISLen);
			System.out.println("expectedLISLen: "+expectedLISLen);
			
			Assert.assertEquals(expectedLISLen, actualLISLen);
			System.out.println();
		}
	}
	
	@Test
	public void testGetLISLenUsingIterationAndTabulation() {
		
		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[] = {4, 0, 6, 8};
		
		System.out.println("Inside testGetLISLenUsingIterationAndTabulation():");
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int actualLISLen = 
					lis.getLISLenUsingIterationAndTabulation(inputSequences[i]),
					expectedLISLen = expectedResults[i];
			System.out.println("actualLISLen: "+actualLISLen);
			System.out.println("expectedLISLen: "+expectedLISLen);
			
			Assert.assertEquals(expectedLISLen, actualLISLen);
			System.out.println();
		}
	}
	
	@Test
	public void testDisplayLIS() {
		

		int inputSequences[][] = {
				{1, 2, 5, 3, 4},
				{},
				{10, 22, 42, 33, 21, 50, 41, 60, 80, 3},
				{4, 2, 3, 6, 7, -1, 9, 15, 20, 14, 13, 14, 18},
		};
		
		int expectedResults[][][] = {
				{{1, 2, 3, 4}},
				{},
				{
					{10, 22, 33, 50, 60, 80},
					{10, 22, 42, 50, 60, 80},
					{10, 22, 33, 41, 60, 80}
					
				},
				{{2, 3, 6, 7, 9, 13, 14, 18}}
		};
		
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			
			int lisLengths[] = lis.findLISLenUsingIterationAndTabulation(inputSequences[i]);
			ArrayList<ArrayList<Integer>> actualLisList = 
					lis.displayLIS(inputSequences[i], lisLengths);
		
			System.out.println("Expected number of solutions: "+expectedResults[i].length);
			System.out.println("Actual number of solutions: "+actualLisList.size());
			Assert.assertEquals("Number of solutions don't match!", expectedResults[i].length, actualLisList.size());
		
			for(int row[]: expectedResults[i]) {
				ArrayList<Integer> expectedLIS = new ArrayList<>();
				for(int j:row) {
					expectedLIS.add(j);
				}
				Assert.assertEquals(true, actualLisList.contains(expectedLIS));
			}
			System.out.println();
		
		}
		
	}
}
