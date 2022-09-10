package test_alogorithms.test_graph_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import algorithms.graph_algorithms.Graph;
import algorithms.graph_algorithms.GraphType;
import algorithms.graph_algorithms.GraphUsingAdjacencyList;
import algorithms.graph_algorithms.GraphUsingAdjacencyMatrix;
import algorithms.graph_algorithms.GraphUsingEdgeList;
import algorithms.graph_algorithms.Vertex;

import org.apache.logging.log4j.*;

public class CommonGraphMethods_Test {
	
	static final Logger log = LogManager.getLogger(CommonGraphMethods_Test.class.getName());
	
	List<Graph> graphs;
	
	public void initializeGraphs(String verticesGroups[][], String edgesGroups[][], GraphType gts[] ) {
		
		graphs = new ArrayList<>();
		
		graphs.addAll(GraphUsingEdgeList_Test.initializeGraphUsingEdgeList(verticesGroups, edgesGroups, gts));
		graphs.addAll(GraphUsingAdjacencyMatrix_Test.initializeGraphUsingAdjacencyMatrix(verticesGroups, edgesGroups, gts));
		graphs.addAll(GraphUsingAdjacenyList_Test.initializeGraphUsingAdjacencyList(verticesGroups, edgesGroups, gts));
	}
	
	public static void printGraphImplemetationType(Graph graph) {
		if(graph instanceof GraphUsingEdgeList)
			log.info("Graph Implementation Type: GraphUsingEdgeList");
		if(graph instanceof GraphUsingAdjacencyMatrix)
			log.info("Graph Implementation Type: GraphUsingAdjacencyMatrix");
		if(graph instanceof GraphUsingAdjacencyList)
			log.info("Graph Implementation Type: GraphUsingAdjacencyList");
	}
	
	@Test
	public void testAddEdgeUsingIndices() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		int inputEdgeGroups[][][] = {
				{{3, 5}, {1, 4}},
				{{1, 2}},
				{{3, 4}}
		};
		
