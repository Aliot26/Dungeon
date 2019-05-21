package com.codecool.quest.logic;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case '-':
                            cell.setType(CellType.GRASS);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.setSkeletons(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case '^':
                            cell.setType(CellType.SKULL);
                            break;
                        case 'X':
                            cell.setType(CellType.GRAVE);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'W':
                            cell.setType(CellType.WATER1);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE);
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
