package com.techelevator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.techelevator.Inventory;
import com.techelevator.Transaction;
import com.techelevator.TransactionException;

public class TransactionTest {

//test more money
	@Test
	public void testMoreMoney_insert10_shouldReturn_rightAmount() throws TransactionException {
		Transaction transaction = new Transaction();
		int expected = 1000;
		int result = transaction.moreMoney("$10");
		assertEquals(expected, result);
	}
	
	
	public void testMoreMoney_insert4_shouldReturnException()  {
		Transaction transaction = new Transaction();
		int expected = 1000;
		try { transaction.moreMoney("$4"); 
		
		} catch (TransactionException e) {	
		}
	}
		

	
//test return change 
	
	@Test
	public void testReturnChange_with1dollar_shouldReturn4_0_0() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 1 quarter\r";
		String result = transaction.returnChange(25);
		assertEquals(expected, result);
	}
	
	
	@Test 
	public void testInventory_shouldReturnTrue() {
		Inventory inventory = new Inventory();
		inventory.fillInventory();
		boolean expected = true;
		boolean result = inventory.checkInventory("A1");
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testInventory_shouldReturnOOS() {
		Inventory inventory = new Inventory();
		inventory.fillInventory();
		inventory.removeSnackFromSlot("A1");
		inventory.removeSnackFromSlot("A1");
		inventory.removeSnackFromSlot("A1");
		inventory.removeSnackFromSlot("A1");
		inventory.removeSnackFromSlot("A1");
		boolean expected = false;
		boolean result = inventory.checkInventory("A1");
		assertEquals(expected, result);
	}
//test value of input
	
}


//test sufficient fund?
