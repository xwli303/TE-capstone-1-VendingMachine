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

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
	}
	

	public void run() {
		inventory.fillInventory();
		Scanner userInput = new Scanner (System.in);
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
						int output;
						System.out.println("You chose to Deposit Money");
						System.out.println("How much? ( $1, $2, $5, $ 10 )");
						String amount = userInput.nextLine();
						
						System.out.println("Money in machine: "  + transaction.moreMoney(amount));
					
					}
					else if (action.equals(PURCHASE_MENU_SELECT)) {
						//select item
						System.out.println("you chose to Select and item");
					}
					else if (action.equals(PURCHASE_MENU_EXIT)) {
						//exit purchase menu
						isSelect = false;
					}
				}                     //END SELECT ITEMS MENU
				
			}
			else if (choice.contentEquals(MAIN_MENU_OPTIONS_EXIT)) {
				loop = false;
				System.out.println("bye bye");
			}
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		
		
	}
}
