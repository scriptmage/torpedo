package com.epam.torpedo.exceptions;

public class InvalidShipPositionException extends RuntimeException {

	private static final long serialVersionUID = -7959745139965948965L;

	public InvalidShipPositionException() {
		super("Invalid ship position");
	}

	public InvalidShipPositionException(String message) {
		super(message);
	}

}
