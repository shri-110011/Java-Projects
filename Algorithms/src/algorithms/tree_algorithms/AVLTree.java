package algorithms.tree_algorithms;


public class AVLTree extends BinarySearchTree {
	
	private void balanceBST(Node root, int addedData) {
		this.root = performHeightBalance(root, addedData);
	}
	
	private Node performHeightBalance(Node node, int addedData) {
		
		if(node == null) return null;
		
		node.m_left_child = performHeightBalance(node.m_left_child, addedData);
		node.m_right_child = performHeightBalance(node.m_right_child, addedData);
		
		int leftSubtreeHeight = this.getTreeHeight(node.m_left_child);
		int rightSubtreeHeight = this.getTreeHeight(node.m_right_child);
		int balanceFactor = leftSubtreeHeight - rightSubtreeHeight;
		
		if(balanceFactor > 1) {
			/* Here the tree is unbalanced at the current node represented 
			 * by node and the type of imbalance is either:
			 * left-left imbalance or left-right imbalance
			 */
			
			if(addedData < node.m_left_child.m_info) {
				// Here it is determined that the imbalance is of left-left 
				// type. So we need to do right rotation.
				return rightRotate(node);	
			}
			else {
				// Here it is determined that the imbalance is of left-right 
				// type. So we need to do first left rotation and then right
				// rotation.
				node.m_left_child = leftRotate(node.m_left_child);
				return rightRotate(node);
				
			}
			
		}
		else if(balanceFactor < -1) {
			/* Here the tree is unbalanced at the current node represented 
			 * by node and the type of imbalance is either:
			 * right-right imbalance or right-left imbalance
			 */
			if(addedData > node.m_right_child.m_info) {
				// Here it is determined that the imbalance is of right-right 
				// type. So we need to do left rotation.
				
				return leftRotate(node);
			}
			else {
				// Here it is determined that the imbalance is of right-left 
				// type. So we need to do first right rotation and then left
				// rotation.
				
				node.m_right_child = rightRotate(node.m_right_child);
				return leftRotate(node);
			}
		}
		return node;
		
	}
	
	private Node rightRotate(Node node) {
		Node temp = node.m_left_child;
		if(node.m_left_child.m_right_child != null) {
			node.m_left_child = node.m_left_child.m_right_child;
		}
		else {
			node.m_left_child = null;
		}
		temp.m_right_child = node;
		return temp;
	}
	
	private Node leftRotate(Node node) {
		Node temp = node.m_right_child;
		if(node.m_right_child.m_left_child != null) {
			node.m_right_child = node.m_right_child.m_left_child;
		}
		else {
			node.m_right_child = null;
		}
		temp.m_left_child = node;
		return temp;
	}

	public static void main(String[] args) {
		int a[] = {6,3,14,2,4,19,1,8,-1,5,-9,20,-2};
		// {70, 50, 40} LL imbalance
		// {40, 50, 70} RR imbalance
		// {40, 60, 50} RL imbalance
		// {50, 40, 45} LR imbalance
		
		AVLTree tree = new AVLTree();

		for(int i=0; i<a.length; i++) {
			tree.add(a[i]);
			tree.balanceBST(tree.root, a[i]);
		}
		
		tree.displayBST(tree.root, TraversalType.LEVELORDER);
		System.out.println();
		tree.printBT();
//		System.out.println("\n"+tree.getTreeHeight(tree.root));
		

	}

}
