package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

import java.util.List;

public class QuestionFactory {

    public static List<Question> createQuestions(ThemeType theme) {
        switch (theme) {
            case BASICS:
                return createBasicQuestions();
            case ALL:
                return createAllQuestions();
            default:
                return createAllQuestions();
        }
    }

    private static List<Question> createAllQuestions() {
        return null;
    }

    private static List<Question> createBasicQuestions() {
        return null;
    }
}
