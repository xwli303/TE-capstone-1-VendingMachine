package com.techelevator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import com.techelevator.search.WordLocation;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTIONS_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,MAIN_MENU_OPTIONS_EXIT };

	private static final String PURCHASE_MENU_DEPOSIT = "* Deposit money";
	private static final String PURCHASE_MENU_SELECT = "* Select Item";
	private static final String PURCHASE_MENU_EXIT = "* Exit";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_DEPOSIT, PURCHASE_MENU_SELECT, PURCHASE_MENU_EXIT };
	private Menu menu;
	public Inventory inventory;
	private Transaction transaction = new Transaction();
	boolean isMain = true;
	boolean isSelect = true;
	Scanner userInput = new Scanner (System.in);
	private Log purchaseLog = new Log();
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
	}
	

	public void run() {
		inventory.fillInventory();
		
		while (isMain) {
			isSelect = true;  //reset Purchase Menu
			
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.displayChoices();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				// SELECT ITEMS MENU
				while(isSelect) {
					String action = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					if(action.equals(PURCHASE_MENU_DEPOSIT)) {
						//deposit money
						depositPrompt ();			
						
					}
					else if (action.equals(PURCHASE_MENU_SELECT)) {
						//select item
						
						System.out.println(transactionPrompt());
					
						
					}
					else if (action.equals(PURCHASE_MENU_EXIT)) {
					
						System.out.println(changePrompt());
						//exit purchase menu
						isSelect = false;
						
						
					}
				}                     //END SELECT ITEMS MENU
				
			}
			else if (choice.contentEquals(MAIN_MENU_OPTIONS_EXIT)) {
				isMain = false;
				System.out.println("bye bye");
			}
		}
	}
	
	
	public void depositPrompt() {
		int output;
		System.out.println("You chose to Deposit Money");
		System.out.println("How much? ( $1, $2, $5, $10 )");
		String amount = userInput.nextLine();
			
		try {
			double money = (double)transaction.moreMoney(amount)/100.0;
			
			System.out.println("Money in machine: " + formatter.format(money));
			
			purchaseLog.log("FEED MONEY: " + formatter.format((double)transaction.valueOfInput(amount)/100.0) + 
					" " + formatter.format(money));
			
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\nMoney in machine: "  + formatter.format((double)transaction.getCurrentMoney()/100.0) + ".");
		
		}
		
	
	}
	
	public String transactionPrompt(){
		//check inventory for availability, else return SOLD OUT
		//if available then check if sufficient fund
		//if both are true, return selected item
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		inventory.displayChoices();
		String selection;   // this is the key
		String type;
		System.out.println("Which item would you like? A1, A2, etc.");
		
		//selecting product
		selection = userInput.nextLine();

		SnacksInSlot selectedItem = inventory.getSnackInslot(selection);
		
		if (selectedItem == null) {
			return "INVALID SELECTION";
		}
		
		System.out.println(selectedItem.getName());
		double price = (double)selectedItem.getPrice()/100.0;
		System.out.println(formatter.format(price));

		
		if (!inventory.checkInventory(selection)) {
			return "SOLD OUT";
		} 
		
		if (!transaction.checkSufficientFund(selectedItem)) {
			return "INSUFFICIENT FUNDS";	
			
		} 
		
		//customer receives snacks from machine
		inventory.removeSnackFromSlot(selection);
		
		double startingMoney = (double)transaction.getCurrentMoney()/100.0;
		transaction.subtractCostOfItem(selectedItem.getPrice());
		double money = (double)transaction.getCurrentMoney()/100.0;
		
		purchaseLog.log(selectedItem.getName() + " " + selection + " " + formatter.format((double)startingMoney) +
		" " + formatter.format((double) money));
		
		
		
		System.out.println("Money in machine: " + formatter.format(money));
		type = selectedItem.getType();
		if(type.equals("Chip")) {
			return "Crunch Crunch, Yum!";
		}
		if(type.equals("Candy")) {
			return "Munch Munch, Yum!";
		}
		if(type.equals("Drink")) {
			return "Glug glug, Yum!";
		}
		if (type.equals("Gum")) {
			return "Chew Chew, Yum!";
		}
		return "";
	
	}
	
	public String changePrompt () {
		
		double amountRemain = transaction.getCurrentMoney()/100;
		purchaseLog.log("GIVE CHANGE: " + formatter.format((double)amountRemain) + " 0.00" );
		
		int [] coins = transaction.returnChange();
		
		return "Your change is " + coins[0] + " quarters, " +
				coins[1] + " dimes, and " + coins[2] + " nickels.";
		
	
		
	}
	


	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		
		
	}
}
