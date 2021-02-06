package com.techelevator.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.techelevator.VendingMachineCLI;

public class VendingMachineTest {

//get sound test
	@Test
	public void testGetSound_withDrink_shouldReturnMessage() {
		VendingMachineCLI vendingMachine = new VendingMachineCLI();
		
		String expected = "Glug glug, Yum!";
		String result = vendingMachine.getSnackSound("Drink");
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testGetSound_withEmpty_shouldReturnEmpty() {
		VendingMachineCLI vendingMachine = new VendingMachineCLI();
		
		String expected = "";
		String result = vendingMachine.getSnackSound("");
		assertEquals(expected, result);
	}
	

//pennies to dollars test
	@Test
	public void testPenniesToDollars_100_shouldReturn1() {
		VendingMachineCLI vendingMachine = new VendingMachineCLI();
		
		String expected = "$1.00";
		String result = vendingMachine.penniesToDollars(100);
		assertEquals(expected, result);
	}
	
	@Test
	public void testPenniesToDollars_50_shouldReturnpoint5() {
		VendingMachineCLI vendingMachine = new VendingMachineCLI();
		
		String expected = "$0.50";
		String result = vendingMachine.penniesToDollars(50);
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testPenniesToDollars_with0_shouldReturnZero() {
		VendingMachineCLI vendingMachine = new VendingMachineCLI();
		
		String expected = "$0.00";
		String result = vendingMachine.penniesToDollars(0);
		assertEquals(expected, result);
	}
	
	
	
}