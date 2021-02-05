package com.techelevator;

import java.sql.Array;
import java.util.Scanner;

public class Transaction {

	private int currentMoney = 0;
	 //keep deposit or not 
	private static final int VALUE_OF_QUARTER = 25;
	private static final int VALUE_OF_DIME = 10;
	private static final int VALUE_OF_NICKEL = 5;
	
	public Transaction () {
		
	}
	
	public int getCurrentMoney() {
		return currentMoney;
	}
	
	public void subtractCostOfItem( int cost){
		currentMoney -= cost;
	}
	
	
	public void getMoney(String userMoney) throws TransactionException {
		//System.out.println("Please insert money in amounts of $1, $2, $5, or $10.");
		//Scanner userInput = new Scanner (System.in);
		//String userMoney = userInput.nextLine();
		//String userMoney = "$1";
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
			throw new TransactionException ("Invalid input amount, please put in "
					+ "amounts of $1, $2, $5, or $10");
		}
		
	}
	public int moreMoney(String amount) throws TransactionException {
		//System.out.println("in more money");
		System.out.println("Deposited " + amount);
		int deposit = 0;
		if (amount.equals("$1")) {
			deposit = 100;
			currentMoney += deposit;
		} else if (amount.equals("$2")) {
			deposit = 200;
			currentMoney += deposit;
		} else if (amount.equals("$5")) {
			deposit = 500;
			currentMoney += deposit;
		} else if (amount.equals("$10")) {
			deposit = 1000;
			currentMoney += deposit;
		} else {
			throw new TransactionException ("Invalid input amount, please enter "
					+ "in amounts of $1, $2, $5, or $10.");
		}
		
		return currentMoney;
	}
	
	public void manageTransaction(Inventory inventory, String YorN) throws TransactionException {
		boolean continueDeposit = true; 
		while(continueDeposit) {
//			getMoney();
		
		Scanner userInput = new Scanner (System.in);
		
		if (YorN.equals("N") || YorN.equals("n")){
			continueDeposit = false;
			}
		}
			System.out.println("Current money is: " + currentMoney);
			this.selectItem(inventory);
	}
	

	public void selectItem (Inventory inventory) throws TransactionException {
		inventory.displayChoices();
		System.out.println("Please input the slot number: ");

		Scanner userInput = new Scanner (System.in);
		String slotNum = userInput.nextLine();
		
		SnacksInSlot snack = inventory.getSnackInslot(slotNum);
		int snackPrice = inventory.snacksInMachine.get(slotNum).getPrice();
		
		if (currentMoney - snackPrice < 0) {
			throw new TransactionException("Insufficient funds");
		} 
		
		currentMoney -= snackPrice;
		//print out new balance 
		System.out.println( "You got " + snack.getName());
		}
		
	public boolean checkSufficientFund (SnacksInSlot snack) {
		int snackPrice = snack.getPrice();
		if (currentMoney - snackPrice >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int [] returnChange() {
		int [] change = {0, 0, 0};
		
		int quarters = currentMoney / VALUE_OF_QUARTER;
		currentMoney = currentMoney - quarters * VALUE_OF_QUARTER;
		change [0] = quarters;
		
		int dimes = currentMoney / VALUE_OF_DIME;
		currentMoney = currentMoney - dimes * VALUE_OF_DIME;
		change [1] = dimes;
		
		int nickels = currentMoney / VALUE_OF_NICKEL;
		currentMoney = currentMoney - nickels * VALUE_OF_NICKEL;
		change [2] = nickels;
		
		return change;
	}
	
	
	
	}
	
	
	
	

