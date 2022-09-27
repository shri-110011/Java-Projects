package test_algorithms.test_subsequences_algorithms;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.subsequences_algorithms.LongestCommonSubsequence;

public class LongestCommonSubsequence_Test {
	
	@Test
	public void testGetLCSUsingRecursion() {
		
		String inputArrayOfStringPairs[][] = {
				{"abcd", "ijac"},
				{"", ""},
				{"abbcdebbcd", "bcaaedcd"},
				{"abghcef", "aghef"},
				{"abcd", "bd"}
		};
		int expectedResults[] = {2, 0, 5, 5, 2}, count = 0;
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		System.out.println("Inside testGetLCSUsingRecursion():");
		for(String stringPair[]: inputArrayOfStringPairs) {
			System.out.println("String pair: "+(count+1));
			int actualLenOfLCS = lcs.getLCSUsingRecursion(stringPair[0], stringPair[1]);
			System.out.println("actualLenOfLCS: "+actualLenOfLCS);
			System.out.println("expectedLenOfLCS: "+expectedResults[count]);
			
			Assert.assertEquals(expectedResults[count], actualLenOfLCS);
			
			count++;
			System.out.println();
		}
		
	}
	
	@Test
	public void testGetLCSUsingRecursionAndMemoization() {
		
		String inputArrayOfStringPairs[][] = {
				{"abcd", "ijac"},
				{"", ""},
				{"abbcdebbcd", "bcaaedcd"},
				{"abghcef", "aghef"},
				{"abcd", "bd"}
		};
		int expectedResults[] = {2, 0, 5, 5, 2}, count = 0;
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		System.out.println("Inside testGetLCSUsingRecursionAndMemoization():");
		for(String stringPair[]: inputArrayOfStringPairs) {
			System.out.println("String pair: "+(count+1));
			int actualLenOfLCS = lcs.getLCSUsingRecursionAndMemoization(stringPair[0], stringPair[1]);
			System.out.println("actualLenOfLCS: "+actualLenOfLCS);
			System.out.println("expectedLenOfLCS: "+expectedResults[count]);
			
			Assert.assertEquals(expectedResults[count], actualLenOfLCS);
			
			count++;
			System.out.println();
		}
		
	}
	
	@Test
	public void testGetLCSUsingIterationAndTabulation() {
		
		String inputArrayOfStringPairs[][] = {
				{"abcd", "ijac"},
				{"", ""},
				{"abbcdebbcd", "bcaaedcd"},
				{"abghcef", "aghef"},
				{"abcd", "bd"}
		};
		int expectedResults[] = {2, 0, 5, 5, 2}, count = 0;
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		System.out.println("Inside testGetLCSUsingIterationAndTabulation():");
		for(String stringPair[]: inputArrayOfStringPairs) {
			System.out.println("String pair: "+(count+1));
			int actualLenOfLCS = lcs.getLCSUsingIterationAndTabulation(stringPair[0], stringPair[1]);
			System.out.println("actualLenOfLCS: "+actualLenOfLCS);
			System.out.println("expectedLenOfLCS: "+expectedResults[count]);
			
			Assert.assertEquals(expectedResults[count], actualLenOfLCS);
			
			count++;
			System.out.println();
		}
		
	}
	
	@Test
	public void testGetLCSUsingIterationAndSpaceOptimizedTabulation() {
		
		String inputArrayOfStringPairs[][] = {
				{"abcd", "ijac"},
				{"", ""},
				{"abbcdebbcd", "bcaaedcd"},
				{"abghcef", "aghef"},
				{"abcd", "bd"}
		};
		int expectedResults[] = {2, 0, 5, 5, 2}, count = 0;
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		System.out.println("Inside testGetLCSUsingIterationAndTabulation():");
		for(String stringPair[]: inputArrayOfStringPairs) {
			System.out.println("String pair: "+(count+1));
			int actualLenOfLCS = lcs.getLCSUsingIterationAndSpaceOptimizedTabulation(stringPair[0], stringPair[1]);
			System.out.println("actualLenOfLCS: "+actualLenOfLCS);
			System.out.println("expectedLenOfLCS: "+expectedResults[count]);
			
			Assert.assertEquals(expectedResults[count], actualLenOfLCS);
			
			count++;
			System.out.println();
		}
		
	}
	
	@Test
	public void testPrintLCS() {
		String inputArrayOfStringPairs[][] = {
				{"abcd", "ijac"},
				{"", ""},
				{"abbcdebbcd", "bcaaedcd"},
				{"abghcef", "aghef"},
				{"abcd", "bd"}
		};
		
		String expectedResults[][] = {
				{"ac"},
				{},
				{"bcecd", "bcdcd"},
				{"aghef"},
				{"bd"}
		};
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int count = 0;
		System.out.println("Inside testPrintLCS():");
		for(String stringPair[]: inputArrayOfStringPairs) {
			System.out.println("String pair: "+(count+1));
			ArrayList<String> actualListOfLCS = lcs.printLCS(stringPair[0],stringPair[1]);
			
			Assert.assertEquals(expectedResults[count].length, actualListOfLCS.size());
			for(String commonStr:expectedResults[count]) {
				Assert.assertEquals(true, actualListOfLCS.contains(commonStr));
			}
			count++;
			System.out.println();
		}
	}


}
