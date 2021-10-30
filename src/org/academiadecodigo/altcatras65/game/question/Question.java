package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

public class Question {

    private String description;
    private String answer;
    private String[] answers;
    private QuestionType qType;
    private ThemeType theme;

    public Question() {
        this.description = "test question";
        this.answer = "Correct";
        this.answers = new String[]{
                "Correct",
                "Wrong",
                "more Wrong",
                "just wrong"
        };
        this.qType = QuestionType.LOW;
        this.theme = ThemeType.ALL;
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

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public QuestionType getqType() {
        return qType;
    }

    public void setqType(QuestionType qType) {
        this.qType = qType;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }

    //endregion

}
