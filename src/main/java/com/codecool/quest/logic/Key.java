package com.codecool.quest.logic;

public class Key extends Object {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}