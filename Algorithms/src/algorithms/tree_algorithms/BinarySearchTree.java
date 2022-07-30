package algorithms.tree_algorithms;

import java.util.LinkedList;



public class BinarySearchTree {
	
	public enum TraversalType {
		INORDER, PREORDER, POSTORDER, LEVELORDER
	}
	
	Node root;
	
	public Node getRoot() {
		return root;
	}

	public BinarySearchTree() {
		root = null;
	}
	
	// This method adds a node to the BinarySearchTree.
	public void add(int data) {
		root = addNode(root, data);
//		System.out.println("Node added!");
	}

	public Node addNode(Node root, int data) {
		
		if(root == null) {
			root = new Node(data);
			return root;
		}
		else {
			Node temp = root;
			while(temp != null) {
				// If data == temp's info
				if(data == temp.m_info) {
					throw new IllegalArgumentException("Duplicate key:"+data+" provided!");
				}
				else {
					/* Only if temp's left child is empty we will do the following:
					 * create a new node by passing the data
					 * make it as left child of temp
					 */
					if(data < temp.m_info) {
						if(temp.m_left_child == null) {
							temp.m_left_child = new Node(data);
							break;
						}
						temp = temp.m_left_child;		
					}
					/* Only if temp's right child is empty we will do the following:
					 * create a new node by passing the data
					 * make it as right child of temp
					 */
					else {
						if(temp.m_right_child == null) {
							temp.m_right_child = new Node(data);
							break;
						}
						temp = temp.m_right_child;
					}
					
				}
			}
			return root;
		}
		
	}
	
	// This method returns the in order predecessor of the node having 
	// info = data.
	public Node getInorderPredecessor(int data) {
		if(root != null) {
			Node temp = root, inOrderPredecessor = null;

			while(temp != null) {
				if(temp.m_info == data) {
					temp = temp.m_left_child;
				}
				else if(data < temp.m_info){
					temp = temp.m_left_child;
				}
				else {
					inOrderPredecessor = temp;
					temp = temp.m_right_child;
				}
			}
			return inOrderPredecessor;
		}
		else {
			System.out.println("Can't find inorder predecessor because root of BST is null!");
			return null;
		}
	}
	
	// This method removes the node having info = data.
	public void remove(int data) {
		root = removeNode(root, data);
//		System.out.println("Node removed!");
	}
	
	public Node removeNode(Node node, int data) {
		if(node == null) {
			return null;
		}
		else {
			if(data < node.m_info) {
				node.m_left_child = removeNode(node.m_left_child, data);
			}
			else if(data > node.m_info) {
				node.m_right_child = removeNode(node.m_right_child, data);
			}
			else {
				if(node.m_left_child == null && node.m_right_child == null) {
					return null;
				}
				else if(node.m_left_child == null) {
					return node.m_right_child;
				}
				else if(node.m_right_child == null) {
					return node.m_left_child;
				}
				else {
					Node temp = getInorderPredecessor(data);
					node.m_info = temp.m_info;
					removeNode(node.m_left_child, temp.m_info);
				}
			}
			return node;
		}

	}
	
	/* This method prints node at level specified by level.
	 * 
	 * spaceBN is used to specify space between internal nodes.
	 * spaceBN is used when we want to print the binary tree pattern.
	 * 
	 * When this method is used to print the binary tree pattern then the
	 * tree is printed like below:
	 * 
	 *                     7
	 *                   
	 *                   5   9
	 */
	 
	 private void printNodesAtLevel(Node root, int level, boolean isLastLevel, int ...spaceBN) {
 		
 		if(root == null) return;
 		else {
 			if(level == 0) {
 				System.out.print(root.m_info);
 				if(spaceBN.length == 0)
 					System.out.print(" ");
 				return;
 			}
 			else {
 				printNodesAtLevel(root.m_left_child, level-1, isLastLevel, spaceBN);

 				if(spaceBN.length > 0) {
 					
 					if(!isLastLevel) {
 						if(root.m_left_child == null) System.out.print(" ");
 					}
 					else {
 						if(root.m_left_child == null) {
 							int noOfNodesEliminated = (int)Math.pow(2, level-1);
 							printSpace((noOfNodesEliminated-1)*3+noOfNodesEliminated);
 						}
 					}
 					
 					printSpace(spaceBN[0]);
 					
 					
 				}
 				
 				
 				printNodesAtLevel(root.m_right_child, level-1, isLastLevel, spaceBN);
 				if(spaceBN.length > 0) {
 					
 					if(!isLastLevel) {
 						if(root.m_right_child == null) System.out.print(" ");
 					}
 					else {
 						if(root.m_right_child == null) {
 							int noOfNodesEliminated = (int)Math.pow(2, level-1);
 							printSpace((noOfNodesEliminated-1)*3+noOfNodesEliminated);
 						}
 					}
 					
 				}
 			}
 		}
 		
 	}
 	
