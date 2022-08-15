package algorithms.tree_algorithms;

public class Node implements Comparable<Node> {
	public int m_info;
	public Node m_left_child;
	public Node m_right_child;
	public Enum<Color> nodeColor;
	public boolean isDoubleBlack;
	
	
	public Node(int info) {
		m_info = info;
		m_left_child = null;
		m_right_child = null;
		nodeColor = Color.RED;
		isDoubleBlack = false;
	}
	
	public Node(int info, Enum<Color> color) {
		m_info = info;
		m_left_child = null;
		m_right_child = null;
		nodeColor = color;
		isDoubleBlack = false;
	}
	
	public enum Color {
		RED, BLACK
	} 

	@Override
	public int compareTo(Node node) {
		return m_info - node.m_info;
	}
	
	public String toString() {
		return "{info: "+m_info+", color: "+nodeColor+"}";
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Node)) return false;
		
		Node node = (Node)o;
		
		return m_info == node.m_info && nodeColor == node.nodeColor;

	}
	
}