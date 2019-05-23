package com.codecool.quest.logic;

public class Cell implements Drawable {
    private CellType type = CellType.EMPTY;
    private Item object;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;


    Cell(GameMap gameMap, int x, int y) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setObject(Item object) {
        this.object = object;
    }


    public Item getObject() {
        return object;
    }



    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEmpty() {
        return this.getActor() == null
                && !(this.getType().equals(CellType.WALL))
                && !(this.getType().equals(CellType.DOOR))
                && !(this.getType().equals(CellType.EXITDOOR))
                && !(this.getType().equals(CellType.TREE))
                && !(this.getType().equals(CellType.GRAVE));
    }


    public boolean hasItem() {return this.getObject() != null;}}



