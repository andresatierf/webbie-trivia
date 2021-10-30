package org.academiadecodigo.altcatras65.game.player;

import java.awt.*;
import java.net.Socket;

public class Player implements Runnable {
    private Socket playerSocket;
    private String name;
    private Color color;
    private PlayerType playerType;
    private int score;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    @Override
    public void run() {

    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
