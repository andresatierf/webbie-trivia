package org.academiadecodigo.altcatras65;

import org.academiadecodigo.altcatras65.network.Server;

public class Main {

    public static final int DEFAULT_PORT = 8090;

    public static void main(String[] args) {

        try {

            int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

            Server server = new Server(port);
            server.listen();

        } catch (NumberFormatException e) {

            System.err.println("Usage: Buzz [PORT]");
            System.exit(1);

        }
    }
}
