package algorithms.tree_algorithms;

import algorithms.tree_algorithms.Node.Color;

public class RedBlackTree extends AVLTree {
	
	boolean flag1, flag2, flag3;
	
	public void add(int data) {
		/* Check if tree is empty or not if it is then create a new node
		 * and assign that to root of the tree.
		 * Also set the color of the root node as black.
		 */
		if(root == null) {
			root = addRBNode(root, data);
			root.nodeColor = Color.BLACK;
		}
		else {
			// Tree is not empty here.
			root = addRBNode(root, data);
		}
	}
	
	private Node addRBNode(Node temp, int data) {
	
		if(temp == null) {
			/* This is the place to insert the new node with 
			 * value specified by data.
			 * Also flag1 is set if a node whose color is RED is met.
			 * And every newly created node has a RED color by default.
			 */
			flag1 = true;
			return new Node(data);
		}
		else {
			/* Here we check where the new node with value specified by 
			 * data is to be added i.e. in the left subtree of temp
			 * or right subtree of temp.
			 * And temp holds the reference of the current node.
			 */
			if(data < temp.m_info) {
				temp.m_left_child = addRBNode(temp.m_left_child, data);
				/* flag2 is set when the child of a parent is RED and 
				 * the parent is also RED.
				 * 
				 * Whether the child of a parent is RED or not that info
				 * we get from the flag1.
				 * 
				 */
				if(flag1 && temp.nodeColor == Color.RED) 
					flag2 = true;
				/* In case the parent is BLACK and child is RED then we
				 * need to reset flag1 because if in the next call stack
				 * the node happens to be RED then flag2 will be set
				 * which is wrong.
				 */
				else if(!flag3)
					flag1 = false;
			}
			else if(data > temp.m_info) {
				temp.m_right_child =  addRBNode(temp.m_right_child, data);
				if(flag1 && temp.nodeColor == Color.RED) 
					flag2 = true;
				else if(!flag3)
					flag1 = false;
			}
			else {
				throw new IllegalArgumentException("Duplicate key:"+data+" provided!");
			}
			
			/* Here we check the status of flag3. When flag3 is set it 
			 * indicates that this node's child and and its grand child 
			 * have a RED-RED collision. In other words flag3 helps to 
			 * determine RED-RED collision b/w parent and child.
			 */
			if(flag3) {
				if(data < temp.m_info) {
					/* Here it is confirmed that the node having value 
					 * specified by data is in the left subtree of temp
					 * which is the current node.
					 * 
					 * So the type of imbalance at this node temp 
					 * would be of Left-Left or Left-Right.
					 */
					if(data < temp.m_left_child.m_info) {
						/* Here it is confirmed that the type of 
						 * imbalance is of Left-Left at the node 
						 * referenced by temp.
						 */
						if(temp.m_right_child == null || 
							temp.m_right_child.nodeColor == Color.BLACK) {
							/* Here it is confirmed that the parent
							 * is RED and its sibling is either null or
							 * BLACK.
							 * So perform left rotation and recolouring.
							 */
							Node temp2 = rightRotate(temp);
							recolour(temp2);
							recolour(temp2.m_right_child);
							flag3 = false;
							return temp2;
						}
						else {
							/* Here it is confirmed that the parent
							 * is RED and its sibling is also RED.
							 * So perform recolouring of parent and
							 * sibling and check if parent's parent is not
							 * root then recolour that as well.
							 */
							recolourChildrenAndCheckParent(temp);
						}
					}
					else {
						if(temp.m_right_child == null || 
							temp.m_right_child.nodeColor == Color.BLACK) {
							/* Here it is confirmed that the type of 
							 * imbalance is of Left-Right at the node 
							 * referenced by temp.
							 * So perform left rotation, right rotation 
							 * and recolouring.
							 */
							temp.m_left_child =  leftRotate(temp.m_left_child);
							Node temp2 = rightRotate(temp);
							recolour(temp2);
							recolour(temp2.m_right_child);
							flag3 = false;
							return temp2;
						}
						else {
							/* Here it is confirmed that the parent
							 * is RED and its sibling is also RED.
							 * So perform recolouring of parent and
							 * sibling and check if parent's parent is not
							 * root then recolour that as well.
							 */
							recolourChildrenAndCheckParent(temp);
						}
					}	
				}
				else {
					/* Here it is confirmed that the node having value 
					 * specified by data is in the right subtree of temp
					 * which is the grandparent of that node.
					 * 
					 * So the type of imbalance at this node temp 
					 * would be of Right-Right or Right-Left.
					 */
					if(data < temp.m_right_child.m_info) {
						if(temp.m_left_child == null || 
							temp.m_left_child.nodeColor == Color.BLACK) {
							/* Here it is confirmed that the type of 
							 * imbalance is of Right-Left at the node 
							 * referenced by temp.
							 * So perform right rotation, left rotation 
							 * and recolouring.
							 */
							temp.m_right_child = rightRotate(temp.m_right_child);
							Node temp2 = leftRotate(temp);
							recolour(temp2);
							recolour(temp2.m_left_child);
							flag3 = false;
							return temp2;
						}
						else {
							/* Here it is confirmed that the parent
							 * is RED and its sibling is also RED.
							 * So perform recolouring of parent and
							 * sibling and check if parent's parent is not
							 * root then recolour that as well.
							 */
							recolourChildrenAndCheckParent(temp);
						}
					}
					else {
						if(temp.m_left_child == null || 
							temp.m_left_child.nodeColor == Color.BLACK) {
							/* Here it is confirmed that the type of 
							 * imbalance is of Right-Right at the node 
							 * referenced by temp.
							 * So perform left rotation and recolouring.
							 */
							Node temp2 = leftRotate(temp);
							recolour(temp2);
							recolour(temp2.m_left_child);
							flag3 = false;
							return temp2;
						}
						else {
							recolourChildrenAndCheckParent(temp);
						}
					}
				}
				flag3 = false;
			}
			
			/* Here we need to check the parent of that node for which 
			 * flag1 was set most recently.
			 * 
			 * flag2 is set when the child of a parent is RED and 
			 * the parent is also RED.
			 */
			if(flag2) {
				flag3 = true;
				flag1 = flag2 = false;
			}
			
			/* Here we are detecting the color of this current node and
			 * setting flag1 if flag3 is not set.
			 * 
			 * This is to account for those cases where the rotations has 
			 * caused RED-RED collisions b/w parent and child during 
			 * backtracking.
			 */
			if(!flag3 && temp.nodeColor == Color.RED) {
				flag1 = true;
			}
			
			return temp;
		}
	}
	
