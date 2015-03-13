package com.epam.torpedo.network.protocol.responses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResponseSet {

	private List<Response> queue = new ArrayList<>();
	
	public void add(Response response) {
		queue.add(response);
	}
	
	public Iterator<Response> iterator() {
		return queue.iterator();
	}
	
	public int size() {
		return queue.size();
	}
}
