package org.academiadecodigo.altcatras65.game.player;

import org.academiadecodigo.altcatras65.game.Colors;
import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player implements Runnable {

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

                Set<String> stringOptions = new HashSet<>();
                stringOptions.add("");

                StringInputScanner question = new StringSetInputScanner(stringOptions);

                StringBuilder stringBuilder = new StringBuilder();

                int linelength = 80;
                stringBuilder.append("\n" + new String( new char[linelength]).replace("\0", "-"));
                stringBuilder.append("\n*" + new String( new char[linelength-2]).replace("\0", " ") + "*");
                stringBuilder.append("\n* " + this.currentQuestion.getDescription());
                stringBuilder.append("\n*" + new String( new char[linelength-2]).replace("\0", " ") + "*");
                stringBuilder.append("\n*" + new String( new char[linelength]).replace("\0", "-"));


                question.setMessage(stringBuilder.toString());
                System.out.println(stringBuilder);
                prompt.displayMessage(question);



                this.currentQuestion = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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

        List<String> list = Stream.of(Colors.values())
                .map(color -> color.getName())
                .collect(Collectors.toList());

        options = list.toArray(new String[0]);

        MenuInputScanner askColor = new MenuInputScanner(options);

        askColor.setMessage("What color do you want?");

        int colorIndex = prompt.getUserInput(askColor) - 1;

        this.color = Colors.values()[colorIndex];

        System.out.println(this.name + " chose " + this.color.getName());
    }

    private void askPlayerName(Prompt prompt) throws IOException {

        StringInputScanner askName = new StringInputScanner();

        askName.setMessage("What's your name?\n");

        this.name = prompt.getUserInput(askName);

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
