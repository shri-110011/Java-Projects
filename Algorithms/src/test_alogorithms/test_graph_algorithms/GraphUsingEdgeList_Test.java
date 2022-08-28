package test_alogorithms.test_graph_algorithms;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.graph_algorithms.GraphUsingEdgeList;

public class GraphUsingEdgeList_Test {
	
	static List<GraphUsingEdgeList> graphs;
	
	@BeforeClass
	public static void initializeGraphUsingEdgeList() {
		// Create an array of Vertices.
		String vertices[][] = {
				{
					"A", "B", "C", "D", "E", "F", "G", "H", "I"
				},
				{"A", "B", "C"}
			};
		// Create an array of Edges.
		String edges[][]= {
				{
					"B A", "A C", "D A", "C B", "B D", "C D", "C E", "D H",
						"E F", "E G", "F I", "G H", "G I", "H I"
				},
				{"A B", "A C"}
			};
		graphs = new ArrayList<GraphUsingEdgeList>();
		
		for(int i=0; i<vertices.length; i++) {
			graphs.add(new GraphUsingEdgeList());
			graphs.get(i).createGraph(vertices[i], edges[i]);
		}
		
	}
	
	
	@Test
	public void testAddEdgeUsingIndices() {
		int inputEdges[][][] = {
				{{3, 5}, {1, 4}},
				{{1, 2}}
		};
		
		for(GraphUsingEdgeList graph: graphs) {
			for(int edges[][]: inputEdges) {
				for(int edge[]: edges) {
					
					System.out.println(edge[0]+" "+edge[1]);
					// Add an edge to the graph
					graph.addEdge(edge[0], edge[1]);
					
					// Check if that edge exist in the graph
					Assert.assertEquals(true, graph.checkIfEdgeExistInGraph(edge[0], edge[1]));
				}
			}
		}
		
	}

}
