package runner;

import graph.Graph;
import image.DrawGraph;
import logger.Log;
import parser.GxlParser;
import serialize.ToGiraph;
import core.Comparator;

public class Runner {
	public static void main(String args[]) throws Exception {
		GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_1.gxl");
		Graph graph = parser.parse();
		Comparator compare = new Comparator();
		DrawGraph drawer = new DrawGraph();
		drawer.serializeImage(drawer.draw(graph), "F:\\Graph Databases\\Images\\graphimage.png");
		// System.out.println(MathHelper.getLargePrime());
		boolean result = compare.compareGraphs(graph, graph);
		System.out.println("Comparision result : " + result);
		new ToGiraph().serializeGraphToGiraphFormat(graph);
		Log.closeConnection();
	}
}
