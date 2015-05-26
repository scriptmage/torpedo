package com.epam.torpedo.network;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectionDataTest {

	private ConnectionData underTest;

	@Before
	public void setUp() {
		underTest = new ConnectionData();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPortNumberWhenPortNumberIsNotANumberStringThrowsException() {
		// GIVEN in setup
		// WHEN
		underTest.setPortNumber("abc");
		// THEN thrown exception
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPortNumberWhenPortNumberIsLessThanMinimumProtNumberRangeThrowsException() {
		// GIVEN in setup
		// WHEN
		underTest.setPortNumber(1000);
		// THEN thrown exception
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPortNumberWhenPortNumberIsGreaterThanMaximumProtNumberRangeThrowsException() {
		// GIVEN in setup
		// WHEN
		underTest.setPortNumber(70000);
		// THEN thrown exception
	}

	@Test
	public void testIsServerConnectionWithoutHostnameSetInShouldTrue() {
		// GIVEN in setup
		// WHEN
		boolean result = underTest.isServerConnection();
		// THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testIsServerConnectionWhenHostnameIsEmptyStringShouldTrue() {
		// GIVEN
		underTest.setHostName("");
		// WHEN
		boolean result = underTest.isServerConnection();
		// THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testIsServerConnectionWhenHostnameSetInShouldFalse() {
		// GIVEN
		underTest.setHostName("localhost");
		// WHEN
		boolean result = underTest.isServerConnection();
		// THEN
		Assert.assertFalse(result);
	}
}
