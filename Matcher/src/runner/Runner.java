package runner;

import helper.FileHelper;
import helper.FileNotFoundException;

import java.util.Scanner;

import logger.Log;
import optimalSubsetSelect.SelectSubSet;
import analyze.AnalyzeNodeCount;
import analyze.ComplexityAnalyzer;
import configuration.BuildConfig;

public class Runner {
	
	public static final String INPUT_FILE_LOCATION = "/home/venkatvb/project/GraphDatabases/input/input.gxl";
	public static final String GXL_BASE_LOCATION = "/home/venkatvb/project/GraphDatabases/gxl/";
	
	static void analyzeNodePoints() throws Exception {
		AnalyzeNodeCount analyzeNodeCount = new AnalyzeNodeCount(false);
		String address = "/home/venkatvb/project/GraphDatabases/mapped.txt";
		analyzeNodeCount.serailizeContent(FileHelper.createFile(address), new String(""));
		analyzeNodeCount.printMappedContents();
	}
	static void analyzeComplexity() throws FileNotFoundException {
		// N - Denotes the average number of nodes in the graph
		// M - denotes the number of graphs in the Graph Database
		// start, end, difference - Denotes the number of queries in the database.
		int N = 25;
		int M = 500;
		int start = 100;
		int end = 1000;
		int diff = 1;
		String address = "/home/venkatvb/project/GraphDatabases/dataPoints/complexity.txt";
		ComplexityAnalyzer a = new ComplexityAnalyzer(N, M, start, end, diff);
		a.serailizeContent(FileHelper.createFile(address), new String(""));
	}	
		
	public static void main(String args[]) throws Exception {
//		BuildConfig.setQueryEnzymeId(1);
		// This module analyzes the time complexity
		// analyzeComplexity();
		// This module analyzed the locality of node points in the data set
		// analyzeNodePoints();
		
		// Drawing GXL files
//		DrawGraph drawGraph = new DrawGraph();
//		for(int i=1; i<500; i++ ) {
//			drawGraph.drawImageOfGXL(i);
//			System.out.println("Graph " + i + " completed!");
//		}
		
		// QuadTree
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input file : ");
		int n = sc.nextInt();
		String queryLocation = GXL_BASE_LOCATION + "enzyme_62.gxl";
		SelectSubSet optimalSet = new SelectSubSet();
		optimalSet.selectObtimalSubSet(queryLocation);
//		QuadInit quadtree = new QuadInit();
//		for(Integer i=1; i<=600; i++ ) {
//			GxlParser inputParser = new GxlParser(GXL_BASE_LOCATION + "enzyme_" + i.toString() + ".gxl");
//			Graph inputGraph = inputParser.parse();
//			quadtree.serializeStringInit();
//			for(Node inputNode : inputGraph.getNodeList()) {	
//				try {
//					quadtree.printIndexesOfSequence(inputNode.getNodeValue(), i);
//				} catch(Exception e) {
//					System.out.println("Exception catched : sequence not found, " + e);
//				}
//			}
//			quadtree.serializeCurrentString(i);
//		}
		// System.out.println("Comparing the input files");
		// Actual processing
//		GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_3.gxl");
//		GxlParser parser2 = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_3.gxl");
//		Graph graph = parser.parse();
//		Graph graph1 = parser2.parse();
//		Comparator compare = new Comparator();
//		DrawGraph drawer = new DrawGraph();
//		drawer.serializeImage(drawer.draw(graph), "F:\\Graph Databases\\Images\\graphimage1.png");
//		drawer.serializeImage(drawer.draw(graph1), "F:\\Graph Databases\\Images\\graphimage3.png");
//		// System.out.println(MathHelper.getLargePrime());
//		// boolean result = compare.compareGraphs(graph, graph1);
//		// System.out.println("Comparision result : " + result);
//		new ToGiraph().serializeGraphToGiraphFormat(graph);
		Log.closeConnection();
	}
}
