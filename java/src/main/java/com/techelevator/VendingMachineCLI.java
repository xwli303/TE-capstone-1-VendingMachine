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
	private static final String MAIN_MENU_SALES_REPORT = "Write sales report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,MAIN_MENU_OPTIONS_EXIT, MAIN_MENU_SALES_REPORT };

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
	private SalesReport salesReport = new SalesReport();
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
	}
	public VendingMachineCLI() {
		
	}
	

	public void run() {
		inventory.fillInventory();
		salesReport.initializeSalesNumber(inventory);
		
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
						System.out.println("Money in machine: " + penniesToDollars(transaction.getCurrentMoney()));
						
					}
					else if (action.equals(PURCHASE_MENU_EXIT)) {
					
						System.out.println(changePrompt());
						//exit purchase menu
						isSelect = false;
						
					}
			
				}      //END SELECT ITEMS MENU
				
			}
			else if (choice.contentEquals(MAIN_MENU_OPTIONS_EXIT)) {
				isMain = false;
				
				System.out.println("THANK YOU, BYE BYE");
			}
			else if (choice.contentEquals(MAIN_MENU_SALES_REPORT)) {
				salesReport.writeSalesReport(inventory);
				System.out.println("SALES REPORT GENERATED");
			}
		}
	}
	
	
	public void depositPrompt() {
		int output;
		System.out.println("You chose to Deposit Money");
		System.out.println("How much? ($1, $2, $5, $10)");
		String amountDeposited = userInput.nextLine();
		int deposit = transaction.valueOfInput(amountDeposited);
			
		try {
			int balance = transaction.moreMoney(amountDeposited);
			String balanceString = penniesToDollars(balance);
			
			System.out.println("Money in machine: " + balanceString);
			
			purchaseLog.log("FEED MONEY: " + penniesToDollars(deposit) + "  " + balanceString);
			
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\nMoney in machine: "  + penniesToDollars(transaction.getCurrentMoney()) + ".");
		
		}
		
	
	}
	
	public String transactionPrompt(){
		//check inventory for availability, else return SOLD OUT
		//if available then check if sufficient fund
		//if both are true, return selected item
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		inventory.displayChoices();
		String selection;   // this is the key
		String type;		// type of snack
		System.out.println("Which item would you like? A1, A2, etc.");
		
		//selecting product
		selection = userInput.nextLine();
		SnacksInSlot selectedItem = inventory.getSnackInslot(selection);
		
		if (selectedItem == null) {
			return "INVALID SELECTION";
		}
		
		// display snack choice and price
		System.out.println(selectedItem.getName());
		int price = selectedItem.getPrice();
		System.out.println(penniesToDollars(price));

		// check if snack available and enough money in machine
		if (!inventory.checkInventory(selection)) {
			return "SOLD OUT";
			} 
		if (!transaction.checkSufficientFund(selectedItem)) {
			return "INSUFFICIENT FUNDS";	
			} 
		
		//customer receives snacks from machine and sales report updated
		inventory.removeSnackFromSlot(selection);
		
		//add 1 to item number sold for sales report
		salesReport.updateSalesReport(inventory, selection);
		
		// handling transaction and logging transaction
		int startingMoney = transaction.getCurrentMoney();
		String stringStartingMoney = penniesToDollars(startingMoney);
		
		transaction.subtractCostOfItem(selectedItem.getPrice());
		int balance = transaction.getCurrentMoney();
		String stringBalance = penniesToDollars(balance);
		
		purchaseLog.log(selectedItem.getName() + "  " + selection + "  " + stringStartingMoney +
		"  " + stringBalance);
		
		
		// make snack sound
		type = selectedItem.getType();
		return (getSnackSound(type));
		
	}
	
	public String changePrompt () {
		
		int balance = transaction.getCurrentMoney();
		purchaseLog.log("GIVE CHANGE: " + penniesToDollars(balance) + "  0.00" );

		return transaction.returnChange(balance);	
	}
	
	
	public String getSnackSound(String type) {
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
	public String penniesToDollars(int pennies) {
		double doubleDollars = (double)pennies/100.0;
		return formatter.format(doubleDollars);
	}
		

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		
		
	}
}
