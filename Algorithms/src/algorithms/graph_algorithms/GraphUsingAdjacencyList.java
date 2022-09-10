package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import algorithms.graph_algorithms.graph_exceptions.DuplicateEdgeException;
import algorithms.graph_algorithms.graph_exceptions.InvalidEdgeFormatException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexNameException;
import algorithms.graph_algorithms.graph_exceptions.NumberOfVerticesOutOfBoundsException;
import algorithms.graph_algorithms.graph_exceptions.VertexNotExistException;

public class GraphUsingAdjacencyList extends Graph {
	
	ArrayList<LinkedList<Integer>> adjacencyList;
	
	public GraphUsingAdjacencyList(int noOfVertices) {
		this(noOfVertices, GraphType.UNDIRECTED);
	}
	
	public GraphUsingAdjacencyList(int noOfVertices, GraphType gt) {
		setNoOfVerticesLimit(noOfVertices);
		if(noOfVertices > 10 || noOfVertices < 0) {
			throw new NumberOfVerticesOutOfBoundsException(10, noOfVertices);
		}
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<noOfVertices; i++) {
			adjacencyList.add(new LinkedList<Integer>());
		}
		
		if(gt.equals(GraphType.DIRECTED))
			isDirected = true;
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
		addEdges(connections);
	}
		
	/* addEdge(int i, int j) has a time complexity of O(|V| + 1 + 2*|V|) which 
	 * is O(3*|V| + 1) and is equivalent to O(|V|).
	 */
	@Override
	public void addEdge(int i, int j) {
		checkIfEdgeIsValid(i, j);
		if(checkIfEdgeExistInGraph(i, j))
			throw new DuplicateEdgeException(i, j, isDirected);
		
		adjacencyList.get(i).add(j);
		
		if(!isDirected)
			adjacencyList.get(j).add(i);
	}
	
	/* addEdges(String connections[]) has a time complexity of 
	 * O(size*(2*|V| + 3*|V| +1) where size is the length of 
	 * connections[].
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
				int idx1 = getVertexIndex(endPoints[0]),
					idx2 = getVertexIndex(endPoints[1]);
				addEdge(idx1, idx2);
			}
			else {
				throw new InvalidEdgeFormatException(connections[i], i);
			}
		}
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
		StringBuffer sbuf = new StringBuffer();
		for(LinkedList<Integer> list: adjacencyList) {
			if(count == getNoOfVertices())
				break;
			log.info("Adjacency list for vertex: "+count+"\n");
			sbuf.append("head");
			for(Integer i: list) {
				sbuf.append(" -> "+i);
			}
			sbuf.append("\n");
			log.info(sbuf);
			sbuf.delete(0, sbuf.length());
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
		
		if(!isDirected)
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
	 * complexity of O(2*|V|+2*|E|) which is equivalent to
	 * O(|V|+|E|).
	 * 
	 * Block 1 will run |V| times.
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run exactly |V| times.
	 * 
	 * The inner for loop
	 * for(int destinationIdx: adjacencyList.get(sourceIdx)) { }
	 * will run E_sourceIdx times
	 * where E_sourceIdx represents the number of adjacent nodes 
	 * of the vertex vertices[sourceIdx].
	 * 
	 * In total this inner for loop will run 2*|E| times when all
	 * vertices in the graph are considered.
	 */
	protected void getBFSResult(int curNodeIdx, Queue<Integer> queue, boolean visitedNodes[], ArrayList<Integer> bfsResult) {
		if(!visitedNodes[curNodeIdx]) {
			// Block1
			queue.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		
		
		while(!queue.isEmpty()) {
			int sourceIdx = queue.peek();
			
			for(int destinationIdx: adjacencyList.get(sourceIdx)) {
				if(!visitedNodes[destinationIdx]) {
					queue.add(destinationIdx);
					visitedNodes[destinationIdx] = true;
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
	 * So the for loop will run exactly 2*|E| times by the time all
	 * nodes in that component of the graph of which the node having 
	 * index specified by nodeIdx in the for loop of
	 * public ArrayList<Integer> performDFS(int startNodeIdx) { }
	 * is a part are visited.
	 * 
	 * And the the statements in block 1, the sourceIdx declaration 
	 * and initialization, popping  of the stack; all will run 
	 * exactly |V| times by the time all nodes in the graph are 
	 * visited.
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
	 * O(2*|E|) + O(|V|) + O(2*|E| + 1 - |V|) + O(2*|E| + 1)
	 * which is equivalent to O(|V|+4*|E|) because
	 * O(2*|E| + 1 - |V|) corresponds to the return statement and 
	 * this time will be very less in comparison to the other 
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
		
		int sourceIdx = stack.peek();
		
		for(int i:adjacencyList.get(sourceIdx)) {
			forLoopCountInDFS++;
			
			int destinationIdx = i;
			/* At this point the vertex having index source_idx
			 * is not completely explored so we don't remove it 
			 * from the stack.
			 * 
			 * We now start exploring the vertex having index 
			 * destination_idx.
			 */
			getDFSResult(destinationIdx, stack, visitedNodes, dfsResult);
		}
		/* This is the point where the vertex having index 
		 * specified by source_idx is completely explored.
		 * So we remove that vertex having index source_idx
		 * from the stack.
		 */
		stack.pop();
	}
	
	/* For Graph using adjacency list cycleCheckerUsingBFS() has a 
	 * time complexity of O(2*|V|+2*|E|) which is equivalent to
	 * O(|V|+|E|).
	 * 
	 * Block 1 will run |V| times.
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will at most |V| times.
	 * 
	 * The inner for loop
	 * for(int destinationIdx: adjacencyList.get(sourceIdx)) { }
	 * will run E_sourceIdx times
	 * where E_sourceIdx represents the number of adjacent nodes 
	 * of the vertex vertices[sourceIdx].
	 * 
	 * In total this inner for loop will run at most 2*|E| times 
	 * when all vertices in the graph are considered.
	 */
	protected boolean cycleCheckerUsingBFS(int curNodeIdx, int prevNodeIdx, Queue<Integer[]> queue, boolean visitedNodes[]) {
		/* If a vertex is unvisited then store that vertex index and
		 * the previous vertex index from which we came to this vertex
		 * in the queue in the format {curNodeIdx, prevNodeIdx}.
		 */
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			queue.add(new Integer[] {curNodeIdx, prevNodeIdx});
			visitedNodes[curNodeIdx] = true;
		}
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			/*
			 * Note frontNodeInfo[] contains the curNodeIdx as its first
			 * element and the prevNodeIdx as its second element.
			 * 
			 */
			Integer frontNodeInfo[] = queue.remove();
			
			for(int destinationIdx:adjacencyList.get(frontNodeInfo[0])) {
				innerForLoopCountInBFSCycleChecker++;
				/*
				 * destinationIdx refers to the index of an 
				 * adjacent node of the node having index 
				 * frontNodeInfo[0].
				 */
				if(!visitedNodes[destinationIdx]) {
					queue.add(new Integer[] {destinationIdx, frontNodeInfo[0]});
					visitedNodes[destinationIdx] = true;
				}
				else {
					/*
					 * Here we see the node having index 
					 * destinationIdx is already visited so we
					 * compare this node's index with prevNodeIdx
					 * of the node having index frontNodeInfo[0].
					 */
					if(frontNodeInfo[1] != destinationIdx) {
						// Here cycle is detected.
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/*
	 * cycleCheckerUsingDFS() has a time complexity of 
	 * O(2*|E| + 2*|V|).
	 * 
	 * O(2*|V|) is the time complexity of the for-loop.
	 * 
	 * O(|V|) is the time complexity of Block 1.
	 * 
	 * O(|V|) is the time complexity of the number of times the 
	 * cycleCheckerUsingDFS() is called recursively.
	 */
	protected boolean cycleCheckerUsingDFS(int curNodeIdx, int prevNodeIdx, Stack<Integer[]> stack, boolean visitedNodes[]) {
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			stack.add(new Integer[] {curNodeIdx, prevNodeIdx});
			visitedNodes[curNodeIdx] = true;
		}
			
		Integer topNodeInfo[] = stack.peek();
		for(int destinationIdx:adjacencyList.get(topNodeInfo[0])) {
			forLoopCountInDFSCycleChecker++;
			
			/*
			 * destinationIdx refers to the index of an 
			 * adjacent node of the node having index 
			 * topNodeInfo[0].
			 */
			if(!visitedNodes[destinationIdx]) {
				if(cycleCheckerUsingDFS(destinationIdx, topNodeInfo[0], stack, visitedNodes)) 
					return true;
			}
			else {
				/*
				 * Here the node having index destinationIdx is 
				 * visited so we compare its index with the 
				 * prevNodeIdx.
				 */
				if(destinationIdx != topNodeInfo[1]) {
					return true;
				}
			}
		}
		stack.pop();
		
		return false;
		
	}
	
	/*
	 * bipartiteCheckerUsingBFS() has a time complexity of 
	 * O(|V| + 2*|E| + 1) which is equivalent to O(|V|^2).
	 * 
	 * O(|V|) is the time complexity for the while-loop for the 
	 * case when the graph is bipartite.
	 * 
	 * O(2*|E|) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 * O(1) is the time complexity of Block 1.
	 * 
	 */
	@Override
	protected boolean bipartiteCheckerUsingBFS(int curNodeIdx, int prevNodeIdx, Queue<Integer[]> queue,
			int[] nodesColor) {
		if(nodesColor[curNodeIdx] == 0) {
			// Block 1
			queue.add(new Integer[] {curNodeIdx, prevNodeIdx});
			nodesColor[curNodeIdx] = 1;
		}
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSBipartiteChecker++;
			
			Integer frontNodeInfo[] = queue.remove();
			
			for(int destinationIdx: adjacencyList.get(frontNodeInfo[0])) {
				innerForLoopCountInBFSBipartiteChecker++;
			
				if(nodesColor[destinationIdx] == 0) {
					queue.add(new Integer[] {destinationIdx, frontNodeInfo[0]});
					if(nodesColor[frontNodeInfo[0]] == 1)
						nodesColor[destinationIdx] = 2;
					else
						nodesColor[destinationIdx] = 1;
				}
				else {
					/* 
					 * Here we have found an adjacent node that
					 * is already colored.
					 */
					if(nodesColor[destinationIdx] == nodesColor[frontNodeInfo[0]])
						return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * bipartiteCheckerUsingDFS() has a time complexity of 
	 * O(|V| + 2*|E|) which is equivalent to O(|V|+|E|).
	 * 
	 * O(|V|) is the time complexity of the Block 1 and that
	 * of the recursive calls for bipartiteCheckerUsingDFS()
	 * in the case when the graph is bipartite.
	 * 
	 * O(2*|E|) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 */
	@Override
	protected boolean bipartiteCheckerUsingDFS(int curNodeIdx, int prevNodeIdx, Stack<Integer[]> stack,
			int[] nodesColor) {
		
		if(nodesColor[curNodeIdx] == 0) {
			stack.add(new Integer[] {curNodeIdx, prevNodeIdx});
			nodesColor[curNodeIdx] = 1;
		}
		
		Integer topNodeInfo[] = stack.pop();
		
		for(int destinationIdx: adjacencyList.get(topNodeInfo[0])) {
			forLoopCountInDFSBipartiteChecker++;
			/*
			 * Here we have found one adjacent node of the 
			 * node having index topNodeInfo[0].
			 * 
			 * And that adjacent node's index is 
			 * destinationIdx;
			 */
			if(nodesColor[destinationIdx] == 0) {
				stack.add(new Integer[] {destinationIdx, topNodeInfo[0]});
				if(nodesColor[topNodeInfo[0]] == 1)
					nodesColor[destinationIdx] = 2;
				else
					nodesColor[destinationIdx] = 1;
				
				if(!bipartiteCheckerUsingDFS(destinationIdx, topNodeInfo[0], stack, nodesColor))
					return false;
			}
			else {
				/* 
				 * Here we have found an adjacent node that
				 * is already colored.
				 */
				if(nodesColor[destinationIdx] == nodesColor[topNodeInfo[0]])
					return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// Create a list of Vertices.
		String vertices[] = {"A", "B", "C", "D", "E", "F", "G"};
		String edges[]= {"A B", "C B", "C D", "D E", "F E", "C G", "A F"};
		
		GraphUsingAdjacencyList graph = new GraphUsingAdjacencyList(9);
		graph.createGraph(vertices, edges);
		
		graph.printGraph();
		
		log.info("----------------------");
		
//		graph.removeEdge(1, 2);
//		graph.printGraph();
		
//		graph.addVertex("E");
//		graph.addEdge(7, 2);
		
//		graph.printGraph();
		
//		ArrayList<Integer> adjacentNodes = graph.getAdjacentNodes(2);
//		log.info("Adjacent Nodes: "+adjacentNodes);
		
//		ArrayList<Integer> bfsResult = graph.performBFS(1);
//		log.info("BFS result: "+bfsResult);
//		
//		ArrayList<Integer> dfsResult = graph.performDFS(1);
//		log.info("BFS result: "+dfsResult);
		
//		log.info("Cycle check using BFS says, Cycle present: "+graph.cycleCheckUsingBFS());
//		log.info("Cycle check using DFS says, Cycle present: "+graph.cycleCheckUsingDFS());
		
//		log.info("Bipartite check using BFS says: "+graph.bipartiteCheckUsingBFS());
//		log.info("Bipartite check using DFS says: "+graph.bipartiteCheckUsingDFS());
	}

}
