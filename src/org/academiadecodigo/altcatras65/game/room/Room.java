package org.academiadecodigo.altcatras65.game.room;


import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.player.Player;
import org.academiadecodigo.altcatras65.game.player.PlayerFactory;
import org.academiadecodigo.altcatras65.game.player.PlayerType;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.question.QuestionFactory;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Room implements Runnable {

    public static final int DEFAULT_ROOM_SIZE = 1;
    public static final int DEFAULT_MAX_QUESTIONS = 2;

    private ExecutorService playerPool;

    private List<Player> players;
    private int maxQuestions;
    private int maxRoomSize;
    private ThemeType theme;
    private boolean gameStarted;

    public Room() {
    }

    public void init() {
        this.players = new LinkedList<>();
        this.theme = ThemeType.ALL;
        this.maxRoomSize = DEFAULT_ROOM_SIZE;
        this.maxQuestions = DEFAULT_MAX_QUESTIONS;
        this.playerPool = Executors.newFixedThreadPool(DEFAULT_ROOM_SIZE);
        this.gameStarted = false;
    }

    public void start() {

        // wait for players
        awaitPlayers();

        // wait start
        awaitGameStart();

        List<Question> questions = getQuestions();
        int currentQuestionIndex = 0;

        // loop
        while (gameStarted) {

            Question question = questions.get(currentQuestionIndex);

            for (Player player : players) {
                player.sendQuestion(question);
            }

            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //currentQuestionIndex++;

        }

    }

    private void awaitGameStart() {
        while (!gameStarted) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startGame();
        }
    }

    private void awaitPlayers() {
        while (this.players.size() < this.maxRoomSize) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void nextQuestion(int questionIndex) {


    }

    private void awaitAnswers() {

    }

    private List<Question> getQuestions() {
        return QuestionFactory.createQuestions(theme);
    }

    public synchronized void addPlayer(Socket socket) {
        Player player = PlayerFactory.createPlayer(socket);
        player.setPlayerType(this.players.isEmpty() ? PlayerType.ADMIN : PlayerType.NORMAL);
        player.setRoom(this);
        this.players.add(player);
        this.playerPool.submit(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getMaxRoomSize() {
        return maxRoomSize;
    }

    public void startGame() {
        this.gameStarted = true;
        for (Player player : this.players) {
            player.setGameStarted(true);
        }
    }

    @Override
    public void run() {
        // setup room
        init();
        start();

    }
}
