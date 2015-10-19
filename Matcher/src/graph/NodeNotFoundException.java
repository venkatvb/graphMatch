package graph;

import java.io.IOException;

import logger.Log;

@SuppressWarnings("serial")
public class NodeNotFoundException extends Exception {
	public NodeNotFoundException(String message) throws IOException{
		Log.error(message);
	}
}
