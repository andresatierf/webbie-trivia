package org.academiadecodigo.altcatras65.game;

public enum ThemeType {
    ALL("All themes"),
    BASICS("Programming basics"),
    OOP("Object oriented programming"),
    FUNCTIONAL("Functional programming"),
    INTERNET("Internet"),
    WEB("Web & web architecture");

    private String description;

    ThemeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
