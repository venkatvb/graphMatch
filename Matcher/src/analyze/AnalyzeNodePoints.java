package analyze;

import graph.Graph;
import graph.Node;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import serialize.Serializable;

public class AnalyzeNodePoints implements Serializable{
	
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public AnalyzeNodePoints(Graph g) {
		nodes = g.getNodeList();
	}
	
	public void listNodeValues() {
		for(Node node : nodes) {
			System.out.println(node.getNodeValue());
		}
	}
	
	public ArrayList<String> getNodeValues() {
		ArrayList<String> nodeValues = new ArrayList<String>();
		for(Node node : nodes ) {
			nodeValues.add(node.getNodeValue());
		}
		return nodeValues;
	}

	@Override
	public boolean serailizeContent(File file, String content) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			Integer size = nodes.size();
			pw.write(size.toString());
			pw.write("\n");
			for(Node node : nodes ) {
				pw.write(node.getNodeValue());
				pw.write("\n");
			}
			pw.close();
		} catch(Exception e) {
			System.out.println("Exception caught while serializing the node values");
		}	
		return false;
	}
	
	public boolean isUnique() {
		HashSet<String> st = new HashSet<String>();
		for(Node node : nodes) {
			if(st.contains(node.getNodeValue())) {
				return false;
			}
			st.add(node.getNodeValue());
		}
		return true;
	}
}
