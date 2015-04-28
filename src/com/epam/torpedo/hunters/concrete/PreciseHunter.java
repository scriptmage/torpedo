package com.epam.torpedo.hunters.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;

public class PreciseHunter extends Hunter {

	CoordinateSet targetPoints = new CoordinateSet();
	
	@Override
	public Coordinate nextShot() {
		Coordinate coordinate;
		
		if(targetPoints.isEmpty()) {
			do {
				int width = dimension.getWidth();
				int height = dimension.getHeight();
				coordinate = Coordinate.getRandomPosition(width, height);
			} while (isExists(coordinate));
			setLastShot(coordinate);
		} else {
			coordinate = targetPoints.pop();
		}
		
		addShot(coordinate);
		return coordinate;
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		targetPoints.add(coordinate);
	}
	
	@Override
	public void clear() {
		addShots(targetPoints);
		targetPoints.clear();
	}

}
