package algorithms.graph_algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Vertex {
	private String name;
	
	public Vertex(String vertexName) {
		name = vertexName;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}

class Edge {
	private int endPoint1, endPoint2;
	
	public Edge(int ep1, int ep2) {
		endPoint1 = ep1;
		endPoint2 = ep2;
	}
	
	public int getEndpoint1() {
		return endPoint1;
	}
	
	public int getEndpoint2() {
		return endPoint2;
	}

	public String toString() {
		return "{"+endPoint1+" -- "+endPoint2+"}";
	}
	
}

public class Graph {
	
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	private int noOfVertices;
	
	public Graph() {
		// Create a list of vertices.
		vertices =  new ArrayList<Vertex>();
		edges = new LinkedList<Edge>();
	}
	
	private void addVertex(Vertex vertex) {
		if(!checkIfVertexExistInGraph(vertex.getName())) {
			if(!vertex.getName().matches("[a-zA-Z][0-9a-zA-Z]*"))
				throw new IllegalArgumentException("Invalid vertex name: "+vertex.getName()+", vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
			vertices.add(vertex);
			// Update the noOfVertices in this graph.
			noOfVertices++;
		}
		else {
			throw new IllegalArgumentException("Duplicate vertex with name "+vertex.getName()+" provided."); 
		}
	}
	
	private void addVertex(String vertexName) {
		Vertex vertex = new Vertex(vertexName);
		addVertex(vertex);
	}
	
	private void addVertices(List<Vertex> verticesList) {
		for(Vertex vertex: verticesList)
			addVertex(vertex);
	}
	
	private void addEdge(Edge connection) {
		edges.add(connection);
	}
	
	private void addEdge(String vertexName1, String vertexName2) {
		if(!checkIfVertexExistInGraph(vertexName1))
			throw new IllegalArgumentException("vertexName1: \""+vertexName1+"\" does not exist in the graph.");
		if(!checkIfVertexExistInGraph(vertexName2))
			throw new IllegalArgumentException("vertexName2: \""+vertexName2+"\" does not exist in the graph.");
		
		Edge edge = new Edge(getVertexIndex(vertexName1), getVertexIndex(vertexName2));
		addEdge(edge);
	}
	
	private void addEdges(List<Edge> connections) {
		edges.addAll(connections);
	}
	
	private int getVertexIndex(Vertex vertex) {
		return vertices.indexOf(vertex);
	}
	
	private int getVertexIndex(String vertexName) {
		Vertex vertex = null;
		boolean vertexFound = false;
		for(int i=0; i<noOfVertices; i++) {
			vertex = vertices.get(i);
			if(vertex.getName().equals(vertexName)) {
				vertexFound = true;
				break;
			}
		}
		if(!vertexFound) return -1;
		else return getVertexIndex(vertex);
	}
	
	private boolean checkIfVertexExistInGraph(String vertexName) {
		if(getVertexIndex(vertexName) != -1) return true;
		else return false;
	}
	
	/* createGraph(String verticesNames[], String  connections[]) will
	 * create an undirected graph.
	 * 
	 * Note: Here  we use a ArrayList to store the vertices in our 
	 * graph and a LinkedList to store the connection b/w vertices.
	 */
	public void createGraph(String verticesNames[], String  connections[]) {
	
		/* Create vertex for each vertexName in verticesNames[] 
		 * and add that to the vertices list.
		 */
		
		int count = 0;
		for(String name: verticesNames) {
			if(!checkIfVertexExistInGraph(name)) {
				vertices.add(new Vertex(name));
				// Update the noOfVertices in this graph.
				noOfVertices++;
			}
			else {
				throw new IllegalArgumentException("Found duplicate vertex \""+name+"\" in verticesNames["+count+"]."); 
			}
			count++;
		}
		
		/* Create edge for each connection in connections[] 
		 * and add that to the edges list.
		 */
		for(int i=0; i<connections.length; i++) {
			String endPoints[] = connections[i].split(" ");
			
			if(endPoints.length == 2) {
				for(String endpoint: endPoints) {
					// Here we check if vertex name is valid.
					if(!endpoint.matches("[a-zA-Z][0-9a-zA-Z]*")) {
						throw new IllegalArgumentException("Invalid vertex name: \""+endpoint+"\" at connections["+i+"], vertex name can only contain alphanumeric characters and first letter of it should be an alphabet.");
					}
					else {
						if(!checkIfVertexExistInGraph(endpoint)) {
							throw new IllegalArgumentException("Vertex with name: \""+endpoint+"\" in the connection \""+connections[i]+"\" at connections["+i+"] does not exist in the graph.");
						}
					}
				}
				edges.add(new Edge(getVertexIndex(endPoints[0]), getVertexIndex(endPoints[1])) );
			}
			else {
				throw new IllegalArgumentException("Invalid connection: \""+connections[i]+"\" at connections["+i+"].");
			}
			
			
		}
	}
	
	

	public static void main(String[] args) {
		// Create a list of Vertices.
		String vertices[] = {"A", "B", "C", "D"};
		String edges[]= {"A $ B", "A C", "B D"};
		
		
		
		Graph graph = new Graph();
		
		graph.createGraph(vertices, edges);
		System.out.println("Edges: "+graph.edges);
		
		graph.addVertex("E");
		graph.addEdge("C", "E");
		System.out.println("Edges: "+graph.edges);
		
		
	}

}
