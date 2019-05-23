package com.codecool.quest.logic;


public abstract class Actor implements Drawable {

    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }


    public void move(int dx, int dy) {
        if (isOnMap(dx, dy)) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.isEmpty()) {
                move(nextCell);
                if (nextCell.getType().equals(CellType.EMPTY)) {
                    MapLoader.currentMap = "/map2.txt";
                    GameMap.isLevelFinished = true;

                }

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

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
