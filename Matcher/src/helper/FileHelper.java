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
	
	private static File getFile(String pathToFile) throws FileNotFoundException {
		File file = new File(pathToFile);
		if(file.exists() && !file.isDirectory()) {
			return file;
		}
		throw new FileNotFoundException();
	}
	
	public static File createFile(String address) throws FileNotFoundException {
		if(isValidFile(address)) {
			return getFile(address);
		}
		File file = new File(address);
		return file;
	}
}
