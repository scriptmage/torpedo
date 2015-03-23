package com.epam.torpedo.network.protocol.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandQueue {

	private List<Command> queue = new ArrayList<>();

	public void add(Command command) {
		queue.add(command);
	}

	public Collection<Command> get() {
		return Collections.unmodifiableCollection(queue);
	}

	public Iterator<Command> iterator() {
		return queue.iterator();
	}
	
	public void clear() {
		queue.clear();
	}
	
	public boolean isRunnable() {
		boolean hasRunnable = true;
		if(queue.size() == 1) {
			Command lastCommand = queue.get(queue.size() - 1);
			hasRunnable = lastCommand.isRunnable();
		}
		return hasRunnable;
	}

	@Override
	public String toString() {
		String result = "";
		Iterator<Command> iterator = queue.iterator();

		if (iterator.hasNext()) {
			result = iterator.next().toString();
		}

		while (iterator.hasNext()) {
			result += "\n" + iterator.next().toString();
		}

		return result;
	}

}
