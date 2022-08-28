package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingEdgeList extends Graph {
	private List<Edge> edges;
	
	private boolean isDirected;
	
	public GraphUsingEdgeList() {
		edges = new LinkedList<Edge>();
//		this.isDirected = isDirected;
	}
	
	
	/* addEdge(int i, int j) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 * Note: edges is a linked list.
	 */
	public void addEdge(int i, int j) {
		
		checkIfEdgeIsValid(i, j);

		if(checkIfEdgeExistInGraph(i, j))
			throw new DuplicateEdgeException(i, j);
		Edge edge = new Edge(i, j);
		edges.add(edge);
	}
	
	/* addEdge(Edge connection) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 * Note: edges is a linked list.
	 */
	public void addEdge(Edge connection) {
		int i = connection.getEndpoint1(), j = connection.getEndpoint2();
		checkIfEdgeIsValid(i, j);
		
		if(checkIfEdgeExistInGraph(i, j))
			throw new DuplicateEdgeException(i, j);
		
		edges.add(connection);
	}
	
	/* addEdge(String vertexName1, String vertexName2) has a time complexity of O(|E|+2*|V|) where |E| represents 
	 * the number of edges and |V| represents the number of vertices in the graph.
	 * 
	 * If the |E| are close to |V|^2 then the time complexity is O(|E|) = O(|V|^2).
	 * Note: edges is a linked list.
	 */
	public void addEdge(String vertexName1, String vertexName2) {
		if(!checkIfVertexExistInGraph(vertexName1))
			throw new VertexNotExistException(vertexName1);
		if(!checkIfVertexExistInGraph(vertexName2))
			throw new VertexNotExistException(vertexName2);
		
		Edge edge = new Edge(getVertexIndex(vertexName1), getVertexIndex(vertexName2));
		addEdge(edge);
	}
	
	/* addEdges(List<Edge> connections) has a time complexity of O(size*|E|) where |E| represents 
	 * the number of edges in the graph and size represents the number of edges in connections.
	 * Note: edges is a linked list.
	 */
	public void addEdges(List<Edge> connections) {
		for(Edge edge: edges) {
			addEdge(edge);
		}
	}
	
	/* addEdges(String connections[]) has a time complexity of O(size*(2*(|V|+1)+|E|+1)) where |V| represents 
	 * the number of vertices, |E| is the number of edges in the graph and size is the number of edges
	 * where each edge is represented as string in the format "vertex1Name vertex2Name" in connections[].
	   
	   Note: The space as the delimiter for every edge representation in connection is must.
	 */
	public void addEdges(String connections[]) {
		/* Create edge for each connection in connections[] 
		 * and add that to the edges list.
		 */
		for(int i=0; i<connections.length; i++) {
			String endPoints[] = connections[i].split(" ");
			
			if(endPoints.length == 2) {
				for(String endPoint: endPoints) {
					// Here we check if vertex name is valid.
					if(!endPoint.matches("[a-zA-Z][0-9a-zA-Z]*")) {
						throw new InvalidVertexNameException(endPoint, connections[i], i);
					}
					else {
						// Here we check if vertex is already present in the graph.
						if(!checkIfVertexExistInGraph(endPoint)) {
							throw new VertexNotExistException(endPoint, connections[i], i);
						}
					}
				}
				// Here check if the edge is already present in the graph.
				int idx1 = getVertexIndex(endPoints[0]), idx2 = getVertexIndex(endPoints[1]);
				if(!checkIfEdgeExistInGraph(idx1, idx2)) {
					edges.add(new Edge(idx1, idx2));
				}
				else {
					throw new DuplicateEdgeException(idx1, idx2, i);
				}
			}
			else {
				throw new InvalidEdgeFormatException(connections[i], i);
			}
		}
	}
	
	/* checkIfEdgeExistInGraph(int endPoint1, int endPoint2) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 */
	public boolean checkIfEdgeExistInGraph(int endPoint1, int endPoint2) {
		for(Edge e: edges) {
			if(e.getEndpoint1() == endPoint1 && e.getEndpoint2() == endPoint2) return true;
		}
		return false;
	}
	
	/* createGraph(String verticesNames[], String  connections[]) has a time complexity of 
	 * O(size1*|V|+ size2*(2*|V|+|E|)) where |V| represents the number of vertices in the graph, size1 is the 
	 * number of vertices in verticesNames, size2 is the number of connection in connections.
	 * 
	 * O(size1*|V|) is the time complexity of addVertices(verticesNames).
	 * O(size2*(2*|V|+|E|)) is the time complexity of addEdges(connections).
	 * 
	 * If the |E| are close to |V|^2 then the time complexity of 
	 * createGraph(String verticesNames[], String  connections[]) is O(size2*|E|).
	 * 
	 */
	public void createGraph(String verticesNames[], String  connections[]) {
		
		// Create a vertices list.
		addVertices(verticesNames);
		
		// Create a edges list.
		addEdges(connections);
	}
	
	/* removeVertex(int idx) has a time complexity of O(|V|+|E|) where |V| represents 
	 * the number of vertices in the graph.
	 */
	@Override
	public boolean removeVertex(int idx) {
		/* Remove all the edges from the edges list that contain this vertex 
		 * as an endpoint.
		 */
		ListIterator<Edge> li = edges.listIterator();
		while(li.hasNext()) {
			Edge edge = (Edge)li.next();
			if(edge.getEndpoint1() == idx || edge.getEndpoint2() == idx) {
				li.remove();
			}
		}
		
		// Remove this vertex from the vertices list.
		return super.removeVertex(idx);
	}

	/* removeEdge(int idx1, int idx2) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 */
	@Override
	public boolean removeEdge(int idx1, int idx2) {
		boolean flag = false;
		for(Edge edge: edges) {
			if(edge.getEndpoint1() == idx1 && edge.getEndpoint2() == idx2) {
				edges.remove(edge);
				flag = true;
				break;
			}
		}
		if(flag) return true;
		return false;
	}

	/* getAdjacentNodes(int idx) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 */
	@Override
	public ArrayList<Integer> getAdjacentNodes(int idx) {
		ArrayList<Integer> adjacentNodes = new ArrayList<>();
		for(Edge edge: edges) {
			if(edge.getEndpoint1() == idx) {
				adjacentNodes.add(edge.getEndpoint2());
			}
			else if(edge.getEndpoint2() == idx) {
				adjacentNodes.add(edge.getEndpoint1());
			}
		}
		return adjacentNodes;
	}
	
	public ArrayList<Integer> performBFS() {
		return performBFS(0);
	}
	
	/* For Graph using edge list getBFSResult() has a time complexity 
	 * of O(|V|*(1+|E|)+1) = O(|V|+|V|*|E|+1) which is equivalent to
	 * O(|V|*|E|).
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run exactly |V| times.
	 * 
	 * The inner for loop
	 * for(Edge e: edges) { }
	 * will run |E| times.
	 */
	@Override
	protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult) {	
		if(!visitedNodes[curNodeIdx]) { 
			queue.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		while(!queue.isEmpty()) {
			int source_idx = queue.peek();
			for(Edge e: edges) {
				if(e.getEndpoint1() == source_idx) {
					int destination_idx = e.getEndpoint2();
					if(!visitedNodes[destination_idx]) {
						queue.add(destination_idx);
						visitedNodes[destination_idx] = true;
					}
				}
				else if(e.getEndpoint2() == source_idx) {
					int destination_idx = e.getEndpoint1();
					if(!visitedNodes[destination_idx]) {
						queue.add(destination_idx);
						visitedNodes[destination_idx] = true;
					}
				}
				innerForLoopCountInBFS++;
			}
			whileLoopCountInBFS++;
			bfsResult.add(queue.remove());
		}
	}
	
	public ArrayList<Integer> performDFS() {
		return performDFS(0);
	}
	
	
	
	/* The for loop in 
	 * getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {}
	 * will run only when the node having index curNodeIdx is unvisited.
	 * 
	 * So the for loop will run exactly |V|*|E| times by the time all
	 * nodes in that component of the graph of which the node having 
	 * index specified by nodeIdx in the for loop of
	 * public ArrayList<Integer> performDFS(int startNodeIdx) { }
	 * is a part are visited.
	 * 
	 * 
	 * And the the statements in block 1, the source_idx declaration and
	 * initialization, popping  of the stack; all will run exactly 
	 * |V| times by the time all nodes in the graph are visited.
	 * 
	 * And the return statement will run 2*|E| + 1 - |V| times.
	 * 
	 * The getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {}
	 * method itself would run 2*|E| + 1 times by the time all nodes in 
	 * the current component of the graph are visited.
	 * 
	 * Note: |E| here is the number of edges in that component of the 
	 * graph of which the node having index specified by nodeIdx in the 
	 * for loop of
	 * public ArrayList<Integer> performDFS(int startNodeIdx) { }
	 * is a part of.
	 * 
	 * So time complexity of 
	 * getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {}
	 * is:
	 * O(|V|*|E|) + O(|V|) + O(2*|E| + 1 - |V|) + O(2*|E| + 1)
	 * which is equivalent to O(|V|*|E|).
	 * 
	 */
	@Override
	protected void getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {	
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			stack.add(curNodeIdx);
			dfsResult.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		else return;
		
		int source_idx = stack.peek();
		
		for(Edge e: edges) {
			forLoopCountInDFS++;
			if(e.getEndpoint1() == source_idx) {
				int destination_idx = e.getEndpoint2();
				/* At this point the vertex having index source_idx
				 * is not completely explored so we don't remove it 
				 * from the stack.
				 * 
				 * We now start exploring the vertex having index 
				 * destination_idx.
				 */
				getDFSResult(destination_idx, stack, visitedNodes, dfsResult);
			}
			else if(e.getEndpoint2() == source_idx) {
				int destination_idx = e.getEndpoint1();
				/* At this point the vertex having index source_idx
				 * is not completely explored so we don't remove it 
				 * from the stack.
				 * 
				 * We now start exploring the vertex having index 
				 * destination_idx.
				 */
				getDFSResult(destination_idx, stack, visitedNodes, dfsResult);
			}
		}
		/* This is the point where the vertex having index 
		 * specified by source_idx is completely explored.
		 * So we remove that vertex having index source_idx
		 * from the stack.
		 */
		stack.pop();
	}
	
	public static void main(String[] args) {
		// Create a list of Vertices.
		String vertices[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		String edges[]= {
				"B A", "C A", "1D A", "C B", "B D", "C D", "C E", "D H",
				"E F", "E G", "F I", "G H", "G I", "H I"
				};
		// {"A B", "A C", "B D", "B E", "E D"}
		/* {
		"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
		"E F", "E G", "F I", "G H", "G I", "H I"
		}
		*/

		GraphUsingEdgeList graph = new GraphUsingEdgeList();
		
		graph.createGraph(vertices, edges);
		System.out.println("Vertices: "+graph.getVertices());
		System.out.println("Edges: "+graph.edges);
		
//		graph.addVertex("E");
//		System.out.println("Vertices: "+graph.getVertices());
//		graph.addEdge(7, 8);
		graph.addEdge("E", "D1");
//		System.out.println("Vertices: "+graph.getVertices());
//		System.out.println("Edges: "+graph.edges);
		
//		ArrayList<Integer> adjacentNodes = graph.getAdjacentNodes(1);
//		System.out.println("Adjacent Nodes: "+adjacentNodes);
//		
//		graph.removeVertex(0);
//		System.out.println("Vertices: "+graph.getVertices());
//		System.out.println("Edges: "+graph.edges);
//		
//		boolean edgeRemoved = graph.removeEdge(1, 3);
//		System.out.println(edgeRemoved);
//		System.out.println("Vertices: "+graph.getVertices());
//		System.out.println("Edges: "+graph.edges);
		
//		ArrayList<Integer> bfsResult = graph.performBFS(0);
//		System.out.println("BFS result: "+bfsResult);
//		
//		ArrayList<Integer> dfsResult = graph.performDFS(0);
//		System.out.println("DFS result: "+dfsResult);

	}
}
