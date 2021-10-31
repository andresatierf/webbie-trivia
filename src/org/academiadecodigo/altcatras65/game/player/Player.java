package org.academiadecodigo.altcatras65.game.player;

import org.academiadecodigo.altcatras65.game.Colors;
import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.altcatras65.ui.DisplayMessages;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;

public class Player implements Runnable {

    public static final int DELAY = 5;
    public static final String CLEAR_SCREEN = new String(new char[100]).replace("\0", "\n");

    private Socket playerSocket;
    private String name;
    private Colors color;
    private PlayerType playerType;
    private Room room;
    private int score;

    private boolean gameStarted;
    private boolean answerTime;

    private Question currentQuestion;
    private boolean roundEnd;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
        this.gameStarted = false;
        this.answerTime = false;
        this.roundEnd = false;
    }

    @Override
    public void run() {
        try {
            Prompt prompt = new Prompt(this.playerSocket.getInputStream(),
                    new PrintStream(this.playerSocket.getOutputStream()));

            StringInputScanner header = new StringInputScanner();
            header.setMessage(CLEAR_SCREEN + DisplayMessages.getStartMessage());

            // ask name
            prompt.displayMessage(header);
            askPlayerName(prompt);

            // ask color
            prompt.displayMessage(header);
            askPlayerColor(prompt);

            // ask admin for theme
            if (this.playerType.equals(PlayerType.ADMIN)) {

                prompt.displayMessage(header);
                chooseTheme(prompt);

            } else {

                prompt.displayMessage(header);
                presentTheme(prompt);

            }

            // Waiting for game to start
            while (!this.gameStarted) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (gameStarted) {
                this.roundEnd = false;

                prompt.displayMessage(header);
                awaitQuestion();

                prompt.displayMessage(header);
                presentQuestion(prompt);

                prompt.displayMessage(header);
                presentCountdown(prompt);

                awaitButtonPress(prompt);

                waitToAnswer();

                awaitNextRound();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void awaitNextRound() {
        while (!roundEnd) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitToAnswer() {
        while (answerTime & !roundEnd) {


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void awaitButtonPress(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage(CLEAR_SCREEN + "GO!\n" +
                DisplayMessages.displayQuestion(this.currentQuestion));
        System.out.print(this.name + " GO!\n");
        prompt.getUserInput(stringInputScanner);
        this.room.addAttempt(this);
    }

    private void presentTheme(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage("The theme for this game is " + this.room.getTheme().getDescription() + "\n");
        prompt.displayMessage(stringInputScanner);
    }

    private void chooseTheme(Prompt prompt) {
        String[] options = null;

        options = Stream.of(ThemeType.values())
                .map(ThemeType::getDescription)
                .toArray(String[]::new);

        MenuInputScanner askTheme = new MenuInputScanner(options);

        askTheme.setMessage("What theme do you want?");

        int themeIndex = prompt.getUserInput(askTheme) - 1;

        this.room.setTheme(ThemeType.values()[themeIndex]);

        System.out.println(this.name + " decided to play " + this.room.getTheme().getDescription());
    }

    private void presentCountdown(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();

        for (int i = DELAY; i > 0; i--) {
            stringInputScanner.setMessage(CLEAR_SCREEN + "You have " + i + " seconds to think...\n" +
                    DisplayMessages.displayQuestion(this.currentQuestion));
            prompt.displayMessage(stringInputScanner);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void presentQuestion(Prompt prompt) {
        Set<String> stringOptions = new HashSet<>();
        stringOptions.add("");

        StringInputScanner question = new StringInputScanner();

        String q = DisplayMessages.displayQuestion(this.currentQuestion);

        question.setMessage(q);
        System.out.println("Question: " + q);


        prompt.displayMessage(question);
    }

    private void awaitQuestion() {

        while (this.currentQuestion == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void askPlayerColor(Prompt prompt) {

        String[] options = null;

        options = Stream.of(Colors.values())
                .map(Colors::getName)
                .toArray(String[]::new);

        MenuInputScanner askColor = new MenuInputScanner(options);

        askColor.setMessage("What color do you want?");

        int colorIndex = prompt.getUserInput(askColor) - 1;

        this.color = Colors.values()[colorIndex];

        System.out.println(this.name + " chose " + this.color.getName());
    }

    private void askPlayerName(Prompt prompt) throws IOException {

        StringInputScanner askName = new StringInputScanner();

        askName.setMessage("What's your name?\n");

        this.name = prompt.getUserInput(askName).trim();

        if (this.name.equalsIgnoreCase("tony")) {

            new PrintStream(this.playerSocket.getOutputStream()).println("Fuck you " + this.name);

        }

        System.out.println(this.name + " joined the room");

    }

    public void sendQuestion(Question question) {
        this.currentQuestion = question;
    }




    //region Getters and Setters

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public void setPlayerSocket(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(boolean answerTime) {
        this.answerTime = answerTime;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public boolean isRoundEnd() {
        return roundEnd;
    }

    public void setRoundEnd(boolean roundEnd) {
        this.roundEnd = roundEnd;
    }

    //endregion

}
