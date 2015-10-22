package logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static final String PATH_TO_LOG_FILE = "F:\\Graph Databases\\log\\log.txt";
	public static final String ENCODING_FORMAT = "UTF-8";
	static PrintWriter writer;
	
	public static String getCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedTimeStamp = simpleDateFormat.format(date);
		return formattedTimeStamp;
	}
	
	public static void debug(String message) throws IOException {
		establishConnection();
		writer.println("DEBUG : " + getCurrentTimeStamp() + ": " + message);
		closeConnection();
	}
	
	public static void error(String message) throws IOException {
		establishConnection();
		writer.println("ERROR : " + getCurrentTimeStamp() + ": " + message);
		closeConnection();
	}
	
	public static void info(String message) throws IOException {
		establishConnection();
		writer.println("INFO : " + getCurrentTimeStamp() + ": " + message);
		closeConnection();
	}
	
	public static boolean establishConnection() throws IOException {
		// Returns true is the connection establishment is successful.
		writer = new PrintWriter(
					new FileOutputStream(
						new File(PATH_TO_LOG_FILE), 
						true)
					);
		return true;
	}
	
	public static void closeConnection() throws IOException {
		writer.close();
	}
}
