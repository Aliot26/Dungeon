package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    WATER("water"),
    WATER1("water1"),
    TREE("tree"),
    SKULL("skull"),
    GRAVE("grave"),
    DOOR("door"),
    OPEN_DOOR("opened_door"),
    GRASS("grass");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
