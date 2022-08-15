package test_algorithms.test_tree_algorithms;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.tree_algorithms.BinarySearchTree;
import algorithms.tree_algorithms.BinarySearchTree.TraversalType;
import algorithms.tree_algorithms.Node;
import utilities.HelperClass;

public class BST_Test {
	
	@Test
	public void testAddMethod() {
		int input[][] = {
				{12, 8, 16, 2, 14, 19, 1, 17, 20},
				{6, 3, 14, 2, 4, 19, 1, 8, -1, 5, -9, 20, -2},
				{234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		}, no_of_test_cases = input.length;
		
		// {12, 8, 16, 2, 10, 14, 19, 1, 3, 9, 11, 13, 15, 17, 20}
		// {10, 3, 15, 2, 13, 19, 1, 16, 20}
		// {234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		// {6,3,14,2,4,19,1,8,-1,5,-9,20,-2}
		
		int output[][] = {
				{12, 8, 16, 2, 14, 19, 1, 17, 20},
				{6, 3, 14, 2, 4, 8, 19, 1, 5, 20, -1, -9, -2},
				{234, 123, 567, 45, 543, 1001, 46, 678, 4567, 789, 1234, 2345}
		};
		
		for(int row=0; row<no_of_test_cases; row++) {
			LinkedList<Node> expectedResult = new LinkedList<>();
			for(int i=0; i<input[row].length; i++) {
				expectedResult.add(new Node(output[row][i]));
			}
			BinarySearchTree bst = new BinarySearchTree();
			
			for(int i: input[row]) {
				bst.add(i);
			}
			
			LinkedList<Node> actualResult = null;
			actualResult = bst.getListView(bst.getRoot(), TraversalType.LEVELORDER);
			System.out.println("-------------------Testing add method");
			System.out.println("expectedResult: "+expectedResult);
			System.out.println("actualResult: "+actualResult);
			
			Assert.assertEquals("Actual and expected result don't match!", true, actualResult.equals(expectedResult));	
		}

	}
	
	@Test
	public void testRemoveMethod() {
		int input[][] = {
				{12, 8, 16, 2, 14, 19, 1, 17, 20},
				{10, 3, 15, 2, 13, 19, 1, 16, 20},
				{234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		},
		no_of_test_cases = input.length;
		
		for(int row=0; row<no_of_test_cases; row++) {
			LinkedList<Node> expectedResult = new LinkedList<>();
			
			int curInputLen = input[row].length;
			for(int i=0; i<curInputLen; i++) {
				expectedResult.add(new Node(input[row][i]));
			}
			
			Collections.sort(expectedResult);
			
			BinarySearchTree bst = new BinarySearchTree();
			
			for(int i: input[row]) {
				bst.add(i);
			}
			
			/* We delete:
			 * 3 nodes from the BST if the number of nodes in the BST 
			 * is greater than 6.
			 * 2 node from the BST if the number of nodes in the BST 
			 * is less than or equal to 6.
			 * 0 nodes from the BST if the number of nodes in the BST 
			 * is 0.
			 * 
			 */
			int noOfElementsToBeRemoved = curInputLen>0 ? curInputLen>6 ? 3:1 : 0, len = curInputLen;
			for(int j=0; j<noOfElementsToBeRemoved; j++) {
				int randomIndex = HelperClass.generateRandomNumber(0, len);
				Integer elementToBeRemoved = expectedResult.get(randomIndex).m_info;
				
				expectedResult.remove(new Node(elementToBeRemoved));
				
				bst.remove(elementToBeRemoved);
				
				LinkedList<Node> actualResult = null;
				actualResult = bst.getListView(bst.getRoot(), TraversalType.INORDER);
				
				System.out.println("-------------------Testing remove method");
				System.out.println("elementToBeRemoved: "+elementToBeRemoved);
				System.out.println("expectedResult: "+expectedResult);
				System.out.println("actualResult: "+actualResult);
				
				Assert.assertEquals("Actual and expected result don't match!", true, actualResult.equals(expectedResult));
				
				/* len contains the length of the expectedResult list
				 * so after every deletion of a node from BST the
				 * length of expectedResult also reduces by 1 because 
				 * the node also gets removed from the expectedResult 
				 * list.
				 */
				len--;
			}
		}
		
	}

}
