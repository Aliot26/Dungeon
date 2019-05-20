package com.codecool.quest.logic;

public abstract class Actor implements Drawable {

    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void checkBounds() {

    }

    public void move(int dx, int dy) {

        int mapWidth = MapLoader.loadMap().getWidth();
        int mapHeight = MapLoader.loadMap().getHeight();
        int actorX = this.getX();
        int actorY = this.getY();

        if(dx + actorX >= mapWidth || dy + actorY >= mapHeight) {
            System.out.println("Kocham warzywa");
        }
        else {
            Cell nextCell = cell.getNeighbor(dx, dy);
            CellType neighborCell = nextCell.getType();
            if(nextCell.getX() >= mapWidth || nextCell.getY() >= mapHeight) {
                System.out.println("Kocham pączki");
            }
            else {
                if(neighborCell.equals(CellType.WALL)) {
                    System.out.println("DUPA");
                }

                else if(nextCell.getActor() == null){
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
            }
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
