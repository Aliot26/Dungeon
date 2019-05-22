package com.codecool.quest.logic;

public abstract class Item implements Drawable {

    private Cell cell;


    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setObject(this);
    }


    public Cell getCell () {
        return cell;
    }

    public int getX () {
        return cell.getX();
    }

    public int getY () {
        return cell.getY();
    }

    abstract public boolean isInInventory();

    abstract public void setInInventory(boolean inInventory);

}
