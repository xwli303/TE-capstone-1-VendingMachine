package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Inventory {

	Map <String, SnacksInSlot> snacksInMachine = new TreeMap <>();
	
	
	public Inventory () {
		
	}
	
	public SnacksInSlot getSnackInslot(String key ) {
		return snacksInMachine.get(key);
	}
	public void displayChoices(){
		Set<String> keys = snacksInMachine.keySet();
		for( String key: keys) {
			String snackName = snacksInMachine.get(key).getName();
			int snackPrice = snacksInMachine.get(key).getPrice();
			System.out.println(key + " " + snackName + " " + snackPrice);
		}
	
	}
	public void fillInventory () {
//		snacksInMachine.
	
	String inputFile = "vendingmachine.csv";
	File merchandiseFile = new File(inputFile);
	
	try (Scanner merchandise = new Scanner(merchandiseFile)){
		while (merchandise.hasNextLine()) {
			String lineOfInput = merchandise.nextLine();
			String [] temp = lineOfInput.split("\\|");
			String key = temp [0];
			String name = temp [1];
			double tempPrice = Double.valueOf(temp [2]);
			int priceInPenny = (int)(tempPrice * 100.0);
			String type = temp [3];
			
			
			SnacksInSlot slot = new SnacksInSlot (name, priceInPenny, key, type);
			snacksInMachine.put(key, slot);
			
			
		}
	} catch (FileNotFoundException e){
		System.err.println(e.getMessage());
		}
	
	}
	
	public boolean checkInventory(String key)  {
		if (snacksInMachine.get(key).getNumberRemaining() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
 