	// This method return a LinkedList containing the nodes at level
    // specified by level in the level order.
 	private void getNodesAtLevel(Node root, int level, LinkedList<Integer> list) {
 		
 		if(root == null) return;
 		else {
 			if(level == 0) {
 				list.add(root.m_info);
 				return;
 			}
 			else {
 				getNodesAtLevel(root.m_left_child, level-1, list);
 				getNodesAtLevel(root.m_right_child, level-1, list);
 			}
 		}
 		
 	}
 	
 	/* This method prints the binary tree pattern without showing the edges
 	 * and don't use it for printing binary trees with height greater than 4.
 	 * Otherwise the printed binary tree becomes unmanageable and difficult
 	 * to visualize.
 	 */
 	
 	public void printBinaryTree(Node node) {
 		
 		int height, max_leaf_nodes, width, current_leading_space = -1, 
 		next_leading_space=-1, spaceBN = 3 , noOfNewLines;
 		
 		height = getTreeHeight(node);
 		max_leaf_nodes = (int)Math.pow(2, height);
 		width = spaceBN*(max_leaf_nodes-1)+max_leaf_nodes;

 		System.out.println("width: "+width);
 		for(int level=0; level<=height; level++) {
 			if(level == 0) {
 				current_leading_space = width/((int)Math.pow(2, level+1));
 				printSpace(current_leading_space);
 				printNodesAtLevel(node, level, false);
 				
 			}
 			else {
 				current_leading_space = width/((int)Math.pow(2, level+1))-1;
 				printSpace(current_leading_space);
 				if(level != height) {
 					spaceBN = (width/(int)Math.pow(2, level));
 					printNodesAtLevel(node, level, false, spaceBN);
 				}
 					
 				else {
 					spaceBN = 3;
 					printNodesAtLevel(node, level, true, spaceBN);
 				}
 			}
 			if(level<height) {
 				next_leading_space = width/((int)Math.pow(2, level+2))-1;
 				noOfNewLines = current_leading_space - next_leading_space - 1;
// 				System.out.println("noOfNewLines:|"+noOfNewLines+"|");
 				while(noOfNewLines>0) {
 					System.out.println();
 					noOfNewLines--;
 				}
 			}
 			System.out.println();
 			
 		}
 		
 	}
 	
 	// This method prints the space as many times specified by noOfTimes.
 	private void printSpace(int noOfTimes) {
 		while(noOfTimes > 0) {
 			System.out.print(" ");
 			noOfTimes--;
 		}
 	}
 	
 	/* This method is used to print the BinarySearchTree pattern as a 
 	 * horizontal tree.
 	 * 
 	 * This method specially print the root node as:
 	 *             root
 	 *             |--
 	 * instead of
 	 *             root
 	 *                |-- 
 	 */
 	private String traversePreorder(Node root) {
 		
 		if(root == null)
 			return "";
 		
 		StringBuilder sb = new StringBuilder();
 		sb.append(root.m_info);
 		
 		String pointerForLeft = root.m_right_child != null? "├──" : "└──";
 		String pointerForRight = "└──";
 		

 		 traverseNodes(sb, root.m_left_child, "", pointerForLeft, root.m_right_child != null);
	     traverseNodes(sb,  root.m_right_child, "", pointerForRight, false);
 		
 		return sb.toString();
 	}
 	
 	
 	/* This method is used to print the BinarySearchTree pattern as a 
 	 * horizontal tree.
 	 * This is the method that prints all the nodes of BinarySearchTree but 
 	 * the root.
 	 */
 	private void traverseNodes(StringBuilder sb, Node node, String padding, String pointer, boolean hasRightSibling) {
 		if(node != null) {
 			sb.append("\n");
	        sb.append(padding);
	        sb.append(pointer);
	        sb.append(node.m_info);
	        
	        StringBuilder paddingBuilder = new StringBuilder(padding);
	        if(hasRightSibling)
	        	paddingBuilder.append("│  ");
	        else
	        	paddingBuilder.append("   ");

	        String paddingForBoth = paddingBuilder.toString();
	        String pointerForRight = "└──";
	        String pointerForLeft = (node.m_right_child != null) ? "├──" : "└──";

	        traverseNodes(sb, node.m_left_child, paddingForBoth, pointerForLeft, node.m_right_child != null);
	        traverseNodes(sb, node.m_right_child, paddingForBoth, pointerForRight, false);
	        
 		}
 		
 	}
 	
 	/* This method prints the BinarySearchTree pattern as a horizontal tree and this
 	 * tree pattern is the best to visualize a binary tree of any height.
 	 * The only disadvantage being that it takes time to interpret which
 	 * child is right child or left child.
 	 */
 	public void printBT() {
 		System.out.println(traversePreorder(root));
 	}
 	
