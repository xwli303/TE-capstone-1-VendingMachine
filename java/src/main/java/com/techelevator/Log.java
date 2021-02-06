package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	public static void log (String message) {
		
		File purchaseLog = new File("Log.log");
		
		boolean appendMode = purchaseLog.exists() ? true : false;
		
		try (PrintWriter writer = 
				new PrintWriter (new FileOutputStream(purchaseLog.getAbsoluteFile(), appendMode))) {
			writer.append(getDateTimeString() + " ");
			writer.append(message + "\n");
			
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static String getDateTimeString() {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.a");
		return timestamp.format(formatter);
	}
	
}
