package org.academiadecodigo.altcatras65.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.altcatras65.game.room.RoomFactory;

public class Server {
    private ServerSocket serverSocket;
    private LinkedList<Room> roomList;
    private ExecutorService cachedPool;

    /** Starts a new server and invokes method listen
     *
     * @param port
     */

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            roomList = new LinkedList<>();
            cachedPool = Executors.newCachedThreadPool();
            createRoom();
            listen();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** Listens to incoming clients and accepts them
     *  Sends the clients to an existing room from roomList
     *
     */

    private void listen() {
        while(true) {
            try {
                serve(serverSocket.accept());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /** Creates a new room
     *
     */

    private void createRoom() {
        Room room = RoomFactory.createRoom();
        roomList.add(room);
        cachedPool.submit(room);

    }

    /** Serves the client to a room
     *
     *
     */

    private void serve(Socket clientSocket) {
        if(roomList.getLast().getPlayers().size() >= roomList.getLast().getMaxRoomSize()) {
            createRoom();
            serve(clientSocket);
        }

        roomList.getLast().addPlayer(clientSocket);
    }
}
