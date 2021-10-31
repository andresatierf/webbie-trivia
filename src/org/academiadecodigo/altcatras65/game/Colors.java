package org.academiadecodigo.altcatras65.game;

public enum Colors {
    WHITE("White", "\u001B[0m"),
    RED("Red", "\u001B[31m"),
    GREEN("Green", "\u001B[32m"),
    BLUE("Blue", "\u001B[34m"),
    YELLOW("Yellow", "\u001B[33m"),
    CYAN("Cyan", "\u001B[36m");

    private String name;
    private String asciiColor;

    Colors(String name, String asciiColor) {
        this.name = name;
        this.asciiColor = asciiColor;
    }

    public String getName() {
        return name;
    }

    public String getAsciiColor() {
        return asciiColor;
    }
}
