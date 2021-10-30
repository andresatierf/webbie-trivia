package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        return new LinkedList<Question>(Arrays.asList(
                new Question()
        ));
    }

    private static List<Question> createBasicQuestions() {
        return null;
    }
}
