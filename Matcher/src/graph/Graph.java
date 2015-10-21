package graph;

import java.io.IOException;
import java.util.ArrayList;

public class Graph {
	
	ArrayList<Node> graph = new ArrayList<Node>();
	
	boolean isNodePresent (int nodeId) {
		// Returns true if the node with the given id is present in the graph
		// else return false.
		for ( Node node : graph ) {
			if ( node.getNodeId() == nodeId ) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Node> getNodeList() {
		return this.graph;
	}
	
	public boolean addNode(int nodeId) {
		// Adding the node value as empty string.
		return addNode(nodeId, "");
	}
	
	public boolean addNode(int nodeId, String nodeValue) { 
		// Returns false if the node with the given nodeId is already present.
		// else adds the node to the given graph then creates.
		if( isNodePresent(nodeId) ) {
			// the node to be added is already present.
			return false;
		}
		graph.add(new Node(nodeId, nodeValue));
		return true;
	}
	
	Node getNode(Integer nodeId) throws NodeNotFoundException, IOException {
		// throws nodeNotFound exception if the node with the given nodeId is not present
		for(Node node : graph ) {
			if(node.getNodeId() == nodeId) {
				return node;
			}
		}
		throw new NodeNotFoundException("Node with id " + nodeId.toString() + "is not present");
	}
	
	public boolean addEdge(int nodeIdA, int nodeIdB, double distance) throws NodeNotFoundException, IOException {
		// returns false if atleast one of the node id is not present
		// else adds the undirected edge from both
		// nodeA -> nodeB with distance
		// nodeB -> nodeA with distance
		if( isNodePresent(nodeIdA) == false || isNodePresent(nodeIdB) == false ) {
			// atleast one of the node is not present.
			return false;
		}
		Node source = getNode(nodeIdA);
		Node destination = getNode(nodeIdB);
		source.addEdge(nodeIdB, distance);
		destination.addEdge(nodeIdA, distance);
		return true;
	}
	
	public String toString() {
		String result = "";
		for( Node node : graph ) { 
			result += node.getNodeId().toString() 
					+ " -> "
					+ node.getNodeValue().toString()
					+ "\n";
			for(int i=0; i<node.edges.size(); i++ ) {
				result += node.edges.get(i).getDestinationNodeId().toString() + ", ";
			}
			result += node.edges.get(node.edges.size()-1).getDestinationNodeId().toString();
			result += "\n";
		}
		return result;
	}
}
