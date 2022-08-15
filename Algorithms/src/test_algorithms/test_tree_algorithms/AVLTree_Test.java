package test_algorithms.test_tree_algorithms;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import algorithms.tree_algorithms.AVLTree;
import algorithms.tree_algorithms.BinarySearchTree.TraversalType;
import algorithms.tree_algorithms.Node;
import utilities.HelperClass;

public class AVLTree_Test {
	
	@Test
	public void testAddMethod() {
		int input[][] = {
				{6, 3, 14, 2, 4, 19, 1, 8, -1, 5, -9, 20, -2},
				{10, 3, 15, 2, 13, 19, 1, 16, 20},
				{12, 8, 16, 2, 10, 14, 19, 1, 3, 9, 11, 13, 15, 17, 20},
				{234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		};
		// {12, 8, 16, 2, 10, 14, 19, 1, 3, 9, 11, 13, 15, 17, 20}
		// {10, 3, 15, 2, 13, 19, 1, 16, 20}
		// {234, 567, 123, 45, 1001, 4567, 678, 1234, 543, 789, 2345, 46}
		// {6,3,14,2,4,19,1,8,-1,5,-9,20,-2}
		
		int output[][] = {
				{3, 1, 6, -2, 2, 4, 14, -9, -1, 5, 8, 19, 20},
				{10, 2, 15, 1, 3, 13, 19, 16, 20},
				{12, 8, 16, 2, 10, 14, 19, 1, 3, 9, 11, 13, 15, 17, 20},
				{567, 234, 1001, 46, 543, 678, 2345, 45, 123, 789, 1234, 4567}
		};
		// {3, 1, 6, -2, 2, 4, 14, -9, -1, 5, 8, 19, 20}
		
		for(int row=0; row<input.length; row++) {
			LinkedList<Node> expectedResult = new LinkedList<>();
			for(int i=0; i<input[row].length; i++) {
				expectedResult.add(new Node(output[row][i]));
			}
			AVLTree tree = new AVLTree();
			
			for(int i: input[row]) {
				tree.add(i);
			}
			
			LinkedList<Node> actualResult = null;
			actualResult = tree.getListView(tree.getRoot(), TraversalType.LEVELORDER);
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
		};
		
		for(int row=0; row<input.length; row++) {
			LinkedList<Node> expectedResult = new LinkedList<>();
			
			int curInputLen = input[row].length;
			for(int i=0; i<curInputLen; i++) {
				expectedResult.add(new Node(input[row][i]));
			}
			
			Collections.sort(expectedResult);
			
			AVLTree tree = new AVLTree();
			
			for(int i: input[row]) {
				tree.add(i);
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
				
				tree.remove(elementToBeRemoved);
				
				LinkedList<Node> actualResult = null;
				actualResult = tree.getListView(tree.getRoot(), TraversalType.INORDER);
				
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
