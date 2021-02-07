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
		//Map<key, numberSold>
		// constructor get keys from inventory
		//create map with the keys and numberSold = 0;
		// print sales report
		public void sales () {
			
			try {
				File salesReport = new File("SalesReport.txt");
				
				if(!salesReport.exists()) {
					System.out.println("file already exists");
				}
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
		
		// public void updateReport
		// add 1 to numbersold in our sales<map>
		
		//constructor open existing file and populate data structure

}
