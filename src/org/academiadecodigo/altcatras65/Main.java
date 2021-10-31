package org.academiadecodigo.altcatras65;

import org.academiadecodigo.altcatras65.network.Server;

public class Main {

    public static final int DEFAULT_PORT = 8090;

    public static void main(String[] args) {

        try {

            int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
            System.out.println("Server now listening on port: " + port + " (for another port use: Webbie-Trivia [PORT])");

            Server server = new Server(port);

        } catch (NumberFormatException e) {

            System.err.println("Usage: Webbie-Trivia [PORT]");
            System.exit(1);

        }
    }
}
