package com.epam.torpedo.protocols;

import java.util.StringTokenizer;

import com.epam.torpedo.Protocol;

public class FirstProtocol implements Protocol {

	@Override
	public String processInput(String input) {
		String answer = "ERROR PROTOCOL";
		StringTokenizer command = new StringTokenizer(input.toUpperCase(), " ");

		switch (command.nextToken()) {
		case "FIRE":
			break;
		}

		System.out.println("Input: " + input);
		System.out.println("Answer: " + answer);
		return answer;
	}

}
