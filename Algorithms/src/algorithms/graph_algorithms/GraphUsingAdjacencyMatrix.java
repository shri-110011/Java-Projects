package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import algorithms.graph_algorithms.graph_exceptions.InvalidEdgeFormatException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexNameException;
import algorithms.graph_algorithms.graph_exceptions.NumberOfVerticesOutOfBoundsException;
import algorithms.graph_algorithms.graph_exceptions.VertexNotExistException;

public class GraphUsingAdjacencyMatrix extends Graph {
	
	boolean adjacencyMatrix[][];
	
	static final Logger log = LogManager.getLogger(GraphUsingAdjacencyMatrix.class.getName());
	
	public GraphUsingAdjacencyMatrix(int noOfVertices) {
		this(noOfVertices, GraphType.UNDIRECTED);
	}
	
	public GraphUsingAdjacencyMatrix(int noOfVertices, GraphType gt) {
		setNoOfVerticesLimit(noOfVertices);
		if(noOfVertices > 10 || noOfVertices < 0) {
			throw new NumberOfVerticesOutOfBoundsException(10, noOfVertices);
		}
		adjacencyMatrix = new boolean[noOfVertices][noOfVertices];
		if(gt.equals(GraphType.DIRECTED))
			isDirected = true;
	}
	
	// addEdge(int i, int j) has a time complexity of O(1)
	public void addEdge(int i, int j) {
		// Check if i and j are valid indices for the vertices.
		checkIfEdgeIsValid(i, j);
			
		adjacencyMatrix[i][j] = true;
		
		if(!isDirected)
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
	
	/* checkIfEdgeExistInGraph(int endPoint1, int endPoint2) has a 
	 * time complexity O(1).
	 */
	public boolean checkIfEdgeExistInGraph(int endPoint1, int endPoint2) {
		return adjacencyMatrix[endPoint1][endPoint2];
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
		StringBuffer sbuf = new StringBuffer();
		log.info("Printing the adjacency matrix ...");
		if(noOfVertices > 0) {
			for(int i=-2; i<noOfVertices; i++) {
				if(i<0) sbuf.append(" ");
				else {
					sbuf.append(getVertex(i));
					if(i!=noOfVertices-1)
						sbuf.append(" ");
				}
			
			}
			sbuf.append("\n");
			log.info(sbuf);
			sbuf.delete(0, sbuf.length());
			for(int i=0; i<noOfVertices; i++) {
				sbuf.append(getVertex(i)+" ");
				for(int j=0; j<noOfVertices; j++) {
					sbuf.append(adjacencyMatrix[i][j]?1:0);
					if(j!=noOfVertices-1)
						sbuf.append(" ");
				}
				sbuf.append("\n");
				log.info(sbuf);
				sbuf.delete(0, sbuf.length());
			}
		}
		else
			log.info("Graph is empty.");
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
		
		if(!isDirected)
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
	
	/* For Graph using adjacency matrix cycleCheckerUsingBFS() has 
	 * a time complexity of O(|V|*(1+|V|)+1) = O(|V|+|V|^2+1) which 
	 * is equivalent to O(|V|^2).
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run at most |V| times.
	 * 
	 * The inner for loop
	 * for(int i=0; i<noOfVertices; i++) { }
	 * will run at most |V| times for each iteration of the while 
	 * loop.
	 */
	protected boolean cycleCheckerUsingBFS(Queue<Integer[]> queue, boolean visitedNodes[]) {
	int noOfVertices = getNoOfVertices();
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			/*
			 * Note frontNodeInfo[] contains the curNodeIdx as its first
			 * element and the prevNodeIdx as its second element.
			 * 
			 */
			Integer frontNodeInfo[] = queue.remove();
			
			for(int i=0; i<noOfVertices; i++) {
				innerForLoopCountInBFSCycleChecker++;
				if(adjacencyMatrix[frontNodeInfo[0]][i]) {
					/*
					 * destinationIdx refers to the index of an 
					 * adjacent node of the node having index 
					 * frontNodeInfo[0].
					 */
					int destinationIdx = i;
					if(!visitedNodes[destinationIdx]) {
						/* If a vertex is unvisited then store that vertex index and
						 * the previous vertex index from which we came to this vertex
						 * in the queue in the format {curNodeIdx, prevNodeIdx}.
						 */
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
		}
		
		return false;
	}
	
	/*
	 * cycleCheckerUsingDFS(int curNodeIdx, int prevNodeIdx, Stack<Integer[]> stack, boolean visitedNodes[])
	 * has a time complexity of O(|V|*|V| + |V|) which is 
	 * equivalent to O(|V|^2).
	 * 
	 * O(|V|^2) is the time complexity of the for-loop in case when
	 * there is no cycle in the graph.
	 * 
	 * O(|V|) is the time complexity for popping the stack and for 
	 * recursive calls to cycleCheckerUsingDFS().
	 * 
	 */
	protected boolean cycleCheckerUsingDFS(Stack<Integer[]> stack, boolean visitedNodes[]) {
		int noOfVertices = getNoOfVertices();
			
		Integer topNodeInfo[] = stack.peek();
		for(int i=0; i<noOfVertices; i++) {
			forLoopCountInDFSCycleChecker++;
			if(adjacencyMatrix[topNodeInfo[0]][i]) {
				/*
				 * destinationIdx refers to the index of an 
				 * adjacent node of the node having index 
				 * topNodeInfo[0].
				 */
				int destinationIdx = i;
				if(!visitedNodes[destinationIdx]) {
					stack.add(new Integer[] {destinationIdx, topNodeInfo[0]});
					visitedNodes[destinationIdx] = true;
					if(cycleCheckerUsingDFS(stack, visitedNodes)) 
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
		}
		stack.pop();
		
		return false;
	}
	
	/*
	 * bipartiteCheckerUsingBFS() has a time complexity of 
	 * O(|V| + |V|*|V| + 1) which is equivalent to O(|V|^2).
	 * 
	 * O(|V|) is the time complexity for the while-loop for the 
	 * case when the graph is bipartite.
	 * 
	 * O(|V|^2) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 * O(1) is the time complexity of return true; statement 
	 * after the while loop in the case if the graph is bipartite.
	 * 
	 */
	@Override
	protected boolean bipartiteCheckerUsingBFS(Queue<Integer[]> queue,
			int[] nodesColor) {
		int noOfVertices = getNoOfVertices();

		while(!queue.isEmpty()) {
			whileLoopCountInBFSBipartiteChecker++;
			
			Integer frontNodeInfo[] = queue.remove();
			
			for(int i=0; i<noOfVertices; i++) {
				innerForLoopCountInBFSBipartiteChecker++;
				
				if(adjacencyMatrix[frontNodeInfo[0]][i]) {
					int destinationIdx = i;
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
		}
		return true;
	}
	
	/*
	 * bipartiteCheckerUsingDFS() has a time complexity of 
	 * O(|V| + |V|*|V|) which is equivalent to O(|V|^2).
	 * 
	 * O(|V|) is the time complexity of popping the stack and 
	 * that of the recursive calls for bipartiteCheckerUsingDFS()
	 * in the case when the graph is bipartite.
	 * 
	 * O(|V|^2) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 */
	@Override
	protected boolean bipartiteCheckerUsingDFS(Stack<Integer[]> stack,
			int[] nodesColor) {
		
		int noOfVertices = getNoOfVertices();
		
		Integer topNodeInfo[] = stack.pop();
		
		for(int i=0; i<noOfVertices; i++) {
			forLoopCountInDFSBipartiteChecker++;

			if(adjacencyMatrix[topNodeInfo[0]][i]) {
				/*
				 * Here we have found one adjacent node of the 
				 * node having index topNodeInfo[0].
				 * 
				 * And that adjacent node's index is 
				 * destinationIdx;
				 */
				int destinationIdx = i;
				if(nodesColor[destinationIdx] == 0) {
					stack.add(new Integer[] {destinationIdx, topNodeInfo[0]});
					if(nodesColor[topNodeInfo[0]] == 1)
						nodesColor[destinationIdx] = 2;
					else
						nodesColor[destinationIdx] = 1;
					
					if(!bipartiteCheckerUsingDFS(stack, nodesColor))
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
		}
		
		return true;
	}
	
	/*
	 * cycleCheckerUsingDFSForDirectedGraph() has a time complexity
	 * of O(|V| + |V|^2) which is equivalent to O(|V|^2).
	 * 
	 * O(|V|) is the time complexity of the Block 1
	 * in the case when the graph is acyclic.
	 * 
	 * The recursive calls to cycleCheckerUsingDFSForDirectedGraph()
	 * will occur at most |V| times in case when the graph is 
	 * acyclic. 
	 * 
	 * O(|V|^2) is the time complexity of for-loop for the 
	 * case when the graph is acyclic.
	 * 
	 */
	protected boolean cycleCheckerUsingDFSForDirectedGraph(Stack<Integer[]> stack, boolean visitedNodes[], boolean dfsVisited[]) {
		
		int noOfVertices = getNoOfVertices();
		Integer topNodeInfo[] = stack.pop();
		
		for(int i=0; i<noOfVertices; i++) {
			forLoopCountInDFSCycleChecker++;
			if(adjacencyMatrix[topNodeInfo[0]][i]) {
				/* Found the adjacent node of the node having
				 * index curNodeIdx.
				 */
				int destinationIdx = i;
				
				if(!visitedNodes[destinationIdx]) {
					// Block 1
					stack.add(new Integer[] {destinationIdx, topNodeInfo[0]});
					visitedNodes[destinationIdx] = true;
					dfsVisited[destinationIdx] = true;
					
					if(cycleCheckerUsingDFSForDirectedGraph(stack, visitedNodes, dfsVisited)) return true;
				}
				else {
					/* Here the adjacent node has already 
					 * been visited.
					 * So we compare its index with the 
					 * prevNodeIdx.
					 * 
					 * Note: topNodeInfo[0] contains curNodeIdx
					 * topNodeInfo[1] contains prevNodeIdx
					 */
					if(destinationIdx != topNodeInfo[1] &&
							dfsVisited[destinationIdx]) {
						return true;
					}
				}
			}
		}
		/* Here we have a node whose exploration is complete. So
		 * we mark the dfsVisited for that node as false.
		 * 
		 */
		dfsVisited[topNodeInfo[0]] = false;
			
		return false;
	}
	
	/*
	 * getDFSTopologicalSortResult() has a time complexity of 
	 * O(|V|*|E| + |V|) which is equivalent to O(|V|*|E|).
	 * 
	 * The for-loop will run exactly |V|*|E| times.
	 * 
	 * The recursive call for getDFSTopologicalSortResult() and 
	 * stack push operation will occur exactly |V| times.  
	 */
	@Override
	protected void getDFSTopologicalSortResult(int curNodeIdx, Stack<Integer> stack, boolean[] visitedNodes,
			ArrayList<Integer> dfsTopoSortResult) {
		int noOfVertices = getNoOfVertices();
		
		for(int i=0; i<noOfVertices; i++) {
			forLoopCountInDFSTopoSort++;
			int destinationIdx = i;
			if(adjacencyMatrix[curNodeIdx][i]) {
				if(!visitedNodes[destinationIdx]) {
					visitedNodes[destinationIdx] = true;
					// Pushing the adjacent node to the stack
					getDFSTopologicalSortResult(destinationIdx, stack, visitedNodes, dfsTopoSortResult);
				}
			}
		}
		stack.push(curNodeIdx);
		if(!stack.isEmpty())
			dfsTopoSortResult.add(stack.pop());
	}
	
	/* getIndegrees() has a time complexity of O(|V|^2+1)
	 * which is equivalent to O(|V|^2).
	 * 
	 * The outer for-loop runs exactly |V| times.
	 * The inner for-loop runs exactly |V|^2 times.
	 * 
	 */
	protected int[] getIndegrees() {
		int noOfVertices = getNoOfVertices(),
				inDegrees[] = new int[noOfVertices];
		
		for(int i=0; i<noOfVertices; i++) {
			outerForLoopOfIndegrees++;
			int inDegree = 0;
			for(int j=0; j<noOfVertices; j++) {
				innerForLoopOfIndegrees++;
				if(adjacencyMatrix[j][i]) {
					inDegree++;
				}
			}
			inDegrees[i] = inDegree;
		}
		log.info("Indegrees array: "+Arrays.toString(inDegrees));
		return inDegrees;
	}
	
	/* getBFSTopologicalSortResult() has a time complexity of
	 * O(|V|^2+|V|) which is equivalent to O(|V|^2).
	 * 
	 * The while loop runs exactly |V| times in case the graph 
	 * is directed.
	 * 
	 * The inner for loop runs exactly |V|^2 times in case 
	 * the graph is directed.
	 * 
	 */
	protected void getBFSTopologicalSortResult(Queue<Integer> queue, int indegrees[], ArrayList<Integer> bfsTopoSortResult) {
		
		/* At this point the queue already contains nodes having
		 * indegree as 0.
		 */

		int noOfVertices = getNoOfVertices();
		while(!queue.isEmpty()) {
			whileLoopCountInBFSTopoSort++;
			int nodeIdx = queue.remove();
			
			for(int i=0; i<noOfVertices; i++) {
				innerForLoopCountInBFSTopoSort++;
				if(adjacencyMatrix[nodeIdx][i]) {
					/* Here we have found the adjacent node of the 
					 * node having index nodeIdx. 
					 */
					int destinationIdx = i;
					if(--indegrees[destinationIdx] == 0) {
						queue.add(destinationIdx);
						bfsTopoSortResult.add(destinationIdx);
					}
				}
			}
		}
	}
	
	/* cycleCheckerUsingBFSForDirectedGraph() has a time 
	 * complexity of O(|V|+|V|^2+1) which is equivalent to 
	 * O(|V|^2);
	 * 
	 * The while loop will run at most |V| times and that 
	 * in the case the graph is acyclic.
	 * 
	 * The inner for-loop will run at most |V|^2 times in the 
	 * case the graph is acyclic. 
	 * 
	 */
	protected boolean cycleCheckerUsingBFSForDirectedGraph(Queue<Integer> queue, int inDegrees[]) {
		
		int noOfZerosInInDegrees = queue.size(),
				noOfVertices = getNoOfVertices();
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			int nodeIdx = queue.remove();
			
			for(int i=0; i<noOfVertices; i++) {
				innerForLoopCountInBFSCycleChecker++;
				if(adjacencyMatrix[nodeIdx][i]) {
					/* Here we have found the adjacent node of the 
					 * node having index nodeIdx. 
					 */
					int destinationIdx = i;
					if(--inDegrees[destinationIdx] == 0) {
						queue.add(destinationIdx);
						noOfZerosInInDegrees++;
					}
				}
			}
		}
		
		log.info("noOfZerosInInDegrees: "+noOfZerosInInDegrees);
		if(noOfZerosInInDegrees == getNoOfVertices()) return false;
		return true;
	}
	
	public static void main(String[] args) {
		// Create a list of Vertices.
		String vertices[] = {"A", "B", "C", "D", "E"};
		String edges[]= {"B A", "A C", "D C", "B D", "C E"};
		
		// {"A B", "A C", "B D"}
		/* {
		"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
		"E F", "E G", "F I", "G H", "G I", "H I"
		}
		*/
		
		GraphUsingAdjacencyMatrix graph = new GraphUsingAdjacencyMatrix(9, GraphType.DIRECTED);
		graph.createGraph(vertices, edges);
		
//		graph.addVertex("E");
//		graph.addEdge(0, 4);
		
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
//		log.info("Adjacent Nodes: "+adjacentNodes);
		
//		ArrayList<Integer> bfsResult = graph.performBFS(0);
//		log.info("BFS result: "+bfsResult);
//		
//		ArrayList<Integer> dfsResult = graph.performDFS(0);
//		log.info("DFS result: "+dfsResult);
		
//		log.info("Cycle check using BFS says, Cycle present: "+graph.cycleCheckUsingBFS());
//		log.info("Cycle check using DFS says, Cycle present: "+graph.cycleCheckUsingDFS());
	
//		log.info("Bipartite check using BFS says: "+graph.bipartiteCheckUsingBFS());
//		log.info("Bipartite check using DFS says: "+graph.bipartiteCheckUsingDFS());
	
//		ArrayList<Integer> dfsTopoSortResult = graph.performDFSTopologicalSorting(1);
//		log.info("DFS topo sort result: "+dfsTopoSortResult);
		
//		ArrayList<Integer> bfsTopoSortResult = graph.performBFSTopologicalSorting();
//		log.info("BFS topo sort result: "+bfsTopoSortResult);

	}

}
