package org.academiadecodigo.altcatras65.game.room;

import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.player.Player;
import org.academiadecodigo.altcatras65.game.player.PlayerFactory;
import org.academiadecodigo.altcatras65.game.question.QuestionFactory;

import java.awt.*;
import java.net.Socket;
import java.util.List;

public class Room implements Runnable {
    private List<Player> players;
    private int maxQuestions;
    private int maxRoomSize;
    private ThemeType theme;

    public void init() {

    }

    public void start() {

    }


    private void nextQuestion() {

    }

    private void awaitAnswers() {

    }

    private void getQuestions() {
        QuestionFactory.createQuestions(theme);
    }

    public void addPlayer(Socket socket, String name, Color color) {
        PlayerFactory.createPlayer(socket, name, color);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getMaxRoomSize() {
        return maxRoomSize;
    }

    @Override
    public void run() {

    }
}
