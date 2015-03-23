package com.epam.torpedo.network.protocol.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Command {

	protected Command successor;
	
	private String command;
	private List<String> params = new ArrayList<>();

	public void initCommand(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");

		if (st.hasMoreTokens()) {
			command = st.nextToken();

			params.clear();
			while (st.hasMoreTokens()) {
				params.add(st.nextToken());
			}
		}
	}
	
	public boolean isEqual(String commandName) {
		if(command == null) {
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

	abstract public CommandQueue getResponse(String input);
}