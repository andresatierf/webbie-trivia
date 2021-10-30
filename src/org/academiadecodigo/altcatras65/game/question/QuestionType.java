package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.Colors;

import java.awt.*;

public enum QuestionType {
    HIGH(50,-25, Colors.RED),
    MEDIUM(30, -15, Colors.YELLOW),
    LOW(10, -5, Colors.WHITE);

    private int winValue;
    private int loseValue;
    private Colors color;

    QuestionType(int winValue, int loseValue, Colors color) {
        this.winValue = winValue;
        this.loseValue = loseValue;
        this.color = color;
    }

    //region Getters and Setters

    public int getWinValue() {
        return winValue;
    }

    public void setWinValue(int winValue) {
        this.winValue = winValue;
    }

    public int getLoseValue() {
        return loseValue;
    }

    public void setLoseValue(int loseValue) {
        this.loseValue = loseValue;
    }

    public Colors getColor() {
        return color;
    }

//endregion

}
