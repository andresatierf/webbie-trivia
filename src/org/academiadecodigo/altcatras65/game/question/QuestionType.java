package org.academiadecodigo.altcatras65.game.question;

public enum QuestionType {
    HIGH(50,-25),
    MEDIUM(30, -15),
    LOW(10, -5);

    private int winValue;
    private int loseValue;

    QuestionType(int winValue, int loseValue) {
        this.winValue = winValue;
        this.loseValue = loseValue;
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

    //endregion

}
