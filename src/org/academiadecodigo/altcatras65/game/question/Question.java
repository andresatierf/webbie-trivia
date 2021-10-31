package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.util.ThemeType;

public class Question {

    private String description;
    private String answer;
    private String[] options;
    private QuestionType questionType;
    private ThemeType theme;

    public Question(String description, String answer, String[] options, QuestionType questionType, ThemeType theme) {
        this.description = description;
        this.answer = answer;
        this.options = options;
        this.questionType = questionType;
        this.theme = theme;
    }

    //region Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }

    //endregion

}
