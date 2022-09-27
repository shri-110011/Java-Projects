package algorithms.graph_algorithms;

public class Pair {
	private int idx, weight;
	
	public Pair(int idx) {
		this.idx = idx;
	}
	
	public Pair(int idx, int weight) {
		this.idx = idx;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getIdx() {
		return idx;
	}
	
	
	
}
