package test_algorithms.test_graph_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algorithms.graph_algorithms.GraphType;
import algorithms.graph_algorithms.GraphUsingAdjacencyMatrix;
import algorithms.graph_algorithms.GraphUsingEdgeList;
import algorithms.graph_algorithms.Vertex;
import algorithms.graph_algorithms.graph_exceptions.VerticesAndEdgesGroupsLengthMismatchException;

public class GraphUsingAdjacencyMatrix_Test {
	
	static List<GraphUsingAdjacencyMatrix> graphs;
	
	public static List<GraphUsingAdjacencyMatrix> initializeGraphUsingAdjacencyMatrix(
			String verticesGroups[][], String edgesGroups[][], GraphType gts[], boolean ...hasWeights ) {
		
		final int noOfTestCases = verticesGroups.length;
		
		int sizeOfAdjacencyMatrices[] = new int[noOfTestCases];
		Arrays.fill(sizeOfAdjacencyMatrices, 10);
		
		graphs = new ArrayList<GraphUsingAdjacencyMatrix>();
		
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
			graphs.add(new GraphUsingAdjacencyMatrix(sizeOfAdjacencyMatrices[i], gts[i], hasWeights[i]));
			graphs.get(i).createGraph(verticesGroups[i], edgesGroups[i]);
		}
		
		return graphs;
		
	}
}
//730