import javax.swing.*;
import java.awt.*;

// blah blah blah

public class Arena {

    public static final Tile[][] emptyMap = createEmptyMap(40, 60);

    private Tile[][] map;

    public Arena(int mapCode, int tileSize) {
        Tile.setTileSize(tileSize);
        switch(mapCode) {
            case 1:
                map = createSetMap(Maps.MAP_1_CODE);
                break;
            case 2:
                map = createSetMap(Maps.BATTLE_MAP_CODE);
                break;
            default:
                map = createEmptyMap(20, 30);
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public Tile getTileAt(int[] arr) {
        return map[arr[0]][arr[1]];
    }

    public void drawTiles(Graphics g) {
        for(Tile[] tileArr : map) {
            for(Tile tile : tileArr) {
                tile.draw(g);
            }
        }
    }

    private static Tile[][] createEmptyMap(int y, int x) {
        Tile[][] map = new Tile[y][x];
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                map[i][j] = new Tile(j, i, Tile.AIR_TILE);
            }
        }
        return map;
    }



    public static Tile[][] createSetMap(int[][] mapCode) {
        Tile[][] map = createEmptyMap(mapCode.length, mapCode[0].length);

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(j, i, mapCode[i][j]);
            }
        }

        return map;
    }

    public void printAsNumbers() {
        for(Tile[] arr : map) {
            System.out.print("{");
            for(int i = 0; i < arr.length - 1; i++) {
                System.out.print(arr[i].getTileType() + ",");
            }
            System.out.println(arr[arr.length - 1].getTileType() + "},");
        }
    }
}