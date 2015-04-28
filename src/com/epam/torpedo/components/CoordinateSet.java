package com.epam.torpedo.components;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CoordinateSet {

	private Set<Coordinate> coords = new HashSet<>();

	public boolean add(int x, int y) {
		return add(new Coordinate(x, y));
	}

	public boolean add(Coordinate coordinate) {
		return coords.add(coordinate);
	}

	public void addAll(CoordinateSet coordinateSet) {
		Iterator<Coordinate> iterator = coordinateSet.iterator();
		while (iterator.hasNext()) {
			add(iterator.next());
		}
	}

	public boolean retainAll(CoordinateSet coordinateSet) {
		Set<Coordinate> intersect = new HashSet<>();
		Iterator<Coordinate> iterator = coordinateSet.iterator();
		while (iterator.hasNext()) {
			intersect.add(iterator.next());
		}
		return coords.retainAll(intersect);
	}

	public boolean contains(int x, int y) {
		return contains(new Coordinate(x, y));
	}

	public boolean contains(Coordinate coordinate) {
		return coords.contains(coordinate);
	}

	public int size() {
		return coords.size();
	}

	public Iterator<Coordinate> iterator() {
		return coords.iterator();
	}

	public boolean isEmpty() {
		return coords.isEmpty();
	}
	
	public Coordinate pop() {
		Iterator<Coordinate> iterator = coords.iterator();
		if(!iterator.hasNext()) {
			throw new RuntimeException("CoordinateSet is empty");
		}
		Coordinate next = iterator.next();
		coords.remove(next);
		return next;
	}
	
	public void clear() {
		coords.clear();
	}

	@Override
	public String toString() {
		return "";
	}
	
}
