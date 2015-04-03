package com.epam.torpedo.battlefield;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.concrete.RandomBattleField;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.targets.concrete.LineShip;

public class BattleFieldTest {

	private BattleField underTest;

	@Before
	public void setUp() {
		underTest = new RandomBattleField();
		underTest.setDimension(new Dimension(10, 10));
	}

	@Test
	@Ignore
	public void testGetNumberOfShipsWhenAddedTwoShipsShouldTrue() {
		// GIVEN
		underTest.setMaxNumberOfShips(2);
		underTest.createBattleField();

		// WHEN
		int result = underTest.getNumberOfShips();

		// THEN
		Assert.assertEquals(2, result);
	}

	@Test
	@Ignore
	public void testGetShipWhenAddedOneShipShouldPositionSame() {
		// GIVEN
		Coordinate position = new Coordinate(2, 2);

		Ship ship = new LineShip(1);
		ship.setPosition(position);

		underTest.setMaxNumberOfShips(0);
		underTest.addShip(ship);

		// WHEN
		Ship result = underTest.getShip(position);

		// THEN
		Assert.assertEquals(position.getX(), result.getPositionX());
		Assert.assertEquals(position.getY(), result.getPositionY());
	}

}
