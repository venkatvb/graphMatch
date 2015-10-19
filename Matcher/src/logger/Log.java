package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static final String PATH_TO_LOG_FILE = "F:\\Graph Databases\\log\\log.txt";
	public static boolean isConnected = false;
	static FileWriter file;
	static BufferedWriter bufferedWriter; 
	
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
		bufferedWriter.write("DEBUG : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static void error(String message) throws IOException {
		if(isConnected == false) {
			establishConnection();
		}
		bufferedWriter.write("ERROR : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static void info(String message) throws IOException {
		System.out.println("Came here to add message " + message.toString());
		if(isConnected == false) {
			establishConnection();
		}
		bufferedWriter.write("INFO : " + getCurrentTimeStamp() + ": " + message);
	}
	
	public static boolean establishConnection() throws IOException {
		// returns true if the connection establishment is successful.
		File file = new File(PATH_TO_LOG_FILE);

		// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		bufferedWriter = new BufferedWriter(fileWriter);
		isConnected = true;
		return true;
	}
	
	public static void closeConnection() throws IOException {
		if(isConnected == false) {
			return;
		}
		bufferedWriter.close();
		file.close();
	}
}
