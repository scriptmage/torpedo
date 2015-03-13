package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.network.protocol.responses.Response;

public class HitResponse extends Response {

	private static final String COMMAND_NAME = "HIT";

	@Override
	public String get() {
		return COMMAND_NAME;
	}

}
