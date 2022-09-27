package algorithms.graph_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import algorithms.data_structures.HEAP_TYPE;
import algorithms.data_structures.Heap;
import algorithms.graph_algorithms.graph_exceptions.InvalidEdgeFormatException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexNameException;
import algorithms.graph_algorithms.graph_exceptions.MethodNotSupportedException;
import algorithms.graph_algorithms.graph_exceptions.NumberOfVerticesOutOfBoundsException;
import algorithms.graph_algorithms.graph_exceptions.VertexNotExistException;

public class GraphUsingAdjacencyMatrix extends Graph {
	
	int adjacencyMatrix[][];
	
	static final Logger log = LogManager.getLogger(GraphUsingAdjacencyMatrix.class.getName());
	
	public GraphUsingAdjacencyMatrix(int noOfVertices) {
		this(noOfVertices, GraphType.UNDIRECTED);
	}
	
	public GraphUsingAdjacencyMatrix(int noOfVertices, GraphType gt) {
		setNoOfVerticesLimit(noOfVertices);
		if(noOfVertices > 10 || noOfVertices < 0) {
			throw new NumberOfVerticesOutOfBoundsException(10, noOfVertices);
		}
		adjacencyMatrix = new int[noOfVertices][noOfVertices];
		
		/* Is any cell at (i, j) in adjacencyMatrix has a 
		 * vale of Integer.MAX_VALUE this means that the
		 * nodes having indices i, j are not connected.
		 */
		for(int row[]: adjacencyMatrix)
		Arrays.fill(row, Integer.MAX_VALUE);
		
		if(gt.equals(GraphType.DIRECTED))
			isDirected = true;
	}
	
	public GraphUsingAdjacencyMatrix(int noOfVertices, GraphType gt, boolean hasWeight) {
		this(noOfVertices, gt);
		this.hasWeight = hasWeight;
	}
	
	// addEdge(int i, int j) has a time complexity of O(1)
	public void addEdge(int i, int j) {
		if(hasWeight) {
			throw new MethodNotSupportedException("addEdge", "unweighted edge in weighted graph");
		}
		
		// Check if i and j are valid indices for the vertices.
		checkIfEdgeIsValid(i, j);
			
		adjacencyMatrix[i][j] = 1;
		
		if(!isDirected)
			adjacencyMatrix[j][i] = 1;
	}
	
	public void addEdge(int i, int j, int weight) {
		if(!hasWeight) {
			throw new MethodNotSupportedException("addEdge", "weighted edge in unweighted graph");
		}
		// Check if i and j are valid indices for the vertices.
		checkIfEdgeIsValid(i, j);
			
		adjacencyMatrix[i][j] = weight;
		
		if(!isDirected)
			adjacencyMatrix[j][i] = weight;
	}
	
	/* addEdges(String connections[]) has a time complexity of
	 * O(size*(4*|V|)) + O(1) where size is the number of edges in 
	 * connections.
	 */
	public void addEdges(String connections[]) {
		for(int i=0; i<connections.length; i++) {
			String endPoints[] = connections[i].split(" ");
			
			int expectedEndPointsLen = hasWeight?3:2;
			if(endPoints.length == expectedEndPointsLen) {
				int count = 0;
				for(String endPoint: endPoints) {
					if(count == 2) break;

					// Here we check if vertex name is valid.
					if(!endPoint.matches("[a-zA-Z][0-9a-zA-Z]*")) {
						throw new InvalidVertexNameException(endPoint, connections[i], i);
					}
					else {
						if(!checkIfVertexExistInGraph(endPoint)) {
							throw new VertexNotExistException(endPoint, connections[i], i);
						}
					}
					count++;
				}
				/* Here we are sure that the string representation of the edge
				 * is valid so we can get the indices of the end points of the 
				 * edge.
				 */
				int idx1 = getVertexIndex(endPoints[0]),
					idx2 = getVertexIndex(endPoints[1]);
				
				if(!hasWeight)
					addEdge(idx1, idx2);
				else {
					int weight = Integer.parseInt(endPoints[2]);
					addEdge(idx1, idx2, weight);
				}
			}
			else {
				if(!hasWeight)
					throw new InvalidEdgeFormatException(connections[i], i);
				else
					throw new InvalidEdgeFormatException(connections[i], i, hasWeight);
			}
		}
	}
	
