package runner;

import helper.FileHelper;
import analyze.AnalyzeNodeCount;
import analyze.ComplexityAnalyzer;

public class Runner {
	
	static void analyzeNodePoints() throws Exception {
		AnalyzeNodeCount analyzeNodeCount = new AnalyzeNodeCount(false);
		String address = "F:\\Graph Databases\\nodevalues\\mapped.txt";
		analyzeNodeCount.serailizeContent(FileHelper.createFile(address));
	}
	static void analyzeComplexity() {
		// N - Denotes the average number of nodes in the graph
		// M - denotes the number of graphs in the Graph Database
		// start, end, difference - Denotes the number of queries in the database.
		int N = 25;
		int M = 500;
		int start = 100;
		int end = 1000;
		int diff = 1;
		String address = "F:\\Graph Databases\\dataPoints\\complexity.txt";
		ComplexityAnalyzer a = new ComplexityAnalyzer(N, M, start, end, diff);
		a.serailizeContent(FileHelper.createFile(address));
	}	
		
	public static void main(String args[]) throws Exception {
		analyzeComplexity();
		//analyzeNodePoints();
		/*GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\test1");
		GxlParser parser2 = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_3.gxl");
		Graph graph = parser.parse();
		Graph graph1 = parser2.parse();
		Comparator compare = new Comparator();
		DrawGraph drawer = new DrawGraph();
		drawer.serializeImage(drawer.draw(graph), "F:\\Graph Databases\\Images\\graphimage1.png");
		drawer.serializeImage(drawer.draw(graph1), "F:\\Graph Databases\\Images\\graphimage3.png");
		// System.out.println(MathHelper.getLargePrime());
		boolean result = compare.compareGraphs(graph, graph1);
		System.out.println("Comparision result : " + result);
		new ToGiraph().serializeGraphToGiraphFormat(graph);
		Log.closeConnection();
		*/
	}
}
