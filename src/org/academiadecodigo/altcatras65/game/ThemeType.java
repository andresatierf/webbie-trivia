package org.academiadecodigo.altcatras65.game;

public enum ThemeType {
    ALL("All Themes"),
    GENERAL("General Programming"),
    OOP("Object Oriented Programming"),
    FUNCTIONAL("Functional Programming"),
    INTERNET("Internet & Networking"),
    WEB("Web & Web Architecture");

    private String description;

    ThemeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