	/* checkIfEdgeExistInGraph(int endPoint1, int endPoint2) has a 
	 * time complexity O(1).
	 */
	public boolean checkIfEdgeExistInGraph(int endPoint1, int endPoint2) {
		return adjacencyMatrix[endPoint1][endPoint2] != Integer.MAX_VALUE;
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
					sbuf.append(adjacencyMatrix[i][j] != Integer.MAX_VALUE?adjacencyMatrix[i][j]:"*");
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
		adjacencyMatrix[idx1][idx2] = Integer.MAX_VALUE;
		
		if(!isDirected)
			adjacencyMatrix[idx2][idx1] = Integer.MAX_VALUE;
		
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
			if(adjacencyMatrix[idx][i] != Integer.MAX_VALUE)
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
				if(adjacencyMatrix[source_idx][i] != Integer.MAX_VALUE && !visitedNodes[i]) {
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
			if(adjacencyMatrix[source_idx][i] != Integer.MAX_VALUE) {
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
				if(adjacencyMatrix[frontNodeInfo[0]][i] != Integer.MAX_VALUE) {
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
			if(adjacencyMatrix[topNodeInfo[0]][i] != Integer.MAX_VALUE) {
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
				
				if(adjacencyMatrix[frontNodeInfo[0]][i] != Integer.MAX_VALUE) {
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

			if(adjacencyMatrix[topNodeInfo[0]][i] != Integer.MAX_VALUE) {
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
			if(adjacencyMatrix[topNodeInfo[0]][i] != Integer.MAX_VALUE) {
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
			if(adjacencyMatrix[curNodeIdx][i] != Integer.MAX_VALUE) {
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
				if(adjacencyMatrix[j][i] != Integer.MAX_VALUE) {
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
				if(adjacencyMatrix[nodeIdx][i] != Integer.MAX_VALUE) {
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
		
		if(!isDirected) {
			throw new MethodNotSupportedException("cycleCheckerUsingBFSForDirectedGraph", "undirected graph");
		}
		
		int noOfZerosInInDegrees = queue.size(),
				noOfVertices = getNoOfVertices();
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			int nodeIdx = queue.remove();
			
			for(int i=0; i<noOfVertices; i++) {
				innerForLoopCountInBFSCycleChecker++;
				if(adjacencyMatrix[nodeIdx][i] != Integer.MAX_VALUE) {
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
	
	/* findSDPWeightsUsingBFS(int srcIdx) 
	 * has a time complexity of BigOmega(|V|^2) because we are 
	 * using BFS algorithm over here in order to find the 
	 * shortest distance of each node from the source.
	 * 
	 * Space complexity is: O(N)+O(N) = O(2N)
	 * O(N) for sda(shortest distance array[])
	 * O(N) for the queue
	 * 
	 * Here we use BFS algorithm to find the weight/length 
	 * of the shortest distance path in undirected or 
	 * directed graphs having edge weight or not.
	 * 
	 * The only limitation on this algorithm is that the
	 * the graph shouldn't contain any -ve edge cycles
	 * because that would result in an infinite loop. 
	 */
	@Override
	protected int[] findSDPWeightsUsingBFS(int srcIdx) {
		
//		if(isDirected) {
//			throw new MethodNotSupportedException("findShortestDistanceInUndirectedGraph", "directed graph");
//		}
		
		int sda[] = new int[getNoOfVertices()];
		Arrays.fill(sda, Integer.MAX_VALUE);
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		/* Insert the src node to the queue and put 0 at
		 * sda[src node idx].
		 */
		queue.add(srcIdx);
		sda[srcIdx] = 0;
		
		// Run a while loop until the queue is empty
		while(!queue.isEmpty()) {
			whileLoopCountInFindSDPWeights++;
			
			// Remove an element from the queue
			int idx = queue.remove();
			
			for(int i=0; i<getNoOfVertices(); i++) {
				forLoopCountInFindSDPWeights++;
				int destinationIdx = -1;
				boolean adjacentNodeFound = false;
				if(adjacencyMatrix[idx][i] != Integer.MAX_VALUE) {
					destinationIdx = i;
					adjacentNodeFound = true;
				}
				if(adjacentNodeFound) {
					/* Find the adjacent nodes of that removed element
					 * and check if the distance of the adjacent node
					 * from the src is less than the value at
					 * sda[adjacent node idx] and sda[adjacent node idx]
					 * is not IntMax then push that adjacent
					 * node to the queue.
					 */
					int newDistance = sda[idx]+adjacencyMatrix[idx][destinationIdx];
					if(newDistance < sda[destinationIdx]) {
						queue.add(destinationIdx);
						sda[destinationIdx] = newDistance;
					}
					adjacentNodeFound = false;
				}
			}
		}
		log.info("whileLoopCountInFindSDPWeights: "+whileLoopCountInFindSDPWeights);
		log.info("forLoopCountInFindSDPWeights: "+forLoopCountInFindSDPWeights);
		return sda;
	}
	
	/* 
	 * findSDPWeightsInDAG() has a time complexity of O(|V|^2).
	 */
	@Override
	protected int[] findSDPWeightsInDAG(int srcIdx, ArrayList<Integer> topoSortResult) {
		int sda[] = new int[getNoOfVertices()];
		Arrays.fill(sda, Integer.MAX_VALUE);
		
		sda[srcIdx] = 0;
		
		for(int idx: topoSortResult) {
			outerForLoopCountInFindSDPWeightsInDAG++;
			for(int i=0; i<getNoOfVertices(); i++) {
				innerForLoopCountInFindSDPWeightsInDAG++;
				int destinationIdx = -1;
				boolean adjacentNodeFound = false;
				if(adjacencyMatrix[idx][i] != Integer.MAX_VALUE) {
					destinationIdx = i;
					adjacentNodeFound = true;
				}
				if(adjacentNodeFound) {
					int newDistance = sda[idx]+adjacencyMatrix[idx][destinationIdx];
					if(newDistance < sda[destinationIdx]) {
						sda[destinationIdx] = newDistance;
					}
					adjacentNodeFound = false;
				}
			}
		}
		log.info("outerForLoopCountInFindSDPWeightsInDAG: "+outerForLoopCountInFindSDPWeightsInDAG);
		log.info("innerForLoopCountInFindSDPWeightsInDAG: "+innerForLoopCountInFindSDPWeightsInDAG);
		return sda;
	}
	
	/* TC of findSDPWeightsUsingDijkstra(int srcIdx) is:
	 * O(|V|*log|V| + |V|*(log(|V|)+|V|)+ |V|^2) which is
	 * equivalent to O(|V|^2 + |V|*log(|V|)).
	 * 
	 * Operations being performed in Dijkstra:
	 * 1. Extraction of node having minimum weight from the 
	 * heap. TC: O(log|V|)
	 * 2. Decrease key operation.
	 * TC: O(log|V|).
	 * 3. Finding adjacent nodes for a vertex.
	 * TC: O(|V|)
	 * 4. Finding index of adjacent node in the heap.
	 * TC: O(|V|)
	 * 
	 * Space Complexity of 
	 * findSDPWeightsUsingDijkstra(int srcIdx) is:
	 * O(|V|) for creating the ArrayList to store the heap. 
	 * 
	 */
	@Override
	protected int[] findSDPWeightsUsingDijkstra(int srcIdx) {
		int sda[] = new int[getNoOfVertices()];
		Arrays.fill(sda, Integer.MAX_VALUE);
		
		/* Initialize the priority value corresponding to each 
		 * vertex to +Inf.
		 * 
		 * That step is done while creation of the graph 
		 * itself.
		 */
		
		/* We use a binary min heap to store the priority 
		 * values i.e. the reaching cost of each vertex in 
		 * the graph.
		 */
		ArrayList<Vertex> al = new ArrayList<>();
		al.addAll(getVertices());
		Heap<Vertex> dValues = new Heap<>(al, HEAP_TYPE.MIN_HEAP);
		dValues.heapify();
		
		// Make the dValue of source node to be 0.
		Vertex srcVertex = getVertices().get(srcIdx);
		srcVertex.setPriorityValue(0);
		
		// While loop will run until the heap is empty.
		while(dValues.getHeapSize() != 0) {
			/* Extract the node having the minimum priority 
			 * value.
			 */
			whileLoopCountInFindSDPWeightsUsingDijkstra++;
			
			/* Here we are extracting the node having the 
			* minimum reaching cost from the heap.
			* 
			* Note: The delete() in Heap<T> will return the 
			* minimum element from the heap because we have
			* created a min heap.
			* 
			* And the TC for delete() is O(log(N)) where N
			* is the number of nodes in the heap.
			* 
			* Also this delete() does not remove the minimum
			* element completely from the ArrayList al.
			*/
			int idx = getVertexIndex(dValues.delete().getName());
			Vertex alternateVertex = getVertices().get(idx);
			sda[idx] = alternateVertex.getPriorityValue();
			
			/* Relax the adjacent nodes of this node having 
			 * index idx.
			 */
			for(int i=0; i<getNoOfVertices(); i++) {
				forLoopCountInFindSDPWeightsUsingDijkstra++;
				Vertex adjacentVertex = null;
				boolean adjacentNodeFound = false;
				int edgeWeight = 0;
				if((edgeWeight = adjacencyMatrix[idx][i]) != Integer.MAX_VALUE) {
					adjacentVertex = getVertex(i);
					adjacentNodeFound = true;
				}
				if(adjacentNodeFound) {
					int newPriorityValue = alternateVertex.getPriorityValue()+edgeWeight;
					if(newPriorityValue < adjacentVertex.getPriorityValue()) {
						// decrease key operation
						adjacentVertex.setPriorityValue(newPriorityValue);			
						dValues.decreaseKey(al.indexOf(adjacentVertex), adjacentVertex);
					}
					adjacentNodeFound = false;
				}
			}
		}
		log.info("whileLoopCountInFindSDPWeightsUsingDijkstra: "+whileLoopCountInFindSDPWeightsUsingDijkstra);
		log.info("forLoopCountInFindSDPWeightsUsingDijkstra: "+forLoopCountInFindSDPWeightsUsingDijkstra);
		return sda;
	}
	
	public static void main(String[] args) {
		// Create a list of Vertices.
		String vertices[] = {"A", "B", "C", "D", "E", "F", "G"};
		String edges[]= {"A B 5", "A C 1", "C E 1", "E F 1", "B D 1", "D G 2", "F B 1", "F D 3"};
		
		// {"A B", "A C", "B D"}
		/* {
		"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
		"E F", "E G", "F I", "G H", "G I", "H I"
		}
		*/
		
		GraphUsingAdjacencyMatrix graph = new GraphUsingAdjacencyMatrix(9, GraphType.DIRECTED, true);
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

		log.info("sda[]: "+Arrays.toString(graph.findSDPWeightsUsingBFS(0)));
//		log.info("sda[]: "+Arrays.toString(graph.getSDPWeightsInDAG(0)));
	}
}
