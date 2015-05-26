package com.epam.torpedo.network.protocol;

import com.epam.torpedo.network.protocol.commands.concrete.ErrorCommand;
import com.epam.torpedo.network.protocol.commands.concrete.FireCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HitCommand;
import com.epam.torpedo.network.protocol.commands.concrete.MissCommand;
import com.epam.torpedo.network.protocol.commands.concrete.QuitCommand;
import com.epam.torpedo.network.protocol.commands.concrete.SunkCommand;
import com.epam.torpedo.network.protocol.commands.concrete.WinCommand;

public class ProtocolBuilder {

	static public Command getProtocol() {
		
		Command error = new ErrorCommand();
		Command win = new WinCommand();
		Command hello = new HelloCommand();
		Command fire = new FireCommand();
		Command hit = new HitCommand();
		Command miss = new MissCommand();
		Command sunk = new SunkCommand();
		Command quit = new QuitCommand();

		error.setSuccessor(win);
		win.setSuccessor(hello);
		hello.setSuccessor(fire);
		fire.setSuccessor(hit);
		hit.setSuccessor(miss);
		miss.setSuccessor(sunk);
		sunk.setSuccessor(quit);

		return error;
	}

}