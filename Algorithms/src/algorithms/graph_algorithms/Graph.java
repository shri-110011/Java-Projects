package algorithms.graph_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import algorithms.graph_algorithms.graph_exceptions.DuplicateVertexException;
import algorithms.graph_algorithms.graph_exceptions.InvalidEdgeException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexIndexException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexNameException;
import algorithms.graph_algorithms.graph_exceptions.MaxLimitForVerticesReachedException;

public abstract class Graph {
	
	private List<Vertex> vertices;
	
	private int noOfVertices;
	
	private int noOfVerticesLimit=-1;
	
	protected boolean isDirected;
	
	protected int innerForLoopCountInBFS,
	whileLoopCountInBFS, forLoopCountInDFS,
	innerForLoopCountInBFSCycleChecker, whileLoopCountInBFSCycleChecker,
	forLoopCountInDFSCycleChecker, innerForLoopCountInBFSBipartiteChecker
	, whileLoopCountInBFSBipartiteChecker, forLoopCountInDFSBipartiteChecker,
	forLoopCountInDFSTopoSort, whileLoopCountInBFSTopoSort,
	innerForLoopCountInBFSTopoSort, outerForLoopOfIndegrees,
	innerForLoopOfIndegrees;
	
	static final Logger log = LogManager.getLogger(Graph.class.getName());
	
	Graph() {
		// Create a list of vertices.
		vertices =  new ArrayList<Vertex>();
	}
	