	private void recolourChildrenAndCheckParent(Node node) {
		/* Here node specified by node is the parent.
		 * 
		 * So perform recolouring of tits children and then check if the 
		 * parent is not root then recolour that too.
		 * 
		 */
		if(node != root) {
			recolour(node);
		}
		recolour(node.m_left_child);
		recolour(node.m_right_child);
		
	}
	
	private void recolour(Node node) {
		if(node.nodeColor == Color.BLACK)
			node.nodeColor = Color.RED;
		else
			node.nodeColor = Color.BLACK;
	}
	
	public void delete(int data) {
		root = deleteRBNode(root, data);
	}
	
	private Node deleteRBNode(Node temp, int data) {
		if(temp == null) return null;
		
		if(data < temp.m_info) {
			temp.m_left_child = deleteRBNode(temp.m_left_child, data);
			
			// Copying the node reference in temp to temp2.
			Node temp2 = temp;
			boolean flag = false;
			
			while(temp.m_left_child != null && temp.m_left_child.isDoubleBlack) {
				/* Here temp is the parent of the node that was to be 
				 * deleted.
				 * flag = set indicates that the control came inside this 
				 * while loop.
				 */
				flag = true;
				
				/* Note: temp.m_right_child won't be null because the 
				 * node to be deleted now is the double black DB and
				 * since we are dealing with a RED-BLACK tree hence its
				 * the sibling of DB node which is here 
				 * temp.m_right_child can't be null.
				 */
				int category = findCase(temp.m_right_child, -1);
				
				temp = performOperation(temp, category, -1);
			}
			if(flag) {
				/* This is where the actual deletion of the DB double
				 * node occurs.
				 */
				temp2.m_left_child = null;
			}
		}
		else if(data > temp.m_info) {
			temp.m_right_child = deleteRBNode(temp.m_right_child, data);
			Node temp2 = temp;
			boolean flag = false;
			
			while(temp.m_right_child != null && temp.m_right_child.isDoubleBlack) {
				/* Here temp is the parent of the node that was to be 
				 * deleted.
				 * flag = set indicates that the control came inside this 
				 * while loop.
				 */
				flag = true;
				
				/* Note: temp.m_left_child won't be null because the 
				 * node to be deleted now is the double black DB and
				 * since we are dealing with a RED-BLACK tree hence its
				 * the sibling of DB node which is here 
				 * temp.m_left_child can't be null.
				 */
				
				int category = findCase(temp.m_left_child, 1);
				temp = performOperation(temp, category, 1);
			}
			if(flag) {
				/* This is where the actual deletion of the DB double
				 * node occurs.
				 */
				temp2.m_right_child = null;
			}
		}
		else {
			if(temp.m_left_child == null && temp.m_right_child == null) {
				/* Here we are at the leaf node so first we check its 
				 * color.
				 * 
				 * If color is RED then simply delete the node.
				 */
				if(temp.nodeColor == Color.RED)
					// Deleting the leaf node.
					return null;
				else {
					/* Here the leaf node color is BLACK so we just
					 * set this node as double black node.
					 */
					temp.isDoubleBlack = true;
				}
			}
			else if(temp.m_left_child == null) {
				if(temp.nodeColor == Color.BLACK && 
						temp.m_right_child != null)
					temp.m_right_child.nodeColor = Color.BLACK;
				return temp.m_right_child;
			}
			else if(temp.m_right_child == null) {
				if(temp.nodeColor == Color.BLACK && 
						temp.m_left_child != null)
					temp.m_left_child.nodeColor = Color.BLACK;
				return temp.m_left_child;
			}
			else {
				Node iop = getInorderPredecessor(temp);	
				temp.m_info = iop.m_info;
				temp.m_left_child = deleteRBNode(temp.m_left_child, temp.m_info);
			}
		}
		return temp;
	}
	
