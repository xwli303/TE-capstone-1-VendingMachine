package com.techelevator;

import java.util.Scanner;

public class Transaction {

	private int currentMoney = 0;
	Boolean continueDeposit = true; //keep deposit or not 

	
//	Scanner userInput = new Scanner(System.in);  //user's choice
	
//	String slotNum = userInput.toString();
//	int snackPrice = inventory.snacksInMachine.get(slotNum).getPrice();
	
	public Transaction () {
		
	}
	
	
	public void getMoney() {
		System.out.println("Please insert money in amounts of $1, $2, $5, or $10.");
		Scanner userInput = new Scanner (System.in);
		String userMoney = userInput.nextLine();
		int deposit = 0;
		if (userMoney.equals("$1")) {
			deposit = 100;
			currentMoney += deposit;
		} else if (userMoney.equals("$2")) {
			deposit = 200;
			currentMoney += deposit;
		} else if (userMoney.equals("$5")) {
			deposit = 500;
			currentMoney += deposit;
		} else if (userMoney.equals("$10")) {
			deposit = 1000;
			currentMoney += deposit;
		} else {
			System.out.println("Invalid input amount, please put in "
					+ "amounts of $1, $2, $5, or $10");
		}
		
	}
	
	public void manageTransaction(Inventory inventory) {
		while (continueDeposit) {
			getMoney();
		
		Scanner userInput = new Scanner (System.in);
		System.out.println("Are you depositing more money? Y/N");	
		String YorN = userInput.nextLine();
		if (YorN.equals("N") || YorN.equals("n")){
			continueDeposit = false;
			}
		}
			System.out.println("Current money is: " + currentMoney);
			this.selectItem(inventory);
	}
	
	public void selectItem (Inventory inventory) {
		//Inventory inventory = new Inventory();
		inventory.displayChoices();
		System.out.println("Please input the slot number: ");
		
		Scanner userInput = new Scanner (System.in);
		String slotNum = userInput.nextLine();
		
		SnacksInSlot snack = inventory.getSnackInslot(slotNum);
	
			System.out.println( "snack is: " + snack.getName());
		}
		
	}
	
	
	
	
	//if item does not exist, return to menu and send error message.
	
	
	//if item is sold out, return to menu and send error message
	
	//if in stock, print item type message, dispense, remaining qty - x, update balance
	
	//			if currentMoney > inventory.getPrice
	
	
	
	
	//if currentMoney > inventory.getPrice

	
	

