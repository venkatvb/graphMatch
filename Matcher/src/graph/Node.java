package graph;

import java.util.ArrayList;
import java.util.Collections;

import core.Hasher;

public class Node implements Hashable {
	public static final Double DEFAULT_DISTANCE = 0.0;
	public int nodeId;
	public String nodeValue;
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Node(int id) {
		this.nodeId = id;
		this.nodeValue = "";
	}
	
	public Node(Integer nodeId, String nodeValue) {
		this.nodeId = nodeId;
		this.nodeValue = nodeValue;
	}
	
	void setNodeId(int id) {
		this.nodeId = id;
	}
	
	void setNodeValue(String value) {
		this.nodeValue = value;
	}
	
	public String getNodeValue() {
		return this.nodeValue;
	}
	
	public Integer getNodeId() {
		return this.nodeId;
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}
	
	boolean addEdge(Integer destinationId, Double distance) {
		// returns false if the added edge is a duplicate else returns true
		// TODO adding each edge costs linear. Optimize it.
		for(Edge edge : edges ) {
			if(edge.getDestinationNodeId() == destinationId) {
				// this destination is already added.
				return false;
			}
		}
		edges.add(new Edge(this.nodeId, destinationId, distance));
		return true;
	}
	
	boolean addEdge (Integer destinationId) {
		// Adding the edge with the default distance.
		return addEdge (destinationId, DEFAULT_DISTANCE);
	}
	
	public String getHash() {
		return getHashX() + getHashY();
	}
	
	public String getHashX() {
		return Hasher.rollingHash(this.getNodeValue()).toString();
	}
	
	public String getHashY() {
		String reversedNodeValue = new StringBuilder(this.getNodeValue()).reverse().toString();
		return Hasher.rollingHash(reversedNodeValue).toString();
	}
	
	public String getEdgeHash() {
		String input = "";
		Collections.sort(edges);
		for(Edge edge : edges) {
			input += edge.getDistance().toString();
		}
		Integer length = new Integer(edges.size());
		input += length.toString();
		return Hasher.rollingHash(input).toString();
	}
}	
