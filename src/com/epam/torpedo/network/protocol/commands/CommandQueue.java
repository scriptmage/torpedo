package com.epam.torpedo.network.protocol.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CommandQueue {

	private List<Command> queue = new ArrayList<>();

	public void add(Command command) {
		queue.add(command);
	}

	public Collection<Command> get() {
		return Collections.unmodifiableCollection(queue);
	}
	
	public int size() {
		return queue.size();
	}

}
