package com.epam.torpedo.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoordinateSetTest {

	private CoordinateSet underTest;

	@Before
	public void setUp() {
		underTest = new CoordinateSet();
	}

	@Test
	public void testIsEmptyWithoutElementsShouldTrue() {
		// GIVEN in setup
		// WHEN
		boolean result = underTest.isEmpty();
		// THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testIsEmptyWhenAnElementAddedShouldFalse() {
		// GIVEN
		underTest.add(10, 10);
		// WHEN
		boolean result = underTest.isEmpty();
		// THEN
		Assert.assertFalse(result);
	}

	@Test
	public void testContainsWhenSetNotContainTheElementShouldFalse() {
		// GIVEN in setup
		// WHEN
		boolean result = underTest.contains(10, 10);
		// THEN
		Assert.assertFalse(result);
	}

	@Test
	public void testContainsWhenSetContainTheElementShouldTrue() {
		// GIVEN
		underTest.add(10, 10);
		// WHEN
		boolean result = underTest.contains(10, 10);
		// THEN
		Assert.assertTrue(result);
	}

	@Test
	public void testAddWhenAddedTwoElementShouldSizeTwo() {
		// GIVEN
		underTest.add(5, 5);
		underTest.add(10, 10);
		// WHEN
		int result = underTest.size();
		// THEN
		Assert.assertEquals(2, result);
	}

	@Test
	public void testSizeWhenNoAddedElementsShouldSizeZero() {
		// GIVEN in setup
		// WHEN
		int result = underTest.size();
		// THEN
		Assert.assertEquals(0, result);
	}

	@Test
	public void testAddAllWhenAddedASetOfElementsWithThreeCoordinateShouldContainsAllElements() {
		// GIVEN
		CoordinateSet coordinateSet = new CoordinateSet();
		coordinateSet.add(0, 0);
		coordinateSet.add(5, 5);
		coordinateSet.add(10, 10);
		// WHEN
		underTest.addAll(coordinateSet);
		// THEN
		Assert.assertTrue(underTest.contains(0, 0));
		Assert.assertTrue(underTest.contains(5, 5));
		Assert.assertTrue(underTest.contains(10, 10));
	}
	
	@Test
	public void testRetainAll() {
		// GIVEN
		underTest.add(0, 0);
		underTest.add(5, 5);
		underTest.add(10, 10);

		CoordinateSet coordinateSet = new CoordinateSet();
		coordinateSet.add(0, 0);
		coordinateSet.add(5, 5);
		// WHEN
		boolean result = underTest.retainAll(coordinateSet);
		// THEN
		Assert.assertTrue(result);
		Assert.assertTrue(underTest.contains(0, 0));
		Assert.assertTrue(underTest.contains(5, 5));
		Assert.assertFalse(underTest.contains(10, 10));
	}
	
}
