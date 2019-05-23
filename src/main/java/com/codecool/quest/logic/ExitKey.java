package com.codecool.quest.logic;

public class ExitKey extends Item {

    private boolean isInInventory;

    public boolean getIsInInventory() {
        return isInInventory;
    }

    public void setIsInInventory(boolean inInventory) {
        isInInventory = inInventory;
    }

    public ExitKey(Cell cell) {
        super(cell);
    }

    @Override
    public boolean isInInventory() {
        return false;
    }

    @Override
    public void setInInventory(boolean inInventory) {

    }

    @Override
    public String getTileName() {
        return "exitkey";
    }
}
