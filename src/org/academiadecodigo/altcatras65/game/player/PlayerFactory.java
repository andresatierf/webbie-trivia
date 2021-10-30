package org.academiadecodigo.altcatras65.game.player;

import java.awt.*;
import java.net.Socket;

public class PlayerFactory {

    public static Player createPlayer(Socket playerSocket) {
        return new Player(playerSocket);
    }
}
