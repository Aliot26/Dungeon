package com.codecool.quest.logic;

import com.codecool.quest.Main;

public abstract class Actor implements Drawable {

    private Cell cell;
    private int health = 10;
    private boolean buttonOn;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }



    public void move(int dx, int dy) {

        if (isOnMap(dx, dy)) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.isEmpty()) {
                move(nextCell);
            }
            if(nextCell.hasItem()) {
                buttonOn = true;

            }
            else {
                buttonOn = false;
            }
        }
    }

    private void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    private boolean isOnMap (int dx, int dy){
            return dx + this.getX() < MapLoader.loadMap().getWidth() && dy + this.getY() < MapLoader.loadMap().getHeight();
        }

        public int getHealth () {
            return health;
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

    public boolean isItemPicked() {
        return buttonOn;
    }


}