	/* int findCase(Node sibling, int posOfDB) method return the case
	 * number to which the DB node falls. Based on that case number we 
	 * perform certain operation on the parent of the DB node.
	 */
	private int findCase(Node sibling, int posOfDB) {
		// Here it is for sure that sibling is not null here.
		
		boolean isSiblingBlack = false, isLeftChildBlack = false, 
				isRightChildBlack = false, hasLeftChild = false, 
				hasRightChild = false;
		isSiblingBlack = sibling.nodeColor == Color.BLACK;
		
		if(sibling.m_left_child != null) {
			hasLeftChild = true;
			if(sibling.m_left_child.nodeColor == Color.BLACK)
				isLeftChildBlack = true;
		}
		
		if(sibling.m_right_child != null) {
			hasRightChild = true;
			if(sibling.m_right_child.nodeColor == Color.BLACK)
				isRightChildBlack = true;
		}
		
		if(isSiblingBlack) {
			// Check if sibling's both children are black or are null.
			if((isLeftChildBlack && isRightChildBlack) || 
					(!hasLeftChild && !hasRightChild)) {
				return 1;
			}
			if(posOfDB == -1) {
				/* Here the double black node is the left sibling of
				 * sibling specified in this method.
				 */
				
				if(isRightChildBlack || !hasRightChild) {
					/* Here the right child of sibling is either BLACK
					 * or null.
					 */
					if(!isLeftChildBlack)
						// Here the left child of sibling is RED.
						return 3;
					else
						// Here the left child of sibling is BLACK.
						return 1;
				}
				else {
					// Here the right child of sibling is RED.
					return 4;
				}
			}
			else {
				/* Here the double black node is the right sibling of
				 * sibling specified in this method.
				 */
				
				if(isLeftChildBlack || !hasLeftChild) {
					/* Here the left child of sibling is either BLACK
					 * or null.
					 */
					if(!isRightChildBlack)
						// Here the right child of sibling is RED.
						return 3;
					else
						// Here the right child of sibling is BLACK.
						return 1;
				}
				else {
					// Here the left child of sibling is RED.
					return 4;
				}
			}
		}
		else {
			return 2;
		}
		
	}
	
