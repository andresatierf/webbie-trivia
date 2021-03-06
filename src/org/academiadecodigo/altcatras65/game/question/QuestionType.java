package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.util.Colors;

public enum QuestionType {
    HIGH(72,-36, Colors.RED),
    MEDIUM(60, -24, Colors.YELLOW),
    LOW(48, -12, Colors.WHITE);

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
