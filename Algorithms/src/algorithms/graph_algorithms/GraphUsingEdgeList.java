package algorithms.graph_algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import algorithms.graph_algorithms.graph_exceptions.DuplicateEdgeException;
import algorithms.graph_algorithms.graph_exceptions.InvalidEdgeFormatException;
import algorithms.graph_algorithms.graph_exceptions.InvalidVertexNameException;
import algorithms.graph_algorithms.graph_exceptions.VertexNotExistException;

public class GraphUsingEdgeList extends Graph {
	private List<Edge> edges;
	
	static final Logger log = LogManager.getLogger(GraphUsingEdgeList.class.getName());
	
	public GraphUsingEdgeList() {
		edges = new LinkedList<Edge>();
	}
	
	public GraphUsingEdgeList(GraphType gt) {
		edges = new LinkedList<Edge>();
		if(gt.equals(GraphType.DIRECTED))
			isDirected = true;
	}
	
	/* addEdge(int i, int j) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 * Note: edges is a linked list.
	 */
	public void addEdge(int i, int j) {
		
		checkIfEdgeIsValid(i, j);

		if(checkIfEdgeExistInGraph(i, j)) {
			throw new DuplicateEdgeException(i, j, isDirected);
		}
			
		Edge edge = new Edge(i, j);
		
		if(isDirected)
			edge.setDirected(true);
		
		edges.add(edge);
	}
	
