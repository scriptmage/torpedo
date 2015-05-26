package com.epam.torpedo.network.protocol;

import com.epam.torpedo.network.protocol.commands.CommandQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Command {

  protected Command    successor;

  private String       command;
  private List<String> params        = new ArrayList<>();
  private CommandQueue responseQueue = new CommandQueue();
  private boolean      hasRunnable   = true;
  private boolean      hasSendable   = true;

  public void initCommand(String input) {
    StringTokenizer st = new StringTokenizer(input, " ");

    if (st.hasMoreTokens()) {
      command = st.nextToken();

      params.clear();
      while (st.hasMoreTokens()) {
        params.add(st.nextToken());
      }
    }
    responseQueue.clear();
  }

  public void addResponse(Command response) {
    responseQueue.add(response);
  }

  public CommandQueue getResponseQueue() {
    CommandQueue commandQueue = new CommandQueue();
    commandQueue.addAll(responseQueue);
    return commandQueue;
  }

  public boolean isCommand(String commandName) {
    if (command == null) {
      throw new IllegalStateException("Please, first init the command");
    }
    return command.equals(commandName);
  }

  public String getCommand() {
    return command;
  }

  public Object[] getParams() {
    return params.toArray();
  }

  public void setSuccessor(Command successor) {
    this.successor = successor;
  }

  public boolean isRunnable() {
    return hasRunnable;
  }

  public void runnableOff() {
    hasRunnable = false;
  }

  public boolean isSendable() {
    return hasSendable;
  }

  public void sendableOff() {
    hasSendable = false;
  }

  public abstract CommandQueue getResponse(String input);
}