package com.epam.torpedo.network.protocol;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.concrete.ErrorCommand;
import com.epam.torpedo.network.protocol.commands.concrete.FireCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HitCommand;
import com.epam.torpedo.network.protocol.commands.concrete.LastCommand;
import com.epam.torpedo.network.protocol.commands.concrete.MissCommand;
import com.epam.torpedo.network.protocol.commands.concrete.SunkCommand;
import com.epam.torpedo.network.protocol.commands.concrete.WinCommand;

public class ProtocolFactory {

	static public Command getProtocol(BattleField battleField, Hunter hunter) {

		Command error = new ErrorCommand();
		Command win = new WinCommand();
		Command hello = new HelloCommand(battleField, hunter);
		Command fire = new FireCommand(battleField, hunter);
		Command hit = new HitCommand();
		Command miss = new MissCommand();
		Command sunk = new SunkCommand();
		Command last = new LastCommand();

		error.setSuccessor(win);
		win.setSuccessor(hello);
		hello.setSuccessor(fire);
		fire.setSuccessor(hit);
		hit.setSuccessor(miss);
		miss.setSuccessor(sunk);
		sunk.setSuccessor(last);

		return error;
	}

}