package com.codecool.quest.logic;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        CellType neighboorCell = nextCell.getType();

        if(neighboorCell.equals(CellType.WALL)) {
            System.out.println("DUPA");
        }

        else if(nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

//        if(!neighboorCell.equals(CellType.WALL)) {
//            if(nextCell.getActor() == null) {
//                cell.setActor(null);
//                nextCell.setActor(this);
//                cell = nextCell;
//            }
//
//            else {
//
//        }

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
