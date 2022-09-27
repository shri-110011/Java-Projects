package algorithms.graph_algorithms;

import java.util.Comparator;

// Vertex index and its Priority value
public class VIPV implements Comparator<VIPV> {
	private int index, dValue;

	public VIPV(int index, int dValue) {
		this.index = index;
		this.dValue = dValue;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getdValue() {
		return dValue;
	}

	public void setdValue(int dValue) {
		this.dValue = dValue;
	}

	@Override
	public int compare(VIPV o1, VIPV o2) {
		return o1.dValue - o2.dValue;
	}
	
}
