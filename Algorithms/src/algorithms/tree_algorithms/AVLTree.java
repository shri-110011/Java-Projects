package algorithms.tree_algorithms;


public class AVLTree extends BinarySearchTree {
	
	public void add(int data) {
		super.add(data);
		balanceBSTAfterInsertion(root, data);
	}
	
	private void balanceBSTAfterInsertion(Node root, int addedData) {
		this.root = performHeightBalanceAfterInsertion(root, addedData);
	}
	
	private Node performHeightBalanceAfterInsertion(Node node, int addedData) {
		
		if(node == null) return null;
		
		node.m_left_child = performHeightBalanceAfterInsertion(node.m_left_child, addedData);
		node.m_right_child = performHeightBalanceAfterInsertion(node.m_right_child, addedData);
		
		
		int balanceFactor = getBalanceFactor(node);
		
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
	
	public void remove(int data) {
		super.remove(data);
		root = performHeightBalanceAfterDeletion(root);
	}
	
	private Node performHeightBalanceAfterDeletion(Node node) {
		
		if(node == null) return null;
		
		node.m_left_child = performHeightBalanceAfterDeletion(node.m_left_child);
		node.m_right_child = performHeightBalanceAfterDeletion(node.m_right_child);
		
		
		int balanceFactorOfCurNode = getBalanceFactor(node);
		
		if(balanceFactorOfCurNode > 1) {
			int balanceFactorOfLeftChild = getBalanceFactor(node.m_left_child);
			if(balanceFactorOfLeftChild >= 0) {
				return rightRotate(node);
			}
			else {
				node.m_left_child = leftRotate(node.m_left_child);
				return rightRotate(node);
			}
		}
		else if(balanceFactorOfCurNode < -1) {
			int balanceFactorOfRightChild = getBalanceFactor(node.m_right_child);
			if(balanceFactorOfRightChild <= 0) {
				return leftRotate(node);
			}
			else {
				node.m_right_child = rightRotate(node.m_right_child);
				return leftRotate(node);
			}
		}
		return node;
	}
	
	protected int getBalanceFactor(Node node) {
		int leftSubtreeHeight = this.getTreeHeight(node.m_left_child);
		int rightSubtreeHeight = this.getTreeHeight(node.m_right_child);
		
		return leftSubtreeHeight - rightSubtreeHeight;
	}
	
	protected Node rightRotate(Node node) {
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
	
	protected Node leftRotate(Node node) {
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
		int a[] = {10, 3, 15, 2, 13, 19, 1, 16, 20};
		// {70, 50, 40} LL imbalance
		// {40, 50, 70} RR imbalance
		// {40, 60, 50} RL imbalance
		// {50, 40, 45} LR imbalance
		
		AVLTree tree = new AVLTree();

		for(int i=0; i<a.length; i++) {
			tree.add(a[i]);
		}
		
//		tree.displayBST(tree.root, TraversalType.LEVELORDER);
//		System.out.println();
		tree.printBT();
//		System.out.println("\n"+tree.getTreeHeight(tree.root));
		
		tree.remove(1);
		tree.printBT();
		
		tree.remove(19);
		tree.printBT();
		
		tree.remove(2);
		tree.printBT();
		

	}

}
