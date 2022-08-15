package test_algorithms.test_tree_algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import algorithms.tree_algorithms.Node;
import algorithms.tree_algorithms.Node.Color;
import algorithms.tree_algorithms.RedBlackTree;
import algorithms.tree_algorithms.BinarySearchTree.TraversalType;

public class RedBlackTree_Test {

	@Test
	public void testAddMethod() {
		
		int input[][] = {
				{6, 3, 14, 2, 4, 19, 1, 8, -1, 5, -9, 20, -2},
				{10, 18, 7, 15, 16, 30, 25, 40, 60, 2, 1, 70}
		};
		
		int output[][] = {
				{3, 1, 6, -2, 2, 4, 14, -9, -1, 5, 8, 19, 20},
				{16, 10, 25, 2, 15, 18, 40, 1, 7, 30, 60, 70}
		};
		
		/* This colors[][] array contain the color of each 
		 * corresponding nodes encountered in Level Order traversal of 
		 * the Red Black tree, as an array.
		 * Here "r" is the symbol for "RED" and "b" for "BLACK".
		 */
		String colors[][] = {
				{"b", "b", "b", "b", "b", "b", "r", "r", "r", "r", "b", "b", "r"},
				{"b", "b", "b", "b", "b", "b", "r", "r", "r", "b", "b", "r"}
				
		};
		
		for(int row=0; row<input.length; row++) {
			RedBlackTree tree = new RedBlackTree();
			
			/* Here we are creating our Red Black tree with the 
			 * items in input[row].
			 */
			for(int j=0; j<input[row].length; j++) {
				tree.add(input[row][j]);
			}
			
			List<Node> expectedResult = new ArrayList<>();
			
			/* Here we create the expectedResult list which will 
			 * contain the nodes in the same order that we will get 
			 * while the Level Order traversal of the Red Black tree.
			 * 
			 * The expectedResult list is created using the array
			 * output[row] and colors[row].
			 */
			for(int k=0 ; k<output[row].length; k++) {
				String colorSymbol = colors[row][k], color = null;
				if(colorSymbol.equals("r")) {
					color = "RED";
				}
				else if(colors[row][k].equals("b")) {
					color = "BLACK";
				}
				else {
					throw new IllegalArgumentException("Value for colors"
							+ " can be only \"r\" or \"b\" but found "
							+ colorSymbol +" at colors["+row+"]["+k+"]."
							);
				}
				expectedResult.add(new Node(output[row][k], Color.valueOf(color)));
			}
			
			LinkedList<Node> actualResult = null;
			actualResult = tree.getListView(tree.getRoot(), TraversalType.LEVELORDER);
			System.out.println("-------------------Testing add method");
			System.out.println("expectedResult: "+expectedResult);
			System.out.println("actualResult: "+actualResult);
			
			Assert.assertEquals("Actual and expected result don't match!", true, actualResult.equals(expectedResult));	
			
		}
		
	}
}
