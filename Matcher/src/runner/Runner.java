package runner;

import graph.Graph;
import logger.Log;
import parser.GxlParser;
import serialize.ToGiraph;

public class Runner {
	public static void main(String args[]) throws Exception {
		GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_1.gxl");
		Graph graph = parser.parse();
		new ToGiraph().serializeGraphToGiraphFormat(graph);
		Log.closeConnection();
	}
}
