package runner;

import logger.Log;
import parser.GxlParser;

public class Runner {
	public static void main(String args[]) throws Exception {
		GxlParser parser = new GxlParser("F:\\Graph Databases\\gxl\\enzyme_1.gxl");
		parser.parse();
		Log.closeConnection();
	}
}
