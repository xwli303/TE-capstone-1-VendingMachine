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
	
//	Map <String, SnacksInSlot> snacks = new TreeMap<>();
	
	Inventory inventory = new Inventory();
	
	public  void sales () {
		Map <String, Integer> salesNumber = inventory.getSalesNumber();
		
		
		try {
			File salesReport = new File("SalesReport.txt");
			salesReport.createNewFile();
			
			boolean appendMode = salesReport.exists() ? true : false;
			
			try (PrintWriter writer =
				new PrintWriter (new FileOutputStream(salesReport.getAbsoluteFile(), appendMode))){

					for (Map.Entry<String, Integer> entry : salesNumber.entrySet()) {
						writer.append(entry.getKey() + " " + entry.getValue());
						writer.append("\n");
					}
		
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
//	public void getName () {
//		String inputFile = "vendingmachine.csv";
//		File merchandiseFile = new File(inputFile);
//		
//		try (Scanner merchandise = new Scanner(merchandiseFile)){
//			while (merchandise.hasNextLine()) {
//				String lineOfInput = merchandise.nextLine();
//				String [] temp = lineOfInput.split("\\|");
//				String key = temp [0];
//				String name = temp [1];
//				double tempPrice = Double.valueOf(temp [2]);
//				int priceInPenny = (int)(tempPrice * 100.0);
//				String type = temp [3];
//				
//				SnacksInSlot slot = new SnacksInSlot (name, priceInPenny, key, type);
//				snacks.put(key, slot);
//				
//				
//				int numberSales = snacks.get(key).getNumberSold();
//				
//				Set<String> keys = snacks.keySet();
//				for(String slotNum : keys) {
//					salesNumber.put(name, numberSales);
//				}
//				
//				
//			}
//		} catch (FileNotFoundException e){
//			System.err.println(e.getMessage());
//			}
//	}

	
	
}
