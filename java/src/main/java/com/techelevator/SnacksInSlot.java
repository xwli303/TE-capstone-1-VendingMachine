package com.techelevator;

public class SnacksInSlot {
	private String name;
	private int price;  //pennies
	private String slotNumber;
	private String type; //chips, candy, gum, drink
	private int numberRemaining; //derived?
	
	private static final int startingQuantity = 5;      //remove if not needed
	
	//constructors
	public SnacksInSlot (String name, int price, String slotNumber, String type) {
		this.name = name;
		this.price = price;
		this.slotNumber = slotNumber;
		this.type = type;
		this.numberRemaining = startingQuantity;
	}


	
	//setters
	public void setName (String name) {
		this.name = name;
	}
	public void setPrice (int price) {
		this.price = price;
	}
	public void setSlotNumber (String slotNumber) {
		this.slotNumber = slotNumber;
	}
	public void setType (String type) {
		this.type = type;
	}
	public void setNumberRemaining (int numberUsed) {
		this.numberRemaining = numberRemaining - numberUsed;
	}
	
	//getters
	public String getName () {
		return name;
	}
	public int getPrice () {
		return price;
	}
	public String getSlotNumber () {
		return slotNumber;
	}
	public String getType () {
		return type;
	}
	public int getNumberRemaining () {
		return numberRemaining;
	}
}
