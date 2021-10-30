package org.academiadecodigo.altcatras65.game;

import java.awt.*;

public enum Colors {
    WHITE(Color.WHITE, "White"),
    BLACK(Color.BLACK, "Black"),
    RED(Color.RED, "Red"),
    GREEN(Color.GREEN, "Green"),
    BLUE(Color.BLUE, "Blue"),
    YELLOW(Color.YELLOW, "Yellow");

    private Color color;
    private String name;

    Colors(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
