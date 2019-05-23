package com.codecool.quest.logic;

public class Skeleton extends Actor {

    private boolean canMove = true;

    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public boolean canMove() {
        return canMove;
    }

    public void canMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean ifNextToPlayer(int playerX, int playerY, int skeletonX, int skeletonY) {
        return (skeletonX + 1 == playerX && skeletonY == playerY) || (skeletonX - 1 == playerX && skeletonY == playerY) || (skeletonY + 1 == playerY && skeletonX == playerX) || (skeletonY - 1 == playerY && skeletonX == playerX);
    private boolean isOnMap(int dx, int dy) {
        return dx + this.getX() < MapLoader.loadMap(MapLoader.currentMap).getWidth() && dy + this.getY() < MapLoader.loadMap(MapLoader.currentMap).getHeight();
    }
}