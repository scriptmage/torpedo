package com.epam.torpedo;

import com.epam.torpedo.components.CoordinateSet;

public interface Drawable {
	void draw(CoordinateSet pointsOfShip, Hunter hunter);
}
