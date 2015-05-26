package com.epam.torpedo;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class HunterTest {

  private Hunter underTest;

  @Before
  public void setUp() {
    underTest = new Hunter() {

      @Override
      public void setPosition(Coordinate coordinate) {
      }

      @Override
      public Coordinate nextShot() {
        return null;
      }

      @Override
      public void clear() {
      }
    };
    underTest.setShotsContainer(new CoordinateSet());
  }

  @Test
  public void testGetLastShotWithShots() {
    // GIVEN
    underTest.addShot(15, 20);
    underTest.addShot(5, 10);

    // WHEN
    Coordinate result = underTest.getLastShot();

    // THEN
    Assert.assertEquals(5, result.getX());
    Assert.assertEquals(10, result.getY());
  }

  @Test
  public void testGetLastShotWithoutShotsShouldReturnNull() {
    // GIVEN in setup

    // WHEN
    Coordinate result = underTest.getLastShot();

    // THEN
    Assert.assertNull(result);
  }

  @Test
  public void testIsExistsWhenShotsDoesNotContainCheckedPositionOfShotShouldReturnFalse() {
    // GIVEN
    underTest.addShot(5, 5);

    // WHEN
    boolean result = underTest.isExists(10, 10);

    // THEN
    Assert.assertFalse(result);
  }

  @Test
  public void testIsExistsWithoutShotsShouldReturnFalse() {
    // GIVEN in setup

    // WHEN
    boolean result = underTest.isExists(10, 10);

    // THEN
    Assert.assertFalse(result);
  }

  @Test
  public void testIsExistsWhenCheckedPositionExistsShouldReturnTrue() {
    // GIVEN
    underTest.addShot(5, 5);

    // WHEN
    boolean result = underTest.isExists(5, 5);

    // THEN
    Assert.assertTrue(result);
  }

  @Test
  public void testAddShot() {
    // GIVEN
    CoordinateSet shotsMock = Mockito.mock(CoordinateSet.class);
    underTest.setShotsContainer(shotsMock);

    Coordinate shot = new Coordinate(5, 5);

    // WHEN
    underTest.addShot(shot);

    // THEN
    Mockito.verify(shotsMock).add(shot);
  }

  @Test
  public void testAddShots() {
    // GIVEN
    CoordinateSet shotsMock = Mockito.mock(CoordinateSet.class);
    underTest.setShotsContainer(shotsMock);

    CoordinateSet setOfShots = new CoordinateSet();
    setOfShots.add(5, 5);
    setOfShots.add(10, 10);
    setOfShots.add(15, 15);

    // WHEN
    underTest.addShots(setOfShots);

    // THEN
    Mockito.verify(shotsMock).addAll(setOfShots);
  }

  @Test
  public void testGetShotsWhenAddedTwoShots() {
    // GIVEN
    Coordinate coordinate1 = new Coordinate(5, 5);
    Coordinate coordinate2 = new Coordinate(10, 10);
    underTest.addShot(coordinate1);
    underTest.addShot(coordinate2);

    // WHEN
    CoordinateSet result = underTest.getShots();

    // THEN
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(result.contains(coordinate1));
    Assert.assertTrue(result.contains(coordinate2));
  }

}
