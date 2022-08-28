package algorithms.graph_algorithms;

public class Edge {
	private int endPoint1, endPoint2;
	
	private int weight = 1;
	
	public Edge(int ep1, int ep2) {
		endPoint1 = ep1;
		endPoint2 = ep2;
	}
	
	public int getEndpoint1() {
		return endPoint1;
	}
	
	public int getEndpoint2() {
		return endPoint2;
	}

	public String toString() {
		return "{"+endPoint1+" -- "+endPoint2+"}";
	}
}
