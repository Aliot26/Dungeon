package com.codecool.quest.logic;

public class Player extends Actor {
    private String namePlayer;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
}
