package org.academiadecodigo.altcatras65.game.player;

import java.awt.*;
import java.net.Socket;

public class PlayerFactory {

    public static Player createPlayer(Socket playerSocket, String name, Color color) {
        return new Player(playerSocket, name, color);
    }
}
