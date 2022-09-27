package test_algorithms.test_subsequences_algorithms;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.subsequences_algorithms.LongestIncreasingSubArray;

public class LongestIncreasingSubArray_Test {
	
	@Test
	public void testGetLISALen() {
		int inputSequences[][] = {
				{5, 7, 10, 6, 9, 12, 14, -1, 0, 1, 3, 8, 9},
				{},
				{3, 7, 10, 12, 4, 6, 8, 1},
				{1, 2, 3, -3, -2, -1},
				{1}
		};
		
		int expectedResults[] = {6, 0, 4, 3, 1};
		
		LongestIncreasingSubArray lisa = new LongestIncreasingSubArray();
		
		System.out.println("Inside testGetLISALen():");
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			int actualLISALen = lisa.getLISALen(inputSequences[i]);
			Assert.assertEquals(expectedResults[i], actualLISALen);
			System.out.println();
		}
	}
	
	@Test
	public void testPrintLISA() {
		int inputSequences[][] = {
				{5, 7, 10, 6, 9, 12, 14, -1, 0, 1, 3, 8, 9},
				{},
				{3, 7, 10, 12, 4, 6, 8, 1},
				{1, 2, 3, -3, -2, -1},
				{1}
		};
		
		int expectedResults[][][] = {
				{{-1, 0, 1, 3, 8, 9}},
				{},
				{{3, 7, 10, 12}},
				{{1, 2, 3}, {-3, -2, -1}},
				{{1}}
		};
		
		LongestIncreasingSubArray lisa = new LongestIncreasingSubArray();
		
		System.out.println("Inside testGetLISALen():");
	
		for(int i=0; i<inputSequences.length; i++) {
			System.out.println("Sequence number: "+(i+1));
			ArrayList<ArrayList<Integer>> actualLISAList = lisa.printLISA(inputSequences[i]);
			
			System.out.println("actualLISAList: "+actualLISAList);
			Assert.assertEquals("Number of solutions don't match!", expectedResults[i].length, actualLISAList.size());
			for(int row[]: expectedResults[i]) {
				ArrayList<Integer> expectedLISA = new ArrayList<>();
				for(int j:row) {
					expectedLISA.add(j);
				}
				Assert.assertEquals(true, actualLISAList.contains(expectedLISA));
			}
			System.out.println();
		}
	}
}
