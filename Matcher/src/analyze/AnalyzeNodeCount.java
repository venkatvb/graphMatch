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
	ArrayList<Integer> ar;
	Map<String, ArrayList<Integer>> mp;
	
	public AnalyzeNodeCount(boolean store) throws Exception{
		mp = new HashMap<String, ArrayList<Integer>>();
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
				ArrayList<Integer> ar = mp.get(nodeName);
				if(ar == null) {
					ar = new ArrayList<Integer>();
				}
				ar.add(i);
				mp.put(nodeName, ar);
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
		for(Map.Entry<String, ArrayList<Integer>> entry : mp.entrySet()) {
			String sequence = entry.getKey();
			ArrayList<Integer> fileIds = entry.getValue();
			System.out.println(sequence);
			System.out.print("[");
			for(int i=0; i<fileIds.size(); i++ ) {
				System.out.print(fileIds.get(i));
				if(i < fileIds.size()-1 ) {
					System.out.print(",");
				}
			}
			System.out.print("]");
		}
	}

	@Override
	public boolean serailizeContent(File file) {
		PrintWriter pw;
		List<SequenceListPair> items = new ArrayList<SequenceListPair>();
		for(Map.Entry<String, ArrayList<Integer>> entry : mp.entrySet()) {
			SequenceListPair tempPair = new SequenceListPair(entry.getKey(), entry.getValue());
			items.add(tempPair);
		}
		Collections.sort(items);
		try {
			pw = new PrintWriter(file);
			for(SequenceListPair item : items ) {
				pw.write(item.getSequence() + " " + item.getCount());
				pw.write("\n");
				ArrayList<Integer> fileIds = item.getList();
				pw.write("[");
				for(int i=0; i<fileIds.size(); i++ ) {
					pw.write(fileIds.get(i).toString());
					if(i < fileIds.size()-1) {
						pw.write(",");
					}
				}
				pw.write("]\n");
			}
			pw.close();
		} catch(Exception e) {
			System.out.println("Exception caught while serializing the node values");
		}	
		return false;
	}
}
