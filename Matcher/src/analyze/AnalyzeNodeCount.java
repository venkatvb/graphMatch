package analyze;

import graph.Graph;
import helper.FileHelper;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.GxlParser;
import serialize.Serializable;

public class AnalyzeNodeCount implements Serializable{
	
	public static final String nodeValuesFileBaseAddress = "F:\\Graph Databases\\nodevalues";
	AnalyzeNodePoints analyzeNodePoints;
	Map<String, Integer> mp;
	
	public AnalyzeNodeCount(boolean store) throws Exception{
		mp = new HashMap<String, Integer>();
		for(Integer i = 1; i <= 600; i++ ) {
			System.out.println("Processing " + i.toString() + "th file");
			GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_" + i.toString() + ".gxl");
			Graph g = parser.parse();
			analyzeNodePoints = new AnalyzeNodePoints(g);
			if(store) {
				storeContents(i);
			}
			ArrayList<String> nodes = analyzeNodePoints.getNodeValues();
			for(String nodeName : nodes) {
				mp.put(nodeName, i);
			}
		}
	}
	
	private void storeContents(Integer fileNumber) {
		String nodeValuesFileAddress = nodeValuesFileBaseAddress + "\\" + fileNumber.toString();
		if(analyzeNodePoints.isUnique()) {
			nodeValuesFileAddress += "_true.txt";
		} else {
			nodeValuesFileAddress += "_false.txt";
		}
		File file = FileHelper.createFile(nodeValuesFileAddress);
		analyzeNodePoints.serailizeContent(file);
	}
	
	public void printMappedContents() {
		for(Map.Entry<String, Integer> entry : mp.entrySet()) {
			System.out.println("Sequence : " + entry.getKey() + ", Times : " + entry.getValue());
		}
	}

	@Override
	public boolean serailizeContent(File file) {
		PrintWriter pw;
		List<SequenceCountPair> items = new ArrayList<SequenceCountPair>();
		Integer size = 0; 
		for(Map.Entry<String, Integer> entry : mp.entrySet()) {
			SequenceCountPair tempPair = new SequenceCountPair(entry.getKey(), entry.getValue());
			items.add(tempPair);
		}
		Collections.sort(items);
		try {
			pw = new PrintWriter(file);
			pw.write(size.toString());
			pw.write("\n");
			for(SequenceCountPair item : items ) {
				pw.write(item.getSequence() + " " + item.getCount());
				pw.write("\n");
			}
			pw.close();
		} catch(Exception e) {
			System.out.println("Exception caught while serializing the node values");
		}	
		return false;
	}
}
