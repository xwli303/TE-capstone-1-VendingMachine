package com.techelevator;

import java.util.Scanner;

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
	boolean loop = true;
	boolean isSelect = true;
	Scanner userInput = new Scanner (System.in);
	
	
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
	}
	

	public void run() {
		inventory.fillInventory();
		
		while (loop) {
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
						//exit purchase menu
						isSelect = false;
						//return change if any
					}
				}                     //END SELECT ITEMS MENU
				
			}
			else if (choice.contentEquals(MAIN_MENU_OPTIONS_EXIT)) {
				loop = false;
				System.out.println("bye bye");
			}
		}
	}
	
	
	public void depositPrompt() {
		int output;
		System.out.println("You chose to Deposit Money");
		System.out.println("How much? ( $1, $2, $5, $ 10 )");
		String amount = userInput.nextLine();
		
		try {
			System.out.println("Money in machine: "  + transaction.moreMoney(amount));
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n Money in machine: "  + transaction.getCurrentMoney() + ".");
		}
	
	}
	
	public String transactionPrompt() {
		//check inventory for availability, else return SOLD OUT
		//if available then check if sufficient fund
		//if both are true, return selected item
		//
		inventory.displayChoices();
		String selection;   // this is the key
		int moneyLeft;
		String type;
		System.out.println("Which item would you like? A1, A2, etc.");
		selection = userInput.nextLine();
		SnacksInSlot selectedItem = inventory.getSnackInslot(selection);
		System.out.println(selectedItem.getName());
		System.out.println(selectedItem.getPrice());
		//System.out.println("Is this correct?");
		
		if (!inventory.checkInventory(selection)) {
			return "SOLD OUT";
		} 
		
		if (!transaction.checkSufficientFund(selectedItem)) {
			return "INSUFFICIENT FUNDS";	
			
		} 
		inventory.removeSnackFromSlot(selection);
		
		transaction.subtractCostOfItem(selectedItem.getPrice());
		System.out.println("Money remaining: " + transaction.getCurrentMoney());
		type = selectedItem.getType();
		System.out.println(type);
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
	
	

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		
		
	}
}