		int count = 0;
		log.info("Inside testAddEdgeUsingIndices()");
		for(Graph graph: graphs) {
			int edges[][] = inputEdgeGroups[count];
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			for(int edge[]: edges) {
				
				log.info("Edge to be added: ("+edge[0]+","+edge[1]+")");
				// Add an edge to the graph
				graph.addEdge(edge[0], edge[1]);
				
				graph.printGraph();
				
				// Check if that edge exist in the graph
				Assert.assertEquals(true, graph.checkIfEdgeExistInGraph(edge[0], edge[1]));
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testAddEdgesUsingStringArrayOfEdges() {
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputEdgeGroups[][] = {
				{"E A", "B E"},
				{"B C"},
				{"B G"}
		};
		
		int count = 0;
		log.info("Inside testAddEdgesUsingStringArrayOfEdges()");
		for(Graph graph: graphs) {
			String edges[] = inputEdgeGroups[count];
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			log.info("Edges to be added: "+Arrays.toString(edges));
			
			graph.addEdges(edges);
			
			graph.printGraph();
			
			for(String edge: edges) {
				String endPoints[] = edge.split(" ");
				// Here we check if the edge is present in the graph.
				int idx1 = graph.getVertexIndex(endPoints[0]), idx2 = graph.getVertexIndex(endPoints[1]);
//				log.info(edge);
				if(!graph.checkIfEdgeExistInGraph(idx1, idx2)) {
					Assert.fail("The following edge: (\""+edge+"\") doesn't exist in the graph.");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testRemoveEdgeUsingIndices() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		int inputEdgeGroups[][][] = {
				{{4, 6}, {1, 0}},
				{{1, 0}},
				{{3, 1}, {5, 4}}
		};
		log.info("Inside testRemoveEdgeUsingIndices()");

		int count=0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			int edges[][] = inputEdgeGroups[count];
			for(int edge[]: edges) {
				log.info("Edge to be removed: ("+edge[0]+","+edge[1]+")");
				
				graph.removeEdge(edge[0], edge[1]);
				graph.printGraph();
				
				// Here we check if the edge is present in the graph.
				if(graph.checkIfEdgeExistInGraph(edge[0], edge[1])) {
					Assert.fail("The following edge: ("+edge[0]+" ,"+edge[1]+") doesn't exist in the graph.");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testAddVertexUsingVertexObject() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputVertexGroups[][] = {
				{"P", "Q"},
				{"K"},
				{"L"}
		};
		log.info("Inside testAddVertexUsingVertexObject()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String verticesNames[] = inputVertexGroups[count];
			for(String vertexName: verticesNames) {
				log.info("Vertex to be added: "+vertexName);
				graph.addVertex(new Vertex(vertexName));
				
				log.info(graph.getVertices());
				Assert.assertEquals(true, graph.checkIfVertexExistInGraph(vertexName));
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testAddVertexUsingName() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
	
		String inputVertexGroups[][] = {
				{"P", "Q"},
				{"K"},
				{"L"}
		};
		log.info("Inside testAddVertexUsingName()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String verticesNames[] = inputVertexGroups[count];
			for(String vertexName: verticesNames) {
				log.info("Vertex to be added: "+vertexName);
				graph.addVertex(vertexName);
				
				log.info(graph.getVertices());
				Assert.assertEquals(true, graph.checkIfVertexExistInGraph(vertexName));
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testAddStringArrayOfVertices() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputVertexGroups[][] = {
				{"P", "Q"},
				{"K"},
				{"M", "N"}
		};
		log.info("Inside testAddStringArrayOfVertices()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String verticesNames[] = inputVertexGroups[count];
			log.info("Vertices array to be added: "+Arrays.toString(verticesNames));
			graph.addVertices(verticesNames);
			
			log.info(graph.getVertices());
			
			for(String vertexName: verticesNames) {
				Assert.assertEquals(true, graph.checkIfVertexExistInGraph(vertexName));
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testAddVerticesUsingListOfVertexObjects() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputVertexGroups[][] = {
				{"P", "Q"},
				{"K"},
				{"M", "N"}
		};
		log.info("Inside testAddVerticesUsingListOfVertexObjects()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String verticesNames[] = inputVertexGroups[count];
			List<Vertex> vertexList = graph.getListOfVertexObjects(verticesNames);
			
			log.info("Vertex list to be added: "+vertexList);
			graph.addVertices(vertexList);
			
			log.info(graph.getVertices());
			
			for(String vertexName: verticesNames) {
				Assert.assertEquals(true, graph.checkIfVertexExistInGraph(vertexName));
			}
			
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testRemoveVertexUsingIndex() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputVertexGroups[][] = {
				{"C", "A", "D"},
				{"A"},
				{"D", "A"}
		};
		log.info("Inside testRemoveVertexUsingIndex()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String verticesNames[] = inputVertexGroups[count];
			
			for(String vertexName: verticesNames) {
				int idx = graph.getVertexIndex(vertexName);
				
				log.info("Vertex to be removed: \""+vertexName+"\"");
				graph.removeVertex(idx);
				
				log.info(graph.getVertices());
				
				Assert.assertNotEquals(true, graph.checkIfVertexExistInGraph(vertexName));
			}
			
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testGetAdjacentNodes() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputSetsOfVertices[][] = {
				{"C", "A", "E"},
				{"A","D"},
				{"F", "G"}
		};
		
		/* This arrayOfSetsOfAdjacentNodes[][][] contains the 
		 * expected adjacent nodes for each of the vertex in the 
		 * inputSetsOfVertices[][].
		 * 
		 */
		String arrayOfSetsOfAdjacentNodes[][][] = {
				{{"A", "B", "D", "F"}, {"B", "C", "D"}, {"H", "F", "G"}},
				{{"C"}, {"B"}},
				{{"C", "E", "G"}, {"H"}}
		};
		
		log.info("Inside testGetAdjacentNodes()");
		
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			/* A row from the inputSetsOfVertices[][] represents the 
			 * inputs for the graph specified by graph.
			 */
			String set[] = inputSetsOfVertices[count];
			int vertexCount = 0;
			for(String vertexName: set) {
				int idx = graph.getVertexIndex(vertexName);
				List<Integer> adjacentNodes = graph.getAdjacentNodes(idx);
				
				String expectedAdjacentNodesNames[] = arrayOfSetsOfAdjacentNodes[count][vertexCount];

				if(expectedAdjacentNodesNames.length != adjacentNodes.size()) {
					Assert.fail("The length of expected adjacent nodes and the actual adjacent nodes for the vertex: \""+vertexName+"\" in graph: "+(count+1)+" do not match.");
				}
				
				log.info("For vertex: \""+vertexName+"\"");
				log.info("expectedAdjacentNodesNames: "+ Arrays.toString(expectedAdjacentNodesNames));
				log.info("adjacentNodes: "+adjacentNodes);
				
				for(String adjacentNodeName: expectedAdjacentNodesNames) {
					int adjacentNodeIdx = graph.getVertexIndex(adjacentNodeName);
					if(!adjacentNodes.contains(adjacentNodeIdx)) {
						Assert.fail("For vertex: \""+vertexName+"\", in graph: "+(count+1)+" the adjacent node \""+adjacentNodeName+"\" is missing in the actual adjacent nodes list.");
					}
				}
				vertexCount++;
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testGetBFSResult() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputBFSTraversalResults[][] = {
				{"A", "B", "C", "D", "F", "E", "G", "H"},
				{"A", "C", "D", "B", "E", "G", "F"},
				{"A", "C", "D", "B", "E", "G", "H", "F"}
		};
		
		log.info("Inside testGetBFSResult()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String inputBFSTraversalResult[] = inputBFSTraversalResults[count];
			List<Integer> actualBFSResult = graph.performBFS();
			List<Integer> expectedBFSResult = graph.getListOfVertexIndices(inputBFSTraversalResult);
			
			log.info("actualBFSResult: "+actualBFSResult);
			log.info("expectedBFSResult: "+expectedBFSResult);
			Assert.assertEquals(true, actualBFSResult.equals(expectedBFSResult));
			
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testGetDFSResult() {
		
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
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		String inputDFSTraversalResults[][] = {
				{"A", "B", "C", "D", "F", "E", "G", "H"},
				{"A", "C", "D", "B", "E", "G", "F"},
				{"A", "C", "B", "D", "E", "G", "H", "F"}
		};
		
		log.info("Inside testGetDFSResult()");
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			String inputDFSTraversalResult[] = inputDFSTraversalResults[count];
			List<Integer> actualDFSResult = graph.performDFS();
			List<Integer> expectedDFSResult = graph.getListOfVertexIndices(inputDFSTraversalResult);
			
			log.info("actualDFSResult: "+actualDFSResult);
			log.info("expectedDFSResult: "+expectedDFSResult);
			Assert.assertEquals(true, actualDFSResult.equals(expectedDFSResult));
			
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testCycleCheckUsingBFS() {
		
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{
					"A", "B", "C", "D", "E"
				},
				{"A", "B", "C", "D", "E", "F"},
				{"A", "B", "C", "D", "E", "F", "G"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{"A B", "D A", "E B", "C D", "D E"},
				{"A B", "D A", "C B", "E D", "E F"},
				{"A B", "C B", "C D", "D E", "F E", "C G", "C E"}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.UNDIRECTED, GraphType.UNDIRECTED};
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		boolean inputBFSCycleCheckResults[] = {true, false, true};
		
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			if(!graph.isDirected()) {
				boolean hasCycle = inputBFSCycleCheckResults[count],
						hasCycleAfterDetection = graph.cycleCheckUsingBFS();
				log.info("hasCycle: "+hasCycle+", hasCycleAfterDetection: "+hasCycleAfterDetection);
				if(hasCycleAfterDetection != hasCycle) {
					Assert.fail("Cycle is"+(hasCycle?" ":" not ")+"present but detected as cycle is"+(hasCycleAfterDetection?" ":" not ")+"present in graph "+(count+1)+".");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testCyclyeCheckUsingDFS() {
		
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{
					"A", "B", "C", "D", "E"
				},
				{"A", "B", "C", "D", "E", "F"},
				{"A", "B", "C", "D", "E", "F", "G"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{"A B", "D A", "E B", "C D", "D E"},
				{"A B", "D A", "C B", "E D", "E F"},
				{"A B", "C B", "C D", "D E", "F E", "C G"}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.UNDIRECTED, GraphType.UNDIRECTED};
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		boolean inputDFSCycleCheckResults[] = {true, false, false};
		
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			if(!graph.isDirected()) {
				boolean hasCycle = inputDFSCycleCheckResults[count],
						hasCycleAfterDetection = graph.cycleCheckUsingDFS();
				log.info("hasCycle: "+hasCycle+", hasCycleAfterDetection: "+hasCycleAfterDetection);
				if(hasCycleAfterDetection != hasCycle) {
					Assert.fail("Cycle is"+(hasCycle?" ":" not ")+"present but detected as cycle is"+(hasCycleAfterDetection?" ":" not ")+"present in graph "+(count+1)+".");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testBipartiteCheckUsingBFS() {
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{"A B", "C B", "C D", "D E", "F E", "C G"},
				{"A B", "C B", "C D", "D E", "F E", "C G", "E A"},
				{"A B", "C B", "C D", "D E", "F E", "C G", "A F"}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.UNDIRECTED, GraphType.UNDIRECTED};
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		boolean inputBFSBipartiteCheckResults[] = {true, false, true};
		
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			if(!graph.isDirected()) {
				boolean isBipartite = inputBFSBipartiteCheckResults[count],
						isBipartiteAfterDetection = graph.bipartiteCheckUsingBFS();
				log.info("isBipartite: "+isBipartite+", isBipartiteAfterDetection: "+isBipartiteAfterDetection);
				if(isBipartiteAfterDetection != isBipartite) {
					Assert.fail("Graph is"+(isBipartite?" ":" not")+" bipartite but detected as"+(isBipartiteAfterDetection?" ":" not")+"bipartite in graph "+(count+1)+".");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
	
	@Test
	public void testBipartiteCheckUsingDFS() {
		// Create an array of Vertices.
		String verticesGroups[][] = {
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G"},
				{"A", "B", "C", "D", "E", "F", "G"}
			};
		// Create an array of Edges.
		String edgesGroups[][]= {
				{"A B", "C B", "C D", "D E", "F E", "C G"},
				{"A B", "C B", "C D", "D E", "F E", "C G", "E A"},
				{"A B", "C B", "C D", "D E", "F E", "C G", "A F"}
			};
		GraphType gts[] = {GraphType.UNDIRECTED, GraphType.UNDIRECTED, GraphType.UNDIRECTED};
		initializeGraphs(verticesGroups, edgesGroups, gts);
		
		boolean inputDFSBipartiteCheckResults[] = {true, false, true};
		
		int count = 0;
		for(Graph graph: graphs) {
			log.info("For Graph "+(count+1)+":");
			printGraphImplemetationType(graph);
			
			if(!graph.isDirected()) {
				boolean isBipartite = inputDFSBipartiteCheckResults[count],
						isBipartiteAfterDetection = graph.bipartiteCheckUsingDFS();
				log.info("isBipartite: "+isBipartite+", isBipartiteAfterDetection: "+isBipartiteAfterDetection);
				if(isBipartiteAfterDetection != isBipartite) {
					Assert.fail("Graph is"+(isBipartite?" ":" not")+" bipartite but detected as"+(isBipartiteAfterDetection?" ":" not")+"bipartite in graph "+(count+1)+".");
				}
			}
			count = (count+1)%3;
			log.info("\n");
		}
	}
}
