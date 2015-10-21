package helper;

import java.io.File;

public class FileHelper {
	public static boolean isValidFile(String pathToFile) {
		// Returns true if the pathToFile is the valid file
		// else returns false.
		File file = new File(pathToFile);
		if(file.exists() && !file.isDirectory()) {
			return true;
		}
		return false;
	}
}
