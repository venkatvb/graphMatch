package helper;

@SuppressWarnings("serial")
public class FileNotFoundException extends Exception {
	public String message() {
		return new String("The file is not found in the given address.");
	}
}
