package com.epam.torpedo.network.protocol;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.concrete.FireCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;

public class ProtocolFactory {

	static public Command getProtocol(BattleField battleField, Hunter hunter) {

		Command hello = new HelloCommand(battleField, hunter);
		Command fire = new FireCommand(battleField, hunter);

		hello.setSuccessor(fire);

		return hello;
	}

}