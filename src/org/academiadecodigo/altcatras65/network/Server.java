package org.academiadecodigo.altcatras65.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class Server {
    private ServerSocket serverSocket;
    private LinkedList roomList;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** Listens to incoming clients and accepts them
     *
     *
     */

    private void listen() {
        while(true) {

        }
    }
}
