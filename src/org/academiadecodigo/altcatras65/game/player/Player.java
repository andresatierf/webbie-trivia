package org.academiadecodigo.altcatras65.game.player;

import org.academiadecodigo.altcatras65.game.question.Question;
import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.altcatras65.ui.DisplayMessages;
import org.academiadecodigo.altcatras65.util.Colors;
import org.academiadecodigo.altcatras65.util.ThemeType;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Player implements Runnable {

    public static final int DELAY = 5;
    public static final String CLEAR_SCREEN = new String(new char[100]).replace("\0", "\n");
    public static final String HEADER = CLEAR_SCREEN + DisplayMessages.getStartMessage();

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
    private boolean ready;

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
            header.setMessage(HEADER);

            // ask name
            prompt.displayMessage(header);
            askPlayerName(prompt);

            // ask color
            prompt.displayMessage(header);
            askPlayerColor(prompt);

            // ask admin for theme
            if (this.playerType.equals(PlayerType.ADMIN)) {

                prompt.displayMessage(header);
                askTheme(prompt);

            } else {

                prompt.displayMessage(header);
                presentTheme(prompt);

            }

            awaitGameStart(prompt);

            while (gameStarted) {
                this.roundEnd = false;

                prompt.displayMessage(header);
                awaitQuestion();

                prompt.displayMessage(header);
                presentQuestion(prompt);

                prompt.displayMessage(header);
                presentCountdown(prompt);

                awaitButtonPress(prompt);

                awaitNextRound();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPoints(int points) {
        this.score += Math.max(0, points);
    }

    //region Ask methods

    private void askPlayerName(Prompt prompt) throws IOException {

        StringInputScanner askName = new StringInputScanner();

        askName.setMessage("What's your name?\n");

        this.name = prompt.getUserInput(askName).trim();

        if (this.name.equalsIgnoreCase("tony") || this.name.equalsIgnoreCase("ezequiel")) {

            askName.setMessage("Fuck you " + this.name);
            prompt.displayMessage(askName);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this.name + " joined the room");

    }

    private void askPlayerColor(Prompt prompt) {

        String[] options = null;

        options = Stream.of(Colors.values())
                .map(color -> color.getAsciiColor() + color.getName() + Colors.WHITE.getAsciiColor())
                .toArray(String[]::new);

        MenuInputScanner askColor = new MenuInputScanner(options);

        askColor.setMessage("What color do you want?");

        int colorIndex = prompt.getUserInput(askColor) - 1;

        this.color = Colors.values()[colorIndex];

        System.out.println(this.name + " chose " + this.color.getName());
    }

    private void askTheme(Prompt prompt) {
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

    public int askAnswer(int guesser) {
        try {
            Prompt prompt = new Prompt(this.getPlayerSocket().getInputStream(), new PrintStream(this.getPlayerSocket().getOutputStream()));
            IntegerInputScanner integerInputScanner = new IntegerRangeInputScanner(1, 4);
            String[] first = new String[]{
                    "So Flash... what do you think the answer is?\n",
                    "Mr. Quick Fingers over here, what is your choice?\n",
                    "That was quick! What are you thinking about?\n",
                    "That was fast, let's hope you're slower at other things. What's your answer?\n"
            };
            String[] others = new String[]{
                    "Guess he was wrong... what do you think the answer is?\n",
                    "Oopsies, they were fast but missed. What is your choice?\n",
                    "The fastest is not always right! What are you thinking about?\n",
                    "Slow and steady wins the race! Your guess is?\n"
            };
            integerInputScanner.setMessage(HEADER + this.color.getAsciiColor() + "You" + Colors.WHITE.getAsciiColor() + " are answering..." + DisplayMessages.displayQuestion(this.currentQuestion) + (guesser == 0 ? first : others)[(int) Math.floor(Math.random() * 4)]);
            return prompt.getUserInput(integerInputScanner);

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //endregion

    //region Wait methods

    private void awaitGameStart(Prompt prompt) {
        presentInstructions(prompt);
        this.ready = true;
        while (!this.gameStarted) {
            if (this.playerType == PlayerType.ADMIN) {
                String[] options = new String[]{
                        "Start",
                        "Players",
                        "Wait"
                };
                MenuInputScanner menuInputScanner = new MenuInputScanner(options);
                menuInputScanner.setMessage(HEADER + "What's next?");
                int choice = prompt.getUserInput(menuInputScanner);
                switch (choice) {
                    case 1:
                        boolean ready = this.room.getPlayerList().stream()
                                .map(Player::isReady)
                                .reduce(true, (acc, isReady) -> acc && isReady);
                        if (ready) {
                            this.room.setGameStarted(true);
                            this.gameStarted = true;
                        } else {
                            presentNotReady(prompt);
                        }
                        break;
                    case 2:
                        presentPlayerList(prompt);
                        break;
                    default:
                        break;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void presentNotReady(Prompt prompt) {
        String[] options = new String[]{
                "Go Back"
        };
        MenuInputScanner menuInputScanner = new MenuInputScanner(options);
        menuInputScanner.setMessage(HEADER + "Oops! Some players are not ready");
        prompt.getUserInput(menuInputScanner);
    }

    private boolean isReady() {
        return this.ready;
    }

    private void presentPlayerList(Prompt prompt) {
        String[] options = new String[]{
                "Back"
        };
        MenuInputScanner menuInputScanner = new MenuInputScanner(options);
        menuInputScanner.setMessage(HEADER + "Player List:\n" +
                this.room.getPlayerList().stream()
                        .map(player -> "> " + player.getColor().getAsciiColor() + player.getName() + Colors.WHITE.getAsciiColor() + "\n")
                        .reduce("", (acc, name) -> acc + name));
        prompt.getUserInput(menuInputScanner);

    }

    private void presentInstructions(Prompt prompt) {
        String[] options = new String[]{
                "Continue"
        };
        MenuInputScanner stringInputScanner = new MenuInputScanner(options);
        stringInputScanner.setMessage(HEADER + "\nInstructions:\n\n" +
                "    > Type anything and hit Enter to buzz to be able to answer\n" +
                "    > Answer with the answer number seen on the top of the box");
        prompt.getUserInput(stringInputScanner);
        presentTheme(prompt);
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

    private void awaitButtonPress(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage(HEADER + "\n\u001B[32mGO!\u001B[0m" +
                DisplayMessages.displayQuestion(this.currentQuestion));
        prompt.getUserInput(stringInputScanner);
        this.room.addAttempt(this);
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

    //endregion

    //region Present Methods

    private void presentTheme(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();
        stringInputScanner.setMessage(HEADER + "The theme for this game is '" + this.room.getTheme().getDescription() + "'\n");
        prompt.displayMessage(stringInputScanner);
    }

    private void presentQuestion(Prompt prompt) {
        Set<String> stringOptions = new HashSet<>();
        stringOptions.add("");

        StringInputScanner question = new StringInputScanner();

        String q = DisplayMessages.displayQuestion(this.currentQuestion);

        question.setMessage(HEADER + q);

        prompt.displayMessage(question);
    }

    private void presentCountdown(Prompt prompt) {
        StringInputScanner stringInputScanner = new StringInputScanner();

        for (int i = DELAY; i > 0; i--) {
            stringInputScanner.setMessage(HEADER + "\nYou have \u001B[36m" + i + "\u001B[0m seconds to think..." +
                    DisplayMessages.displayQuestion(this.currentQuestion));
            prompt.displayMessage(stringInputScanner);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //endregion

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
