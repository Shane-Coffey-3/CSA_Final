import javax.swing.*;
import java.awt.*;

public class Arena {

    public static final Tile[][] emptyMap = createEmptyMap(20, 30);


    private Tile[][] map;

    public Arena(int mapCode, int tileSize) {
        Tile.setTileSize(tileSize);
        switch(mapCode) {
            case 1:
                map = createMap1();
                break;
            case 2:
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
                map[i][j] = new AirTile(i, j);
            }
        }
        return map;
    }



    public static Tile[][] createMap1() {
        Tile[][] map = createEmptyMap(20, 30);
        for(int j = 0; j < map[0].length; j++) {
            map[map.length - 1][j] = new GroundTile(j, map.length - 1);
            System.out.println("ground tile added at " + j + ", " + (map.length - 1) + " size " + Tile.tileSize);
        }

        return map;
    }
}
