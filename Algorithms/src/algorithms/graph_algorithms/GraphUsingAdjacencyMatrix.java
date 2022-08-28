package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingAdjacencyMatrix extends Graph {
	
	boolean adjacencyMatrix[][];
	
	public GraphUsingAdjacencyMatrix(int noOfVertices) {
		setNoOfVerticesLimit(noOfVertices);
		if(noOfVertices > 10 || noOfVertices < 0) {
			throw new NumberOfVerticesOutOfBoundsException(10, noOfVertices);
		}
		adjacencyMatrix = new boolean[noOfVertices][noOfVertices];
	}
	
	// addEdge(int i, int j) has a time complexity of O(1)
	public void addEdge(int i, int j) {
		// Check if i and j are valid indices for the vertices.
		checkIfEdgeIsValid(i, j);
			
		adjacencyMatrix[i][j] = true;
		adjacencyMatrix[j][i] = true;
	}
	
	/* addEdges(String connections[]) has a time complexity of
	 * O(size*(4*|V|)) + O(1) where size is the number of edges in 
	 * connections.
	 */
	public void addEdges(String connections[]) {
		for(int i=0; i<connections.length; i++) {
			String endPoints[] = connections[i].split(" ");
			
			if(endPoints.length == 2) {
				for(String endPoint: endPoints) {
					// Here we check if vertex name is valid.
					if(!endPoint.matches("[a-zA-Z][0-9a-zA-Z]*")) {
						throw new InvalidVertexNameException(endPoint, connections[i], i);
					}
					else {
						if(!checkIfVertexExistInGraph(endPoint)) {
							throw new VertexNotExistException(endPoint, connections[i], i);
						}
					}
				}
				/* Here we are sure that the string representation of the edge
				 * is valid so we can get the indices of the end points of the 
				 * edge.
				 */
				int idx1 = getVertexIndex(endPoints[0]),
					idx2 = getVertexIndex(endPoints[1]);
				addEdge(idx1, idx2);
			}
			else {
				throw new InvalidEdgeFormatException(connections[i], i);
			}
		}
	}
	
	/* createGraph(String verticesNames[], String  connections[]) has a time complexity of
	 * O(size1*|V| + size2*(4*|V|) + 1) where size1 is the number of vertices in
	 * verticesNames and size2 is the number of edges in connections.
	 * 
	 * O(size1*|V|) is the time complexity of addVertices(verticesNames).
	 * 
	 * O(size2*(2*|V|)) + O(1) is the time complexity of populating the adjacency matrix
	 * with connections.
	 */
	public void createGraph(String verticesNames[], String  connections[]) {
		// Create a vertices list.
		addVertices(verticesNames);
		
		// Populate the adjacency matrix with the connections.
		addEdges(connections);
	}
	
	// printGraph() has a time complexity of O(|V| + 2 + |V|^2)
	public void printGraph() {
		int noOfVertices = getNoOfVertices();
		System.out.println("Printing the adjacency matrix ...");
		if(noOfVertices > 0) {
			for(int i=-2; i<noOfVertices; i++) {
				if(i<0) System.out.print(" ");
				else {
					System.out.print(getVertex(i));
					if(i!=noOfVertices-1)
						System.out.print(" ");
				}
			
			}
			System.out.println();
			for(int i=0; i<noOfVertices; i++) {
				System.out.print(getVertex(i)+" ");
				for(int j=0; j<noOfVertices; j++) {
					System.out.print(adjacencyMatrix[i][j]?1:0);
					if(j!=noOfVertices-1)
						System.out.print(" ");
				}
				System.out.println();
			}
		}
		else
			System.out.println("Graph is empty.");
	}

	/* removeVertex(int idx) has time complexity of O(3*|V| - 1) 
	 * which is equivalent to O(|V|) where |V| represents 
	 * the number of vertices in the graph..
	 */
	@Override
	public boolean removeVertex(int idx) {
		/* Shift each row to the left starting from index idx
		 * and then move each row at index greater than idx
		 * upward.
		 */
		
		int noOfVertices = getNoOfVertices(), temp = idx;
		
		while(temp < noOfVertices-1) {
			// Shift rows to the left.
			for(int i=0; i<noOfVertices; i++) {
				adjacencyMatrix[i][temp] = adjacencyMatrix[i][temp+1]; 
			}
			// Move columns upward.
			for(int i=0; i<noOfVertices; i++) {
					adjacencyMatrix[temp][i] = adjacencyMatrix[temp+1][i];
			}

			temp++;
		}
				
		// Remove this vertex from the vertices list.
		return super.removeVertex(idx);
	}

	// removeEdge(int idx1, int idx2) has a time complexity of O(1).
	@Override
	public boolean removeEdge(int idx1, int idx2) {
		adjacencyMatrix[idx1][idx2] = false;
		adjacencyMatrix[idx2][idx1] = false;
		return false;
	}

	/* getAdjacentNodes(int idx) has a time complexity of O(|V|) 
	 * where |V| represents the number of vertices in the graph.
	 */
	@Override
	public ArrayList<Integer> getAdjacentNodes(int idx) {
		int noOfVertices = getNoOfVertices();
		ArrayList<Integer> adjacentNodes = new ArrayList<>();
		for(int i=0; i<noOfVertices; i++) {
			if(adjacencyMatrix[idx][i])
				adjacentNodes.add(i);
		}
		return adjacentNodes;
	}
	
	public ArrayList<Integer> performBFS() {
		return performBFS(0);
	}
	
	/* For Graph using adjacency matrix getBFSResult() has a time 
	 * complexity of O(|V|*(1+|V|)+1) = O(|V|+|V|^2+1) which is 
	 * equivalent to O(|V|^2).
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run exactly |V| times.
	 * 
	 * The inner for loop
	 * for(int i=0; i<noOfVertices; i++) { }
	 * will run |V| times for each iteration of the while loop.
	 */
	
	protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult) {	
		int noOfVertices = getNoOfVertices();
		if(!visitedNodes[curNodeIdx]) {
			queue.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		while(!queue.isEmpty()) {
			int source_idx = queue.peek();
				
			for(int i=0; i<noOfVertices; i++) {
				if(adjacencyMatrix[source_idx][i] && !visitedNodes[i]) {
					int destination_idx = i;
					queue.add(destination_idx);
					visitedNodes[destination_idx] = true;
				}
				innerForLoopCountInBFS++;
			}
			whileLoopCountInBFS++;
			bfsResult.add(queue.remove());
		}
	}
	
	/* The for loop in 
	 * getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {}
	 * will run only when the node having index curNodeIdx is unvisited.
	 * 
	 * So the for loop will run exactly |V|*|V| times by the time all
	 * nodes in that component of the graph of which the node having 
	 * index specified by nodeIdx in the for loop of
	 * public ArrayList<Integer> performDFS(int startNodeIdx) { }
	 * is a part are visited.
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
	 * O(|V|*|V|) + O(|V|) + O(2*|E| + 1 - |V|) + O(2*|E| + 1)
	 * which is equivalent to O(|V|^2).
	 * 
	 */
	protected void getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {	
		int noOfVertices = getNoOfVertices();
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			stack.add(curNodeIdx);
			dfsResult.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		else return;
		
		int source_idx = stack.peek();
		
		for(int i = 0; i<noOfVertices; i++) {
			forLoopCountInDFS++;
			if(adjacencyMatrix[source_idx][i]) {
				int destination_idx = i;
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
				"A B", "A C 1", "A D", "B C", "B D", "C D", "C E", "D H",
				"E F", "E G", "F I", "G H", "G I", "H I"
				};
		// {"A B", "A C", "B D"}
		/* {
		"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
		"E F", "E G", "F I", "G H", "G I", "H I"
		}
		*/
		
		GraphUsingAdjacencyMatrix graph = new GraphUsingAdjacencyMatrix(11);
		graph.createGraph(vertices, edges);
		
//		graph.addVertex("E");
		graph.addEdge(0, 4);
		
		graph.printGraph();
		
//		graph.removeVertex(1);
//		graph.printGraph();
//		
//		graph.removeVertex(0);
//		graph.printGraph();
//		
//		graph.removeVertex(0);
//		graph.printGraph();
//		
//		graph.removeVertex(3);
//		graph.printGraph();
//		
//		graph.removeVertex(1);
//		graph.printGraph();
//		
//		graph.removeVertex(0);
//		graph.printGraph();
//		
//		graph.createGraph(vertices, edges);
//		graph.printGraph();
		
//		ArrayList<Integer> adjacentNodes = graph.getAdjacentNodes(0);
//		System.out.println("Adjacent Nodes: "+adjacentNodes);
		
		ArrayList<Integer> bfsResult = graph.performBFS(0);
		System.out.println("BFS result: "+bfsResult);
		
		ArrayList<Integer> dfsResult = graph.performDFS(0);
		System.out.println("BFS result: "+dfsResult);
	}

}
