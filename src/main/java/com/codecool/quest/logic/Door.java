package com.codecool.quest.logic;

public class Door extends Object {
    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "door";
    }
}

