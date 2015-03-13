package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.responses.Response;

public class FireResponse extends Response {
	
	private Hunter hunter;

	public FireResponse(Hunter hunter) {
		this.hunter = hunter;
	}

	@Override
	public Object execute() {
		return hunter.nextShot();
	}

}
