package binarySearchTree;

enum BSTTraversalType {
	INORDER,
	PREORDER,
	POSTORDER
}
class Node {
	private int info;
	private Node left, right;
	
	public Node(int value) {
		info = value;
		left = right = null;
	}
	
	public void insert(int value) {
		insertNode(this, value);
	}
	public Node insertNode(Node node, int value) {
		if(node == null) {
			node = new Node(value);
			return node;
		}
		if(value <= node.info) {
			node.left = insertNode(node.left, value);
			return node;
		}
		else {
			node.right = insertNode(node.right, value);
			return node;
		}
	}
	
	public Node delete(Node node, int value) {
		if(node == null) {
			return node;
		}
		else {
			System.out.println("Current node is: "+node.info+" and value is: "+value);
			if(value < node.info) {
				node.left = delete(node.left, value);
			}
			else if(value > node.info) {
				node.right = delete(node.right, value);
			}
			else {
				System.out.println("Node found!");
				System.out.println("Deleted node: "+node.info);
				
				System.out.println("Flag 1");
				if(node.left == null && node.right == null) {
					return null;
				}
				System.out.println("Flag 2");
				if(node.left == null) {
					return node.right;
				}
				System.out.println("Flag 3");
				if(node.right == null) {
					return node.left;
				}
				System.out.println("Flag 4");
				Node temp = getInOrderPredecessorOf(node.left);
				System.out.println("#"+temp);
				System.out.println("Finding inorder predecessor of node: "+node.info);
			
				System.out.println("Inorder predecessor of node: "+node.info+" is node: "+temp.info);
				node.info = temp.info;
				System.out.println("Flag 5");
				node.left = delete(node.left, temp.info);
				temp = null;
			}
			return node;
		}
	}
	
	public Node getInOrderPredecessorOf(Node node) {
		while(node.right!=null) {
			node= node.right;
		}
		return node;
	}
	public Node getInOrderSuccessorOf(Node node) {
		while(node.left!=null) {
			node= node.left;
		}
		return node;
	}
	
	public void displayBST(Node node, BSTTraversalType type) {
		if(node == null) {
			return;
		}
		
		if(type == BSTTraversalType.PREORDER) {
			System.out.print(node.info+" ");
		}
		
		displayBST(node.left, type);
		
		if(type == BSTTraversalType.INORDER) {
			System.out.print(node.info+" ");
		}
		
		displayBST(node.right, type);
		
		if(type == BSTTraversalType.POSTORDER) {
			System.out.print(node.info+" ");
		}
	}
}
public class BinarySearchTree {

	public static void main(String[] args) {
		
		Node root = new Node(30);
		root.insert(20);
		root.insert(40);
		root.insert(18);
		root.insert(25);
		root.insert(35);
		root.insert(50);
		root.insert(24);
		root.insert(28);
		root.insert(38);
		root.insert(45);
		root.insert(58);
		root.insert(55);
		
		root.displayBST(root, BSTTraversalType.POSTORDER);
		
//		root = root.delete(root, 14);
//		
//		root.displayBST(root, BSTTraversalType.INORDER);
//		

	}

}
