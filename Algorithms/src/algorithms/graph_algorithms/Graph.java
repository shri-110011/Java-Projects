package algorithms.graph_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

abstract class Graph {
	
	private List<Vertex> vertices;
	
	private int noOfVertices;
	
	private int noOfVerticesLimit=-1;
	
	protected static int innerForLoopCountInBFS ,
	whileLoopCountInBFS, forLoopCountInDFS;
	
	Graph() {
		// Create a list of vertices.
		vertices =  new ArrayList<Vertex>();
	}
	
	/* addVertex(Vertex vertex) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph. This is because we are checking for the 
	 * uniqueness of the node/vertex.
	 */
	public void addVertex(Vertex vertex) {
		if(!checkIfVertexExistInGraph(vertex.getName())) {
			if(!vertex.getName().matches("[a-zA-Z][0-9a-zA-Z]*"))
				throw new InvalidVertexNameException(vertex.getName());
			if(noOfVertices == noOfVerticesLimit)
				throw new MaxLimitForVerticesReachedException(vertex.getName(), noOfVerticesLimit);
			vertices.add(vertex);
			// Update the noOfVertices in this graph.
			noOfVertices++;
		}
		else {
			throw new DuplicateVertexException(vertex.getName());
		}
	}
	
	/* addVertex(String vertexName) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public void addVertex(String vertexName) {
		Vertex vertex = new Vertex(vertexName);
		addVertex(vertex);
	}
	
	/* addVertices(List<Vertex> verticesList) has a time complexity of O(size*|V|) where |V| represents 
	 * the number of vertices in the graph and size is the number of vertices in verticesList.
	 */
	public void addVertices(List<Vertex> verticesList) {
		for(Vertex vertex: verticesList)
			addVertex(vertex);
	}
	
	/* addVertices(String verticesNames[]) has a time complexity of O(size*|V|) where |V| represents 
	 * the number of vertices in the graph and size is the number of vertices in verticesNames.
	 */
	public void addVertices(String verticesNames[]) {
		/* Create vertex for each vertexName in verticesNames[] 
		 * and add that to the vertices list.
		 */
		
		int count = 0;
		for(String name: verticesNames) {
			if(!checkIfVertexExistInGraph(name)) {
				if(!name.matches("[a-zA-Z][0-9a-zA-Z]*"))
					throw new InvalidVertexNameException(name, count);
				if(noOfVertices == noOfVerticesLimit)
					throw new MaxLimitForVerticesReachedException(name, noOfVerticesLimit, count);
				vertices.add(new Vertex(name));
				// Update the noOfVertices in this graph.
				noOfVertices++;
			}
			else {
				throw new DuplicateVertexException(name, count);
			}
			count++;
		}
	}
	
