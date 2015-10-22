package helper;

import logger.Log;

@SuppressWarnings("serial")
public class SieveException extends Exception {
	public SieveException(String message) throws Exception {
		Log.error(message);
	}
}
