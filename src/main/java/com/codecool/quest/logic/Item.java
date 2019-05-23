package com.codecool.quest.logic;

public abstract class Item implements Drawable {

    private Cell cell;


    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setObject(this);
    }



    public void move(int dx, int dy) {

        if (isOnMap(dx, dy)) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.isEmpty()) {
                move(nextCell);
            }
        }
    }

    private void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setObject(this);
        cell = nextCell;
    }

    private boolean isOnMap (int dx, int dy){
        return dx + this.getX() < MapLoader.loadMap(MapLoader.currentMap).getWidth() && dy + this.getY() < MapLoader.loadMap(MapLoader.currentMap).getHeight();
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