	private void swapColor(Node node1, Node node2) {
		Enum<Color> color = node1.nodeColor;
		node1.nodeColor = node2.nodeColor;
		node2.nodeColor = color;
	}
	
	/* In Node performOperation(Node temp, int category, int posOfDB) 
	 * we perform certain operation on the parent of the DB node whose
	 * reference is in temp.
	 */
	private Node performOperation(Node temp, int category, int posOfDB) {
		// Here temp is the parent of the double black(DB) node.
		switch(category) {
			case 1:
				return operation1(temp, posOfDB);
			case 2:
				return operation2(temp, posOfDB);
			case 3:
				return operation3(temp, posOfDB);
			case 4:
				return operation4(temp, posOfDB);
			default:
				return temp;
		}
	}
	
	/* This operation corresponds to the case when the DB node's sibling
	 * is BLACK and that sibling's children are also BLACK.
	 */
	private Node operation1(Node temp, int posOfDB) {
		if(posOfDB == -1) {
			temp.m_left_child.isDoubleBlack = false;
			temp.m_right_child.nodeColor = Color.RED;
		}
		else {
			temp.m_right_child.isDoubleBlack = false;
			temp.m_left_child.nodeColor = Color.RED;
		}
		if(temp.nodeColor == Color.BLACK) {
			temp.isDoubleBlack = true;
		}
		else {
			temp.nodeColor = Color.BLACK;
		}
		return temp;
	}
	
	/* This operation corresponds to the case when the DB node's sibling
	 * is RED.
	 */
	private Node operation2(Node temp, int posOfDB) {
		if(posOfDB == -1) {
			swapColor(temp, temp.m_right_child);
			return leftRotate(temp);
		}
		else {
			swapColor(temp, temp.m_left_child);
			return rightRotate(temp);
		}
	}
	
	/* This operation corresponds to the case when the DB node's sibling
	 * is BLACK and that sibling's child that is far from DB node is 
	 * BLACK but the child nearer to the DB node is RED.
	 */
	private Node operation3(Node temp, int posOfDB) {
		if(posOfDB == -1) {
			swapColor(temp.m_right_child, temp.m_right_child.m_left_child);
			temp.m_right_child = rightRotate(temp.m_right_child);
			return temp;
		}
		else {
			swapColor(temp.m_left_child, temp.m_left_child.m_right_child);
			temp.m_left_child = leftRotate(temp.m_left_child);
			return temp;
		}
	}
	
	/* This operation corresponds to the case when the DB node's sibling
	 * is BLACK and that sibling's child that is far from DB node is RED.
	 */
	private Node operation4(Node temp, int posOfDB) {
		if(posOfDB == -1) {
			temp.m_left_child.isDoubleBlack = false;
			swapColor(temp, temp.m_right_child);
			temp.m_right_child.m_right_child.nodeColor = Color.BLACK;
			return leftRotate(temp);
		}
		else {
			temp.m_right_child.isDoubleBlack = false;
			swapColor(temp, temp.m_left_child);
			temp.m_left_child.m_left_child.nodeColor = Color.BLACK;
			return rightRotate(temp);
		}
		
	}
	
	public static void main(String[] args) {
		int a[] = {3, 1, 6, -2, 2, 4, 14, -9, -1, 8, 19};
		// {70, 50, 40} LL imbalance
		// {40, 50, 70} RR imbalance
		// {40, 60, 50} RL imbalance
		// {50, 40, 45} LR imbalance
		// {10, 18, 7, 15, 16, 30, 25, 40, 60, 2, 1, 70}
		// 16 10 25 2 15 18 40 1 7 30 60 70 
		
		RedBlackTree tree = new RedBlackTree();

		for(int i=0; i<a.length; i++) {
			tree.add(a[i]);
		}
		
		tree.printBT(true);
		
//		tree.displayBST(tree.root, TraversalType.LEVELORDER);
		tree.delete(4);
		tree.printBT(true);
		
		tree.delete(-2);
		tree.printBT(true);
		
		tree.delete(2);
		tree.printBT(true);
		
		tree.delete(3);
		tree.printBT(true);
		
		tree.delete(19);
		tree.printBT(true);

	}

}
