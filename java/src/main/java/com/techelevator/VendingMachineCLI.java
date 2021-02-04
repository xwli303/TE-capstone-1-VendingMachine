package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	


	public void run() {
		Inventory inventory = new Inventory ();
		inventory.fillInventory();
		Transaction purchase = new Transaction();
		
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			//choice is the user's input of slot
			
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.displayChoices();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//do transaction
				purchase.manageTransaction();
			}
		}
	}

	public static void main(String[] args) {
		
		
		//System.out.println(tempName);
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		
		
	}
}
