package org.academiadecodigo.altcatras65.game.room;


import org.academiadecodigo.altcatras65.game.Colors;
import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.player.Player;
import org.academiadecodigo.altcatras65.game.player.PlayerFactory;
import org.academiadecodigo.altcatras65.game.player.PlayerType;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.question.QuestionFactory;
import org.academiadecodigo.altcatras65.ui.DisplayMessages;
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

    private List<Player> playerList;
    private int maxQuestions;
    private int maxRoomSize;
    private ThemeType theme;
    private boolean gameStarted;
    private List<Player> roundAttempts;

    public Room() {
        init();
    }

    public void init() {
        this.playerList = new LinkedList<>();
        this.theme = ThemeType.ALL;
        this.maxRoomSize = DEFAULT_ROOM_SIZE;
        this.maxQuestions = DEFAULT_MAX_QUESTIONS;
        this.playerPool = Executors.newFixedThreadPool(DEFAULT_ROOM_SIZE);
        this.gameStarted = false;
        this.roundAttempts = new ArrayList<>();
    }

    public void nextRound() {
        this.roundAttempts = new ArrayList<>();

        this.playerList.forEach(player -> {
            player.setCurrentQuestion(null);
            player.setRoundEnd(true);
        });
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

                this.playerList.forEach(player -> player.setGameStarted(false));
                gameStarted = false;

                break;
            }
            Question question = questions.get(currentQuestionIndex);

            this.playerList.forEach(player -> player.setCurrentQuestion(question));

            // Wait for all players to press the button
            while (this.roundAttempts.size() < this.playerList.size()) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            messagePlayers(Player.HEADER + this.roundAttempts.size() + " players pressed the button" + DisplayMessages.displayQuestion(question));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ask for the answer
            askForAnswers(question);

            messagePlayers(Player.HEADER + "Let's move on to the next question!" + DisplayMessages.displayQuestion(question));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nextRound();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentQuestionIndex++;

        }
        messagePlayers("Thanks for playing\n");
    }

    private void askForAnswers(Question question) {
        int counter = 0;
        for (Player player : this.roundAttempts) {
            messagePlayers(Player.HEADER + player.getColor().getAsciiColor() + player.getName() + Colors.WHITE.getAsciiColor() + " is answering..." + DisplayMessages.displayQuestion(question));
            String playerGuess = question.getOptions()[player.askAnswer(counter) - 1];
            if (playerGuess.equals(question.getAnswer())) {
                player.addPoints(question.getQuestionType().getWinValue() / (Math.min(counter, 4) + 1));
                break;
            }
            player.addPoints(question.getQuestionType().getLoseValue() / (Math.min(counter, 4) + 1));
            messagePlayers(Player.HEADER + player.getName() + " failed. Lets give someone else a try!" + DisplayMessages.displayQuestion(question));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
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
        while (this.playerList.size() < this.maxRoomSize) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Question> createQuestions() {
        return QuestionFactory.createQuestions(theme);
    }

    public synchronized void addPlayer(Socket socket) {
        Player player = PlayerFactory.createPlayer(socket);
        player.setPlayerType(this.playerList.isEmpty() ? PlayerType.ADMIN : PlayerType.NORMAL);
        player.setRoom(this);
        this.playerList.add(player);
        this.playerPool.submit(player);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getMaxRoomSize() {
        return maxRoomSize;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
        messagePlayers(Player.HEADER + "This room's theme is now '" + this.theme.getDescription() + "'\n");
    }

    public ThemeType getTheme() {
        return theme;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    private void messagePlayers(String string) {
        this.playerList.forEach(player -> {
            try {
                Prompt prompt = new Prompt(player.getPlayerSocket().getInputStream(), new PrintStream(player.getPlayerSocket().getOutputStream()));
                StringInputScanner stringInputScanner = new StringInputScanner();
                stringInputScanner.setMessage(string);
                prompt.displayMessage(stringInputScanner);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void startGame() {
        this.gameStarted = true;
        this.playerList.forEach(player -> player.setGameStarted(true));
    }

    @Override
    public void run() {

        start();

    }

    public synchronized void addAttempt(Player player) {
        this.roundAttempts.add(player);
        player.setAnswerTime(true);
        messagePlayers(player.getColor().getAsciiColor() + player.getName() + Colors.WHITE.getAsciiColor() + " has pressed the button\n");
    }
}
