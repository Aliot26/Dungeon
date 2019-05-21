package com.codecool.quest.logic;

public abstract class Object implements Drawable {

    private Cell cell;


    public Object(Cell cell) {
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
        return dx + this.getX() < MapLoader.loadMap().getWidth() && dy + this.getY() < MapLoader.loadMap().getHeight();
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
}
