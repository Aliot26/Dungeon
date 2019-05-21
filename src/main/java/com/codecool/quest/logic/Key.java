package com.codecool.quest.logic;

public class Key extends Object {

    private boolean isInInventory;

    public boolean getIsInInventory() {
        return isInInventory;
    }

    public void setIsInInventory(boolean inInventory) {
        isInInventory = inInventory;
    }

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}