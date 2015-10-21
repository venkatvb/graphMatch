package core;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.Collections;

public class Comparator {
	public boolean compareGraphs(Graph graph1, Graph graph2) {
		ArrayList<NodeHash> nodeHashes1 = new ArrayList<NodeHash>();
		ArrayList<NodeHash> nodeHashes2 = new ArrayList<NodeHash>();
		ArrayList<Node> nodes1 = graph1.getNodeList();
		ArrayList<Node> nodes2 = graph2.getNodeList();
		for(Node node : nodes1) {
			NodeHash temp = new NodeHash();
			temp.setNodeHash(node.getHash());
			temp.setEdgeHash(node.getEdgeHash());
			nodeHashes1.add(temp);
		}
		for(Node node : nodes2) {
			NodeHash temp = new NodeHash();
			temp.setNodeHash(node.getHash());
			temp.setEdgeHash(node.getEdgeHash());
			nodeHashes2.add(temp);
		}
		Collections.sort(nodeHashes1);
		Collections.sort(nodeHashes2);
		boolean matches = true;
		if(nodeHashes1.size() != nodeHashes2.size()) {
			System.out.println("Size mismatched");
			return false;
		}
		for(int i=0; i<nodeHashes1.size() && matches; i++ ) {
			if(nodeHashes1.get(i).equals(nodeHashes2.get(i))) {
				continue;
			}
			System.out.println("Mismatched at " + i );
			matches = false;
		}
		return matches;
	}
}