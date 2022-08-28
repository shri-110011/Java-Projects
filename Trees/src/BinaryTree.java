import java.util.ArrayList;
import java.util.List;

enum TypeOfTreeTraversal {
	INORDER,
	PREORDER,
	POSTORDER
}

class Node {
	private int info;
	private Node left, right;
	
	static int count = 0;
	private boolean displayFlag = true;
	
	List<Integer> nodes = new ArrayList<>();
	
	public Node(int value) {
		info = value;
		left = right = null;
	}
	
	public Node getLeftChild(Node node) {
		return node.left;
	}
	
	public Node setLeftChild(Node node, int info) {
		node.left = new Node(info);
		return node.left;
	}
	
	public Node getRightChild(Node node) {
		return node.right;
	}
	
	public Node setRightChild(Node node, int info) {
		node.right = new Node(info);
		return node.right;
	}
	
	public void displayTreeInOrder(Node node, TypeOfTreeTraversal type) {
		count++;
		if(node == null) {
			return;
		}
		
		if(type==TypeOfTreeTraversal.PREORDER) {
			if(displayFlag) {
				System.out.print(node.info+" ");
			}
			else {
				nodes.add(node.info);
			}
		}
		
		displayTreeInOrder(node.left, type);
		
		if(type==TypeOfTreeTraversal.INORDER) {
			if(displayFlag) {
				System.out.print(node.info+" ");
			}
			else {
				nodes.add(node.info);
			}
		}
	
		displayTreeInOrder(node.right, type);
		
		if(type==TypeOfTreeTraversal.POSTORDER) {
			if(displayFlag) {
				System.out.print(node.info+" ");
			}
			else {
				nodes.add(node.info);
			}
		}
	}
	
	public List<Integer> getNodes(Node rootNode, TypeOfTreeTraversal type) {
		displayFlag = false;
		displayTreeInOrder(rootNode, type);
		displayFlag = true;
		return nodes;
	}

	public boolean isTreeEmpty(Node rootNode) {
		if(rootNode==null) {
			return true;
		}
		else {
			return false;
		}
	}
}

public class BinaryTree {	
	
	public static void main(String[] args) {
		Node root = new Node(10);
		Node first = root.setLeftChild(root, 11);
		Node second = root.setRightChild(root, 12);
		
		Node third = root.setLeftChild(first, 13);
		Node fourth = root.setRightChild(first, 14);
		
		Node fifth = root.setLeftChild(second, 15);
		Node sixth = root.setRightChild(second, 16);
		
		System.out.println("\n>>Inorder Binary Tree Traversal");
		root.displayTreeInOrder(root, TypeOfTreeTraversal.PREORDER);
		
		System.out.println("\nCount: "+Node.count);
		
		List<Integer> list = root.getNodes(root, TypeOfTreeTraversal.PREORDER);
		System.out.println(list);
		
		Node.count = 0;
		root.displayTreeInOrder(root, TypeOfTreeTraversal.INORDER);
		System.out.println("\nCount: "+Node.count);
		
		System.out.println(root.isTreeEmpty(sixth.getLeftChild(sixth)));
	}
}
