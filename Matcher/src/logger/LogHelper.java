package logger;

import java.util.ArrayList;

import core.NodeHash;

public class LogHelper {
	public static void logNodeHashArray(ArrayList<NodeHash> list) throws Exception {
		for(NodeHash nodeHash : list ) {
			Log.info(nodeHash.getLogContent());
		}
	}
}
