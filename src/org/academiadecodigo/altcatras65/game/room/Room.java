package org.academiadecodigo.altcatras65.game.room;


import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.player.Player;
import org.academiadecodigo.altcatras65.game.player.PlayerFactory;
import org.academiadecodigo.altcatras65.game.player.PlayerType;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.question.QuestionFactory;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Room implements Runnable {

    public static final int DEFAULT_ROOM_SIZE = 2;
    public static final int DEFAULT_MAX_QUESTIONS = 2;

    private ExecutorService playerPool;

    private List<Player> players;
    private int maxQuestions;
    private int maxRoomSize;
    private ThemeType theme;
    private boolean gameStarted;
    private List<Player> roundAttempts;

    public Room() {
        init();
    }

    public void init() {
        this.players = new LinkedList<>();
        this.theme = ThemeType.ALL;
        this.maxRoomSize = DEFAULT_ROOM_SIZE;
        this.maxQuestions = DEFAULT_MAX_QUESTIONS;
        this.playerPool = Executors.newFixedThreadPool(DEFAULT_ROOM_SIZE);
        this.gameStarted = false;
        this.roundAttempts = new ArrayList<>();
    }

    public void nextRound() {
        this.roundAttempts = new ArrayList<>();
        for (Player player : this.players) {
            player.setCurrentQuestion(null);
            player.setRoundEnd(true);
        }
    }

    public void start() {

        // wait for players
        awaitPlayers();

        // wait start
        awaitGameStart();

        List<Question> questions = createQuestions();
        int currentQuestionIndex = 0;

        // loop
        while (gameStarted) {

            if (currentQuestionIndex >= questions.size()) {
                for (Player player : this.players) {
                    player.setGameStarted(false);
                    gameStarted = false;
                }
                break;
            }
            Question question = questions.get(currentQuestionIndex);

            for (Player player : players) {
                player.sendQuestion(question);
            }

            // Wait for all players to press the button
            while (this.roundAttempts.size() < this.players.size()) {

                // TODO: 31/10/2021 fix needing all players to press the button
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // ask for the answer

            // check answer

            // if wrong ask next



            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nextRound();
            currentQuestionIndex++;

        }
        messagePlayers("Thanks for playing\n");
    }

    private void awaitGameStart() {
        while (!gameStarted) {
            try {
                Thread.sleep(5000);
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

    private List<Question> createQuestions() {
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

    public void setTheme(ThemeType theme) {
        this.theme = theme;
        messagePlayers("This room's theme is now '" + this.theme.getDescription() + "'\n");
    }

    public ThemeType getTheme() {
        return theme;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    private void messagePlayers(String string) {
        try {
            for (Player player : this.players) {
                Prompt prompt = new Prompt(player.getPlayerSocket().getInputStream(), new PrintStream(player.getPlayerSocket().getOutputStream()));
                StringInputScanner stringInputScanner = new StringInputScanner();
                stringInputScanner.setMessage(string);
                prompt.displayMessage(stringInputScanner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        this.gameStarted = true;
        for (Player player : this.players) {
            player.setGameStarted(true);
        }
    }

    @Override
    public void run() {

        start();

    }

    public synchronized void addAttempt(Player player) {
        this.roundAttempts.add(player);
        player.setAnswerTime(true);
        messagePlayers(player.getName() + " has pressed the button\n");
        System.out.println(player.getName() + " pressed the button!");
    }
}