 	/* This method returns the BinarySearchTree height starting from the 
 	 * node specified by node.
 	 */
 	public int getTreeHeight(Node node) {
 		if(node == null) return -1;
 		else {
 			int left_subtree_height = getTreeHeight(node.m_left_child);
 			int right_subtree_height = getTreeHeight(node.m_right_child);
 			
 			if(left_subtree_height < right_subtree_height) {
 				return right_subtree_height + 1;
 			}
 			else {
 				return left_subtree_height + 1;
 			}
 		}
 	}

 	/* This method displays the BinarySearchTree starting from the node 
 	 * specified by node and in the order specified by the TraversalType 
 	 * enum: type.
 	 * 
 	 */
 	public void displayBST(Node node, TraversalType type) {
		if(node == null) {
			return;
		}
		
		if(type == TraversalType.INORDER) {
			// Keep on going to the root of left subtree
			displayBST(node.m_left_child, type);
			
			System.out.print(node.m_info+" ");
			
			// Keep on going to the root of right subtree
			displayBST(node.m_right_child, type);
		}
		else if(type == TraversalType.PREORDER) {
			System.out.print(node.m_info+" ");
			
			// Keep on going to the root of left subtree
			displayBST(node.m_left_child, type);
		
			// Keep on going to the root of right subtree
			displayBST(node.m_right_child, type);
		}
		else if(type == TraversalType.POSTORDER) {
			// Keep on going to the root of left subtree
			displayBST(node.m_left_child, type);
			
			// Keep on going to the root of right subtree
			displayBST(node.m_right_child, type);
			
			System.out.print(node.m_info+" ");
		}
		else if(type == TraversalType.LEVELORDER) {
			int height = getTreeHeight(root);
			for(int i=0; i<=height; i++) {
				printNodesAtLevel(root, i, false);
			}
		}
		
	}
 	
 	/* This method traverse the BinarySearchTree starting from the node 
 	 * specified by node and in the order specified by the TraversalType 
 	 * enum: type to populate the LinkedList.
 	 * 
 	 */
 	private void traverseNodes(Node node, TraversalType type, LinkedList<Integer> list) {
		if(node == null) {
			return;
		}
		
		if(type == TraversalType.INORDER) {
			// Keep on going to the root of left subtree
			traverseNodes(node.m_left_child, type, list);
			
			list.add(node.m_info);
			
			// Keep on going to the root of right subtree
			traverseNodes(node.m_right_child, type, list);
		}
		else if(type == TraversalType.PREORDER) {
			list.add(node.m_info);
			
			// Keep on going to the root of left subtree
			traverseNodes(node.m_left_child, type, list);
		
			// Keep on going to the root of right subtree
			traverseNodes(node.m_right_child, type, list);
		}
		else if(type == TraversalType.POSTORDER) {
			// Keep on going to the root of left subtree
			traverseNodes(node.m_left_child, type, list);
			
			// Keep on going to the root of right subtree
			traverseNodes(node.m_right_child, type, list);
			
			list.add(node.m_info);
		}
		else if(type == TraversalType.LEVELORDER) {
			int height = getTreeHeight(root);
			for(int i=0; i<=height; i++) {
				getNodesAtLevel(root, i, list);
			}
		}
		
	}
 	
 	/* This method returns the LinkedList view of the BinarySearchTree
 	 * where the list contains elements in the order as the BinarySearchTree
 	 * is traversed.
 	 * 
 	 * The order in which the BinarySearchTree is traversed is determined by 
 	 * TraversalType enum: type.
 	 * 
 	 */
 	public LinkedList<Integer> getListView(Node node, TraversalType type) {
 		LinkedList<Integer> list = new LinkedList<Integer>();
 		if(node == null) {
			return list;
		}
 		traverseNodes(node, type, list);
 		return list;
 	}

	public static void main(String[] args) {
		int a[] = {6,3,14,2,4,19,1,8,-1,5,-9,20,-2};
		
		BinarySearchTree bst = new BinarySearchTree();
		
		for(int i=0; i<a.length; i++) {
			bst.add(a[i]);
		}
		
//		bst.displayBST(bst.root, TraversalType.POSTORDER);
		//System.out.println();
		bst.printBT();
//		bst.printBinaryTree(bst.root);
//		bst.printBT();
		
		
	}

}

/*
 *                                     6
 *                             3            14
 *                          2    4       8       19
 *                        1        5                20
 *                      -1
 *                   -9
 *                      -2
 * 
 * 
 *                            5
 *                           / \
 *                          4   7
 *                         /    /
 *                       -1    6
 *                       
 *                      
 *                     
 *                                       5
 *                                      / \
 *                                     /   \
 *                                    /     \
 *                                   3       8
 *                                  / \     / \
 *                                 1   4   7   9
 *                                 
 *                                 
 *                                 
 *         
 *                                 
                                                                                                                              6
                                                              3                                                                                                                               14
                              2                                                                4                                                                8                                                                19
              1                                                                                                  5                                                                                                                                   20
      -1                                                                                                                                                                          
  -9                                                                                                   
    -2                                            
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *                                 
 *
                                            
 * */

