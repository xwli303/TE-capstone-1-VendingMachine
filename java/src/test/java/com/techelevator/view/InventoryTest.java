package com.techelevator.view;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.techelevator.Inventory;
import com.techelevator.SnacksInSlot;

public class InventoryTest {


	@Test 
	public void testInventory_InStock_shouldReturnTrue() {
		Inventory inventory = new Inventory();
		inventory.fillInventory();
		boolean expected = true;
		boolean result = inventory.checkInventory("A1");
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testInventory_outOfStock_shouldReturnFalse() {
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
	

	
}
