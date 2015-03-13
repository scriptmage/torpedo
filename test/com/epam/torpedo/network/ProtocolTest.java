package com.epam.torpedo.network;
import org.junit.Assert;
import org.junit.Test;

import com.epam.torpedo.network.protocol.Protocol;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.Response;

public class ProtocolTest {

	@Test
	public void testProtocol() {
		Command command = Protocol.getProtocol();
		Response result = command.handle("HELLO 10 20");
		System.out.println(result);
		Assert.assertEquals(result.toString(), "HIT");
	} 
	
}
