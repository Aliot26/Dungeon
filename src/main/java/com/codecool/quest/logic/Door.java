package com.codecool.quest.logic;

public class Door extends Item {
    private boolean isOpened;
    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public boolean isInInventory() {
        return false;
    }

    @Override
    public void setInInventory(boolean inInventory) {

    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void openDoor(Key key) {
        if (key.getIsInInventory()) {
            isOpened = true;
            key.setIsInInventory(false);
        }
    }

    @Override
    public String getTileName() {
        return "door";
    }
}

