//package com.codecool.quest.logic;
//
//import com.codecool.quest.Main;
//
//public class MovingMob extends Actor {
//    private Cell cell;
//
//    public MovingMob(Cell cell) {
//        super(cell);
//    }
//
//    public void move(int dx, int dy) {
//
//        if (isOnMap(dx, dy)) {
//            Cell nextCell = cell.getNeighbor(dx, dy);
//            if (nextCell.isEmpty()) {
//                move(nextCell);
//            }
//        }
//    }
//
//    private void move(Cell nextCell) {
//        cell.setActor(null);
//        nextCell.setActor(this);
//        cell = nextCell;
//    }
//
//
//    private boolean isOnMap (int dx, int dy){
//        return dx + this.getX() < MapLoader.loadMap(MapLoader.currentMap).getWidth() && dy + this.getY() < MapLoader.loadMap(MapLoader.currentMap).getHeight();
//    }
//
//
//    public String getTileName() {
//        return "movingMob";
//    }
//
//    public Cell getCell() {
//        return cell;
//    }
//
//
//}
//
//
