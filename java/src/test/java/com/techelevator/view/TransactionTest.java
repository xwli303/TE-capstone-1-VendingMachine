package com.techelevator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.techelevator.Inventory;
import com.techelevator.SnacksInSlot;
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
	public void testReturnChange_25centChange_shouldReturnQuarter() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 1 quarter\r";
		String result = transaction.returnChange(25);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_20centChange_shouldReturn2Dimes() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 2 dimes\r";
		String result = transaction.returnChange(20);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_10centChange_shouldReturn1Dime() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 1 dime\r";
		String result = transaction.returnChange(10);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_5centChange_shouldReturn1Nicke() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 1 nickel\r";
		String result = transaction.returnChange(5);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_65centChange_shouldReturn2Q1D1N() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 2 quarters\r 1 dime\r 1 nickel\r";
		String result = transaction.returnChange(65);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_80centChange_shouldReturn3Q1N() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 3 quarters\r 1 nickel\r";
		String result = transaction.returnChange(80);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_85centChange_shouldReturn3Q1D() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 3 quarters\r 1 dime\r";
		String result = transaction.returnChange(85);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReturnChange_15centChange_shouldReturn1D1N() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		String expected = "Your change is:\r 1 dime\r 1 nickel\r";
		String result = transaction.returnChange(15);
		assertEquals(expected, result);
	}
	
	
//test value of input
	@Test
	public void testValueOfInput_FiveDollars_shouldReturn500() {
		Transaction transaction = new Transaction();
		int expected = 500;
		int result = transaction.valueOfInput("$5");
		assertEquals(expected,result);
	}
	
	@Test
	public void testValueOfInput_InvalidOneHundredDollars_shouldReturnZero() {
		Transaction transaction = new Transaction();
		int expected = 0;
		int result = transaction.valueOfInput("$100");
		assertEquals(expected,result);
	}
	

	
	
	//test sufficient fund
	@Test
	public void testsufficientFund_enough_shouldReturnTrue () throws TransactionException{
		Transaction transaction = new Transaction();
		transaction.moreMoney("$10");
		SnacksInSlot snack = new SnacksInSlot("Potato Crisps",305,"A1", "Chip");
		boolean expected = true;
		boolean result = transaction.checkSufficientFund(snack);
		assertEquals(expected, result);
	}
	
	@Test
	public void testsufficientFund_notEnough_shouldReturnFalse () throws TransactionException{
		Transaction transaction = new Transaction();
		transaction.moreMoney("$1");
		transaction.moreMoney("$1");
		SnacksInSlot snack = new SnacksInSlot("Potato Crisps",305,"A1", "Chip");
		boolean expected = false;
		boolean result = transaction.checkSufficientFund(snack);
		assertEquals(expected, result);
	}
	

}



