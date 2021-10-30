package org.academiadecodigo.altcatras65.game.room;


import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.player.Player;
import org.academiadecodigo.altcatras65.game.player.PlayerFactory;
import org.academiadecodigo.altcatras65.game.question.QuestionFactory;

import java.awt.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Room implements Runnable {

    public static final int MAX_ROOM_SIZE = 2;
    public static final int MAX_QUESTIONS = 2;

    private List<Player> players;
    private int maxQuestions;
    private int maxRoomSize;
    private ThemeType theme;

    public Room() {
        this.players = new LinkedList<>();
        this.theme = ThemeType.ALL;
        this.maxRoomSize = MAX_ROOM_SIZE;
        this.maxQuestions = MAX_QUESTIONS;
    }

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

    public void addPlayer(Socket socket) {
        this.players.add(PlayerFactory.createPlayer(socket));
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
