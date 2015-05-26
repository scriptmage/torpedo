package com.epam.torpedo.network.protocol;

import com.epam.torpedo.network.protocol.commands.concrete.ErrorCommand;
import com.epam.torpedo.network.protocol.commands.concrete.FireCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;
import com.epam.torpedo.network.protocol.commands.concrete.HitCommand;
import com.epam.torpedo.network.protocol.commands.concrete.MissCommand;
import com.epam.torpedo.network.protocol.commands.concrete.QuitCommand;
import com.epam.torpedo.network.protocol.commands.concrete.SunkCommand;
import com.epam.torpedo.network.protocol.commands.concrete.WinCommand;

import java.util.ArrayList;
import java.util.List;

public class ProtocolBuilder {

  public static Command getProtocol() {

    List<Command> orderOfCommands = new ArrayList<Command>() {
      {
        add(new ErrorCommand());
        add(new WinCommand());
        add(new HelloCommand());
        add(new FireCommand());
        add(new HitCommand());
        add(new MissCommand());
        add(new SunkCommand());
        add(new QuitCommand());
      }
    };

    for (int i = 1; i < orderOfCommands.size(); i++) {
      Command command = orderOfCommands.get(i - 1);
      command.setSuccessor(orderOfCommands.get(i));
    }

    return orderOfCommands.get(0);
  }

}