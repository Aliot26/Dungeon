package com.codecool.quest.logic;

public class Sword extends Item {
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}


