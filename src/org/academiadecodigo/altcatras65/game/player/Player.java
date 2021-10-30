package org.academiadecodigo.altcatras65.game.player;

import org.academiadecodigo.altcatras65.game.Colors;
import org.academiadecodigo.altcatras65.game.ThemeType;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;

public class Player implements Runnable {

    public static final int DELAY = 5;

    private Socket playerSocket;
    private String name;
    private Colors color;
    private PlayerType playerType;
    private int score;
    private boolean gameStarted;
    private Room room;

    private Question currentQuestion;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
        this.gameStarted = false;
    }

    @Override
    public void run() {
        try {
            Prompt prompt = new Prompt(this.playerSocket.getInputStream(),
                    new PrintStream(this.playerSocket.getOutputStream()));
            // ask name
            askPlayerName(prompt);

            // ask color
            askPlayerColor(prompt);

            // ask admin for theme
            if (this.playerType.equals(PlayerType.ADMIN)) {

                chooseTheme(prompt);

            } else {

                presentTheme(prompt);

            }

            // loop
            while (!this.gameStarted) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (gameStarted) {

                awaitQuestion();

                presentQuestion(prompt);

                presentCountdown(prompt);

                StringInputScanner stringInputScanner = new StringSetInputScanner(new HashSet<>(Arrays.asList("")));
                stringInputScanner.setMessage("GO!\n");
                System.out.print(this.name + " GO!\n");
                prompt.getUserInput(stringInputScanner);
                this.room.addAttempt(this);


                this.currentQuestion = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void presentTheme(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage("The theme for this game is " + this.room.getTheme().getDescription());
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

        stringInputScanner.setMessage("You have " + DELAY + " seconds to think...\n");
        prompt.displayMessage(stringInputScanner);
        for (int i = DELAY; i > 0; i--) {
            stringInputScanner.setMessage(i + "...\n");
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

        StringInputScanner question = new StringSetInputScanner(stringOptions);

        String q = this.currentQuestion.getDescription() + "\n";
        for (String answer : this.currentQuestion.getAnswers()) {
            q += answer + "\n";
        }

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

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public String getName() {
        return name;
    }

    public Colors getColor() {
        return color;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void sendQuestion(Question q) {
        this.currentQuestion = q;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
}
