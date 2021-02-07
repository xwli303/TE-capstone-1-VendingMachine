package com.techelevator;

import java.sql.Array;
import java.util.Scanner;

public class Transaction {

	private int currentMoney = 0;
	private static final int VALUE_OF_QUARTER = 25;
	private static final int VALUE_OF_DIME = 10;
	private static final int VALUE_OF_NICKEL = 5;
	
	public Transaction () {
		
	}
	
	public int getCurrentMoney() {
		return currentMoney;
	}
	
	
	public void subtractCostOfItem(int cost){
		 currentMoney = currentMoney - cost;
	}

	public int moreMoney(String amount) throws TransactionException {
		//System.out.println("Deposited " + amount);
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
	
	
	
	public int valueOfInput (String amount) {
		int value = 0;
		if (amount.equals("$1")) {
			value = 100;
		} else if (amount.equals("$2")) {
			value = 200;
		} else if (amount.equals("$5")) {
			value = 500;
		} else if (amount.equals("$10")) {
			value = 1000;
		} 
		return value;
	}


	public boolean checkSufficientFund (SnacksInSlot snack) {
		int snackPrice = snack.getPrice();
		if (currentMoney - snackPrice >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String returnChange(int amount) {
		int [] coins = {0, 0, 0};
		
		int quarters = amount / VALUE_OF_QUARTER;
		amount = amount - quarters * VALUE_OF_QUARTER;
		coins [0] = quarters;
		
		int dimes = amount / VALUE_OF_DIME;
		amount = amount - dimes * VALUE_OF_DIME;
		coins [1] = dimes;
		
		int nickels = amount / VALUE_OF_NICKEL;
		amount = amount - nickels * VALUE_OF_NICKEL;
		coins [2] = nickels;
		
		String change = "Your change is:\r";
		if(coins[0] > 0) {
			if(coins[0] > 1) {
			change = change + " " + coins[0] + " quarters\r";
			}else { change = change + " " + coins[0] + " quarter\r";	
			}
		}
		if(coins[1] > 0) {
			if(coins[1] > 1) {
			change = change + " " + coins[1] + " dimes\r";
			}else { change = change + " " + coins[1] + " dime\r";}
		}
		if( coins[2] > 0) {
			change = change + " " + coins[2] + " nickel\r";
		}
		currentMoney = 0;
		return change;	
		
	}
	
	
	}
	
	
