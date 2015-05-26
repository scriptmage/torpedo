package com.epam.torpedo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.torpedo.components.Dimension;

public class BoardTest {

  private Board underTest;

  @Before
  public void setUp() {
    underTest = new Board(new Dimension(5, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidatePositionWhenXPointLessThanZeroThrowsIllegalArgumentException() {
    // GIVEN in setup

    // WHEN
    underTest.validatePosition(-1, 1);

    // THEN thrown exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidatePositionWhenYPointLessThanZeroThrowsIllegalArgumentException() {
    // GIVEN in setup

    // WHEN
    underTest.validatePosition(1, -1);

    // THEN thrown exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidatePositionWhenXPointGreaterThanWidthOfBoardThrowsIllegalArgumentException() {
    // GIVEN in setup

    // WHEN
    underTest.validatePosition(5, 1);

    // THEN thrown exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidatePositionWhenYPointGreaterThanHeightOfBoardThrowsIllegalArgumentException() {
    // GIVEN in setup

    // WHEN
    underTest.validatePosition(1, 5);

    // THEN thrown exception
  }

  @Test
  public void testValidatePositionWhenCoordinateIsNotOutOfRangeShouldTrue() {
    // GIVEN in setup

    // WHEN
    boolean result = underTest.validatePosition(1, 1);

    // THEN
    Assert.assertTrue(result);
  }
}
