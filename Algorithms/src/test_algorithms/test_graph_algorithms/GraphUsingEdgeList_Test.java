package test_algorithms.test_graph_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import algorithms.graph_algorithms.Edge;
import algorithms.graph_algorithms.GraphType;
import algorithms.graph_algorithms.GraphUsingEdgeList;
import algorithms.graph_algorithms.Vertex;
import algorithms.graph_algorithms.graph_exceptions.VerticesAndEdgesGroupsLengthMismatchException;

public class GraphUsingEdgeList_Test {
	
	static List<GraphUsingEdgeList> graphs;
	
	static final Logger log = LogManager.getLogger(GraphUsingEdgeList_Test.class.getName());
	
	public static List<GraphUsingEdgeList> initializeGraphUsingEdgeList(String verticesGroups[][], 
			String edgesGroups[][], GraphType gts[], boolean ...hasWeights) {

		final int noOfTestCases = verticesGroups.length;
		
		graphs = new ArrayList<GraphUsingEdgeList>();
		
		int vgsLen = verticesGroups.length, egsLen = edgesGroups.length;
		if(vgsLen != egsLen) {
			throw new VerticesAndEdgesGroupsLengthMismatchException(vgsLen, egsLen);
		}
		
		if(vgsLen != noOfTestCases) {
			Assert.fail("Length of verticesGroups: "+vgsLen+" doesn't match the noOfTestCases: "+noOfTestCases);;
		}
		
		if(hasWeights.length == 0) {
			hasWeights = new boolean[noOfTestCases];
		}
		
		for(int i=0; i<noOfTestCases; i++) {
			graphs.add(new GraphUsingEdgeList(gts[i], hasWeights[i]));
			graphs.get(i).createGraph(verticesGroups[i], edgesGroups[i]);
		}
		
		return graphs;
	}
	
	@Test
	public void testAddEdgeUsingEdgeObject() {
		
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{
					"A", "B", "C", "D", "E", "F", "G", "H"
				},
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G", "H"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{
					"B A", "C A", "D A", "C B", "B D", "C D", "C F", "F E",
					"E G", "F G", "F H", "G H", "H E"
				},
				{
					"A C", "C D", "D B", "B A", "E G", "F G", "F E"
				},
				{
					"A C", "A D", "B A", "D B", "D C", "C B", "F C", "E G", "G H", "H F", "F E", "F G"
				}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.DIRECTED, GraphType.DIRECTED};
		initializeGraphUsingEdgeList(verticesGroups, edgesGroups, gts);
		
		int inputEdgeGroups[][][] = {
				{{3, 5}, {1, 4}},
				{{1, 2}},
				{{1, 6}}
		};
		
		int count = 0;
		log.info("Inside testAddEdgeUsingEdgeObject()");
		for(GraphUsingEdgeList graph: graphs) {
			Edge edges[] = getEdgeArray(inputEdgeGroups[count]);
			log.info("For Graph "+(count+1)+":");
			for(Edge edge: edges) {
				
				log.info("Edge to be added: "+edge);
				// Add an edge to the graph
				graph.addEdge(edge);
				
				graph.printGraph();
				
				// Check if that edge exist in the graph
				Assert.assertEquals(true, graph.checkIfEdgeExistInGraph(edge.getEndPoint1(), edge.getEndPoint2()));
			}
			count++;
			log.info("");
		}
		
	}
	
	@Test
	public void testAddEdgeUsingVerticesName() {
	
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{
					"A", "B", "C", "D", "E", "F", "G", "H"
				},
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G", "H"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{
					"B A", "C A", "D A", "C B", "B D", "C D", "C F", "F E",
					"E G", "F G", "F H", "G H", "H E"
				},
				{
					"A C", "C D", "D B", "B A", "E G", "F G", "F E"
				},
				{
					"A C", "A D", "B A", "D B", "D C", "C B", "F C", "E G", "G H", "H F", "F E", "F G"
				}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.DIRECTED, GraphType.DIRECTED};
		initializeGraphUsingEdgeList(verticesGroups, edgesGroups, gts);
		
		String inputEdgeGroups[][] = {
				{"E A", "B E"},
				{"B C"},
				{"B G"}
		};
		int count = 0;
		log.info("Inside testAddEdgeUsingVerticesName()");
		for(GraphUsingEdgeList graph: graphs) {
			String edges[] = inputEdgeGroups[count];
			log.info("For Graph "+(count+1)+":");
			
			for(String edge: edges) {
				String endPoints[] = edge.split(" ");
				log.info("Edge to be added: ["+edge+"]");
				graph.addEdge(endPoints[0], endPoints[1]);
				graph.printGraph();
				
				// Here we check if the edge is present in the graph.
				int idx1 = graph.getVertexIndex(endPoints[0]), idx2 = graph.getVertexIndex(endPoints[1]);
//				log.info(edge);
				if(!graph.checkIfEdgeExistInGraph(idx1, idx2)) {
					Assert.fail("The following vertex: (\""+edge+"\") doesn't exist in the graph.");
				}
			}
			count++;
			log.info("");
		}
		
	}
	
	@Test
	public void testAddEdgesUsingListOfEdgeObjects() {
		
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{
					"A", "B", "C", "D", "E", "F", "G", "H"
				},
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G", "H"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{
					"B A", "C A", "D A", "C B", "B D", "C D", "C F", "F E",
					"E G", "F G", "F H", "G H", "H E"
				},
				{
					"A C", "C D", "D B", "B A", "E G", "F G", "F E"
				},
				{
					"A C", "A D", "B A", "D B", "D C", "C B", "F C", "E G", "G H", "H F", "F E", "F G"
				}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.DIRECTED, GraphType.DIRECTED};
		initializeGraphUsingEdgeList(verticesGroups, edgesGroups, gts);
				
		int inputEdgeGroups[][][] = {
				{{3, 5}, {1, 4}},
				{{1, 2}},
				{{1, 6}}
		};
		log.info("Inside testAddEdgesUsingListOfEdgeObjects()");

		int count=0;
		for(GraphUsingEdgeList graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			
			int edges[][] = inputEdgeGroups[count];
			
			List<Edge> edgeList = getEdgeList(edges);
			
			log.info("Edge list to be added: "+edgeList);
			
			graph.addEdges(edgeList);
			
			graph.printGraph();
			
			for(int edge[]: edges) {
				Assert.assertEquals(true, graph.checkIfEdgeExistInGraph(edge[0], edge[1]));
			}
			count++;
			log.info("");
		}
		
	}
	
	public Edge[] getEdgeArray(int edges[][]) {
		Edge edgeObjects[] = new Edge[edges.length];
		
		int edgeCount = 0;
		for(int edge[]: edges) {
			edgeObjects[edgeCount] = new Edge(edge[0], edge[1]);
			edgeCount++;
		}
		
		return edgeObjects;
	}
	
	public List<Edge> getEdgeList(int edges[][]) {
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		for(int edge[]: edges) {
			edgeList.add(new Edge(edge[0], edge[1]));
		}
		
		return edgeList;
	}
}

//990