package test_algorithms.test_subsequences_algorithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import algorithms.subsequences_algorithms.MaximumSumIncreasingSubsequence;

public class MaximumSumIncreasingSubsequence_Test {

	@Test
	public void testGetMSISUsingRecursion() {
		int inputSequences[][] = {
				{8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6},
				{1, 101, 2, 100},
				{1, 6, 4, 8, 7, 7, 13, 9},
				{}
				
		};
		int expectedResults[] = {29, 103, 28, 0};
		
		MaximumSumIncreasingSubsequence msis = new MaximumSumIncreasingSubsequence();
		
		System.out.println("Inside testGetMSISUsingRecursion()");
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence Number: "+(i+1));
			int actualResult = msis.getMSISUsingRecursion(inputSequences[i]);
		
			Assert.assertEquals(expectedResults[i], actualResult);
			System.out.println();
		}
	}
	
	@Test
	public void testGetMSISUsingRecursionAndTabulation() {
		int inputSequences[][] = {
				{8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6},
				{1, 101, 2, 100},
				{1, 6, 4, 8, 7, 7, 13, 9},
				{}
				
		};
		int expectedResults[] = {29, 103, 28, 0};
		
		MaximumSumIncreasingSubsequence msis = new MaximumSumIncreasingSubsequence();
		
		System.out.println("Inside testGetMSISUsingRecursionAndTabulation()");
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence Number: "+(i+1));
			int actualResult = msis.getMSISUsingRecursionAndTabulation(inputSequences[i]);
		
			Assert.assertEquals(expectedResults[i], actualResult);
			System.out.println();
		}
	}
	
	@Test
	public void testGetMSISUsingIterationAndTabulation() {
		int inputSequences[][] = {
				{8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6},
				{1, 101, 2, 100},
				{1, 6, 4, 8, 7, 7, 13, 9},
				{}
				
		};
		int expectedResults[] = {29, 103, 28, 0};
		
		MaximumSumIncreasingSubsequence msis = new MaximumSumIncreasingSubsequence();
		
		System.out.println("Inside testGetMSISUsingIterationAndTabulation()");
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence Number: "+(i+1));
			int actualResult = msis.findMSISUsingIterationAndTabulation(inputSequences[i]);
		
			Assert.assertEquals(expectedResults[i], actualResult);
			System.out.println();
		}
	}
	
	@Test
	public void testPrintMSIS() {
		int inputSequences[][] = {
				{8, 4, 6, 2, 9, 1, 2, 5, 7, 10, 7, 8, 6},
				{1, 101, 2, 100},
				{1, 6, 4, 8, 7, 7, 13, 9},
				{}
				
		};
		int expectedResults[][] = {
				{4, 6, 9, 10},
				{1, 2, 100},
				{1, 6, 8, 13},
				{}
		};
		
		MaximumSumIncreasingSubsequence msis = new MaximumSumIncreasingSubsequence();
		
		System.out.println("Inside testPrintMSIS()");
		
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence Number: "+(i+1));
			
			List<Integer> expectedList = new ArrayList<>();
			for(int j=0; j<expectedResults[i].length; j++) {
				expectedList.add(expectedResults[i][j]);
			}
			
			List<Integer> actualList = msis.printMSIS(inputSequences[i]);
			
			System.out.println("Expected result: "+expectedList);
			Assert.assertEquals(true, actualList.equals(expectedList));
			System.out.println();
		}
	}
}