	/* addEdge(Edge connection) has a time complexity of O(|E|) where |E| represents 
	 * the number of edges in the graph.
	 * Note: edges is a linked list.
	 */
	public void addEdge(Edge connection) {
		int i = connection.getEndPoint1(), j = connection.getEndPoint2();
		checkIfEdgeIsValid(i, j);

		if(checkIfEdgeExistInGraph(i, j))
			throw new DuplicateEdgeException(i, j, isDirected);
		
		if(isDirected)
			connection.setDirected(true);
		
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
		for(Edge edge: connections) {
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
					Edge edge = new Edge(idx1, idx2);
					if(isDirected)
						edge.setDirected(true);
					edges.add(edge);
				}
				else {
					throw new DuplicateEdgeException(idx1, idx2, i, isDirected);
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
			/* In case of directed graph we need to maintain the 
			 * order of the endPoints i.e. endPoint1 refers to the 
			 * start/source vertex and endPoint2 refers to the 
			 * end/destination vertex.
			 */
			if(isDirected) {
				if(e.getEndPoint1() == endPoint1 && e.getEndPoint2() == endPoint2) return true;
			}
			else {
				if(e.getEndPoint1() == endPoint1 && e.getEndPoint2() == endPoint2) return true;
				else if(e.getEndPoint2() == endPoint1 && e.getEndPoint1() == endPoint2) return true;
			}
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
	
	/* removeVertex(int idx) has a time complexity of O(|V|+|E| +1 ) 
	 * where |V| represents the number of vertices and |E| represents
	 * the number of edges in the graph.
	 */
	@Override
	public boolean removeVertex(int idx) {
		ListIterator<Edge> li = edges.listIterator();
		while(li.hasNext()) {
			Edge edge = (Edge)li.next();
			int endPoint1 = edge.getEndPoint1() , endPoint2 = edge.getEndPoint2();
			
			/* Remove all the edges from the edges list that contain 
			 * this vertex as an endPoint.
			 */
			if(edge.getEndPoint1() == idx || edge.getEndPoint2() == idx) {
				li.remove();
			}
			else {
				/* For nodes having index greater than the index of the 
				 * node to be removed we need to adjust their index i.e.
				 * we have to decrement their index by 1.
				 * 
				 */
				if(endPoint1 > idx) {
					edge.setEndPoint1(endPoint1-1);
				}
				if(endPoint2 > idx) {
					edge.setEndPoint2(endPoint2-1);
				}
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
			/* For directed graph only one way checking would be 
			 * there i.e. the endPoint1 would correspond to idx1 and
			 * endPoint2 to idx2.
			 * 
			 * And for undirected graph two way checking would be 
			 * there.
			 * 
			 */
			if(edge.getEndPoint1() == idx1 && edge.getEndPoint2() == idx2) {
				edges.remove(edge);
				flag = true;
				break;
			}
			if(!isDirected) {
				if(edge.getEndPoint1() == idx2 && edge.getEndPoint2() == idx1) {
					edges.remove(edge);
					flag = true;
					break;
				}
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
			if(edge.getEndPoint1() == idx) {
				adjacentNodes.add(edge.getEndPoint2());
			}
			if(!isDirected) {
				if(edge.getEndPoint2() == idx) {
					adjacentNodes.add(edge.getEndPoint1());
				}
			}
		}
		return adjacentNodes;
	}
	
	public void printGraph() {
		log.info("Printing the edge list ...");
		log.info(edges);
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
				if(e.getEndPoint1() == source_idx) {
					int destination_idx = e.getEndPoint2();
					if(!visitedNodes[destination_idx]) {
						queue.add(destination_idx);
						visitedNodes[destination_idx] = true;
					}
				}
				if(!isDirected) {
					if(e.getEndPoint2() == source_idx) {
						int destination_idx = e.getEndPoint1();
						if(!visitedNodes[destination_idx]) {
							queue.add(destination_idx);
							visitedNodes[destination_idx] = true;
						}
					}
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
		forLoopCountInDFS++;
		if(!visitedNodes[curNodeIdx]) {
			// Block 1
			stack.add(curNodeIdx);
			dfsResult.add(curNodeIdx);
			visitedNodes[curNodeIdx] = true;
		}
		else return;
		
		int source_idx = stack.peek();
		
		for(Edge e: edges) {
		
			if(e.getEndPoint1() == source_idx) {
				int destination_idx = e.getEndPoint2();
				/* At this point the vertex having index source_idx
				 * is not completely explored so we don't remove it 
				 * from the stack.
				 * 
				 * We now start exploring the vertex having index 
				 * destination_idx.
				 */
				getDFSResult(destination_idx, stack, visitedNodes, dfsResult);
			}
			if(!isDirected) {
				if(e.getEndPoint2() == source_idx) {
					int destination_idx = e.getEndPoint1();
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
		}
		/* This is the point where the vertex having index 
		 * specified by source_idx is completely explored.
		 * So we remove that vertex having index source_idx
		 * from the stack.
		 */
		stack.pop();
	}
	
	/* For Graph using edge list 
	 * cycleCheckerUsingBFS() has a time complexity 
	 * of O(|V|*(1+|E|)+1) = O(|V|+|V|*|E|+1) which is equivalent to
	 * O(|V|*|E|).
	 * 
	 * The while loop will run only when queue is not empty and each
	 * node will be added to the queue exactly once so while loop
	 * will run at most |V| times in case if no cycle is present
	 * and where |V| represents the number
	 * of vertices in the current component of the graph.
	 * 
	 * The inner for loop
	 * for(Edge e: edges) { }
	 * will run at most |E| times.
	 * 
	 */
	protected boolean cycleCheckerUsingBFS(Queue<Integer[]> queue, boolean visitedNodes[]) {
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			/*
			 * Note frontNodeInfo[] contains the curNodeIdx as its first
			 * element and the prevNodeIdx as its second element.
			 * 
			 */
			Integer frontNodeInfo[] = queue.remove();
			
			for(Edge edge: edges) {
				innerForLoopCountInBFSCycleChecker++;
				
				boolean hasFoundAdjacentNode = false;
				int destinationIdx = -1;
				
				if(edge.getEndPoint1() == frontNodeInfo[0]) {
					/*
					 * destinationIdx refers to the index of an 
					 * adjacent node of the node having index 
					 * frontNodeInfo[0].
					 */
					destinationIdx = edge.getEndPoint2();
					hasFoundAdjacentNode = true;
				}
				else if(edge.getEndPoint2() == frontNodeInfo[0]) {
					/*
					 * destinationIdx refers to the index of an 
					 * adjacent node of the node having index 
					 * frontNodeInfo[0].
					 */
					destinationIdx = edge.getEndPoint1();
					hasFoundAdjacentNode = true;
				}
				
				if(hasFoundAdjacentNode) {
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
	 * has a time complexity of O(|V|*|E|) + O(|V|) which is 
	 * equivalent to O(|V|*|E|).
	 * 
	 * O(|V|*|E|) is the time complexity of the for-loop in case when
	 * there is no cycle in the graph.
	 * 
	 * O(|V|) is the time complexity for popping the stack and for 
	 * recursive calls to cycleCheckerUsingDFS().
	 */
	protected boolean cycleCheckerUsingDFS(Stack<Integer[]> stack, boolean visitedNodes[]) {
		
		Integer topNodeInfo[] = stack.peek();
		for(Edge edge: edges) {
			forLoopCountInDFSCycleChecker++;
			
			boolean hasFoundAdjacentNode = false;
			int destinationIdx = -1;
			
			if(edge.getEndPoint1() == topNodeInfo[0]) {
				/*
				 * destinationIdx refers to the index of an 
				 * adjacent node of the node having index 
				 * topNodeInfo[0].
				 */
				destinationIdx = edge.getEndPoint2();
				hasFoundAdjacentNode = true;
			}
			else if(edge.getEndPoint2() == topNodeInfo[0]) {
				/*
				 * destinationIdx refers to the index of an 
				 * adjacent node of the node having index 
				 * topNodeInfo[0].
				 */
				destinationIdx = edge.getEndPoint1();
				hasFoundAdjacentNode = true;
			}
			
			if(hasFoundAdjacentNode) {
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
	 * O(|V| + |V|*|E| + 1) which is equivalent to O(|V|*|E|).
	 * 
	 * O(|V|) is the time complexity for the while-loop for the 
	 * case when the graph is bipartite.
	 * 
	 * O(|V|*|E|) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 *  O(1) is the time complexity of return true; statement 
	 *  after the while loop in the case if the graph is bipartite.
	 */
	@Override
	protected boolean bipartiteCheckerUsingBFS(Queue<Integer[]> queue,
			int[] nodesColor) {
		
		while(!queue.isEmpty()) {
			whileLoopCountInBFSBipartiteChecker++;
			
			Integer frontNodeInfo[] = queue.remove();
			
			for(Edge edge: edges) {
				innerForLoopCountInBFSBipartiteChecker++;
				
				boolean hasFoundAdjacentNode= false;
				int destinationIdx = -1;
				
				if(edge.getEndPoint1() == frontNodeInfo[0]) {
					destinationIdx = edge.getEndPoint2();
					hasFoundAdjacentNode = true;
				}
				else if(edge.getEndPoint2() == frontNodeInfo[0]) {
					destinationIdx = edge.getEndPoint1();
					hasFoundAdjacentNode = true;
				}
				
				if(hasFoundAdjacentNode) {
					hasFoundAdjacentNode = false;
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
	 * O(|V| + |V|*|E|) which is equivalent to O(|V|*|E|).
	 * 
	 * O(|V|) is the time complexity of popping the stack and 
	 * that of the recursive calls for bipartiteCheckerUsingDFS()
	 * in the case when the graph is bipartite.
	 * 
	 * O(|V|*|E|) is the time complexity of for-loop for the 
	 * case when the graph is bipartite.
	 * 
	 */
	@Override
	protected boolean bipartiteCheckerUsingDFS(Stack<Integer[]> stack,
			int[] nodesColor) {
		
		Integer topNodeInfo[] = stack.pop();
		
		for(Edge edge: edges) {
			forLoopCountInDFSBipartiteChecker++;
			
			boolean hasFoundAdjacentNode = false;
			int destinationIdx = -1;
			if(edge.getEndPoint1() == topNodeInfo[0]) {
				destinationIdx = edge.getEndPoint2();
				hasFoundAdjacentNode = true;
			}
			else if (edge.getEndPoint2() == topNodeInfo[0]) {
				destinationIdx = edge.getEndPoint1();
				hasFoundAdjacentNode = true;
			}
			
			if(hasFoundAdjacentNode) {
				/*
				 * Here we have found one adjacent node of the 
				 * node having index topNodeInfo[0].
				 * 
				 * And that adjacent node's index is 
				 * destinationIdx;
				 */
				hasFoundAdjacentNode = false;
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
	 * of O(|V| + |V|*|E|) which is equivalent to O(|V|*|E|).
	 * 
	 * O(|V|) is the time complexity of the Block 1
	 * in the case when the graph is acyclic.
	 * 
	 * The recursive calls to cycleCheckerUsingDFSForDirectedGraph()
	 * will occur at most |V| times in case when the graph is 
	 * acyclic. 
	 * 
	 * O(|V|*|E|) is the time complexity of for-loop for the 
	 * case when the graph is acyclic.
	 * 
	 */
	protected boolean cycleCheckerUsingDFSForDirectedGraph(Stack<Integer[]> stack, boolean visitedNodes[], boolean dfsVisited[]) {
		
		Integer topNodeInfo[] = stack.pop();
		
		for(Edge edge: edges) {
			forLoopCountInDFSCycleChecker++;
			if(edge.getEndPoint1() == topNodeInfo[0]) {
				/* Found the adjacent node of the node having
				 * index curNodeIdx.
				 */
				int destinationIdx = edge.getEndPoint2();
				
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
		
		for(Edge edge: edges) {
			forLoopCountInDFSTopoSort++;
			if(edge.getEndPoint1() == curNodeIdx) {
				int destinationIdx = edge.getEndPoint2();
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
	
	/* getIndegrees() has a time complexity of O(|V|+|V|*|E|+1)
	 * which is equivalent to O(|V|*|E|).
	 * 
	 * The outer for-loop runs exactly |V| times.
	 * The inner for-loop runs exactly |V|*|E| times.
	 * 
	 */
	protected int[] getIndegrees() {
		int noOfVertices = getNoOfVertices(),
				inDegrees[] = new int[noOfVertices];
		
		for(int i=0; i<noOfVertices; i++) {
			outerForLoopOfIndegrees++;
			int inDegree = 0;
			for(Edge edge: edges) {
				innerForLoopOfIndegrees++;
				if(edge.getEndPoint2() == i) {
					inDegree++;
				}
			}
			inDegrees[i] = inDegree;
		}
		log.info("Indegrees array: "+Arrays.toString(inDegrees));
		return inDegrees;
	}
	
	/* getBFSTopologicalSortResult() has a time complexity of
	 * O(|V|*|E|+|V|) which is equivalent to O(|V|*|E|).
	 * 
	 * The while loop runs exactly |V| times in case the graph 
	 * is directed.
	 * 
	 * The inner for loop runs exactly |V|*|E| times in case 
	 * the graph is directed.
	 * 
	 */
	protected void getBFSTopologicalSortResult(Queue<Integer> queue, int inDegrees[], ArrayList<Integer> bfsTopoSortResult) {
		
		/* At this point the queue already contains nodes having
		 * indegree as 0.
		 */

		while(!queue.isEmpty()) {
			whileLoopCountInBFSTopoSort++;
			int nodeIdx = queue.remove();
			
			for(Edge edge: edges) {
				innerForLoopCountInBFSTopoSort++;
				if(edge.getEndPoint1() == nodeIdx) {
					/* Here we have found the adjacent node of the 
					 * node having index nodeIdx. 
					 */
					int destinationIdx = edge.getEndPoint2();
					if(--inDegrees[destinationIdx] == 0) {
						queue.add(destinationIdx);
						bfsTopoSortResult.add(destinationIdx);
					}
				}
			}
		}
	}
	
	/* cycleCheckerUsingBFSForDirectedGraph() has a time 
	 * complexity of O(|V|+|V|*|E|+1) which is equivalent to 
	 * O(|V|*|E|);
	 * 
	* The while loop will run at most |V| times and that 
	 * in the case the graph is acyclic.
	 * 
	 * The inner for-loop will run at most |V|*|E| times in the 
	 * case the graph is acyclic. 
	 * 
	 */
	protected boolean cycleCheckerUsingBFSForDirectedGraph(Queue<Integer> queue, int inDegrees[]) {
		
		int noOfZerosInInDegrees = queue.size();
		while(!queue.isEmpty()) {
			whileLoopCountInBFSCycleChecker++;
			int nodeIdx = queue.remove();
			
			for(Edge edge: edges) {
				innerForLoopCountInBFSCycleChecker++;
				if(edge.getEndPoint1() == nodeIdx) {
					/* Here we have found the adjacent node of the 
					 * node having index nodeIdx. 
					 */
					int destinationIdx = edge.getEndPoint2();
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
		String edges[]= {"A B", "B C", "E A", "C D", "C E"};
		// {"A B", "A C", "B D", "B E", "E D"}
		/* {
		"A B", "A C", "A D", "B C", "B D", "C D", "C E", "D H",
		"E F", "E G", "F I", "G H", "G I", "H I"
		}
		*/

		GraphUsingEdgeList graph = new GraphUsingEdgeList(GraphType.DIRECTED);
		
		graph.createGraph(vertices, edges);
		log.info("Vertices: "+graph.getVertices());
		log.info("Edges: "+graph.edges);
		
//		graph.addVertex("E");
//		log.info("Vertices: "+graph.getVertices());
//		graph.addEdge(8, 7);
//		graph.addEdge("E", "B");
//		log.info("Vertices: "+graph.getVertices());
//		log.info("Edges: "+graph.edges);
		
//		ArrayList<Integer> adjacentNodes = graph.getAdjacentNodes(0);
//		log.info("Adjacent Nodes: "+adjacentNodes);
		
//		graph.removeVertex(0);
//		log.info("Vertices: "+graph.getVertices());
//		log.info("Edges: "+graph.edges);
//		log.info(graph.getVertexIndex("C"));
//		
//		boolean edgeRemoved = graph.removeEdge(3, 1);
//		log.info(edgeRemoved);
//		log.info("Vertices: "+graph.getVertices());
//		log.info("Edges: "+graph.edges);
		
//		ArrayList<Integer> bfsResult = graph.performBFS(0);
//		log.info("BFS result: "+bfsResult);
		
//		ArrayList<Integer> dfsResult = graph.performDFS(0);
//		log.info("DFS result: "+dfsResult);
		
		log.info("Cycle check using BFS says, Cycle present: "+graph.cycleCheckUsingBFS());
//		log.info("Cycle check using DFS says, Cycle present: "+graph.cycleCheckUsingDFS());
	
//		log.info("Bipartite check using BFS says: "+graph.bipartiteCheckUsingBFS());
//		log.info("Bipartite check using DFS says: "+graph.bipartiteCheckUsingDFS());
	
//		ArrayList<Integer> dfsTopoSortResult = graph.performDFSTopologicalSorting();
//		log.info("DFS topo sort result: "+dfsTopoSortResult);
		
//		ArrayList<Integer> bfsTopoSortResult = graph.performBFSTopologicalSorting();
//		log.info("BFS topo sort result: "+bfsTopoSortResult);
	
	}
	
}
