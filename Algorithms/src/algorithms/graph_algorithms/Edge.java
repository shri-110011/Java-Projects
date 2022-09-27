package algorithms.graph_algorithms;

public class Edge {
	private int endPoint1, endPoint2;
	
	private int weight = 1;
	private boolean isDirected, hasWeight;
	
	public Edge(int ep1, int ep2) {
		endPoint1 = ep1;
		endPoint2 = ep2;
	}
	
	public Edge(int ep1, int ep2, int weight) {
		endPoint1 = ep1;
		endPoint2 = ep2;
		this.weight = weight;
		this.hasWeight = true;
	}

	public int getEndPoint1() {
		return endPoint1;
	}

	public void setEndPoint1(int endPoint1) {
		this.endPoint1 = endPoint1;
	}

	public int getEndPoint2() {
		return endPoint2;
	}

	public void setEndPoint2(int endPoint2) {
		this.endPoint2 = endPoint2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean getIsDirected() {
		return isDirected;
	}

	public void setIsDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	public boolean getHasWeight() {
		return hasWeight;
	}
	
	public void setHasWeight(boolean hasWeight) {
		this.hasWeight = hasWeight;
	}

	public String toString() {
		if(!hasWeight)
			return "{"+endPoint1+" --"+(isDirected?"> ":" ")+endPoint2+"}";
		else
			return "{"+endPoint1+" --"+(isDirected?"> ":" ")+endPoint2+", "+weight+"}";
	}
}
