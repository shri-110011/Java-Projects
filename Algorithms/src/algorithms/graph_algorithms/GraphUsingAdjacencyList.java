package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphUsingAdjacencyList extends Graph {
	
	ArrayList<LinkedList<Integer>> adjacencyList;
	
	public GraphUsingAdjacencyList(int noOfVertices) {
		setNoOfVerticesLimit(noOfVertices);
		if(noOfVertices > 10 || noOfVertices < 0) {
			throw new NumberOfVerticesOutOfBoundsException(10, noOfVertices);
		}
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<noOfVertices; i++) {
			adjacencyList.add(new LinkedList<Integer>());
		}
	}

	/* createGraph(String[] verticesNames, String[] connections) has a time complexity of 
	 * O(size1*|V| + size2*(2*|V| + 3*|V| +1)) which is O(size1*|V| +size2*(5*|V| + 1)) 
	 * where size1 is the number of vertices in verticesNames and size2 is the number of 
	 * edges in connections.
	 * 
	 * O(size1*|V|) is the time complexity of addVertices(verticesNames).
	 * 
	 * O(size2*(2*|V| + 3*|V| +1) is the time complexity of populating the adjacency list
	 * with connections.
	 * 
	 */
	@Override
	public void createGraph(String[] verticesNames, String[] connections) {
		// Create a vertices list.
		addVertices(verticesNames);
		
		// Add the connections to the AdjacencyList
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
				int idx1 = getVertexIndex(endPoints[0]),
					idx2 = getVertexIndex(endPoints[1]);
				addEdge(idx1, idx2);
			}
			else {
				throw new InvalidEdgeFormatException(connections[i], i);
			}
		}
	}
		
	/* addEdge(int i, int j) has a time complexity of O(|V| + 1 + 2*|V|) which 
	 * is O(3*|V| + 1) and is equivalent to O(|V|).
	 */
	@Override
	public void addEdge(int i, int j) {
		checkIfEdgeIsValid(i, j);
		if(checkIfEdgeExistInGraph(i, j))
			throw new DuplicateEdgeException(i, j);
		
		adjacencyList.get(i).add(j);
		adjacencyList.get(j).add(i);
	}
	
	/* checkIfEdgeExistInGraph(int endPoint1, int endPoint2) has a time complexity of O(|V| + 1) where |V| represents 
	 * the number of vertices in the graph.
	 */
	public boolean checkIfEdgeExistInGraph(int endPoint1, int endPoint2) {
		for(Integer i : adjacencyList.get(endPoint1)) {
			if(i == endPoint2) return true;
		}
		return false;
	}
	
	// printGraph() has a time complexity of O(2*|E|)
	public void printGraph() {
		int count = 0;
		for(LinkedList<Integer> list: adjacencyList) {
			System.out.println("Adjacency list for vertex: "+count);
			System.out.print("head");
			for(Integer i: list) {
				System.out.print(" -> "+i);
			}
			System.out.println();
			count++;
		}
	}

	/* removeEdge(int idx1, int idx2) has a time complexity of 
	 * O(2*|V|).
	 */
	@Override
	public boolean removeEdge(int idx1, int idx2) {
		checkIfEdgeIsValid(idx1, idx2);
		
		adjacencyList.get(idx1).remove((Integer)idx2);
		adjacencyList.get(idx2).remove((Integer)idx1);
		
		return true;
	}

	/* getAdjacentNodes(int idx) has a time complexity of O(|V|) 
	 * where |V| represents the number of vertices in the graph.
	 */
	@Override
	public ArrayList<Integer> getAdjacentNodes(int idx) {
		ArrayList<Integer> adjacentNodes = new ArrayList<>();
		
		for(Integer i: adjacencyList.get(idx)) {
			adjacentNodes.add(i);
		}
		
		return adjacentNodes;
	}

	/* For Graph using adjacency list getBFSResult() has a time 
	 * complexity of O(|V|+2*|E|+1) which is equivalent to
	 * O(|V|+2*|E|).
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run exactly |V| times.
	 * 
	 * The inner for loop
	 * for(int destination_idx: adjacencyList.get(source_idx)) { }
	 * will run E_source_idx times
	 * where E_source_idx represents the number of adjacent nodes 
	 * of the vertex vertices[source_idx].
	 * 
	 * In total this inner for loop will run 2*|E| times when all
	 * vertices in the graph are considered.
	 */
	protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult) {
		if(!visitedNodes[curNodeIdx]) {
			queue.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		while(!queue.isEmpty()) {
			int source_idx = queue.peek();
				
			for(int destination_idx: adjacencyList.get(source_idx)) {
				if(!visitedNodes[destination_idx]) {
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
	 * So the for loop will run exactly |E| times by the time all
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
	 * O(|E|) + O(|V|) + O(2*|E| + 1 - |V|) + O(2*|E| + 1)
	 * which is equivalent to O(|V|+3*|E|) because
	 * O(2*|E| + 1 - |V|) corresponds to the return statement and 
	 * this time will be every less in comparison to the other 
	 * statements.
	 */
	protected void getDFSResult(int curNodeIdx, Stack<Integer> stack, boolean visitedNodes[], ArrayList<Integer> dfsResult) {	
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			stack.add(curNodeIdx);
			dfsResult.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		else return;
		
		int source_idx = stack.peek();
		
		for(int i:adjacencyList.get(source_idx)) {
			forLoopCountInDFS++;
			
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
				"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
				"E F", "E G", "F I", "G H", "G I", "H I"
				};
		
		GraphUsingAdjacencyList graph = new GraphUsingAdjacencyList(9);
		graph.createGraph(vertices, edges);
		
		graph.printGraph();
		
		System.out.println("----------------------");
		
//		graph.removeEdge(1, 2);
//		graph.printGraph();
		
//		graph.addVertex("E");
		graph.addEdge(7, 2);
		
//		graph.printGraph();
		
//		ArrayList<Integer> adjacentNodes = graph.getAdjacentNodes(2);
//		System.out.println("Adjacent Nodes: "+adjacentNodes);
		
		ArrayList<Integer> bfsResult = graph.performBFS(1);
		System.out.println("BFS result: "+bfsResult);
		
		ArrayList<Integer> dfsResult = graph.performDFS(1);
		System.out.println("BFS result: "+dfsResult);
		
	}

}
