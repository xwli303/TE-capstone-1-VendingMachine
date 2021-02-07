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
		
//		Inventory inventory;
//		Map<String, SnacksInSlot> snacksInMachine = inventory.snacksInMachine;
		Map <String, Integer> salesNumber = new HashMap<>();
		//Map<key, numberSold>
		// constructor get keys from inventory
		public SalesReport (){
			
		}
		
		
//		public void getSalesNumber(){
//			Set<String> keys = snacksInMachine.keySet();
//			for(String key : keys) {
//				String snackName = snacksInMachine.get(key).getName();
////				int numberSales = 0;
//				int numberSales = salesNumber.get(key);
//			
//			}
//			
//		}
		
		//create map with the keys and numberSold = 0;
		// print sales report
		public void writeSalesReport (Inventory inventory) {
			
			try {
				File salesReport = new File("SalesReport.txt");
				
				if(!salesReport.exists()) {
					System.out.println("file already exists");
				}
				salesReport.createNewFile();
				boolean appendMode = salesReport.exists() ? true : false;
				
				
				try (PrintWriter writer =
					new PrintWriter (new FileOutputStream(salesReport.getAbsoluteFile(), appendMode))){
					
					
					Set<String> keys = inventory.getKeySet();
					for(String key : keys) {
						String snackName = inventory.getSnackInslot(key).getName();
;						int numberSold = salesNumber.get(key);
					
							writer.append(snackName + " " + numberSold + "\n");
					}
							writer.flush();
							writer.close();
			
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void initializeSalesNumber (Inventory inventory) {
			Set<String> keys = inventory.getKeySet();
			for(String key : keys) {
				salesNumber.put(key, 0);
			}
		}
		
		public void updateSalesReport (Inventory inventory, String slot) {
				int number = salesNumber.get(slot);
				number ++;
				salesNumber.put(slot, number);
			}
		}
		
		// public void updateReport
		// add 1 to numbersold in our sales<map>
		
		//constructor open existing file and populate data structure


