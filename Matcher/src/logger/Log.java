package logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static final String PATH_TO_LOG_FILE = "F:\\Graph Databases\\log\\log.txt";
	public static final String ENCODING_FORMAT = "UTF-8";
	public static boolean isConnected = false;
	static PrintWriter writer;
	
	public static String getCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedTimeStamp = simpleDateFormat.format(date);
		return formattedTimeStamp;
	}
	
	public static void debug(String message) throws IOException {
		if(isConnected == false) {
			establishConnection();
		}
		writer.println("DEBUG : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static void error(String message) throws IOException {
		if(isConnected == false) {
			establishConnection();
		}
		writer.println("ERROR : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static void info(String message) throws IOException {
		if(isConnected == false) {
			establishConnection();
		}
		writer.println("INFO : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static boolean establishConnection() throws IOException {
		// Returns true is the connection establishment is successful.
		writer = new PrintWriter(PATH_TO_LOG_FILE, ENCODING_FORMAT);
		isConnected = true;
		return true;
	}
	
	public static void closeConnection() throws IOException {
		if(isConnected == false) {
			return;
		}
		writer.close();
	}
}
