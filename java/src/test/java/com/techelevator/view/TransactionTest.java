package com.techelevator.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.techelevator.Transaction;
import com.techelevator.TransactionException;

public class TransactionTest {

	@Test
	public void testMoreMoney_insert10_shouldReturn_rightAmount() throws TransactionException {
		Transaction transaction = new Transaction();
		int expected = 1000;
		int result = transaction.moreMoney("$10");
		assertEquals(expected, result);
	}
	
	@Test
	public void testMoreMoney_insertInvalidValue_shouldReturnError() throws TransactionException {
		Transaction transaction = new Transaction();
		String expected = "Invalid input amount, please enter "
				+ "in amounts of $1, $2, $5, or $10.";
		int result = transaction.moreMoney("$4");
		assertEquals(expected, result);
	}
	
}
