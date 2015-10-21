package serialize;

import graph.Edge;
import graph.Graph;
import graph.Node;
import helper.FileHelper;

import java.io.PrintWriter;
import java.util.ArrayList;

import logger.Log;

public class ToGiraph {
	
	public String filePath = "F:\\Graph Databases\\giraph\\graph.txt";
	
	public void setFilePath(String path) {
		this.filePath = path;
	}
	
	public String getGiraphContentString(Graph graphObject) {
		String result = "";
		ArrayList<Node> graph = graphObject.getNodeList();
		for( Node node : graph ) { 
			result += "["
					+ node.getNodeId().toString()
					+ ", 0, [";
			ArrayList<Edge> edges = node.getEdges();
			for(int i=0; i<edges.size(); i++ ) {
				result += "["
						+ edges.get(i).getDestinationNodeId().toString() 
						+ ", "
						+ edges.get(i).getDistance().toString()
						+ "], ";
			}
			if(edges.isEmpty() == false) {
				// check at least one edge is present.
				result = result  
						+ "["
						+ edges.get(edges.size()-1).getDestinationNodeId().toString()
						+ ", "
						+ edges.get(edges.size()-1).getDistance().toString()
						+ "]";
			}
			result += "]]\n";
		}
		return result;
	}
	
	public boolean serializeGraphToGiraphFormat(Graph graph) throws Exception {
		if(FileHelper.isValidFile(filePath) == false) {
			Log.error("The path : "
					+ filePath.toString()
					+ ", doesn't correspond to a valid file.");
			return false;
		}
		PrintWriter writer = new PrintWriter(filePath);
		writer.println(this.getGiraphContentString(graph));
		writer.close();
		return true;
	}
}
