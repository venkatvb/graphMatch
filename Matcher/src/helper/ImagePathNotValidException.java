package helper;

import logger.Log;

@SuppressWarnings("serial")
public class ImagePathNotValidException extends Exception {
	public ImagePathNotValidException(String message) throws Exception {
		Log.error(message);
	}
}
