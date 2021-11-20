
public class FileComparisonData {
	private int lineNum;
	private int colNum;
	
	protected FileComparisonData(int lineNum, int colNum) {
		this.lineNum = lineNum;
		this.colNum = colNum;
	}

	public int getLineNum() {
		return lineNum;
	}

	public int getColNum() {
		return colNum;
	}
}
