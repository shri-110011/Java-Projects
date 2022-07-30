package test_algorithms.test_tree_algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.tree_algorithms.BinarySearchTree;
import algorithms.tree_algorithms.BinarySearchTree.TraversalType;

public class BST_Test {
	
	@Test
	public void testAddMethod() {
		int a[] = {12, 8, 16, 2, 14, 19, 1, 17, 20};
		// {12, 8, 16, 2, 10, 14, 19, 1, 3, 9, 11, 13, 15, 17, 20}
		// {10, 3, 15, 2, 13, 19, 1, 16, 20}
		// {234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		// {6,3,14,2,4,19,1,8,-1,5,-9,20,-2}
		
		LinkedList<Integer> expectedResult = new LinkedList<Integer>();
		for(int i=0; i<a.length; i++) {
			expectedResult.add(a[i]);
		}
		
		BinarySearchTree bst = new BinarySearchTree();
		
		for(int i: a) {
			bst.add(i);
		}
		
		LinkedList<Integer> actualResult = null;
		actualResult = bst.getListView(bst.getRoot(), TraversalType.LEVELORDER);
		System.out.println("-------------------Testing add method");
		System.out.println("expectedResult: "+expectedResult);
		System.out.println("actualResult: "+actualResult);
		
		Assert.assertEquals("Actual and expected result don't match!", true, actualResult.equals(expectedResult));	
	}
	
	@Test
	public void testRemoveMethod() {
		int a[] = {12, 8, 16, 2, 14, 19, 1, 17, 20};
		LinkedList<Integer> expectedResult = new LinkedList<Integer>();
		for(int i=0; i<a.length; i++) {
			expectedResult.add(a[i]);
		}
		
		Collections.sort(expectedResult);
		
		Integer element_to_be_removed = 17;
		
		expectedResult.remove(element_to_be_removed);
		
		BinarySearchTree bst = new BinarySearchTree();
		
		for(int i: a) {
			bst.add(i);
		}
		bst.remove(element_to_be_removed);
		
		LinkedList<Integer> actualResult = null;
		actualResult = bst.getListView(bst.getRoot(), TraversalType.INORDER);
		
		System.out.println("-------------------Testing remove method");
		System.out.println("expectedResult: "+expectedResult);
		System.out.println("actualResult: "+actualResult);
		
		Assert.assertEquals("Actual and expected result don't match!", true, actualResult.equals(expectedResult));	
		
	}

}
