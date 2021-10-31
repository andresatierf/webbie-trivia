package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionFactory {

    public static List<Question> createQuestions(ThemeType theme) {
        switch (theme) {
            case ALL:
                return createAllQuestions();
            case GENERAL:
                return createBasicQuestions();
            case OOP:
            case FUNCTIONAL:
            case INTERNET:
            case WEB:
            default:
                return createAllQuestions();
        }
    }

    private static List<Question> createAllQuestions() {
        return new LinkedList<Question>(Arrays.asList(
                new Question(
                        "Question 1",
                        "Correct answer",
                        new String[] {
                                "Correct answer",
                                "Wrong answer",
                                "More wrong answer",
                                "Just wrong answer"
                        },
                        QuestionType.HIGH,
                        ThemeType.ALL
                ),
                new Question(
                        "Question 2",
                        "Correct answer",
                        new String[] {
                                "Wrong answer",
                                "Correct answer",
                                "More wrong answer",
                                "Just wrong answer"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.ALL
                ),
                new Question(
                        "Question 3",
                        "Correct answer",
                        new String[] {
                                "Wrong answer",
                                "More wrong answer",
                                "Correct answer",
                                "Just wrong answer"
                        },
                        QuestionType.LOW,
                        ThemeType.ALL
                )
        ));
    }

    private static List<Question> createBasicQuestions() {
        return null;
    }
}
