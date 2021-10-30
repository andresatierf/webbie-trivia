package org.academiadecodigo.altcatras65.game;

import java.awt.*;

public enum Colors {
    WHITE(Color.WHITE, "White", "\u001B[0m"),
    BLACK(Color.BLACK, "Black", "\u001B[30m"),
    RED(Color.RED, "Red", "\u001B[31m"),
    GREEN(Color.GREEN, "Green", "\u001B[32m"),
    BLUE(Color.BLUE, "Blue", "\u001B[34m"),
    YELLOW(Color.YELLOW, "Yellow", "\u001B[33m"),
    CYAN(Color.CYAN, "Cyan", "\u001B[36m");

    private Color color;
    private String name;
    private String asciiColor;

    Colors(Color color, String name, String asciiColor) {
        this.color = color;
        this.name = name;
        this.asciiColor = asciiColor;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getAsciiColor() {
        return asciiColor;
    }
}
