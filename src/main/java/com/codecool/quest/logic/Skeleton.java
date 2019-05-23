package com.codecool.quest.logic;

public class Skeleton extends Actor {

    private Cell cell;

    public Skeleton(Cell cell) {
        super(cell);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "skeleton";
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
        nextCell.setActor(this);
        cell = nextCell;
    }

    private boolean isOnMap(int dx, int dy) {
        return dx + this.getX() < MapLoader.loadMap(MapLoader.currentMap).getWidth() && dy + this.getY() < MapLoader.loadMap(MapLoader.currentMap).getHeight();
    }
}
