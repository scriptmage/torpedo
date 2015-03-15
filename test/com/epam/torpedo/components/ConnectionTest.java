package com.epam.torpedo.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {

	private Connection underTest;

	@Before
	public void setUp() {
		underTest = new Connection();
	}

	@Test
	public void testIsServerConnectionShouldTrue() {
		// GIVEN in setup

		// WHEN
		boolean result = underTest.isServerConnection();

		// THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testIsServerConnectionWhenSetHostnameShouldFalse() {
		// GIVEN
		underTest.setHostName("localhost");

		// WHEN
		boolean result = underTest.isServerConnection();

		// THEN
		Assert.assertFalse(result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPortNumberWhenPortNumberLessThanMinimumPortNumberThrowsIllegalArgumentException() {
		// GIVEN
		underTest.setPortNumber(1000);

		// WHEN
		// THEN thrown exception
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPortNumberWhenPortNumberGreaterThanMaximumPortNumberThrowsIllegalArgumentException() {
		// GIVEN
		underTest.setPortNumber(70000);

		// WHEN
		// THEN thrown exception
	}

	@Test
	public void testSetPortNumberWhenPortNumberBetweenMinimumAndMaximumPortNumberShouldGetPortNumber() {
		// GIVEN
		underTest.setPortNumber(Connection.DEFAULT_PORT_NUMBER);
		
		// WHEN
		int result = underTest.getPortNumber();
		
		// THEN
		Assert.assertEquals(Connection.DEFAULT_PORT_NUMBER, result);
	}

}
