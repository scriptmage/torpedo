package com.epam.torpedo.components;

public class Connection {

	private static final int DEFAULT_PORT_NUMBER = 3235;
	private static final int MAXIMUM_PORT_NUMBER = 65535;
	private static final int MINIMUM_PORT_NUMBER = 1024;

	private String hostName;
	private int portNumber;

	public Connection() {
		portNumber = DEFAULT_PORT_NUMBER;
	}

	public Connection(int portNumber) {
		setPortNumber(portNumber);
	}

	public Connection(String portNumber) {
		setPortNumber(portNumber);
	}

	public Connection(String hostName, int portNumber) {
		this.hostName = hostName;
		setPortNumber(portNumber);
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		try {
			validatePortNumber(portNumber);
			this.portNumber = portNumber;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("This isn't a number: "
					+ portNumber + ". Please, give me a number.", e);
		}
	}

	public void setPortNumber(String portNumber) {
		int parsedPortNumber = Integer.parseInt(portNumber);
		validatePortNumber(parsedPortNumber);
		this.portNumber = parsedPortNumber;
	}

	private static void validatePortNumber(int portNumber) {
		if (!isValidPortNumber(portNumber)) {
			throw new IllegalArgumentException("Port number should be between "
					+ MINIMUM_PORT_NUMBER + " and " + MAXIMUM_PORT_NUMBER);
		}
	}

	private static boolean isValidPortNumber(int portNumber) {
		return portNumber > MINIMUM_PORT_NUMBER
				&& portNumber < MAXIMUM_PORT_NUMBER;
	}
	
	public boolean isServerConnection() {
		return hostName == null;
	}

}
