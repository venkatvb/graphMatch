package helper;

import logger.Log;

@SuppressWarnings("serial")
public class UnknownImageFormatException extends Exception {
	public UnknownImageFormatException(String message) throws Exception {
		Log.error(message);
	}
}
