package org.academiadecodigo.altcatras65.ui;

import org.academiadecodigo.altcatras65.game.Colors;
import org.academiadecodigo.altcatras65.game.question.Question;

public class DisplayMessages {
    public static final int LINE_LENGTH = 80;
    public static final String resetColorASCII = "\u001B[0m";

    public static String displayQuestion(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] answers = question.getOptions();

        //question
        stringBuilder.append(boxedString(question));
        stringBuilder.append(resetColorASCII);

        //answer1 and answer2
        stringBuilder.append(dualBox(answers[0], answers[1], 1, 2, 0));

        //answer3 and answer4
        stringBuilder.append(dualBox(answers[2], answers[3], 3, 4, 1));

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

    public static String boxedString(Question question) {
        String str = question.getDescription();
        String colorASCI = question.getQuestionType().getColor().getAsciiColor();
        StringBuilder stringBuilder = new StringBuilder();
        int strLength = LINE_LENGTH;

        int qSize = (strLength / 2) - (str.length() / 2);

        stringBuilder.append(colorASCI);
        stringBuilder.append("\n" + repeatingString(strLength,"="));
        stringBuilder.append("\n§" + repeatingString(strLength - 2," ") + "§");
        stringBuilder.append("\n§" + repeatingString(qSize - 1," ") + resetColorASCII + str + colorASCI + repeatingString(qSize - evenCheck(str) - 1, " ") + "§");
        stringBuilder.append("\n§" + repeatingString(strLength - 2," ") + "§");
        stringBuilder.append("\n" + repeatingString(strLength,"="));

        return stringBuilder.toString();
    }

    public static String boxedString(String str) {
        String colorASCI = Colors.WHITE.getAsciiColor();
        StringBuilder stringBuilder = new StringBuilder();
        int strLength = LINE_LENGTH;

        int qSize = (strLength / 2) - (str.length() / 2);

        stringBuilder.append(colorASCI);
        stringBuilder.append("\n" + repeatingString(strLength,"="));
        stringBuilder.append("\n§" + repeatingString(strLength - 2," ") + "§");
        stringBuilder.append("\n§" + repeatingString(qSize - 1," ") + resetColorASCII + str + colorASCI + repeatingString(qSize - evenCheck(str) - 1, " ") + "§");
        stringBuilder.append("\n§" + repeatingString(strLength - 2," ") + "§");
        stringBuilder.append("\n" + repeatingString(strLength,"="));

        return stringBuilder.toString();
    }

    public static String dualBox(String str1, String str2, int n1, int n2, int n) {
        String ANSIColor1 = "";
        String ANSIColor2 = "";

        StringBuilder stringBuilder = new StringBuilder();
        int lineLength = LINE_LENGTH;
        int boxLength = (lineLength / 2) - 1;


        int answerSize1 = (boxLength / 2) - (str1.length() / 2);
        int answerSize2 = (boxLength / 2) - (str2.length() / 2);

        if(n == 0) {
            ANSIColor1 = "\033[1;34m";
            ANSIColor2 = "\033[1;31m";
        } else if (n == 1) {
            ANSIColor1 = "\033[1;32m";
            ANSIColor2 = "\033[1;33m";
        }

        stringBuilder.append("\n" + repeatingString(boxLength / 2,"-")
                + ANSIColor1 + n1 + Colors.WHITE.getAsciiColor()
                + repeatingString(boxLength / 2,"-")
                + repeatingString(2," ")
                + repeatingString(boxLength / 2,"-")
                + ANSIColor2 + n2 + Colors.WHITE.getAsciiColor()
                + repeatingString(boxLength / 2,"-"));
        stringBuilder.append("\n*" + repeatingString(boxLength - 2," ") + "*"
                + repeatingString(2," ")
                + '*'
                + repeatingString(boxLength - 2," ")
                + '*'
        );


        stringBuilder.append("\n*" + repeatingString(answerSize1 - 1," ") + str1 + repeatingString(answerSize1 - evenCheck(str1)," ") + "*"
                + repeatingString(2," ")
                + "*" + repeatingString(answerSize2 - 1," ") + str2 + repeatingString(answerSize2 - evenCheck(str2)," ") + "*");


        stringBuilder.append("\n*" + repeatingString(boxLength - 2," ") + "*"
                + repeatingString(2," ")
                + "*"
                + repeatingString(boxLength - 2," ")
                + "*"
        );
        stringBuilder.append("\n"
                + repeatingString(boxLength,"-")
                + repeatingString(2," ")
                + repeatingString(boxLength,"-"));
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

    public static String repeatingString(int length, String character) {
        return new String(new char[length]).replace("\0", character);
    }
}
