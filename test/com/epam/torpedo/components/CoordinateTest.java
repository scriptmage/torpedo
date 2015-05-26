package com.epam.torpedo.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

  private Coordinate underTest;

  @Before
  public void setUp() {
    underTest = new Coordinate(5, 5);
  }

  @Test
  public void testHashCodeWithTwoSameCoordinate() {
    // GIVEN
    Coordinate otherCoordinate = new Coordinate(5, 5);

    // WHEN

    // THEN
    Assert.assertEquals(otherCoordinate.hashCode(), underTest.hashCode());
  }

  @Test
  public void testHashCodeWithTwoDifferentCoordinate() {
    // GIVEN
    Coordinate otherCoordinate = new Coordinate(1, 1);

    // WHEN

    // THEN
    Assert.assertNotEquals(otherCoordinate.hashCode(), underTest.hashCode());
  }

  @Test
  public void testEqualsWithTwoSameCoordinateShouldEquals() {
    // GIVEN
    Coordinate otherCoordinate = new Coordinate(5, 5);

    // WHEN

    // THEN
    Assert.assertEquals(otherCoordinate, underTest);
  }

  @Test
  public void testEqualsWithTwoDifferentCoordinateShouldNoEquals() {
    // GIVEN
    Coordinate otherCoordinate = new Coordinate(1, 1);

    // WHEN

    // THEN
    Assert.assertNotEquals(otherCoordinate, underTest);
  }

}
