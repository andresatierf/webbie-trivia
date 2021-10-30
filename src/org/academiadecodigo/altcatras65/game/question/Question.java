package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

import java.util.List;

public class Question {
    private String description;
    private String correctAnswer;
    private List<String> possibleAnswers;
    private QuestionType type;
    private ThemeType theme;

    public String getDescription() {
        return description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public QuestionType getType() {
        return type;
    }
}
