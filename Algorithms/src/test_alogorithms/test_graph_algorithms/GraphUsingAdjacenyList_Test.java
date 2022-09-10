package test_alogorithms.test_graph_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algorithms.graph_algorithms.GraphType;
import algorithms.graph_algorithms.GraphUsingAdjacencyList;
import algorithms.graph_algorithms.GraphUsingAdjacencyMatrix;
import algorithms.graph_algorithms.GraphUsingEdgeList;
import algorithms.graph_algorithms.Vertex;
import algorithms.graph_algorithms.graph_exceptions.VerticesAndEdgesGroupsLengthMismatchException;

public class GraphUsingAdjacenyList_Test {
	
	static List<GraphUsingAdjacencyList> graphs;
	
	public static List<GraphUsingAdjacencyList> initializeGraphUsingAdjacencyList(String verticesGroups[][], String edgesGroups[][], GraphType gts[] ) {

		final int noOfTestCases = verticesGroups.length;
		
		int sizeOfAdjacencyList[] = {10, 10, 10};
		graphs = new ArrayList<GraphUsingAdjacencyList>();
		
		int vgsLen = verticesGroups.length, egsLen = edgesGroups.length;
		if(vgsLen != egsLen) {
			throw new VerticesAndEdgesGroupsLengthMismatchException(vgsLen, egsLen);
		}
		
		if(vgsLen != noOfTestCases) {
			Assert.fail("Length of verticesGroups: "+vgsLen+" doesn't match the noOfTestCases: "+noOfTestCases);;
		}
		
		for(int i=0; i<noOfTestCases; i++) {
			graphs.add(new GraphUsingAdjacencyList(sizeOfAdjacencyList[i], gts[i]));
			graphs.get(i).createGraph(verticesGroups[i], edgesGroups[i]);
		}
		
		return graphs;
		
	}
}

//740