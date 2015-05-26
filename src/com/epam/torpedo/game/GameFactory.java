package com.epam.torpedo.game;

import com.epam.torpedo.Startable;
import com.epam.torpedo.game.concrete.LocalGame;
import com.epam.torpedo.game.concrete.SocketGame;
import com.epam.torpedo.network.ConnectionData;
import com.epam.torpedo.resolvers.Resolver;

public class GameFactory {

  public static Startable createGame(String[] args) {
    Resolver resolver = GameConfig.getResolver();
    String gameMode = resolver.get("game");

    Startable result = null;
    switch (gameMode.trim()) {
      case "socket":
        result = new SocketGame();
        ConnectionData connectionData = createConnection(args);
        ((SocketGame) result).setConnection(connectionData);
        break;
      case "local":
        result = new LocalGame();
        break;
      default:
        throw new IllegalArgumentException("Unknown game mode: " + gameMode + "! Use the following: local, socket");
    }
    return result;
  }

  private static ConnectionData createConnection(String[] args) {
    ConnectionData connection = new ConnectionData();
    switch (args.length) {
      case 0:
        System.out.println(String.format("Starting server on %d port", connection.getPortNumber()));
        break;
      case 1:
        connection.setPortNumber(args[0]);
        System.out.println(String.format("Starting server on %d port", connection.getPortNumber()));
        break;
      case 2:
        connection.setHostName(args[0]);
        connection.setPortNumber(args[1]);
        System.out.println(String.format("Connect to %s on %d port", connection.getHostName(), connection.getPortNumber()));
        break;
      default:
        System.err.println("Please, give me a port number for server mode, or hostname and port number for client mode");
        System.err.println("Server mode: torpedo <port number>");
        System.err.println("Client mode: torpedo <hostname> <port number>");
        System.exit(1);
    }
    return connection;
  }

}
