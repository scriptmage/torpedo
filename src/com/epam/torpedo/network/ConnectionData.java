package com.epam.torpedo.network;

public class ConnectionData {

	public static final int DEFAULT_PORT_NUMBER = 3235;
	public static final int MAXIMUM_PORT_NUMBER = 65535;
	public static final int MINIMUM_PORT_NUMBER = 1024;

	private String hostName;
	private int portNumber;

	public ConnectionData() {
		portNumber = DEFAULT_PORT_NUMBER;
	}

	public ConnectionData(int portNumber) {
		setPortNumber(portNumber);
	}

	public ConnectionData(String portNumber) {
		setPortNumber(portNumber);
	}

	public ConnectionData(String hostName, int portNumber) {
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
		validatePortNumber(portNumber);
		this.portNumber = portNumber;
	}

	public void setPortNumber(String portNumber) {
		try {
			setPortNumber(Integer.parseInt(portNumber));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("This isn't a number: "
					+ portNumber + ". Please, give me a number.", e);
		}
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
