package com.techelevator.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


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
		
	
//test selectItem
	
	
	
	
	
//test return change 
	
	@Test
	public void testReturnChange_with1dollar_shouldReturn4_0_0() {
		Transaction transaction = new Transaction();
		int currentMoney = transaction.getCurrentMoney();
		int [] expected = {1 , 0 , 0};
		int [] result = transaction.returnChange();
		assertEquals(expected, result);
	}
	
	
	
//test value of input
	
}


//test sufficient fund?
