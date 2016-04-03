package core;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.Collections;

import logger.LogHelper;
import parser.GxlParser;

public class Comp {
	
	public boolean compareGraphs(Graph graph1, Graph graph2) throws Exception {
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
		LogHelper.logNodeHashArray(nodeHashes1);
		LogHelper.logNodeHashArray(nodeHashes2);
		return matches;
	}
	
	public int compareGraphsUsingId(Integer queryNode, Integer fileId) {
		GxlParser parser = new GxlParser("/home/venkatvb/project/Graph Databases/gxl/enzyme_" + queryNode.toString() +  ".gxl");
		GxlParser parser2 = new GxlParser("/home/venkatvb/project/Graph Databases/gxl/enzyme_" + fileId.toString() + ".gxl");
		try {
			Graph graph = parser.parse();
			Graph graph1 = parser2.parse();
			boolean result = compareGraphs(graph, graph1);
			if(result == true) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
