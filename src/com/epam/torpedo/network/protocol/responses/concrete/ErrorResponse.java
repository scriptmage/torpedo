package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.network.protocol.responses.Response;

public class ErrorResponse extends Response {

	private static final String COMMAND_NAME = "ERROR";
	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	@Override
	public String get() {
		return String.format("%s %s", COMMAND_NAME, message);
	}

}
