package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class SalesReport {

		
//		Map <String, SnacksInSlot> snacks = new TreeMap<>();
		
		Inventory inventory = new Inventory();
		Map <String, Integer> salesNumber = inventory.getSalesNumber();
		
		public void sales () {
			
			try {
				File salesReport = new File("SalesReport.txt");
				salesReport.createNewFile();
				
				boolean appendMode = salesReport.exists() ? true : false;
				
				try (PrintWriter writer =
					new PrintWriter (new FileOutputStream(salesReport.getAbsoluteFile(), appendMode))){
						for (Map.Entry<String, Integer> entry : salesNumber.entrySet()) {
							writer.write(entry.getKey() + " " + entry.getValue() + "\n");
							writer.flush();
							writer.close();
						}
			
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

}
