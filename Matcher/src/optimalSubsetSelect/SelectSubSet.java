package optimalSubsetSelect;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import configuration.BuildConfig;

import parser.GxlParser;
import quadtree.QuadInit;

public class SelectSubSet {
	
	public ArrayList<Integer> selectObtimalSubSet(String queryPath) throws Exception {
		QuadInit quadtree = new QuadInit();
		GxlParser inputParser = new GxlParser(queryPath);
		Graph inputGraph = inputParser.parse();
		quadtree.serializeStringInit();
		TreeMap<Integer, Integer> ma = new TreeMap<Integer, Integer>();
		Double numberOfSequences = (double) inputGraph.getNodeList().size();
		for(Node inputNode : inputGraph.getNodeList()) {	
			try {
				Set<Integer> st = quadtree.querySequence(inputNode.getNodeValue());
				System.out.println("Sequence : " + inputNode.getNodeValue());
				for(Integer i : st) {
					System.out.print(i + " ");
					if(ma.containsKey(i)) {
						ma.put(i, ma.get(i)+1);
					} else {
						ma.put(i, 1);
					}
				}
				System.out.println();
			} catch(Exception e) {
				System.out.println("Exception catched : sequence not found, " + e);
			}
		}
		System.out.println("Total Number of Sequences : " + inputGraph.getNodeList().size());
		Map<Integer, Integer> map = ma;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + ", file = enzyme_" + entry.getKey() + ".gxl");
		    int currentCount = (int) entry.getValue();
		    Double curCount = (double) currentCount;
		    Double factor = curCount / numberOfSequences;
		    System.out.println("Factor : " + factor * 100);
		    result.add(entry.getKey());
		}
		return result;
	}
}
