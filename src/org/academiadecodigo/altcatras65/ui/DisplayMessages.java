package org.academiadecodigo.altcatras65.ui;

import org.academiadecodigo.altcatras65.game.question.Question;

public class DisplayMessages {
    public static final int LINE_LENGTH = 80;
    public static final String resetColorASCII = "\u001B[0m";

    public static String displayString(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        String questionStr = question.getDescription();
        String[] answers = question.getOptions();
        //String questionColor =

        int lineLength = LINE_LENGTH;
        int questionLength = (lineLength / 2) - 1;

        //question
        stringBuilder.append(boxedString(questionStr, question));
        stringBuilder.append(resetColorASCII);

        //answer1 and answer2
        stringBuilder.append(dualBox(answers[0], answers[1], 1, 2));

        //answer3 and answer4
        stringBuilder.append(dualBox(answers[2], answers[3], 3, 4));

        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private String questionColor(Question question) {
        String color = null;
        switch (question.getQuestionType().getColor()) {

        }

        return color;
    }

    public static int evenCheck(String str) {
        if (str.length() % 2 != 0)
            return 1;
        else return 0;
    }

    public static String boxedString(String str, Question question) {
        String colorASCI = question.getQuestionType().getColor().getAsciiColor();
        StringBuilder stringBuilder = new StringBuilder();
        int strLength = LINE_LENGTH;

        int qSize = (strLength / 2) - (str.length() / 2);

        stringBuilder.append(colorASCI);
        stringBuilder.append("\n" + new String(new char[strLength]).replace("\0", "="));
        stringBuilder.append("\n§" + new String(new char[strLength - 2]).replace("\0", " ") + "§");
        stringBuilder.append("\n§" + new String(new char[qSize - 1]).replace("\0", " ") + resetColorASCII + str + colorASCI + new String(new char[qSize - evenCheck(str) - 1]).replace("\0", " ") + "§");
        stringBuilder.append("\n§" + new String(new char[strLength - 2]).replace("\0", " ") + "§");
        stringBuilder.append("\n" + new String(new char[strLength]).replace("\0", "="));

        return stringBuilder.toString();
    }

    public static String dualBox(String str1, String str2, int n1, int n2) {
        StringBuilder stringBuilder = new StringBuilder();
        int lineLength = LINE_LENGTH;
        int boxLength = (lineLength / 2) - 1;


        int answerSize1 = (boxLength / 2) - (str1.length() / 2);
        int answerSize2 = (boxLength / 2) - (str2.length() / 2);

        stringBuilder.append("\n" + new String(new char[boxLength / 2]).replace("\0", "-")
                + n1
                + new String(new char[(boxLength / 2)]).replace("\0", "-")
                + new String(new char[2]).replace("\0", " ")
                + new String(new char[boxLength / 2]).replace("\0", "-")
                + n2
                + new String(new char[(boxLength / 2)]).replace("\0", "-"));
        stringBuilder.append("\n*" + new String(new char[boxLength - 2]).replace("\0", " ") + "*"
                + new String(new char[2]).replace("\0", " ")
                + '*'
                + new String(new char[boxLength - 2]).replace("\0", " ")
                + '*'
        );


        stringBuilder.append("\n*" + new String(new char[answerSize1 - 1]).replace("\0", " ") + str1 + new String(new char[answerSize1 - evenCheck(str1)]).replace("\0", " ") + "*"
                + new String(new char[2]).replace("\0", " ")
                + "*" + new String(new char[answerSize2 - 1]).replace("\0", " ") + str2 + new String(new char[answerSize2 - evenCheck(str2)]).replace("\0", " ") + "*");


        stringBuilder.append("\n*" + new String(new char[boxLength - 2]).replace("\0", " ") + "*"
                + new String(new char[2]).replace("\0", " ")
                + "*"
                + new String(new char[boxLength - 2]).replace("\0", " ")
                + "*"
        );
        stringBuilder.append("\n" + new String(new char[boxLength]).replace("\0", "-")
                + new String(new char[2]).replace("\0", " ")
                + new String(new char[boxLength]).replace("\0", "-"));
        return stringBuilder.toString();
    }

    public static String getStartMessage() {
        return "  _| |___________________________________________________________________| |__\n" +
                "(__   ___________________________________________________________________   __)\n" +
                "   | |                                                                   | |\n" +
                "   | |    __    __     _     _     _        _____      _       _         | |\n" +
                "   | |   / / /\\ \\ \\___| |__ | |__ (_) ___  /__   \\_ __(_)_   _(_) __ _   | |\n" +
                "   | |   \\ \\/  \\/ / _ \\ '_ \\| '_ \\| |/ _ \\   / /\\/ '__| \\ \\ / / |/ _` |  | |\n" +
                "   | |    \\  /\\  /  __/ |_) | |_) | |  __/  / /  | |  | |\\ V /| | (_| |  | |\n" +
                "   | |     \\/  \\/ \\___|_.__/|_.__/|_|\\___|  \\/   |_|  |_| \\_/ |_|\\__,_|  | |\n" +
                "   | |                                                                   | |\n" +
                " __| |___________________________________________________________________| |__\n" +
                "(__   ___________________________________________________________________   __)\n" +
                "   | |                                                                   | |\n";
    }
}
