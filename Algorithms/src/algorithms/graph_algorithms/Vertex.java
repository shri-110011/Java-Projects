package algorithms.graph_algorithms;

import java.util.Comparator;

public class Vertex implements Comparable<Vertex> {
	private String name;
	private int priorityValue;

	public Vertex(String vertexName) {
		name = vertexName;
		priorityValue = Integer.MAX_VALUE;
	}

	public String getName() {
		return name;
	}

	public int getPriorityValue() {
		return priorityValue;
	}

	public void setPriorityValue(int priorityValue) {
		this.priorityValue = priorityValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Vertex o) {
		return getPriorityValue() - o.getPriorityValue();
	}
}
