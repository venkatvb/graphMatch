package runner;

import logger.Log;
import parser.GxlToGiraphParser;

public class Runner {
	public static void main(String args[]) throws Exception {
		GxlToGiraphParser parser = new GxlToGiraphParser("F:\\Graph Databases\\gxl\\enzyme_1.gxl");
		parser.parse();
		Log.closeConnection();
	}
}