	public boolean isDirected() {
		return isDirected;
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
	
	public List<Vertex> getListOfVertexObjects(String verticesNames[]) {
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		
		for(String vertexName: verticesNames) {
			vertexList.add(new Vertex(vertexName));
		}
		
		return vertexList;
	}
	
	public List<Integer> getListOfVertexIndices(String verticesNames[]) {
		ArrayList<Integer> vertexList = new ArrayList<>();
		
		for(String vertexName: verticesNames) {
			vertexList.add(getVertexIndex(vertexName));
		}
		
		return vertexList;
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
					log.info("For component: "+componentCount);
					log.debug("whileLoopCountInBFS: "+whileLoopCountInBFS);
					log.debug("innerForLoopCountInBFS: "+innerForLoopCountInBFS);
//					log.debug("#BFS result: "+bfsResult);
				}
			}
		}
		else {
			throw new InvalidVertexIndexException(noOfVertices, startNodeIdx);
		}
		return bfsResult;
	}
	
	public ArrayList<Integer> performBFS() {
		return performBFS(0);
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
					log.info("For component: "+componentCount);
					log.debug("forLoopCountInDFS: "+forLoopCountInDFS);
//					log.debug("#DFS result: "+dfsResult);
				}
			}
		}
		else {
			throw new InvalidVertexIndexException(noOfVertices, startNodeIdx);
		}
		return dfsResult;
	}
	
	public ArrayList<Integer> performDFS() {
		return performDFS(0);
	}
	
	public boolean cycleCheckUsingBFS() {
		
		int noOfVertices = getNoOfVertices(),
				componentCount = 0;
		boolean hasCycle = false;
		
		if(!isDirected) {
			Queue<Integer[]> queue = new ArrayDeque<>();
			boolean visitedNodes[] = new boolean[noOfVertices];
			
			for(int i=0; i<noOfVertices; i++) {
				if(!visitedNodes[i]) {
					queue.add(new Integer[] {i, -1});
					visitedNodes[i] = true;
					componentCount++;
					hasCycle = cycleCheckerUsingBFS(queue, visitedNodes);
					log.info("For component: "+componentCount);
					log.debug("whileLoopCountInBFSCycleChecker: "+whileLoopCountInBFSCycleChecker);
					log.debug("innerForLoopCountInBFSCycleChecker: "+innerForLoopCountInBFSCycleChecker);
					
					if(hasCycle)
						return true;
				}
			}
			return false;
		}
		else {
			Queue<Integer> queue = new ArrayDeque<>();
			int inDegrees[] = getIndegrees();
			for(int i=0; i<noOfVertices; i++) {
				if(inDegrees[i] == 0) {
					queue.add(i);
				}
			}
			hasCycle = cycleCheckerUsingBFSForDirectedGraph(queue, inDegrees);
			
			log.debug("whileLoopCountInBFSCycleChecker: "+whileLoopCountInBFSCycleChecker);
			log.debug("innerForLoopCountInBFSCycleChecker: "+innerForLoopCountInBFSCycleChecker);
			
			if(hasCycle) return true;
			return false;
		}
	}
	
	public boolean cycleCheckUsingDFS() {
		
		Stack<Integer []> stack = new Stack<>();
		int noOfVertices = getNoOfVertices();
		boolean visitedNodes[] = new boolean[noOfVertices];
		int componentCount = 0;
		
		log.info("noOfVertices: "+noOfVertices);
		for(int i=0; i<noOfVertices; i++) {
			if(!visitedNodes[i]) {
				boolean hasCycle;
				stack.add(new Integer[] {i, -1});
				visitedNodes[i] = true;
				if(!isDirected) {
					hasCycle = cycleCheckerUsingDFS(stack, visitedNodes);
				}
				else {
					boolean dfsVisited[] = new boolean[noOfVertices];
					dfsVisited[i] = true;
					hasCycle = cycleCheckerUsingDFSForDirectedGraph(stack, visitedNodes, dfsVisited);
				}
				if(hasCycle) {
					componentCount++;
					log.info("For component: "+componentCount);
					log.debug("forLoopCountInDFSCycleChecker: "+forLoopCountInDFSCycleChecker);
					return true;
				}
				componentCount++;
				log.info("For component: "+componentCount);
				log.debug("forLoopCountInDFSCycleChecker: "+forLoopCountInDFSCycleChecker);
			}
		}
		return false;
	}
	
	public boolean bipartiteCheckUsingBFS() {
		
		Queue<Integer[]> queue = new ArrayDeque<>();
		int noOfVertices = getNoOfVertices();
		int nodesColor[] = new int[noOfVertices];
		Arrays.fill(nodesColor, 0);
		int componentCount = 0;
		
		for(int i=0; i<noOfVertices; i++) {
			if(nodesColor[i]==0) {
				queue.add(new Integer[] {i, -1});
				nodesColor[i] = 1;
				
				componentCount++;
				if(!bipartiteCheckerUsingBFS(queue, nodesColor)) {
					log.info("For component: "+componentCount);
					log.debug("whileLoopCountInBFSBipartiteChecker: "+whileLoopCountInBFSBipartiteChecker);
					log.debug("innerForLoopCountInBFSBipartiteChecker: "+innerForLoopCountInBFSBipartiteChecker);
					return false;
				}
				else {
					log.info("For component: "+componentCount);
					log.debug("whileLoopCountInBFSBipartiteChecker: "+whileLoopCountInBFSBipartiteChecker);
					log.debug("innerForLoopCountInBFSBipartiteChecker: "+innerForLoopCountInBFSBipartiteChecker);
				}
			}
		}
		return true;
	}
	
	public boolean bipartiteCheckUsingDFS() {
		
		Stack<Integer[]> stack = new Stack<>();
		int noOfVertices = getNoOfVertices();
		int nodesColor[] = new int[noOfVertices];
		Arrays.fill(nodesColor, 0);
		int componentCount = 0;
		
		for(int i=0; i<noOfVertices; i++) {
			if(nodesColor[i]==0) {
				stack.add(new Integer[] {i, -1});
				nodesColor[i] = 1;
				
				componentCount++;
				if(!bipartiteCheckerUsingDFS(stack, nodesColor)) {
					log.info("For component: "+componentCount);
					log.debug("forLoopCountInDFSBipartiteChecker: "+forLoopCountInDFSBipartiteChecker);
					return false;
				}
				else {
					log.info("For component: "+componentCount);
					log.debug("forLoopCountInDFSBipartiteChecker: "+forLoopCountInDFSBipartiteChecker);
				}
			}
		}
		return true;
	}
	
	public ArrayList<Integer> performDFSTopologicalSorting(int startNodeIdx) {
		Stack<Integer> stack = new Stack<>();
		int noOfVertices = getNoOfVertices();
		boolean visitedNodes[] = new boolean[noOfVertices];
		ArrayList<Integer> dfsTopoSortResult = new ArrayList<>();
		int componentCount = 0;
		
		if(startNodeIdx >=0 && startNodeIdx<noOfVertices) {
			if(!isDirected) {
				return dfsTopoSortResult;
			}
			for(int i=startNodeIdx; i<noOfVertices+startNodeIdx; i++) {
				int nodeIdx = i%noOfVertices;
				if(!visitedNodes[nodeIdx]) {
					visitedNodes[nodeIdx] = true;
					getDFSTopologicalSortResult(nodeIdx, stack, visitedNodes, dfsTopoSortResult);
					componentCount++;
					log.info("For component: "+componentCount);
					log.debug("forLoopCountInDFSTopoSort: "+forLoopCountInDFSTopoSort);
				}
			}
		}
		else {
			throw new InvalidVertexIndexException(noOfVertices, startNodeIdx);
		}
		Collections.reverse(dfsTopoSortResult);
		return dfsTopoSortResult;
	}
	
	public ArrayList<Integer> performDFSTopologicalSorting() {
		return performDFSTopologicalSorting(0);
	}
	
	public ArrayList<Integer> performBFSTopologicalSorting() {
		Queue<Integer> queue = new ArrayDeque<>();
		int noOfVertices = getNoOfVertices();
		ArrayList<Integer> bfsTopoSortResult = new ArrayList<>();
		
		if(!isDirected) {
			log.info("whileLoopCountInBFSTopoSort: "+whileLoopCountInBFSTopoSort);
			log.info("innerForLoopCountInBFSTopoSort: "+innerForLoopCountInBFSTopoSort);
			return bfsTopoSortResult;
		}
		
		int inDegrees[] = getIndegrees();
		log.info("outerForLoopOfIndegrees: "+outerForLoopOfIndegrees);
		log.info("innerForLoopOfIndegrees: "+innerForLoopOfIndegrees);
		for(int i=0; i<noOfVertices; i++) {
			if(inDegrees[i] == 0) {
				queue.add(i);
				bfsTopoSortResult.add(i);
			}
		}

		getBFSTopologicalSortResult(queue, inDegrees, bfsTopoSortResult);
		log.info("whileLoopCountInBFSTopoSort: "+whileLoopCountInBFSTopoSort);
		log.info("innerForLoopCountInBFSTopoSort: "+innerForLoopCountInBFSTopoSort);
		return bfsTopoSortResult;
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

	abstract public void printGraph();
	
	abstract public void addEdge(int idx1, int idx2);
	
	abstract public void addEdges(String connections[]);
	
	abstract public boolean removeEdge(int idx1, int idx2);
	
	abstract public boolean checkIfEdgeExistInGraph(int endPoint1, int endPoint2);
	
	abstract public ArrayList<Integer> getAdjacentNodes(int idx);
	
	abstract protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult);
	
	abstract protected void getDFSResult(int curNodeIdx, Stack<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult);
	
	abstract protected boolean cycleCheckerUsingBFS(Queue<Integer[]> queue, boolean visitedNodes[]);
	
	abstract protected boolean cycleCheckerUsingDFS(Stack<Integer[]> stack, boolean visitedNodes[]);
	
	abstract protected boolean bipartiteCheckerUsingBFS(Queue<Integer[]> queue, int nodesColor[]);
	
	abstract protected boolean bipartiteCheckerUsingDFS(Stack<Integer[]> stack, int nodesColor[]);
	
	abstract protected boolean cycleCheckerUsingDFSForDirectedGraph(Stack<Integer[]> stack, boolean visitedNodes[], boolean dfsVisited[]);

	abstract protected void getDFSTopologicalSortResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsTopoSortResult);

	abstract protected int[] getIndegrees();
	
	abstract protected void getBFSTopologicalSortResult(Queue<Integer> queue, int inDegrees[], ArrayList<Integer> bfsTopoSortResult);

	abstract protected boolean cycleCheckerUsingBFSForDirectedGraph(Queue<Integer> queue, int inDegrees[]);
}
