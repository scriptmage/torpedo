package com.epam.torpedo.network.protocol.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Command {

	protected Command successor;
	
	private String command;
	private List<String> params = new ArrayList<>();

	private void parseInput(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");

		if (st.hasMoreTokens()) {
			command = st.nextToken();

			params.clear();
			while (st.hasMoreTokens()) {
				params.add(st.nextToken());
			}
		}
	}

	public String getCommand(String input) {
		parseInput(input);
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