	/* getVertexIndex(Vertex vertex) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public int getVertexIndex(Vertex vertex) {
		return vertices.indexOf(vertex);
	}
	
	/* getVertexIndex(String vertexName) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public int getVertexIndex(String vertexName) {
		boolean vertexFound = false;
		int count = 0;
		for(Vertex vertex: vertices) {
			if(vertex.getName().equals(vertexName)) {
				vertexFound = true;
				break;
			}
			count++;
		}
		if(!vertexFound) return -1;
		else return count;
	}
	
	/* checkIfVertexExistInGraph(String vertexName) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public boolean checkIfVertexExistInGraph(String vertexName) {
		if(getVertexIndex(vertexName) != -1) return true;
		else return false;
	}
	
	// checkIfVertexExistInGraph(int idx) has a time complexity of O(1).
	public boolean checkIfVertexExistInGraph(int idx) {
		if(idx >=0 && idx < noOfVertices) return true;
		else return false;
	}
	
	public int getNoOfVertices() {
		return noOfVertices;
	}
	
	// getVertex(int idx) has O(1) time complexity.
	public Vertex getVertex(int idx) {
		return vertices.get(idx);
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public void setNoOfVerticesLimit(int noOfVerticesLimit) {
		this.noOfVerticesLimit = noOfVerticesLimit;
	}
	
	/* removeVertex(int idx) has a time complexity of O(|V|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public boolean removeVertex(int idx) {
		// Remove this vertex having index idx from the vertices list.
		if(idx >= 0 && idx < noOfVertices) {
			vertices.remove(idx);
			// Update the noOfVertices.
			noOfVertices--;
			return true;
		}
		return false;
	}
	
	public void checkIfEdgeIsValid(int i, int j) {
		// Check if i and j are valid indices for the vertices.
		int noOfVertices = getNoOfVertices();
		if(!(i >= 0 && i < noOfVertices))
			throw new InvalidEdgeException(i, "endPoint1");
		if(!(j >= 0 && j < noOfVertices))
			throw new InvalidEdgeException(j, "endPoint2");
	}
	
	public ArrayList<Integer> performBFS(int startNodeIdx) {
		Queue<Integer> queue = new ArrayDeque<>();
		int noOfVertices = getNoOfVertices();
		boolean visitedNodes[] = new boolean[noOfVertices];
		ArrayList<Integer> bfsResult = new ArrayList<>();
		int componentCount = 0;
		
		if(startNodeIdx >=0 && startNodeIdx<noOfVertices) {
			for(int i=startNodeIdx; i<noOfVertices+startNodeIdx; i++) {
				int nodeIdx = i%noOfVertices;
				if(!visitedNodes[nodeIdx]) {
					getBFSResult(nodeIdx, queue, visitedNodes, bfsResult);
					componentCount++;
					System.out.println("For component: "+componentCount);
					System.out.println("whileLoopCountInBFS: "+whileLoopCountInBFS);
					System.out.println("innerForLoopCountInBFS: "+innerForLoopCountInBFS);
					System.out.println("#BFS result: "+bfsResult);
				}
			}
		}
		else {
			throw new InvalidVertexIndexException(noOfVertices, startNodeIdx);
		}
		return bfsResult;
	}
	
	public ArrayList<Integer> performDFS(int startNodeIdx) {
		Stack<Integer> stack = new Stack<>();
		int noOfVertices = getNoOfVertices();
		boolean visitedNodes[] = new boolean[noOfVertices];
		ArrayList<Integer> dfsResult = new ArrayList<>();
		int componentCount = 0;
		
		if(startNodeIdx >=0 && startNodeIdx<noOfVertices) {
			/* Note we need to have a for loop traversing through all the 
			 * vertices because the graph may be disconnected in which case
			 * it will have components. So if we start DFS from a vertex 
			 * which is in one of the components, then a DFS call with that
			 * nodeIdx from this for loop will result in coverage of all
			 * the nodes in that component only.
			 * Hence to cover all the components we need this for loop
			 * traversing through all the vertices.
			 */
			for(int i=startNodeIdx; i<noOfVertices+startNodeIdx; i++) {
				int nodeIdx = i%noOfVertices;
				if(!visitedNodes[nodeIdx]) {
					getDFSResult(nodeIdx, stack, visitedNodes, dfsResult);
					componentCount++;
					System.out.println("For component: "+componentCount);
					System.out.println("forLoopCountInDFS: "+forLoopCountInDFS);
					System.out.println("#DFS result: "+dfsResult);
				}
			}
		}
		else {
			throw new InvalidVertexIndexException(noOfVertices, startNodeIdx);
		}
		return dfsResult;
	}
	
	/* createGraph(String verticesNames[], String  connections[]) will
	 * create an undirected graph.
	 * 
	 * Note: Here we use a ArrayList to store the vertices in our 
	 * graph and the data structure to store the connection b/w 
	 * vertices depends on the type of graph representation we are
	 * using.
	 */
	abstract public void createGraph(String verticesNames[], String  connections[]);

	abstract public void addEdge(int idx1, int idx2);
	
	abstract public boolean removeEdge(int idx1, int idx2);
	
	abstract public ArrayList<Integer> getAdjacentNodes(int idx);
	
	abstract protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult);
	
	abstract protected void getDFSResult(int curNodeIdx, Stack<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult);
	
}
