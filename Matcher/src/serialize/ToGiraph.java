package serialize;

import java.io.PrintWriter;

import graph.Graph;
import helper.FileHelper;
import logger.Log;

public class ToGiraph {
	
	public String filePath = "F:\\Graph Databases\\giraph\\graph.txt";
	
	public void setFilePath(String path) {
		this.filePath = path;
	}
	
	public boolean serializeGraphToGiraphFormat(Graph graph) throws Exception {
		if(FileHelper.isValidFile(filePath) == false) {
			Log.error("The path : "
					+ filePath.toString()
					+ ", doesn't correspond to a valid file.");
			return false;
		}
		PrintWriter writer = new PrintWriter(filePath);
		writer.println(graph.toString());
		writer.close();
		return true;
	}
}
